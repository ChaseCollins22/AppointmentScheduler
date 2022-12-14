package DBAccess;

import Database.DBConnection;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * This class handles all database queries for Customer objects.
 */
public class DBCustomers {

    /**
     * This function gets all of the avaialable Customer objects from the database.
     * @return An ObservableList of Customer objects.
     */
    public static ObservableList<Customers> getAllCustomers() {

        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Date lastUpdate = rs.getDate("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                Customers customer = new Customers(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID );
                customersList.add(customer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customersList;
    }

    /**
     * This functions inserts a Customer object into the database.
     * @param Customer_Name The name of the customer.
     * @param Address The address of the customer.
     * @param Postal_Code The postal code of the customer.
     * @param Phone The phone number of the customer.
     * @param Create_Date The DateTime the customer was created.
     * @param Created_By Who the custom was created by.
     * @param Last_Update When the customer's information was last updated.
     * @param Last_Updated_By Who the customer's information was last updated by.
     * @param Division_ID The state/province where the customer resides.
     * @throws SQLException
     */
    public static void addCustomer(String Customer_Name, String Address, String Postal_Code,
                                  String Phone, String Create_Date, String Created_By,
                                  String Last_Update, String Last_Updated_By, int Division_ID) throws SQLException {

        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";PreparedStatement ps = Database.DBConnection.getConnection().prepareStatement(sql);


        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setString(5, Create_Date);
        ps.setString(6, Created_By);
        ps.setString(7, Last_Update);
        ps.setString(8, Last_Updated_By);
        ps.setInt(9, Division_ID);

        int rowsAffected = ps.executeUpdate();

    }

    /**
     * This function deletes a Customer object from the database.
     * @param customer The customer object to be deleted.
     */
    public static void deleteCustomer(Customers customer) {
        try {
            String query1 = "DELETE FROM appointments WHERE customer_id = " + customer.getCustomer_ID();
            String query2 = "DELETE FROM customers WHERE customer_ID = " + customer.getCustomer_ID();

            PreparedStatement ps1 = DBConnection.getConnection().prepareStatement(query1);
            ps1.execute(query1);

            PreparedStatement ps2 = DBConnection.getConnection().prepareStatement(query2);

            ps2.execute();

        } catch (SQLException e) {

        }
    }

    /**
     * This function updates an existing Customer object.
     * @param Customer_Name The name of the customer.
     * @param Address The address of the customer.
     * @param Postal_Code The postal code of the customer.
     * @param Phone The phone number of the customer.
     * @param Division_ID The state/province id where the customer resides.
     * @param Customer_ID The existing id number of the selected customer.
     * @return
     * @throws SQLException
     */
    public static void updateCustomer(String Customer_Name, String Address, String Postal_Code,
                                  String Phone, int Division_ID, int Customer_ID) throws SQLException {

        String sql = "UPDATE customers SET customer_name = ?, address = ?, postal_code = ?, phone = ?, division_id = ? where customer_id = ?";

        PreparedStatement ps = Database.DBConnection.getConnection().prepareStatement(sql);


        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setInt(5, Division_ID);
        ps.setInt(6, Customer_ID);

        int rowsAffected = ps.executeUpdate();
    }

    /**
     * This function selects all of the customer by a particular type.
     * @param type The type of customer to select.
     * @return An ObservableList of Customer objects.
     */
    public static ObservableList<Customers> getCustomersByType(String type) {

        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "select c.* from customers c\n" +
                         "inner join appointments a on c.customer_id = a.customer_id where a.type = '" + type +"'";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Date lastUpdate = rs.getDate("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                Customers customer = new Customers(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customersList;
    }

    /**
     * This function selects all of the customer by a particular month.
     * @param Month The integer month to selec by.
     * @return An ObservableList of Customer objects.
     */
    public static ObservableList<Customers> getCustomersByMonth(int Month) {

        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers\n" +
                    "WHERE MONTH(Create_Date) = " + Month;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Date lastUpdate = rs.getDate("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                Customers customer = new Customers(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customersList;
    }

    /**
     * This function selects customer by a given postal code.
     * @param PostalCode The postal code to select by.
     * @return An ObservableList of Customer objects.
     */
    public static ObservableList<Customers> getCustomersByPostalCode(String PostalCode) {

        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers\n" +
                    "WHERE postal_code = '" + PostalCode + "'";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Date lastUpdate = rs.getDate("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                Customers customer = new Customers(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customersList;
    }
}
