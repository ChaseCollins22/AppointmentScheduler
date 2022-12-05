package Controller;

import DBAccess.DBCountries;
import DBAccess.DBDivisions;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {

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
    private ComboBox<Countries> countryComboBox;

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

    @FXML
    void onActionAddCustomer(ActionEvent event) {

    }




    public void setFocusTraversableFalse() {
        nameText.setFocusTraversable(false);
        addressText.setFocusTraversable(false);
        phoneNumberText.setFocusTraversable(false);
        postalCodeText.setFocusTraversable(false);
        countryComboBox.setFocusTraversable(false);
        stateComboBox.setFocusTraversable(false);
        addCustomerButton.setFocusTraversable(false);
        cancelButton.setFocusTraversable(false);
    }

    public void setCustomerData(Customers customer) {
        customerIdText.setText(String.valueOf(customer.getCustomer_ID()));
        nameText.setText(customer.getCustomer_Name());
        addressText.setText(customer.getAddress());
        phoneNumberText.setText(customer.getPhone());
        postalCodeText.setText(customer.getPostal_Code());
        countryComboBox.getSelectionModel().select(DBCountries.getCountryByDivisionID(customer.getDivision_ID()) - 1);
        stateComboBox.setItems(DBDivisions.getDivisionByCountryName(countryComboBox.getSelectionModel().getSelectedItem().toString()));
        stateComboBox.getSelectionModel().select(customer.getDivision_ID());
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SwitchView("/View/CustomerScreen.fxml", event);
    }

    @FXML
    void onActionShowCountries(ActionEvent event) {
        if (countryComboBox.getValue().equals("U.S")) {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("U.S"));
        }
        else if (countryComboBox.getValue().equals("UK")) {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("UK"));
        }
        else {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("Canada"));
        }
    }

    @FXML
    void onActionShowStates(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountries.getAllCountries());
        stateComboBox.setDisable(false);
    }
}
