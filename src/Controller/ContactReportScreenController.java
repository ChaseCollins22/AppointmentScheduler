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

/**
 * This class controls the ContactReportScreen.fxml.
 */
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
    private TableColumn<?, ?> startDate;

    @FXML
    private TableColumn<?, ?> startTime;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private RadioButton viewByContact;

    /**
     * Switches the user back to AppointmentScreen.fxml.
     * @param event Clicking the 'Cancel' button.
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/AppointmentScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This function selects the Contact Schedule button.
     * @param event Clicking the contact schedule radio button.
     * @throws IOException
     */
    @FXML
    void onActionViewByContact(ActionEvent event) throws IOException {
        viewByContact.setSelected(true);
    }


    /**
     * This function switches the view to the CustomerReportScreen.fxml and calls the onActionViewByPostalCode function.
     * @param actionEvent Clicking the View By Postal Code button.
     * @throws IOException
     */
    @FXML
    public void onActionViewByLocation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/CustomerReportScreen.fxml"));
        loader.load();

        CustomerReportsScreenController crsController = loader.getController();
        crsController.onActionViewByPostalCode(actionEvent);

        Stage stage = (Stage) ((RadioButton) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This function switches the view to the CustomerReportScreen.fxml and calls the onActionViewByMonth function.
     * @param event Clicking the View By Month radio button.
     * @throws IOException
     */
    @FXML
    void onActionViewByMonth(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/CustomerReportScreen.fxml"));
        loader.load();

        CustomerReportsScreenController crsController = loader.getController();
        crsController.onActionViewByMonth(event);

        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This function switches the view to the CustomerReportScreen.fxml and calls the onActionViewByType function.
     * @param event Clicking the View By Type radio button.
     * @throws IOException
     */
    @FXML
    void onActionViewByType(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/CustomerReportScreen.fxml"));
        loader.load();

        CustomerReportsScreenController crsController = loader.getController();
        crsController.onActionViewByType(event);

        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This function sets the table view based on the selected combo box id number.
     * @param actionEvent Selecting an id number from the combo box.
     */
    public void onActionShowComboBoxItems(ActionEvent actionEvent) {
        try {
            if (contactLabel.getText().equals("Contact")) {
                int contactID = Integer.parseInt(contactComboBox.getValue().toString().substring(0, 1));
                contactTableView.setItems(DBAppointments.getAppointmentsByContactID(contactID));
            }
        }
        catch(NullPointerException e){
                ObservableList clear = FXCollections.observableArrayList();
                contactTableView.setItems(clear);
            }
        }

    /**
     * This function initializes the controller and sets the table view and columns.
     * @param url A URL.
     * @param resourceBundle A resource bundle.
     */
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
    }
}
