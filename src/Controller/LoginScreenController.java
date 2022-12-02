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
    ResourceBundle rb = ResourceBundle.getBundle("LanguageProperties/Nat", Locale.getDefault());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> Languages = new ArrayList<String>();
        Languages.add("English");
        Languages.add("French");
        ObservableList<String> languages = FXCollections.observableList(Languages);
        comboBox.setItems(languages);
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


        for (Users user : usersList) {
            if (usernameText.getText().equals(user.getUserName()) && passwordText.getText().equals(user.getPassword())) {
                SwitchView("/View/AppointmentScreen.fxml", actionEvent);
            }
        }


    }

    public void onActionShowLanguages(ActionEvent actionEvent) throws IOException {
        String selectedLanguage = (String) comboBox.getSelectionModel().getSelectedItem();
        if (selectedLanguage.equals("French")) {
            usernameLabel.setText(rb.getString("Username"));
            passwordLabel.setText(rb.getString("Password"));
            loginButton.setText(rb.getString("Login"));
            timeZoneLabel.setText(rb.getString("TimeZone"));
            languageLabel.setText(rb.getString("Language"));
            timeZoneValue.setText(rb.getString("America"));




            hBoxUserPass.setSpacing(0);
            usernameLabel.setMinWidth(100);


        }
    }

}
