package DBAccess;

import Database.DBConnection;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles all databse queries for Country objects.
 */
public class DBCountries {

    /**
     * This function selects all of the available countries in the database.
     * @return An ObservableList of Country objects.
     */
    public static ObservableList<Countries> getAllCountries() {

        ObservableList<Countries> countriesList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries country = new Countries(countryID, countryName);
                countriesList.add(country);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countriesList;
    }

    /**
     * This function gets the country id based on the division id.
     * @param divisionID The id for a state/province.
     * @return The country id number for the given state/province.
     */
    public static int getCountryByDivisionID(int divisionID) {

        int country_id = 0;

        try {
            String sql = "select c.country_id from countries c\n" +
                    "inner join first_level_divisions f on c.country_id = f.country_id\n" +
                    "where f.division_id = " + divisionID;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                country_id = rs.getInt("country_id");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country_id;
    }
}

