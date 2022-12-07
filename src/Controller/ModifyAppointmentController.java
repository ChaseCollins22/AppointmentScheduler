package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBLogin;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
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
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {
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
    private ComboBox<String> contactIdComboBox;

    @FXML
    private TextField contactText;

    @FXML
    private Label country;

    @FXML
    private Label country1;

    @FXML
    private Label country11;

    @FXML
    private Label country2;

    @FXML
    private Label customerIDLabel;

    @FXML
    private ComboBox<Integer> customerIdComboBox;

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
    private ComboBox<Integer> userIdComboBox;

    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

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
    void onActionAddAppointment(ActionEvent event) throws SQLException, IOException {

        String localDate = LocalDate.now().toString();
        LocalTime locTime = LocalTime.now();
        String time = locTime.truncatedTo(ChronoUnit.SECONDS).toString();
        String date = localDate + " " + time;

        String startHours = startTimeHours.getValue().toString();
        String startMinutes = startTimeMinutes.getValue().toString();
        String startTime = startHours + ":" +startMinutes;

        //get End spinner values into one string: "HH:MM
        String endHours = endTimeHours.getValue().toString();
        String endMinutes = endTimeMinutes.getValue().toString();
        String endTime = endHours + ":" + endMinutes;

        //Format above strings to ensure they're like "HH:MM" and not "H:M"
        String startTimeFormatted = formatTime(startTime);
        String endTimeFormatted = formatTime(endTime);

        String finalTime = startDate.getValue() + " " + startTimeFormatted;
        String finalEndDate = endDate.getValue() + " " + endTimeFormatted;

        //Parse
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(finalTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(finalEndDate, formatter);

        DBAppointments.updateAppointments(
                titleText.getText(), descriptionText.getText(), locationText.getText(), typeText.getText(), startDateTime,
                endDateTime, date, "script", date, "script",
                customerIdComboBox.getValue(), userIdComboBox.getValue(), Integer.parseInt(contactIdComboBox.getValue().substring(0,1)),
                Integer.parseInt(appointmentIDtext.getText()));

        SwitchView("/View/AppointmentScreen.fxml", event);
    }

    public void setAppointmentData(Appointments appointment) {
        appointmentIDtext.setText(String.valueOf(appointment.getApptID()));
        titleText.setText(appointment.getTitle());
        descriptionText.setText(appointment.getDescription());
        locationText.setText(appointment.getLocation());
        typeText.setText(appointment.getType());
        startDate.setValue(appointment.getStartDate());
        startTimeHours.getValueFactory().setValue(appointment.getStartTime().getHour());
        startTimeMinutes.getValueFactory().setValue(appointment.getStartTime().getMinute());
        endDate.setValue(appointment.getEndDate());
        endTimeHours.getValueFactory().setValue(appointment.getEndTime().getHour());
        endTimeMinutes.getValueFactory().setValue(appointment.getEndTime().getMinute());
        customerIdComboBox.setValue(appointment.getCustomerID());
        userIdComboBox.setValue(appointment.getUserID());
        contactIdComboBox.setValue(appointment.getContact());
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SwitchView("/View/AppointmentScreen.fxml", event);
    }

    @FXML
    void onActionTimeClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> startHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> startMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        SpinnerValueFactory<Integer> endHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> endMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        startTimeHours.setValueFactory(startHoursFactory);
        startTimeMinutes.setValueFactory(startMinutesFactory);
        endTimeHours.setValueFactory(endHoursFactory);
        endTimeMinutes.setValueFactory(endMinutesFactory);

        ObservableList contactsList = FXCollections.observableArrayList();
        ObservableList usersList = FXCollections.observableArrayList();
        ObservableList customersList = FXCollections.observableArrayList();

        for (Contacts contact: DBContacts.getAllContacts()) {
            contactsList.add(contact.toString());
        }

        for (Users user : DBLogin.getAllUsers()) {
            usersList.add(user.toString());
        }
        for (Customers customer : DBCustomers.getAllCustomers()) {
            customersList.add(customer.toString());
        }
        contactIdComboBox.setItems(contactsList);
        userIdComboBox.setItems(usersList);
        customerIdComboBox.setItems(customersList);
    }
}
