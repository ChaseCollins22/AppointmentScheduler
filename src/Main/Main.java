package Main;

import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCustomerScreen.fxml"));
        stage.setTitle("Appointment Scheduler");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void main(String[] args) throws IOException {
        DBConnection.startConnection();
        Locale.setDefault(Locale.forLanguageTag("en"));
        System.out.println(Locale.getDefault().toLanguageTag());
        launch(args);
        DBConnection.closeConnection();
    }
}
