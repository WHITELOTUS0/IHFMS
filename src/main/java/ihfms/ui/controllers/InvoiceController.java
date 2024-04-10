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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.scene.control.ListView;

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

    public InvoiceController() {
        currentInvoice = new Invoice();
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
    }

    @FXML
    private void handleCreateInvoice() {
        Patient selectedPatient = comboPatients.getValue();
        double amount = Double.parseDouble(inputAmount.getText());
        Invoice newInvoice = new Invoice();
        newInvoice.setPatientID(selectedPatient.getPatientID()); // This should work correctly now
        newInvoice.setAmount(amount);
        // Calculate the total amount based on the services added
        newInvoice.calculateTotal();
        // Set properties on the newInvoice object
        // Save the invoice to the system (this part needs to be implemented)
        // Clear the form or give feedback to the user
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
    }
    }
