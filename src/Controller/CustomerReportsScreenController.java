package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Hashtable;
import java.util.ResourceBundle;

public class CustomerReportsScreenController implements Initializable {

    @FXML
    private TableColumn<?, ?> createdBy;

    @FXML
    private TableColumn<?, ?> address;

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
    private ComboBox<String> comboBox;

    @FXML
    private Label comboBoxLabel;

    @FXML
    private TableColumn<?, ?> customerID;

    @FXML
    private TableView<Customers> tableView;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private RadioButton totalByMonth;

    @FXML
    private RadioButton totalByPostalCode;

    @FXML
    private RadioButton totalByType;

    ObservableList<String> monthsList = FXCollections.observableArrayList();
    Hashtable<String, Integer> Months = new Hashtable<String, Integer>();
    ObservableList clear = FXCollections.observableArrayList();

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

    @FXML
    void onActionShowComboBoxItems(ActionEvent event) {
        try {
            if (comboBoxLabel.getText().equals("Type")) {
                String type = comboBox.getValue();
                tableView.setItems(DBCustomers.getCustomersByType(type));
                totalAmountLabel.setText(String.valueOf(DBAppointments.getAppointmentsByType(type).size()));
            }
            else if (comboBoxLabel.getText().equals("Month")) {
                String month = comboBox.getValue();
                tableView.setItems(DBCustomers.getCustomersByMonth(Months.get(month)));
                totalAmountLabel.setText(String.valueOf(DBCustomers.getCustomersByMonth(Months.get(month)).size()));
            }
            else {
                String postalCode = comboBox.getValue();
                tableView.setItems(DBCustomers.getCustomersByPostalCode(postalCode));
                totalAmountLabel.setText(String.valueOf(DBCustomers.getCustomersByPostalCode(postalCode).size()));
            }
        }
        catch (NullPointerException e) {
            ObservableList clear = FXCollections.observableArrayList();
            tableView.setItems(clear);
        }
    }

    @FXML
    void onActionViewByContact(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ContactReportScreen.fxml"));
        loader.load();

        ContactReportScreenController crsController = loader.getController();
        crsController.onActionViewByContact(event);

        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionViewByMonth(ActionEvent event) {
        totalAmountLabel.setText("");
        comboBoxLabel.setText("Month");
        totalByMonth.setSelected(true);
        comboBox.setItems(monthsList);
    }

    @FXML
    void onActionViewByPostalCode(ActionEvent event) {
        totalAmountLabel.setText("");
        comboBoxLabel.setText("Location");
        totalByPostalCode.setSelected(true);
        ObservableList locationList = FXCollections.observableArrayList();

        for (Customers customer : DBCustomers.getAllCustomers()) {
            locationList.add(customer.getPostal_Code());
        }
        comboBox.setItems(clear);
        comboBox.setItems(locationList);
    }

    @FXML
    void onActionViewByType(ActionEvent event) {
        totalAmountLabel.setText("");
        comboBoxLabel.setText("Type");
        totalByType.setSelected(true);
        ObservableList typesList = FXCollections.observableArrayList();
        try {
            for (Appointments appointment : DBAppointments.getAllAppointments()) {
                typesList.add(appointment.getType());
                System.out.println(appointment.getType());
            }
            comboBox.setItems(clear);
            comboBox.setItems(typesList);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
