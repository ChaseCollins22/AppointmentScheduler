package Controller;

import DBAccess.DBCustomers;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerScreenController implements Initializable {
    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<?, ?> createdBy;

    @FXML
    private TableColumn<?, ?> customerID;

    @FXML
    private TableView<Customers> customerTableView;

    @FXML
    private TableColumn<?, ?> dateCreated;

    @FXML
    private TableColumn<?, ?> divisionID;

    @FXML
    private TableColumn<?, ?> lastUpdate;

    @FXML
    private TableColumn<?, ?> lastUpdateBy;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> phoneNumber;

    @FXML
    private TableColumn<?, ?> postalCode;

    @FXML
    private ToggleGroup radioButtonGroup1;

    @FXML
    private RadioButton viewAllButton1;

    @FXML
    private RadioButton viewByWeekButton1;

    @FXML
    private RadioButton viewCustomersButton1;

    @FXML
    private RadioButton viewMonthButton1;

    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddAppointment(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionModifyAppointment(ActionEvent event) {

    }

    @FXML
    void onActionViewAll(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AppointmentScreen.fxml"));
        loader.load();

        AppointmentScreenController ascController = loader.getController();
        ascController.onActionViewAll(event);

        Stage stage = stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionViewByMonth(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AppointmentScreen.fxml"));
        loader.load();

        AppointmentScreenController ascController = loader.getController();
        ascController.onActionViewByMonth(event);

        Stage stage = stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionViewByWeek(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AppointmentScreen.fxml"));
        loader.load();

        AppointmentScreenController ascController = loader.getController();
        ascController.onActionViewByWeek(event);

        Stage stage = stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionViewCustomers(ActionEvent event) throws IOException {
        customerTableView.setItems(DBCustomers.getAllCustomers());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
        createdBy.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
        lastUpdate.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));
        lastUpdateBy.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By"));
        divisionID.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));

    }
}
