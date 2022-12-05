package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Countries;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AddCustomerController implements Initializable {

    @FXML
    private Button addCustomerButton;

    @FXML
    private Label addCustomerTitle;

    @FXML
    private Label address;

    @FXML
    private TextField addressText;

    @FXML
    private Button cancelButton;

    @FXML
    private Label country;

    @FXML
    private ComboBox countryComboBox;

    @FXML
    private Label customerIDLabel;

    @FXML
    private TextField customerIdText;

    @FXML
    private Label name;

    @FXML
    private TextField nameText;

    @FXML
    private Label phoneNumber;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private Label postalCode;

    @FXML
    private TextField postalCodeText;

    @FXML
    private Label state;

    @FXML
    private ComboBox<Divisions> stateComboBox;

    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void setColorOnError(Label label, TextField textField) {
        textField.setStyle("-fx-text-box-border: red; -fx-text-box-border-size: 5px;");
        label.setTextFill(Color.web("red"));
    }
    public void resetColors(Label label, TextField textField) {
        textField.setStyle("-fx-text-box-border: lightgray; -fx-text-box-border-size: 1px");
        label.setTextFill(Color.web("black"));
    }

    @FXML
    void onActionAddCustomer(ActionEvent event) throws SQLException, IOException {
        //Get current time and format to: YYYY-MM-DD HH:MM:SS
        String localDate = LocalDate.now().toString();
        LocalTime localTime = LocalTime.now();
        String time = localTime.truncatedTo(ChronoUnit.SECONDS).toString();
        String date = localDate + " " + time;

//        ObservableList customerData = FXCollections.observableArrayList();
        String[] customerData = new String[]{nameText.getText(), addressText.getText(), postalCodeText.getText(),
                                         phoneNumberText.getText(),  String.valueOf(countryComboBox.getValue()),
                                         String.valueOf(stateComboBox.getValue())};
        //System.out.println(state.getId().replace("s", "S"));

        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setX(650);
            alert.setY(250);
            resetColors(name, nameText);
            resetColors(address, addressText);
            resetColors(postalCode, postalCodeText);
            resetColors(phoneNumber, phoneNumberText);
            countryComboBox.setStyle("-fx-text-box-border: lightgray; -fx-text-box-border-size: 1px");
            country.setTextFill(Color.web("black"));
            stateComboBox.setStyle("-fx-text-box-border: lightgray; -fx-text-box-border-size: 1px");
            state.setTextFill(Color.web("black"));
//            for (int i = 0; i < customerData.length; ++i) {
//                if (customerData[i].isEmpty() || customerData[i].equals("null")) {
//                    switch (i) {
//                        case 0:
//                            alert.setContentText("'" + name.getId().replace("n", "N") +"'" + " field is empty");
//                            setColorOnError(name, nameText);
//
//                        case 1:
//                            alert.setContentText("'" + address.getId().replace("a", "A") + "'" + " field is empty");
//                            setColorOnError(address, addressText);
//
//                        case 2:
//                            alert.setContentText("'" + postalCode.getId().replace("p", "P") + "'" + " field is empty");
//                            setColorOnError(postalCode, postalCodeText);
//
//                        case 3:
//                            alert.setContentText("'" + phoneNumber.getId().replace("p", "P") + "'" + " field is empty");
//                            setColorOnError(phoneNumber, phoneNumberText);
//                            System.out.println(phoneNumberText.getText().toString());
//
//                        case 4:
//                           // countryComboBox.setStyle("-fx-text-box-border: red; -fx-text-box-border-size: 5px;");
//                           // country.setTextFill(Color.web("red"));
//                           // alert.setContentText("'" + countryComboBox.getId().substring(0,7).replace("c", "C") + "'" + " field is empty");
//                        case 5:
//                            //stateComboBox.setStyle("-fx-text-box-border: red; -fx-text-box-border-size: 5px;");
//                            //state.setTextFill(Color.web("red"));
//
//                            //alert.setContentText("'" + state.getId().substring(0,6).replace("s", "S") + "'" + " field is empty");
//                    }
//                    alert.setHeaderText("Error: Empty field");
//                    alert.showAndWait();
//                    break;
//                }
//            }
            String phoneNumberFormatted = phoneNumberText.getText(0,3) + "-" +
                    phoneNumberText.getText(3,6) + "-" +
                    phoneNumberText.getText(6,10);

            DBCustomers.addCustomer(nameText.getText(), addressText.getText(), postalCodeText.getText(),
                    phoneNumberFormatted, date, "script", date, "script", stateComboBox.getValue().getDivisionID());
            }
        catch (NullPointerException e) {
            System.out.println("Is NULL");
        }
        SwitchView("/View/CustomerScreen.fxml", event);
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SwitchView("/View/CustomerScreen.fxml", event);
    }

    @FXML
    void onActionShowCountries(ActionEvent event) {
        if (countryComboBox.getValue().equals("U.S")) {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("U.S"));
            stateComboBox.setDisable(false);
        }
        else if (countryComboBox.getValue().equals("UK")) {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("UK"));
            stateComboBox.setDisable(false);
        }
        else {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("Canada"));
            stateComboBox.setDisable(false);
        }


    }

    @FXML
    void onActionShowStates(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList countriesList = FXCollections.observableArrayList();

        for (Countries country: DBCountries.getAllCountries()) {
            countriesList.add(country.toString());
        }
        countryComboBox.setItems(countriesList);
    }
}
