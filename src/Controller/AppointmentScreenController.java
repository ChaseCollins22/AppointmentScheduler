package Controller;

import DBAccess.DBAppointments;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentScreenController implements Initializable {
    public TableColumn<Object, Object> apptID;
    public TableColumn<Object, Object> title;
    public TableColumn description;
    public TableColumn location;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn startDate;
    public TableColumn startTime;
    public TableColumn endTime;
    public TableColumn endDate;
    public TableColumn customerID;
    public TableColumn userID;
    public TableView<Appointments> appointmentTableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
        apptID.setCellValueFactory(new PropertyValueFactory<>("id"));
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

    public void onActionViewCustomers(ActionEvent actionEvent) {
    }

    public void onActionViewByWeek(ActionEvent actionEvent) {
    }

    public void onActionViewByMonth(ActionEvent actionEvent) {
    }

    public void onActionViewAll(ActionEvent actionEvent) {
    }

    public void onActionAddAppointment(ActionEvent actionEvent) {
    }

    public void onActionModifyAppointment(ActionEvent actionEvent) {
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {
    }


}
