package Controller;

import DBAccess.*;
import Interfaces.AlertInterface;
import Model.*;
import Validation.BusinessHours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.net.URL;


import java.util.ResourceBundle;

/**
 * This class controls the "AddAppointmentScreen.fxml" file.
 */
public class AddAppointmentController implements Initializable {
    @FXML
    private ComboBox contactIdComboBox;
    public ComboBox userIdComboBox;
    public ComboBox customerIdComboBox;

    @FXML
    private TextField descriptionText;

    @FXML
    private DatePicker endDate;

    @FXML
    private Spinner<Integer> endTimeHours;

    @FXML
    private Spinner<Integer> endTimeMinutes;

    @FXML
    private TextField locationText;

    @FXML
    private DatePicker startDate;

    @FXML
    private Spinner<Integer> startTimeHours;

    @FXML
    private Spinner<Integer> startTimeMinutes;

    @FXML
    private TextField titleText;

    @FXML
    private TextField typeText;

    /**
     * This function ensures that the values from the spinner are formatted as "HH:MM" when they're parsed as a LocalDateTime.
     * @param tester The value from the spinner
     * @return a value formatted as "HH:mm"
     */
    public String formatTime(String tester) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        boolean formatted = tester.length() == 5 ? true : false;
        while(!formatted) {
            try {
                LocalTime.parse(tester, formatter);
            } catch (DateTimeParseException e) {
                String testHalf = tester.substring(0, e.getErrorIndex());
                for (int i = 0; i < tester.length(); ++i) {
                    if (e.getErrorIndex() == i) {
                        tester = testHalf + "0" + tester.substring(e.getErrorIndex(), tester.length());
                    }
                }
                if (tester.length() == 5) {
                    formatted = true;
                }
            }
        }
        return tester;
    }

    /**
     * This function executes when the user clicks the 'add appointment' button and attempts to input the data given into the database.
     * @param event Clicking the 'add appointment' button
     * @throws IOException
     */
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        String localDate = LocalDate.now().toString();
        LocalTime locTime = LocalTime.now();
        String time = locTime.truncatedTo(ChronoUnit.SECONDS).toString();
        String date = localDate + " " + time;
        boolean isValid = false;

        try {
            //get TextField data
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String type = typeText.getText();
            LocalDate startdate = startDate.getValue();
            LocalDate enddate = endDate.getValue();

            //Parse comboBox values to int
            int customerID = Integer.parseInt(customerIdComboBox.getValue().toString());
            int userID = Integer.parseInt(userIdComboBox.getValue().toString());
            int contactID = Integer.parseInt(contactIdComboBox.getValue().toString().substring(0,1));

            //get Start spinner start values into one string: "HH:mm"
            String startHours = startTimeHours.getValue().toString();
            String startMinutes = startTimeMinutes.getValue().toString();
            String startTime = startHours + ":" +startMinutes;

            //get End spinner end values into one string: "HH:mm"
            String endHours = endTimeHours.getValue().toString();
            String endMinutes = endTimeMinutes.getValue().toString();
            String endTime = endHours + ":" + endMinutes;

            //Format above strings to ensure they're like "HH:MM" and not "H:M"
            String startTimeFormatted = formatTime(startTime);
            String endTimeFormatted = formatTime(endTime);

            String finalTime = startdate + " " + startTimeFormatted;
            String finalEndDate = enddate + " " + endTimeFormatted;

            //Parse
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startDateTime = LocalDateTime.parse(finalTime, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(finalEndDate, formatter);

            if (DBAppointments.isAppointmentOverlap(title, description, location, type, startDateTime, endDateTime,
                                                    customerID, userID, contactID, 0)) {
                throw new Exception("Overlapping appointments");
            }

            if (BusinessHours.isInBusinessHours(startDateTime, endDateTime)) {
                DBAppointments.addAppointment(title,description, location, type, startDateTime, endDateTime,
                        date, "script", date, "script", customerID, userID,
                        contactID);
                isValid = true;
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please schedule an appointment within the business hours of 08:00 and 22:00 EST");

                alert.showAndWait();
            }
        }
        catch (NullPointerException e) {
            AlertInterface alert = (Alert error) -> {
                error.setContentText("Not all fields are valid");
                error.showAndWait();
            };
            alert.createAlert(new Alert(Alert.AlertType.ERROR));
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Overlapping appointments");
            alert.showAndWait();
        }
        if (isValid) {
            SwitchView("/View/AppointmentScreen.fxml", event);
        }
    }

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
     * This function executes when the 'Cancel' button is clicked, and returns the user back to the main appointment screen.
     * @param event 'Cancel' button click
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SwitchView("/View/AppointmentScreen.fxml", event);
    }

    /**
     * This function initializes the controller and sets the values for spinners and table views
     * @param url A URL.
     * @param resourceBundle A resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> startHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> startMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        SpinnerValueFactory<Integer> endHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> endMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        ObservableList contactsList = FXCollections.observableArrayList();
        ObservableList usersList = FXCollections.observableArrayList();
        ObservableList customersList = FXCollections.observableArrayList();

        for (Contacts contact: DBContacts.getAllContacts()) {
            contactsList.add(contact.toString());
        }

        for (Users user :DBLogin.getAllUsers()) {
            usersList.add(user.toString());
        }
        for (Customers customer : DBCustomers.getAllCustomers()) {
            customersList.add(customer.toString());
        }

        customerIdComboBox.setItems(customersList);
        userIdComboBox.setItems(usersList);
        contactIdComboBox.setItems(contactsList);

        startTimeHours.setValueFactory(startHoursFactory);
        startTimeMinutes.setValueFactory(startMinutesFactory);
        endTimeHours.setValueFactory(endHoursFactory);
        endTimeMinutes.setValueFactory(endMinutesFactory);

    }
}
