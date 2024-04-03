package ihfms.ui.controllers;

import ihfms.model.Patient;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientController {

    @FXML
    private TableView<Patient> tablePatients;
    @FXML
    private TableColumn<Patient, String> columnPatientID;
    @FXML
    private TableColumn<Patient, String> columnFirstName;
    @FXML
    private TableColumn<Patient, String> columnLastName;
    // Add other columns as needed

    @FXML
    private TextField inputPatientID;
    @FXML
    private TextField inputFirstName;
    @FXML
    private TextField inputLastName;
    // Add other input fields as needed

    private ObservableList<Patient> patientData;

    public void setPatientData(ObservableList<Patient> patientData) {
        this.patientData = patientData;
        tablePatients.setItems(patientData);
    }

    @FXML
    private void initialize() {
        columnPatientID.setCellValueFactory(cellData -> cellData.getValue().patientIDProperty());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Initialize other columns
    }

    @FXML
    private void handleAddPatient() {
        Patient newPatient = new Patient();
        newPatient.setPatientID(inputPatientID.getText());
        newPatient.setFirstName(inputFirstName.getText());
        newPatient.setLastName(inputLastName.getText());
        // Set other properties from input fields

        patientData.add(newPatient);

        inputPatientID.clear();
        inputFirstName.clear();
        inputLastName.clear();
        // Clear other input fields
    }

    @FXML
    private void handleNavigateToCreateInvoice() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvoiceView.fxml"));
            Parent invoiceViewRoot = loader.load();

            InvoiceController invoiceController = loader.getController();
            invoiceController.setPatientData(patientData); // Pass the patient data to the InvoiceController

            Stage stage = (Stage) tablePatients.getScene().getWindow(); // Get the current stage
            stage.setScene(new Scene(invoiceViewRoot)); // Set the scene to InvoiceView
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}