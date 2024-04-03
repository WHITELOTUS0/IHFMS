package ihfms.ui.controllers;

import ihfms.model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientController {

    @FXML
    private TableView<Patient> tablePatients;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    // Add more @FXML annotated fields if needed

    @FXML
    private void initialize() {
        // Initialize your controller here
    }

    @FXML
    private void handleAddPatient(ActionEvent event) {
        // Handle adding a new patient
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();
        // Validate input and add patient to the system
    }
}
