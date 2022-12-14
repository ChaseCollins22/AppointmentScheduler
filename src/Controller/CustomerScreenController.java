package Controller;

import DBAccess.DBCustomers;
import Interfaces.LoadControllerInterface;
import Model.Customers;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the CustomerScreen.fxml view.
 */
public class CustomerScreenController implements Initializable {
    public RadioButton viewAllButton;
    public RadioButton viewCustomersButton;
    public RadioButton viewByWeekButton;
    public RadioButton viewMonthButton;
    public ToggleGroup radioButtonGroup;
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;
    public Button reportsButton;
    public Button logoutButton;
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

    /**
     * This function switches the users view.
     * @param viewName The path to the desired view.
     * @param event Button click.
     * @throws IOException
     */
    public void SwitchView(String viewName, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(viewName));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This function takes the user to the AddCustomerScreen.fxml.
     * @param actionEvent Clicking the 'Add Customer' button.
     * @throws IOException
     */
    @FXML
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        SwitchView("/View/AddCustomerScreen.fxml", actionEvent);
    }

    /**
     * This function takes the user to the ModifyCustomerScreen.fxml view and popualtes existing data into the available textboxes.
     * @param actionEvent Clicking the 'Modify Customer' button.
     * @throws IOException
     */
    @FXML
    public void onActionModifyCustomer(ActionEvent actionEvent) throws IOException {
        try {
            Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyCustomerScreen.fxml"));
            loader.load();

            ModifyCustomerController mCController = loader.getController();
            mCController.setCustomerData(selectedCustomer);
            mCController.setFocusTraversableFalse();

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer to modify :)");
            alert.setHeaderText("ERROR: No customer selected");
            alert.showAndWait();

        }
    }

    /**
     * This function deletes a user from the database via the customerTableView.
     * @param actionEvent Selecting a customer and then clicking the 'Delete Customer' button.
     */
    @FXML
    public void onActionDeleteCustomer(ActionEvent actionEvent) {
        try {
            Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");
            alert.setHeaderText("Delete customer " + selectedCustomer.getCustomer_Name() + "?");
            Optional<ButtonType> alertResult = alert.showAndWait();

            if (alertResult.get().getText().equals("OK")) {
                DBCustomers.deleteCustomer(selectedCustomer);
                customerTableView.setItems(DBCustomers.getAllCustomers());
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Customer deleted");
                alert2.setHeaderText("Success");
                alert2.showAndWait();
            }
        }

        catch (NullPointerException e) {
            Alert alertError = new Alert(Alert.AlertType.ERROR, "Please select a customer to delete :)");
            alertError.setHeaderText("ERROR: No customer selected");
            alertError.showAndWait();
        }
    }

    /**
     * This function switches the view to teh AppointmentScreen.fxml view and calls onActionViewAll function.
     * Lambda #2 Takes in a string of a path to a view and loads the controller and then switches the view.
     * @param event Clicking the View All radio button.
     * @throws IOException
     */
    @FXML
    void onActionViewAll(ActionEvent event) throws IOException {
        LoadControllerInterface loadController = (String viewPath) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewPath));
            loader.load();

            AppointmentScreenController ascController = loader.getController();
            ascController.onActionViewAll(event);

            Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        };
        loadController.loadNewController("/View/AppointmentScreen.fxml");
    }

    /**
     * This function switches the view to teh AppointmentScreen.fxml view and calls onActionViewByMonth function.
     * @param event Clicking the View By Month radio button.
     * @throws IOException
     */
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

    /**
     * This function switches the view to teh AppointmentScreen.fxml view and calls onActionViewByWeek function.
     * @param event Clicking the View By Week radio button.
     * @throws IOException
     */
    @FXML
    void onActionViewByWeek(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AppointmentScreen.fxml"));
        loader.load();

        AppointmentScreenController ascController = loader.getController();
        ascController.onActionViewByWeek(event);

        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This function sets the tableview to all the existing customers in the database.
     * @param event Clicking the view Customers radio button.
     * @throws IOException
     */
    @FXML
    void onActionViewCustomers(ActionEvent event) throws IOException {
        customerTableView.setItems(DBCustomers.getAllCustomers());
    }

    /**
     * This function takes the user to the ContactReportScreen.fxml.
     * @param actionEvent Clicking the 'Generate Reports' button.
     * @throws IOException
     */
    public void onActionGenerateReports(ActionEvent actionEvent) throws IOException {
        SwitchView("/View/ContactReportScreen.fxml", actionEvent);
    }

    /**
     * This function logs the user out of the application and takes them back to the LoginScreen.fxml view.
     * @param actionEvent Clicking the logout button.
     * @throws IOException
     */
    public void onActionLogout(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Logout?");
        alert.setContentText("Are you sure you want to logout?");
        alert.showAndWait();
        if (alert.getResult().getText().equals("OK")) {
            SwitchView("/View/LoginScreen.fxml", actionEvent);
        }
    }

    /**
     * This function initializes the controller and sets the table view and columns.
     * @param url A URL.
     * @param resourceBundle A resource bundle.
     */
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
        viewCustomersButton.setSelected(true);

    }
}
