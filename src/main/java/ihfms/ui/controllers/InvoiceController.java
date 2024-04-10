package ihfms.ui.controllers;

import ihfms.adapters.BillingAdapter;
import ihfms.adapters.BillingSystemAdapter;
import ihfms.commands.ProcessInvoiceCommand;
import ihfms.external.ExternalBillingSystem;
import ihfms.factories.SimpleMessageFactory;
import ihfms.messages.IMessage;
import ihfms.model.Invoice;
import ihfms.model.Patient;
import ihfms.model.Service;
import ihfms.observers.MessageBoard;
import ihfms.observers.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.scene.control.ListView;
import ihfms.dao.*;
import ihfms.ui.MainApp;
import java.sql.SQLException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class InvoiceController {

    @FXML
    private ComboBox<Patient> comboPatients;
    @FXML
    private TextField inputAmount;
    @FXML
    private TextField serviceIdField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField costField;
    @FXML
    private ListView<Service> servicesListView;

    private ObservableList<Patient> patientData;

    private final MessageBoard messageBoard = new MessageBoard();

    private Invoice currentInvoice;
    private InvoiceDAO invoiceDAO;
    private ServiceDAO serviceDAO;
    private PatientDAO patientDAO;

    public InvoiceController() {
        currentInvoice = new Invoice();
        invoiceDAO = new InvoiceDAO(MainApp.getDatabaseConnection());
        serviceDAO = new ServiceDAO(MainApp.getDatabaseConnection());
        patientDAO = new PatientDAO(MainApp.getDatabaseConnection());
    }


    @FXML
    public void handleAddService() {
        String serviceId = serviceIdField.getText();
        String description = descriptionField.getText();
        double cost = Double.parseDouble(costField.getText());

        Service newService = new Service(serviceId, description, cost);
        currentInvoice.addService(newService);
        servicesListView.getItems().add(newService);

        // Clear the input fields after adding the service
        serviceIdField.clear();
        descriptionField.clear();
        costField.clear();
    }

    public void setPatientData(ObservableList<Patient> patientData) {
        this.patientData = patientData;
        comboPatients.setItems(patientData);
    }

    @FXML
    private void initialize() {
        // Initialize the ComboBox with patient names
        comboPatients.setConverter(new StringConverter<Patient>() {
            @Override
            public String toString(Patient patient) {
                return patient == null ? null : patient.getFirstName() + " " + patient.getLastName();
            }

            @Override
            public Patient fromString(String string) {
                return null; // No conversion from string needed
            }
        });

        // Register an observer to listen for messages
        messageBoard.registerObserver(new Observer() {
            @Override
            public void update(IMessage message) {
                System.out.println("New message received: " + message.getContent());
            }
        });

        // Delay fetching patients for 2 seconds after the view is loaded
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            try {
                ObservableList<Patient> patients = FXCollections.observableArrayList(patientDAO.fetchAllPatients());
                comboPatients.setItems(patients);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions, such as showing an error dialog to the user
            }
        });
        pause.play();
    }

    @FXML
    private void handleCreateInvoice() {
        try {
            Patient selectedPatient = comboPatients.getValue();
            double amount = Double.parseDouble(inputAmount.getText());
            Invoice newInvoice = new Invoice();
            newInvoice.setPatientID(selectedPatient.getPatientID());
            newInvoice.setAmount(amount);
            newInvoice.calculateTotal(); // Calculate the total amount based on the services added

            // Save the invoice and its services to the database
            invoiceDAO.addInvoice(newInvoice); // Save the invoice
            for (Service service : newInvoice.getServicesProvided()) {
                serviceDAO.saveService(service); // Save each service
            }

            // Process the invoice using the command pattern
            ExternalBillingSystem externalBillingSystem = new ExternalBillingSystem();
            BillingSystemAdapter billingAdapter = new BillingAdapter(externalBillingSystem);
            ProcessInvoiceCommand processInvoiceCommand = new ProcessInvoiceCommand(newInvoice, billingAdapter);
            processInvoiceCommand.execute();

            // Send messages
            SimpleMessageFactory messageFactory = new SimpleMessageFactory();
            String messageContent = String.format(
                    "Invoice processed for patient: %s %s. Amount: %.2f",
                    selectedPatient.getFirstName(),
                    selectedPatient.getLastName(),
                    newInvoice.getAmount() // Use the calculated total amount
            );

            IMessage emailMessage = messageFactory.createMessage(
                    "Email",
                    "ihfms@system.com",
                    selectedPatient.getEmail(),
                    "Invoice Processed",
                    messageContent
            );
            emailMessage.send();

            IMessage smsMessage = messageFactory.createMessage(
                    "SMS",
                    "ihfms",
                    selectedPatient.getPhoneNumber(),
                    "",
                    messageContent
            );
            smsMessage.send();

            // Post messages to the message board
            messageBoard.postNewMessage(emailMessage);
            messageBoard.postNewMessage(smsMessage);

            // Clear the form or give feedback to the user
            inputAmount.clear();
            servicesListView.getItems().clear();
            currentInvoice = new Invoice(); // Reset the current invoice

            // Show success message to the user
            // ...

        } catch (NumberFormatException e) {
            // Handle exception for number format errors
            e.printStackTrace();
            // Show an error message to the user
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            // Show an error message to the user
        }
    }
    }
