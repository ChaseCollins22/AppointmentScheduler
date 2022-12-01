package Controller;

import DBAccess.DBCustomers;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerScreenController implements Initializable {
    public TableView customerTableView;
    public TableColumn customerID;
    public TableColumn name;
    public TableColumn address;
    public TableColumn postalCode;
    public TableColumn phoneNumber;
    public TableColumn dateCreated;
    public TableColumn lastUpdate;
    public TableColumn createdBy;
    public TableColumn lastUpdateBy;
    public TableColumn divisionID;

    public void onActionViewAll(Event event) {
    }

    public void onActionViewByMonth(Event event) {
    }

    public void onActionViewByWeek(Event event) {
    }

    public void onActionViewCustomers(Event event) {
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
