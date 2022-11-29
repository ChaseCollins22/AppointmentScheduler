package Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {
    public TextField usernameText;
    public TextField passwordText;
    public Button loginButton;
    public Label timeZoneValue;
    public ComboBox comboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> Languages = new ArrayList<String>();
        Languages.add("English");
        Languages.add("French");
        ObservableList<String> languages = FXCollections.observableList(Languages);
        System.out.println(Languages);
        comboBox.setItems(languages);
    }

    public void onActionLogin(ActionEvent actionEvent) {
        System.out.println("Login Successful");
    }

    public void onActionShowLanguages(ActionEvent actionEvent) {

    }
}
