package ihfms.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        Parent root = loader.load();

        // Set the preferred width and height to half of the screen size
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        Scene scene = new Scene(root, screenWidth / 2, screenHeight / 2);

        primaryStage.setTitle("Integrated Health Finance Management System");
        primaryStage.setScene(scene);

        // Center the stage on the screen
        primaryStage.setX((screenWidth - scene.getWidth()) / 2);
        primaryStage.setY((screenHeight - scene.getHeight()) / 2);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}