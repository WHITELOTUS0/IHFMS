package ihfms.ui.controllers;

import ihfms.adapters.BillingAdapter;
import ihfms.adapters.BillingSystemAdapter;
import ihfms.commands.ProcessInvoiceCommand;
import ihfms.external.ExternalBillingSystem;
import ihfms.factories.SimpleMessageFactory;
import ihfms.messages.IMessage;
import ihfms.model.Invoice;
import ihfms.model.Patient;
import ihfms.observers.MessageBoard;
import ihfms.observers.Observer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class InvoiceController {

    @FXML
    private ComboBox<Patient> comboPatients;
    @FXML
    private TextField inputAmount;

    private ObservableList<Patient> patientData;
    private final MessageBoard messageBoard = new MessageBoard();

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
        // Set properties on the newInvoice object
        // Save the invoice to the system
        // Clear the form or give feedback to the user
        // Process the invoice using the command pattern
        ExternalBillingSystem externalBillingSystem = new ExternalBillingSystem();
        BillingSystemAdapter billingAdapter = new BillingAdapter(externalBillingSystem);
        ProcessInvoiceCommand processInvoiceCommand = new ProcessInvoiceCommand(newInvoice, billingAdapter);
        processInvoiceCommand.execute();

        // Send messages
        SimpleMessageFactory messageFactory = new SimpleMessageFactory();
        IMessage emailMessage = messageFactory.createMessage(
                "Email",
                "ihfms@system.com",
                selectedPatient.getEmail(),
                "Invoice Processed",
                "Invoice processed for patient: " + selectedPatient.getFirstName() + " " + selectedPatient.getLastName()
        );
        emailMessage.send();

        IMessage smsMessage = messageFactory.createMessage(
                "SMS",
                "ihfms",
                selectedPatient.getPhoneNumber(),
                "",
                "Invoice processed for patient: " + selectedPatient.getFirstName() + " " + selectedPatient.getLastName()
        );
        smsMessage.send();

        // Post messages to the message board
        messageBoard.postNewMessage(emailMessage);
        messageBoard.postNewMessage(smsMessage);
    }
    }
