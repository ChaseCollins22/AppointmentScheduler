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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * This class controls the LoginScreen.fxml view.
 */
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
    public Label appointmentSchedulerLabel;
    public VBox loginVbox;
    ResourceBundle rb = ResourceBundle.getBundle("LanguageProperties/Nat", Locale.forLanguageTag("fr"));
    ObservableList<String> setLanguage = FXCollections.observableArrayList();

    /**
     * This function initalizes the controller and sets the labels and combo box to values based on the users system defualt langauge.
     * @param url A URL.
     * @param resourceBundle A resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Locale.getDefault().toLanguageTag().equals("en-US")){
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

    /**
     * This function attempts to log the user in. Log-in attempts successful or not are written to the login_activity.txt file.
     * @param actionEvent Clicking the login button.
     * @throws IOException
     */
    public void onActionLogin(ActionEvent actionEvent) throws IOException {
        ObservableList<Users> usersList = DBLogin.getAllUsers();
        boolean found = false;
        String filename = "C:/Users/LabUser/IdeaProjects/AppointmentScheduler/login_activity.txt", item;
        FileWriter fileWriter = new FileWriter(filename, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Users user : usersList) {
            if (usernameText.getText().equals(user.getUserName()) && passwordText.getText().equals(user.getPassword())) {
                found = true;
                printWriter.println("User: " + user.getUserName() + " successfuly logged in at " + localDateTime.format(formatter));

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/AppointmentScreen.fxml"));
                loader.load();

                AppointmentScreenController ascController = loader.getController();
                ascController.getLoginUser(user);

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (!found && usernameLabel.getText().equals(rb.getString("Username"))) {
            alert.setContentText(rb.getString("Invalid Login"));
            alert.setHeaderText(rb.getString("Error"));
            alert.setTitle(rb.getString("Error"));
            alert.showAndWait();
            printWriter.println("User: " + usernameText.getText() + " gave invalid log-in at " + localDateTime.format(formatter));
        }
        else if (!found) {
            alert.setContentText("Invalid Login");
            alert.setHeaderText("Error");
            alert.setTitle("Error");
            alert.showAndWait();
            printWriter.println("User: " + usernameText.getText() + " gave invalid log-in at " + localDateTime.format(formatter));
        }
        printWriter.close();
        fileWriter.close();
    }

    /**
     * This functions sets the combo box values based on teh selected lanuage.
     * @param actionEvent Selecting a langauge from the combo box.
     * @throws IOException
     */
     public void onActionShowLanguages(ActionEvent actionEvent) throws IOException {
        try {
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
            }
        }
        catch (NullPointerException e) {
            e.getMessage();
        }
     }
}
