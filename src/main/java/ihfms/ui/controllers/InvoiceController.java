package ihfms.ui.controllers;

import ihfms.model.Invoice;
import ihfms.model.Patient;
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
    }

    @FXML
    private void handleCreateInvoice() {
        Patient selectedPatient = comboPatients.getValue();
        double amount = Double.parseDouble(inputAmount.getText());
        Invoice newInvoice = new Invoice();
        // Set properties on the newInvoice object
        // Save the invoice to the system
        // Clear the form or give feedback to the user
    }
}