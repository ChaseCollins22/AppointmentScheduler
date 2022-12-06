package Controller;

import DBAccess.*;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.net.URL;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    @FXML
    public TextField contactIDText;
    public ComboBox contactIdComboBox;
    public ComboBox userIdComboBox;
    public ComboBox customerIdComboBox;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Label addCustomerTitle;

    @FXML
    private Label address;

    @FXML
    private TextField appointmentIDtext;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField contactText;

    @FXML
    private Label country;

    @FXML
    private Label country1;

    @FXML
    private Label country2;

    @FXML
    private Label customerIDLabel;

    @FXML
    private TextField customerIDText;

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
    private Label name;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label postalCode;

    @FXML
    private DatePicker startDate;

    @FXML
    private Spinner<Integer> startTimeHours;

    @FXML
    private Spinner<Integer> startTimeMinutes;

    @FXML
    private Label state;

    @FXML
    private Label state1;

    @FXML
    private Label state11;

    @FXML
    private Label state2;

    @FXML
    private TextField titleText;

    @FXML
    private TextField typeText;

    @FXML
    private TextField userIDText;
    private Object DateTimeParseException;

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


    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException, SQLException {
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
            int contactID = Integer.parseInt(contactIdComboBox.getValue().toString());

            //get Start spinner values into one string: "HH:MM"
            String startHours = startTimeHours.getValue().toString();
            String startMinutes = startTimeMinutes.getValue().toString();
            String startTime = startHours + ":" +startMinutes;

            //get End spiner values into one string: "HH:MM
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

            DBAppointments.addAppointment(title,description, location, type, startDateTime, endDateTime,
                    date, "script", date, "script", customerID, userID,
            contactID);

            isValid = true;

        }
        catch (NullPointerException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Not all fields are valid");
            alert.showAndWait();
        }
        if (isValid) {
            SwitchView("/View/AppointmentScreen.fxml", event);
        }
    }

    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SwitchView("/View/AppointmentScreen.fxml", event);
    }

    public void onActionTimeClicked(MouseEvent event) {

    }

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
