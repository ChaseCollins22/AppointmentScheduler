package DBAccess;

import Database.DBConnection;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

    public static ObservableList<Divisions> getAllDivisions() {

        ObservableList<Divisions> divisionsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Division_ID, Division FROM first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Divisions division = new Divisions(divisionID, divisionName);
                divisionsList.add(division);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisionsList;
    }

    public static ObservableList<Divisions> getDivisionByCountryName(String countryName) {

        ObservableList<Divisions> divisionsList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT f.division_ID, f.division FROM countries c\n" +
                         "INNER JOIN first_level_divisions f ON c.country_id = f.country_id\n" +
                         "WHERE c.country = " + "'" + countryName + "'";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Divisions division = new Divisions(divisionID, divisionName);
                divisionsList.add(division);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisionsList;
    }
}
