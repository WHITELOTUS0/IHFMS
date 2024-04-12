package ihfms.ui.controllers;

import ihfms.util.ConfigLoader;

import ihfms.util.AuthenticationService;
import ihfms.util.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField newUsernameField;
    @FXML private PasswordField newPasswordField;
    @FXML private Label messageLabel;

    private AuthenticationService authService;

    public LoginController() {
        DatabaseConnection dbConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/ihfms",
                ConfigLoader.getProperty("DB_USER"),
                ConfigLoader.getProperty("DB_PASSWORD"));
        authService = new AuthenticationService(dbConnection);
    }

    @FXML
    protected void handleLogin(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isAuthenticated = authService.authenticate(username, password);

        if (isAuthenticated) {
            // Load the main view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            // Show login error
            // This should be replaced with a proper error handling mechanism
            System.out.println("Login failed!");
        }
    }

    @FXML
    private void handleCreateAccount() {
        String username = newUsernameField.getText();
        String password = newPasswordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty", AlertType.ERROR);
            return;
        }
        try {
            authService.createUser(username, password, "user"); // Default role is "user"
            showAlert("Success", "Account created successfully", AlertType.INFORMATION);
            newUsernameField.clear();
            newPasswordField.clear();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to create account", AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
