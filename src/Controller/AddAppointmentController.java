package Controller;

import Model.Appointments;
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

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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


    @FXML
    void onActionAddAppointment(ActionEvent event) {

        try {
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String contact = contactText.getText();
            String type = typeText.getText();
            LocalDate Startdate = startDate.getValue();
            LocalDate enddate = endDate.getValue();
            String customerID = customerIDText.getText();
            String userID = userIDText.getText();

            int startHours = startTimeHours.getValue();
            int startMinutes = startTimeMinutes.getValue();
            int endHours = endTimeHours.getValue();
            int endMinutes = endTimeMinutes.getValue();

            String tempHours = "";
            String tempMinutes = "";
            if (startTimeHours.getValue() < 10 && startTimeMinutes.getValue() < 10) {
                tempHours = "0" + startTimeHours.getValue().toString();
                tempMinutes = "0" + startTimeMinutes.getValue().toString();
            }
            else if (startTimeHours.getValue() < 10) {
                tempHours = "0" + startTimeHours.getValue().toString();
            }
            else if (startTimeMinutes.getValue() < 10) {
                tempMinutes = "0" + startTimeMinutes.getValue().toString();
            }
            if (startTimeHours.getValue() > 10 && startTimeMinutes.getValue() > 10) {
                tempHours = String.valueOf(startHours);
                tempMinutes = String.valueOf(startMinutes);
            }
            else if (startTimeHours.getValue() > 10) {
                tempHours = String.valueOf(startHours);
            }
            else if (startTimeMinutes.getValue() > 10) {
                tempMinutes = String.valueOf(startMinutes);
            }

            LocalTime localTime = LocalTime.parse(tempHours + ":" + tempMinutes + ":00");

            Appointments
        }
        catch (NullPointerException e) {
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
        SpinnerValueFactory<Integer> startHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24);
        SpinnerValueFactory<Integer> startMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        SpinnerValueFactory<Integer> endHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24);
        SpinnerValueFactory<Integer> endMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        startTimeHours.setValueFactory(startHoursFactory);
        startTimeMinutes.setValueFactory(startMinutesFactory);
        endTimeHours.setValueFactory(endHoursFactory);
        endTimeMinutes.setValueFactory(endMinutesFactory);
    }
}
