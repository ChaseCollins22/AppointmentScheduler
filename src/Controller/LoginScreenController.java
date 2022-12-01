package Controller;

import DBAccess.DBLogin;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginScreenController implements Initializable {
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

    public void onActionShowLanguages(ActionEvent actionEvent) {

    }

}
