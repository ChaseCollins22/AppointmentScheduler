package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class controls the ModifyCustomerScreen.fxml view.
 */
public class ModifyCustomerController implements Initializable {

    @FXML
    private Button addCustomerButton;

    @FXML
    private TextField addressText;

    @FXML
    private Button cancelButton;


    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private TextField customerIdText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField postalCodeText;

    @FXML
    private ComboBox<Divisions> stateComboBox;

    /**
     * This function switches the users view.
     * @param viewName The path to the desired view.
     * @param event Button click.
     * @throws IOException
     */
    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This function updates the existing customer data.
     * @param actionEvent Clicking the update customer button.
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    public void onActionUpdateCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        DBCustomers.updateCustomer(nameText.getText(), addressText.getText(), postalCodeText.getText(),
                                   phoneNumberText.getText(), stateComboBox.getValue().getDivisionID(), Integer.parseInt(customerIdText.getText()));
        SwitchView("/View/CustomerScreen.fxml", actionEvent);
    }

    /**
     * This function unselects the focus on any textfield.
     */
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

    /**
     * This function sets the existing data from the selected customer object into the available data-entry points.
     * @param customer The selected customer object from the CustomerScreen.fxml view.
     */
    public void setCustomerData(Customers customer) {
        ObservableList<Divisions> divisionsList = DBDivisions.getAllDivisions();
        customerIdText.setText(String.valueOf(customer.getCustomer_ID()));
        nameText.setText(customer.getCustomer_Name());
        addressText.setText(customer.getAddress());
        phoneNumberText.setText(customer.getPhone());
        postalCodeText.setText(customer.getPostal_Code());
        countryComboBox.getSelectionModel().select(DBCountries.getCountryByDivisionID(customer.getDivision_ID()) - 1);
        stateComboBox.setItems(DBDivisions.getDivisionByCountryName(countryComboBox.getSelectionModel().getSelectedItem().toString()));
        for (Divisions division : divisionsList) { ;
            if (division.getDivisionID() == customer.getDivision_ID()) {
                stateComboBox.getSelectionModel().select(division);
            }
        }
    }

    /**
     * Switches the user back to CustomerScreen.fxml.
     * @param event Clicking the 'Cancel' button.
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SwitchView("/View/CustomerScreen.fxml", event);
    }

    /**
     * This function sets the values for the stateComboBox based on the selected countryComboBox value.
     * @param event Selecting an available country from the combo box.
     */
    @FXML
    void onActionShowCountries(ActionEvent event) {
        if (countryComboBox.getSelectionModel().getSelectedItem().getCountryName().equals("U.S")) {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("U.S"));
            stateComboBox.getSelectionModel().selectFirst();
        }
        else if (countryComboBox.getSelectionModel().getSelectedItem().getCountryName().equals("UK")) {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("UK"));
            stateComboBox.getSelectionModel().selectFirst();
        }
        else {
            stateComboBox.setItems(DBDivisions.getDivisionByCountryName("Canada"));
            stateComboBox.getSelectionModel().selectFirst();
        }
    }

    /**
     * This function initializes the controller and sets the countryComboBox to the existing countries in the database.
     * @param url A URL.
     * @param resourceBundle A resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountries.getAllCountries());
        stateComboBox.setDisable(false);
    }

}
