package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Main.Main;
import Model.Appointments;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Customers;
import Controller.CustomerScreenController;

public class AppointmentScreenController implements Initializable {

    @FXML
    private TableColumn<?, ?> apptID;

    @FXML
    private TableColumn<?, ?> contact;

    @FXML
    private TableColumn<?, ?> customerID;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private TableColumn<?, ?> endDate;

    @FXML
    private TableColumn<?, ?> endTime;

    @FXML
    private TableColumn<?, ?> location;

    @FXML
    private ToggleGroup radioButtonGroup;

    @FXML
    private TableColumn<?, ?> startDate;

    @FXML
    private TableColumn<?, ?> startTime;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> userID;

    @FXML
    private RadioButton viewAllButton;

    @FXML
    private RadioButton viewByWeekButton;

    @FXML
    private RadioButton viewCustomersButton;

    @FXML
    private RadioButton viewMonthButton;

    @FXML
    private TableView<Appointments> appointmentTableView;

    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        SwitchView("/View/AddAppointmentScreen.fxml", event);
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

        try {
            Appointments selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this " + selectedAppointment.getType() + " appointment?");
            alert.setHeaderText("Delete appointment " + selectedAppointment.getApptID() + "?");
            Optional<ButtonType> alertResult = alert.showAndWait();

            if (alertResult.get().getText().equals("OK")) {
                DBAppointments.deleteAppointment(selectedAppointment);
                if (viewAllButton.isSelected()) {
                    appointmentTableView.setItems(DBAppointments.getAllAppointments());
                } else if (viewByWeekButton.isSelected()) {
                    appointmentTableView.setItems(DBAppointments.getAppointmentsByWeek());
                } else if (viewMonthButton.isSelected()) {
                    appointmentTableView.setItems(DBAppointments.getAppointmentsByMonth());
                }

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Appointment deleted");
                alert2.setHeaderText("Success");
                alert2.showAndWait();
            }
        } catch (NullPointerException e) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Please select an appointment to delete :)");
            alertError.setHeaderText("ERROR: No appointment selected");
            alertError.showAndWait();
        }
    }

    @FXML
    void onActionModifyAppointment(ActionEvent event) throws IOException {
        try {
            Appointments selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyAppointmentScreen.fxml"));
            loader.load();

            ModifyAppointmentController macController = loader.getController();
            macController.setAppointmentData(selectedAppointment);

            Stage stage = stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Please select an appointment to modify :)");
            alertError.setHeaderText("ERROR: No appointment selected");
            alertError.showAndWait();
        }
    }

    @FXML
    void onActionViewAll(ActionEvent event) {
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
        viewAllButton.setSelected(true);
    }

    @FXML
    void onActionViewByMonth(ActionEvent event) {
        appointmentTableView.setItems(DBAppointments.getAppointmentsByMonth());
        viewMonthButton.setSelected(true);

    }

    @FXML
    void onActionViewByWeek(ActionEvent event) {
        appointmentTableView.setItems(DBAppointments.getAppointmentsByWeek());
        viewByWeekButton.setSelected(true);
    }

    @FXML
    void onActionViewCustomers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/CustomerScreen.fxml"));
        loader.load();

        CustomerScreenController cscController = loader.getController();
        cscController.onActionViewCustomers(event);

        Stage stage = stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
        apptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));


    }

    public void onActionGenerateReports(ActionEvent actionEvent) {
    }
}
