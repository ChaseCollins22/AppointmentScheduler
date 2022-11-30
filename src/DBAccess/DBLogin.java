package DBAccess;

import Database.DBConnection;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLogin {

    public static ObservableList<Users> getAllUsers() {

        ObservableList<Users> usersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT User_Name, Password FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users user = new Users(userName, password);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }
}
