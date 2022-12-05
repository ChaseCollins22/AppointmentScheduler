package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    private Spinner<Integer> endTimeSeconds;

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

        ;

//        String pattern = "HH:mm:ss";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date1 = simpleDateFormat.format(new Date());
//        System.out.println(date1);

        try {


            int apptID;
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String contact = contactText.getText();
            String type = typeText.getText();
            LocalDate Startdate = startDate.getValue();
            int startHours = startTimeHours.getValue();
            int startMinutes = startTimeMinutes.getValue();
            String start = startHours + " " + startMinutes;
            LocalTime strt = LocalTime.parse(start);
            System.out.println(strt);
            //Time startTime = start;
            int endHours = startTimeHours.getValue();
            int endMinutes = startTimeMinutes.getValue();
            Time endTime;
            LocalDate enddate = endDate.getValue();
            String customerID = customerIDText.getText();
            String userID = userIDText.getText();

        }
        catch (NullPointerException e) {

        }
        System.out.println(startTimeHours.getValue().getClass().getName());
    }

    @FXML
    void onActionCancel(ActionEvent event) {

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
        endTimeSeconds.setValueFactory(endMinutesFactory);
    }
}
