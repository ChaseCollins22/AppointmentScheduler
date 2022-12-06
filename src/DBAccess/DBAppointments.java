package DBAccess;

import Database.DBConnection;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
                String start= rs.getString("Start");
                String startT = rs.getString("Start");
                String end = rs.getString("End");
                String endT = rs.getString("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                LocalTime startTime = Timestamp.valueOf(start).toLocalDateTime().toLocalTime();
                LocalDate startDate = Timestamp.valueOf(startT).toLocalDateTime().toLocalDate();
                LocalTime endTime = Timestamp.valueOf(end).toLocalDateTime().toLocalTime();
                LocalDate endDate = Timestamp.valueOf(endT).toLocalDateTime().toLocalDate();
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
                String start= rs.getString("Start");
                String startT = rs.getString("Start");
                String end = rs.getString("End");
                String endT = rs.getString("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                LocalTime startTime = Timestamp.valueOf(start).toLocalDateTime().toLocalTime();
                LocalDate startDate = Timestamp.valueOf(startT).toLocalDateTime().toLocalDate();
                LocalTime endTime = Timestamp.valueOf(end).toLocalDateTime().toLocalTime();
                LocalDate endDate = Timestamp.valueOf(endT).toLocalDateTime().toLocalDate();
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
                String start= rs.getString("Start");
                String startT = rs.getString("Start");
                String end = rs.getString("End");
                String endT = rs.getString("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                LocalTime startTime = Timestamp.valueOf(start).toLocalDateTime().toLocalTime();
                LocalDate startDate = Timestamp.valueOf(startT).toLocalDateTime().toLocalDate();
                LocalTime endTime = Timestamp.valueOf(end).toLocalDateTime().toLocalTime();
                LocalDate endDate = Timestamp.valueOf(endT).toLocalDateTime().toLocalDate();

                Appointments appointment = new Appointments(appointmentID, title, description, location, contact, type,
                        startDate, startTime, endTime, endDate, customerID, userID);
                appointmentsList.add(appointment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    public static int addAppointment(String Title, String Description, String Location, String Type, LocalDate Start, LocalDate End,
                                              String Create_Date, String Created_By, String Last_Update, String Last_Update_By, int Customer_ID, int User_ID,
                                              int Contact_ID) throws SQLException {

        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Update_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setString(3, Location);
        ps.setString(4, Type);
        ps.setDate(5, Date.valueOf(Start));
        ps.setDate(6, Date.valueOf(End));
        ps.setString(7, Create_Date);
        ps.setString(8, Created_By);
        ps.setString(9, Last_Update);
        ps.setString(10, Last_Update_By);
        ps.setInt(11, Customer_ID);
        ps.setInt(12, User_ID);
        ps.setInt(13, Contact_ID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
}
