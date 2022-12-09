package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class CustomerReportsScreenController implements Initializable {
    @FXML
    private TableColumn<?, ?> apptID;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label comboBoxLabel;

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
    private Label reportsLabel;

    @FXML
    private TableColumn<?, ?> startDate;

    @FXML
    private TableColumn<?, ?> startTime;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private RadioButton totalByMonth;

    @FXML
    private RadioButton totalByPostalCode;

    @FXML
    private RadioButton totalByType;

    @FXML
    private Label totalLabel;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private RadioButton viewByContact;

    ObservableList<String> monthsList = FXCollections.observableArrayList();
    Hashtable<String, Integer> Months = new Hashtable<String, Integer>();

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionShowComboBoxItems(ActionEvent event) {
//        try {
//            if (comboBoxLabel.getText().equals("Contact")) {
//                int contactID = Integer.parseInt(comboBox.getValue().substring(0, 1));
//                tableView.setItems(DBAppointments.getAppointmentsByContactID(contactID));
//                totalAmountLabel.setText(String.valueOf(DBAppointments.getAppointmentsByContactID(contactID).size()));
//            } else if (comboBoxLabel.getText().equals("Type")) {
//                String type = comboBox.getValue().toString();
//                tableView.setItems(DBCustomers.getCustomersByType(type));
//                totalAmountLabel.setText(String.valueOf(DBAppointments.getAppointmentsByType(type).size()));
//            }
//            else if (comboBoxLabel.getText().equals("Month")) {
//                String month = comboBox.getValue().toString();
//                tableView.setItems(DBAppointments.getAppointmentsByMonth(Months.get(month)));
//                totalAmountLabel.setText(String.valueOf(DBAppointments.getAppointmentsByMonth(Months.get(month)).size()));
//            }
//            else {
//                String postalCode = comboBox.getValue().toString();
//                tableView.setItems(DBAppointments.getAppointmentsByLocation(postalCode));
//                totalAmountLabel.setText(String.valueOf(DBAppointments.getAppointmentsByLocation(postalCode).size()));
//            }
//        }
//        catch (NullPointerException e) {
//            ObservableList clear = FXCollections.observableArrayList();
//            tableView.setItems(clear);
//        }
    }

    @FXML
    void onActionViewByContact(ActionEvent event) {

    }

    @FXML
    void onActionViewByMonth(ActionEvent event) {
        comboBoxLabel.setText("Month");
        comboBox.setItems(monthsList);
    }

    @FXML
    void onActionViewByPostalCode(ActionEvent event) {
        comboBoxLabel.setText("Location");

        ObservableList locationList = FXCollections.observableArrayList();

        for (Appointments appointment : DBAppointments.getAllAppointments()) {
            locationList.add(appointment.getLocation());
        }
        //totalAmountLabel.setText(String.valueOf(locationList.size()));
        comboBox.setItems(locationList);
    }

    @FXML
    void onActionViewByType(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] months = new String[] {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        for (String month : months) {
            monthsList.add(month);
        }
        for (int i = 1; i <= months.length; ++i) {
            Months.put(months[i - 1], i);
        }
    }
}
