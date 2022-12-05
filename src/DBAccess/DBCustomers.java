package DBAccess;

import Database.DBConnection;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class DBCustomers {

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

    public static int addCustomer(String Customer_Name, String Address, String Postal_Code,
                                  String Phone, String Create_Date, String Created_By,
                                  String Last_Update, String Last_Updated_By, int Division_ID) throws SQLException {

        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = Database.DBConnection.getConnection().prepareStatement(sql);

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

        return rowsAffected;


    }

    public static boolean deleteCustomer(Customers customer) {

        boolean deleteSuccessful = false;

        try {
            String sql = "DELETE FROM customers WHERE customer_ID = " + customer.getCustomer_ID();
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            deleteSuccessful = ps.execute(sql);

        } catch (SQLException e) {

        }

        return deleteSuccessful;
    }
}
