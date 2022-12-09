package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactReportScreenController {
    @FXML
    private TableView<?> contactTableView;

    @FXML
    private TableColumn<?, ?> apptID;

    @FXML
    private ComboBox<?> contactComboBox;

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
    void onActionViewByContact(ActionEvent event) {

    }

    @FXML
    void onActionViewByCustom(ActionEvent event) {

    }

    @FXML
    void onActionViewByMonth(ActionEvent event) {

    }

    @FXML
    void onActionViewByType(ActionEvent event) {

    }
}
