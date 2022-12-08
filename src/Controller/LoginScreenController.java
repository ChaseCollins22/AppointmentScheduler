package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import DBAccess.DBLogin;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class LoginScreenController implements Initializable {
    public TextField usernameText;
    public TextField passwordText;
    public Button loginButton;
    public Label timeZoneValue;
    public ComboBox comboBox;
    public Label languageLabel;
    public Label timeZoneLabel;
    public Label passwordLabel;
    public Label usernameLabel;
    public VBox textFieldsVBox;
    public HBox hBoxUserPass;
    public VBox userPassVBox;
    public Label appointmentSchedulerLabel;
    ResourceBundle rb = ResourceBundle.getBundle("LanguageProperties/Nat", Locale.forLanguageTag("fr"));
    ObservableList<String> setLanguage = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Locale.getDefault().toString().equals("en")){

            setLanguage.add("English");
            setLanguage.add("French");
            comboBox.setItems(setLanguage);

            appointmentSchedulerLabel.setText("Appointment Scheduler");
            usernameLabel.setText("Username");
            passwordLabel.setText("Password");
            loginButton.setText("Login");
            timeZoneLabel.setText("Time Zone:");
            languageLabel.setText("Language");
        }
        else if (Locale.getDefault().toString().equals("fr")) {

            comboBox.setPromptText(rb.getString("French"));
            setLanguage.add(rb.getString("English"));
            setLanguage.add(rb.getString("French"));
            comboBox.setItems(setLanguage);

            appointmentSchedulerLabel.setText(rb.getString("Appointment Scheduler"));
            usernameLabel.setText(rb.getString("Username"));
            passwordLabel.setText(rb.getString("Password"));
            loginButton.setText(rb.getString("Login"));
            timeZoneLabel.setText(rb.getString("Time Zone:"));
            languageLabel.setText(rb.getString("Language"));
        }


        TimeZone timeZone = TimeZone.getDefault();
        timeZoneValue.setText(timeZone.getID());
    }

    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));

        stage.show();
    }

    public void onActionLogin(ActionEvent actionEvent) throws IOException {
        ObservableList<Users> usersList = DBLogin.getAllUsers();

        System.out.println();
        boolean found = false;
        for (Users user : usersList) {
            if (usernameText.getText().equals(user.getUserName()) && passwordText.getText().equals(user.getPassword())) {
                found = true;
                SwitchView("/View/AppointmentScreen.fxml", actionEvent);
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (!found && usernameLabel.getText().equals(rb.getString("Username"))) {
            alert.setContentText(rb.getString("Invalid Login"));
            alert.setHeaderText(rb.getString("Error"));
            alert.setTitle(rb.getString("Error"));
            alert.showAndWait();
        }
        else if (!found) {
            alert.setContentText("Invalid Login");
            alert.setHeaderText("Error");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }

     public void onActionShowLanguages(ActionEvent actionEvent) throws IOException {
        try {
            System.out.println(comboBox.getValue());
            if (comboBox.getSelectionModel().getSelectedItem().equals(rb.getString("English")) || comboBox.getSelectionModel().getSelectedItem().equals("English")) {
                usernameLabel.setText("Username");
                appointmentSchedulerLabel.setText("Appointment Scheduler");
                passwordLabel.setText("Password");
                loginButton.setText("Login");
                timeZoneLabel.setText("Time Zone:");
                languageLabel.setText("Language");
                ObservableList english = FXCollections.observableArrayList();
                english.add("English");
                english.add("French");
                comboBox.itemsProperty().setValue(english);
                comboBox.setValue("English");
            }

            if (comboBox.getSelectionModel().getSelectedItem().equals("French") || comboBox.getSelectionModel().getSelectedItem().equals(rb.getString("French"))) {
                usernameLabel.setText(rb.getString("Username"));
                appointmentSchedulerLabel.setText(rb.getString("Appointment Scheduler"));
                passwordLabel.setText(rb.getString("Password"));
                loginButton.setText(rb.getString("Login"));
                timeZoneLabel.setText(rb.getString("Time Zone:"));
                languageLabel.setText(rb.getString("Language"));

                ObservableList french = FXCollections.observableArrayList();
                french.add(rb.getString("English"));
                french.add(rb.getString("French"));
                comboBox.itemsProperty().setValue(french);
                comboBox.setValue(rb.getString("French"));

                hBoxUserPass.setSpacing(0);
                usernameLabel.setMinWidth(100);
            }
        }
        catch (NullPointerException e) {
            e.getMessage();
        }
     }
}
