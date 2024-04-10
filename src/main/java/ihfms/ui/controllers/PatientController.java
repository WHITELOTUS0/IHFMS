package ihfms.ui.controllers;

import ihfms.dao.PatientDAO;
import ihfms.model.Patient;
import ihfms.ui.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.util.List;

public class PatientController {

    @FXML
    private TableView<Patient> tablePatients;
    @FXML
    private TableColumn<Patient, String> columnFirstName;
    @FXML
    private TableColumn<Patient, String> columnLastName;
    @FXML
    private TableColumn<Patient, String> columnPhoneNumber;
    @FXML
    private TableColumn<Patient, String> columnEmail;

    @FXML
    private TextField inputFirstName;
    @FXML
    private TextField inputLastName;
    @FXML
    private TextField inputDateOfBirth; // Assuming you have a field for date of birth
    @FXML
    private TextField inputGender; // Assuming you have a field for gender
    @FXML
    private TextField inputContactInfo; // Assuming you have a field for contact info
    @FXML
    private TextField inputPhoneNumber;
    @FXML
    private TextField inputEmail;

    private ObservableList<Patient> patientData = FXCollections.observableArrayList();
    private PatientDAO patientDAO;

    public PatientController() {
        patientDAO = new PatientDAO(MainApp.getDatabaseConnection());
    }

    public void setPatientData(ObservableList<Patient> patientData) {
        this.patientData = patientData;
        tablePatients.setItems(patientData);
    }

    @FXML
    private void initialize() {
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        columnPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        tablePatients.setItems(patientData);
    }

    @FXML
    private void handleAddPatient(ActionEvent event) {
        try {
            // Validate input fields (example for first name)
            String firstName = inputFirstName.getText();
            if (firstName.isEmpty()) {
                // Show an error message to the user
                showErrorMessage("First name cannot be empty.");
                return;
            }

            Patient newPatient = new Patient();
            newPatient.setFirstName(firstName);
            newPatient.setLastName(inputLastName.getText());
            newPatient.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(inputDateOfBirth.getText()));
            newPatient.setGender(inputGender.getText());
            newPatient.setContactInfo(inputContactInfo.getText());
            newPatient.setPhoneNumber(inputPhoneNumber.getText());
            newPatient.setEmail(inputEmail.getText());

            patientDAO.addPatient(newPatient);
            patientData.add(newPatient);

            // Clear the input fields after adding the patient
            clearInputFields();
        } catch (ParseException e) {
            // Log the exception and show an error message to the user
            logException(e);
            showErrorMessage("Invalid date format. Please use yyyy-MM-dd.");
        } catch (SQLException e) {
            // Log the exception and show an error message to the user
            logException(e);
            showErrorMessage("An error occurred while adding the patient. Please try again.");
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            logException(e);
            showErrorMessage("An unexpected error occurred. Please try again.");
        }
    }

    private void logException(Exception e) {
        // Implement logging here
        System.err.println("Exception occurred: " + e.getMessage());
        e.printStackTrace();
    }

    private void showErrorMessage(String message) {
        // Implement showing an error message to the user
        System.err.println(message);
        // Alternatively, use a dialog box or update the UI to display the message
    }

    private void clearInputFields() {
        inputFirstName.clear();
        inputLastName.clear();
        inputDateOfBirth.clear();
        inputGender.clear();
        inputContactInfo.clear();
        inputPhoneNumber.clear();
        inputEmail.clear();
    }


    @FXML
    private void handleNavigateToCreateInvoice(ActionEvent event) {
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

    @FXML
    private void handleGenerateReport() {
        try {
            List<Patient> patients = patientDAO.fetchAllPatients();
            generateCSVReport(patients);
            //generatePDFReport(patients);
            // Show success message or notification to the user
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exceptions and show error message to the user
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IO exceptions and show error message to the user
        }
    }

    private void generateCSVReport(List<Patient> patients) throws IOException {
        FileWriter csvWriter = new FileWriter("patients_report.csv");
        // Write CSV header
        csvWriter.append("Patient ID,First Name,Last Name,Date of Birth,Gender,Contact Info,Phone Number,Email\n");
        // Write patient data
        for (Patient patient : patients) {
            String dateOfBirthStr = (patient.getDateOfBirth() != null) ? patient.getDateOfBirth().toString() : "N/A";
            csvWriter.append(String.join(",",
                            String.valueOf(patient.getPatientID()),
                            patient.getFirstName(),
                            patient.getLastName(),
                            dateOfBirthStr,
                            patient.getGender(),
                            patient.getContactInfo(),
                            patient.getPhoneNumber(),
                            patient.getEmail()))
                    .append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}