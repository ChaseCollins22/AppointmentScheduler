package Controller;

import DBAccess.DBAppointments;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    void onActionAddAppointment(ActionEvent event) throws IOException, SQLException {
        String localDate = LocalDate.now().toString();
        LocalTime locTime = LocalTime.now();
        String time = locTime.truncatedTo(ChronoUnit.SECONDS).toString();
        String date = localDate + " " + time;

        try {
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String type = typeText.getText();
            LocalDate startdate = startDate.getValue();
            LocalDate enddate = endDate.getValue();

            int customerID = Integer.parseInt(customerIDText.getText());
            int userID = Integer.parseInt(userIDText.getText());
            int contactID = Integer.parseInt(contactIDText.getText());

            int startHours = startTimeHours.getValue();
            int startMinutes = startTimeMinutes.getValue();
            int endHours = endTimeHours.getValue();
            int endMinutes = endTimeMinutes.getValue();

            String startTempHours = "";
            String startTempMinutes = "";
            String endTempHours = "";
            String endTempMinutes = "";

            if (startTimeHours.getValue() < 10 && startTimeMinutes.getValue() < 10 ||
                    endTimeHours.getValue() < 10 && endTimeMinutes.getValue() < 10) {
                startTempHours = "0" + startTimeHours.getValue().toString();
                startTempMinutes = "0" + startTimeMinutes.getValue().toString();
                endTempHours = "0" + endTimeHours.getValue().toString();
                endTempMinutes = "0" + endTimeMinutes.getValue().toString();
            }
            else if (startTimeHours.getValue() < 10 || endTimeHours.getValue() < 10) {
                startTempHours = "0" + startTimeHours.getValue().toString();
                endTempHours = "0" + endTimeHours.getValue().toString();
            }
            else if (startTimeMinutes.getValue() < 10 || endTimeMinutes.getValue() < 10) {
                startTempMinutes = "0" + startTimeMinutes.getValue().toString();
                endTempMinutes = "0" + endTimeMinutes.getValue().toString();
            }
            if (startTimeHours.getValue() > 10 && startTimeMinutes.getValue() > 10 ||
                endTimeHours.getValue() > 10 && endTimeMinutes.getValue() > 10) {

                startTempHours = String.valueOf(startHours);
                startTempMinutes = String.valueOf(startMinutes);
                endTempHours = String.valueOf(endHours);
                endTempMinutes = String.valueOf(endMinutes);
            }
            else if (startTimeHours.getValue() > 10 || endTimeHours.getValue() > 10) {
                startTempHours = String.valueOf(startHours);
                endTempHours = String.valueOf(endHours);
            }
            else if (startTimeMinutes.getValue() > 10 || endTimeMinutes.getValue() > 10) {
                startTempMinutes = String.valueOf(startMinutes);
                endTempMinutes = String.valueOf(endMinutes);
            }

            LocalTime localTime = LocalTime.parse(startTempHours + ":" + startTempMinutes + ":00");
            String finalDate = startdate.toString();
            String finalTime = finalDate + " " + startTempHours + ":" + startTempMinutes + ":00";

            LocalTime endTime = LocalTime.parse(endTempHours + ":" + endTempMinutes + ":00");
            String endDate = enddate.toString();
            String finalEndDate = endDate + " " + endTempHours + ":" + endTempMinutes + ":00";

            //Parse
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(finalTime, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(finalEndDate, formatter);

            DBAppointments.addAppointment(title,description, location, type, dateTime, endDateTime,
                    date, "script", date, "script", customerID, userID,
            contactID);

        }
        catch (NullPointerException | SQLException e) {
            e.getStackTrace();

        }

        SwitchView("/View/AppointmentScreen.fxml", event);
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
