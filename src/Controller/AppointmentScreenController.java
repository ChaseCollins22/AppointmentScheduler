package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Main.Main;
import Model.Appointments;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Customers;
import Controller.CustomerScreenController;

public class AppointmentScreenController implements Initializable {
    public TableColumn<Object, Object> apptID;
    public TableColumn title;
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
    public Tab viewCustomersTab;
    public TableView<Customers> customerTableView;
    public  TableColumn customerId;
    public TableColumn name;
    public TableColumn address;
    public TableColumn postalCode;
    public TableColumn phoneNumber;
    public TableColumn dateCreated;
    public TableColumn lastUpdate;
    public TableColumn createdBy;
    public TableColumn lastUpdateBy;
    public TableColumn divisionID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            appointmentTableView.setItems(DBAppointments.getAllAppointments());

//            customerTableView.setItems(DBCustomers.getAllCustomers());
//        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
//        name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        address.setCellValueFactory(new PropertyValueFactory<>("address"));
//        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
//        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
//        lastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
//        lastUpdateBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
//        divisionID.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

    }

    public void onActionViewCustomers(Event actionEvent) throws IOException {
        customerTableView.setItems(DBCustomers.getAllCustomers());
    }

    public void onActionViewByWeek(Event actionEvent) {
        System.out.println("By Week Clicked");
        appointmentTableView.setItems(DBAppointments.getAppointmentsByWeek());
    }

    public void onActionViewByMonth(Event actionEvent) {
        System.out.println("By Month Clicked");
        appointmentTableView.setItems(DBAppointments.getAppointmentsByMonth());
    }


    public void onActionViewAll(Event actionEvent) throws IOException {
        System.out.println("By All Clicked");
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
    }

    public void onActionAddAppointment(ActionEvent actionEvent) {
    }

    public void onActionModifyAppointment(ActionEvent actionEvent) {
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {
    }


}
