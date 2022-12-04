package Main;

import Controller.AppointmentScreenController;
import Controller.LoginScreenController;
import DBAccess.DBDivisions;
import Database.DBConnection;
import Model.Divisions;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.*;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
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
