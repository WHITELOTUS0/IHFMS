package ihfms.ui.controllers;

import ihfms.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button btnAddPatient;

    @FXML
    private Button btnCreateInvoice;

    // Define the patient data list
    private final ObservableList<Patient> patientData = FXCollections.observableArrayList();

    // Provide a method to add patients to the list (could be called from the PatientController)
    public void addPatient(Patient patient) {
        patientData.add(patient);
    }

    // Getter for patientData
    public ObservableList<Patient> getPatientData() {
        return patientData;
    }

    @FXML
    private void handleAddPatient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PatientView.fxml"));
            Parent patientViewRoot = loader.load();

            // Pass the patient data to the PatientController
            PatientController patientController = loader.getController();
            patientController.setPatientData(patientData); // Pass the shared patientData list

            Stage stage = (Stage) btnAddPatient.getScene().getWindow();
            stage.setScene(new Scene(patientViewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @FXML
    private void handleCreateInvoice(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvoiceView.fxml"));
            Parent invoiceViewRoot = loader.load();

            // Pass the patient data to the InvoiceController
            InvoiceController invoiceController = loader.getController();
            invoiceController.setPatientData(patientData); // Pass the shared patientData list

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(invoiceViewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

}