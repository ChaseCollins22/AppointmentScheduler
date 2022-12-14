package DBAccess;

import Database.DBConnection;
import Model.Contacts;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles all database queries for Contact objects.
 */
public class DBContacts {

    /**
     * This function selects all of the existing contacts in the database.
     * @return An ObservableList of Contact objects.
     */
    public static ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Contact_ID, Contact_Name FROM contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Contacts contact = new Contacts(contactID, contactName);
                contactsList.add(contact);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactsList;
    }
}
