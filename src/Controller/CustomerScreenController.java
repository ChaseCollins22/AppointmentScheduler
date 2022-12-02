package Controller;

import DBAccess.DBCustomers;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void onActionViewAll(ActionEvent event) {

    }

    @FXML
    void onActionViewByMonth(ActionEvent event) {

    }

    @FXML
    void onActionViewByWeek(ActionEvent event) {

    }

    @FXML
    void onActionViewCustomers(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        lastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdateBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        divisionID.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

    }
}
