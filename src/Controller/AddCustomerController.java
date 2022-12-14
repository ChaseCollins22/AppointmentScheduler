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

/**
 * This class controls the 'AddCustomerScreen' view.
 */
public class AddCustomerController implements Initializable {

    @FXML
    private TextField addressText;

    @FXML
    private ComboBox countryComboBox;

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
     * This function attempts to add a customer to the database.
     * @param event Clicking the 'Add Customer' button.
     * @throws SQLException
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws SQLException {
        //Get current time and format to: YYYY-MM-DD HH:MM:SS
        String localDate = LocalDate.now().toString();
        LocalTime localTime = LocalTime.now();
        String time = localTime.truncatedTo(ChronoUnit.SECONDS).toString();
        String date = localDate + " " + time;

        try {
            DBCustomers.addCustomer(nameText.getText(), addressText.getText(), postalCodeText.getText(),
                    phoneNumberText.getText(), date, "script", date, "script",
                    stateComboBox.getValue().getDivisionID());
            SwitchView("/View/CustomerScreen.fxml", event);
        }
        catch (NullPointerException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Not all fields are valid");
            alert.showAndWait();
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
     * This function sets the values in the stateComboBox based on the value given in the countryComboBox.
     * @param event Selecting one of the three countries in the countryComboBox.
     */
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

    /**
     * This function initializes the controller and sets the countyComboBox to the available countries in the database.
     * @param url A URL.
     * @param resourceBundle A resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList countriesList = FXCollections.observableArrayList();

        for (Countries country: DBCountries.getAllCountries()) {
            countriesList.add(country.toString());
        }
        countryComboBox.setItems(countriesList);
    }
}
