package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Contacts;
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
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class ContactReportScreenController implements Initializable {
    public ComboBox typeComboBox;
    public Label typeLabel;
    public RadioButton viewByLocation;
    public TableColumn location;
    public Label totalAmountLabel;
    public Label totalLabel;
    @FXML
    private TableView<Appointments> contactTableView;

    @FXML
    private TableColumn<?, ?> apptID;

    @FXML
    private ComboBox contactComboBox;

    @FXML
    private Label contactLabel;

    @FXML
    private TableColumn<?, ?> customerID;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private TableColumn<?, ?> endDate;

    @FXML
    private TableColumn<?, ?> endTime;

    @FXML
    private ToggleGroup radioButtonGroup;

    @FXML
    private Label reportsLabel;

    @FXML
    private TableColumn<?, ?> startDate;

    @FXML
    private TableColumn<?, ?> startTime;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private RadioButton viewByContact;

    @FXML
    private RadioButton viewByCustom;

    @FXML
    private RadioButton viewByMonth;

    @FXML
    private RadioButton viewByType;



    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/AppointmentScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionViewByContact(ActionEvent event) throws IOException {
        //contactComboBox.setItems(DBContacts.getAllContacts());
        SwitchView("/View/ContactReportScreen.fxml", event);
    }

    @FXML
    public void onActionViewByLocation(ActionEvent actionEvent) {

    }


    @FXML
    void onActionViewByMonth(ActionEvent event) throws IOException {

    }

    @FXML
    void onActionViewByType(ActionEvent event) throws IOException {
        contactLabel.setText("Type");
        ObservableList typesList = FXCollections.observableArrayList();

        for (Appointments appointment : DBAppointments.getAllAppointments()) {
            typesList.add(appointment.getType());
        }

        //totalAmountLabel.setText(String.valueOf(typesList.size()));
        contactComboBox.setItems(typesList);

    }

    public void onActionShowComboBoxItems(ActionEvent actionEvent) {
        try {
            if (contactLabel.getText().equals("Contact")) {
                int contactID = Integer.parseInt(contactComboBox.getValue().toString().substring(0, 1));
                contactTableView.setItems(DBAppointments.getAppointmentsByContactID(contactID));
                totalAmountLabel.setText(String.valueOf(DBAppointments.getAppointmentsByContactID(contactID).size()));
            }
        }
        catch(NullPointerException e){
                ObservableList clear = FXCollections.observableArrayList();
                contactTableView.setItems(clear);
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));


        ObservableList contacts = FXCollections.observableArrayList();
        for (Contacts contact : DBContacts.getAllContacts()) {
            contacts.add(contact.getContactID());
        }

        contactComboBox.setItems(contacts);
        totalAmountLabel.setText(String.valueOf(0));
    }
}
