package DBAccess;

import Database.DBConnection;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBAppointments {

    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact = rs.getString("Contact_ID");
                String type = rs.getString("Type");
                Date startDate = rs.getDate("Start");
                Time startTime = rs.getTime("Start");
                Time endTime = rs.getTime("End");
                Date endDate = rs.getDate("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                Appointments appointment = new Appointments(appointmentID, title, description, location, contact, type,
                        startDate, startTime, endTime, endDate, customerID, userID);
                appointmentsList.add(appointment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    public static ObservableList<Appointments> getAppointmentsByMonth() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments\n" +
                         "WHERE MONTH(Start) = MONTH(CURRENT_DATE())";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact = rs.getString("Contact_ID");
                String type = rs.getString("Type");
                Date startDate = rs.getDate("Start");
                Time startTime = rs.getTime("Start");
                Time endTime = rs.getTime("End");
                Date endDate = rs.getDate("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                Appointments appointment = new Appointments(appointmentID, title, description, location, contact, type,
                        startDate, startTime, endTime, endDate, customerID, userID);
                appointmentsList.add(appointment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    public static ObservableList<Appointments> getAppointmentsByWeek() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments\n" +
                         "WHERE WEEK(Start) = WEEK(CURRENT_DATE())";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact = rs.getString("Contact_ID");
                String type = rs.getString("Type");
                Date startDate = rs.getDate("Start");
                Time startTime = rs.getTime("Start");
                Time endTime = rs.getTime("End");
                Date endDate = rs.getDate("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                Appointments appointment = new Appointments(appointmentID, title, description, location, contact, type,
                        startDate, startTime, endTime, endDate, customerID, userID);
                appointmentsList.add(appointment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }
}
