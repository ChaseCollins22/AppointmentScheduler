package DBAccess;

import Database.DBConnection;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

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

                //Zone ID in DB
                ZoneId zoneId = ZoneId.of("UTC");
                //Zone ID of local time
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                //Create ZDT of Start
                ZonedDateTime DbZDTStart = ZonedDateTime.of(startDate, startTime, zoneId);
                //Create ZDT of End
                ZonedDateTime DbZDTEnd = ZonedDateTime.of(endDate, endTime, zoneId);

                //DB Start value to local time
                Instant DbToUTCInstantStart = DbZDTStart.toInstant();
                ZonedDateTime UTCToLocalZDTStart = DbToUTCInstantStart.atZone(localZoneId);
                //DB End value to local time
                Instant DbToUTCInstantEnd = DbZDTEnd.toInstant();
                ZonedDateTime UTCToLocalZDTEnd = DbToUTCInstantEnd.atZone(localZoneId);

                //Get Local Start date and time from DB UTC date and time
                startDate = UTCToLocalZDTStart.toLocalDateTime().toLocalDate();
                startTime = UTCToLocalZDTStart.toLocalDateTime().toLocalTime();
                //Get Local End date and time from Db UTC date and time
                endDate = UTCToLocalZDTEnd.toLocalDateTime().toLocalDate();
                endTime = UTCToLocalZDTEnd.toLocalDateTime().toLocalTime();

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

                //Zone ID in DB
                ZoneId zoneId = ZoneId.of("UTC");
                //Zone ID of local time
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                //Create ZDT of Start
                ZonedDateTime DbZDTStart = ZonedDateTime.of(startDate, startTime, zoneId);
                //Create ZDT of End
                ZonedDateTime DbZDTEnd = ZonedDateTime.of(endDate, endTime, zoneId);

                //DB Start value to local time
                Instant DbToUTCInstantStart = DbZDTStart.toInstant();
                ZonedDateTime UTCToLocalZDTStart = DbToUTCInstantStart.atZone(localZoneId);
                //DB End value to local time
                Instant DbToUTCInstantEnd = DbZDTEnd.toInstant();
                ZonedDateTime UTCToLocalZDTEnd = DbToUTCInstantEnd.atZone(localZoneId);

                //Get Local Start date and time from DB UTC date and time
                startDate = UTCToLocalZDTStart.toLocalDateTime().toLocalDate();
                startTime = UTCToLocalZDTStart.toLocalDateTime().toLocalTime();
                //Get Local End date and time from Db UTC date and time
                endDate = UTCToLocalZDTEnd.toLocalDateTime().toLocalDate();
                endTime = UTCToLocalZDTEnd.toLocalDateTime().toLocalTime();

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

                //Zone ID in DB
                ZoneId zoneId = ZoneId.of("UTC");
                //Zone ID of local time
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                //Create ZDT of Start
                ZonedDateTime DbZDTStart = ZonedDateTime.of(startDate, startTime, zoneId);
                //Create ZDT of End
                ZonedDateTime DbZDTEnd = ZonedDateTime.of(endDate, endTime, zoneId);

                //DB Start value to local time
                Instant DbToUTCInstantStart = DbZDTStart.toInstant();
                ZonedDateTime UTCToLocalZDTStart = DbToUTCInstantStart.atZone(localZoneId);
                //DB End value to local time
                Instant DbToUTCInstantEnd = DbZDTEnd.toInstant();
                ZonedDateTime UTCToLocalZDTEnd = DbToUTCInstantEnd.atZone(localZoneId);

                //Get Local Start date and time from DB UTC date and time
                startDate = UTCToLocalZDTStart.toLocalDateTime().toLocalDate();
                startTime = UTCToLocalZDTStart.toLocalDateTime().toLocalTime();
                //Get Local End date and time from Db UTC date and time
                endDate = UTCToLocalZDTEnd.toLocalDateTime().toLocalDate();
                endTime = UTCToLocalZDTEnd.toLocalDateTime().toLocalTime();

                Appointments appointment = new Appointments(appointmentID, title, description, location, contact, type,
                        startDate, startTime, endTime, endDate, customerID, userID);
                appointmentsList.add(appointment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    public static int addAppointment(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End,
                                     String Create_Date, String Created_By, String Last_Update, String Last_Update_By, int Customer_ID, int User_ID,
                                     int Contact_ID) throws SQLException {
        int rowsAffected = 1;

        try {

            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            LocalDate startDate = Start.toLocalDate();
            LocalTime startTime = Start.toLocalTime();
            LocalDate endDate = End.toLocalDate();
            LocalTime endTime = End.toLocalTime();

            ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
            ZonedDateTime localZDTStart = ZonedDateTime.of(Start, localZoneID);
            ZonedDateTime localZDTEnd = ZonedDateTime.of(End, localZoneID);

            Instant localToUTCStart = localZDTStart.toInstant();
            ZonedDateTime localToUTCStartFinal = localToUTCStart.atZone(ZoneId.of("UTC"));

            Instant localTOUTCEnd = localZDTEnd.toInstant();
            ZonedDateTime localToUTCEndFinal = localTOUTCEnd.atZone(ZoneId.of("UTC"));

            Start = localToUTCStartFinal.toLocalDateTime();
            End = localToUTCEndFinal.toLocalDateTime();

            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Location);
            ps.setString(4, Type);
            ps.setTimestamp(5, Timestamp.valueOf(Start));
            ps.setTimestamp(6, Timestamp.valueOf(End));
            ps.setString(7, Create_Date);
            ps.setString(8, Created_By);
            ps.setString(9, Last_Update);
            ps.setString(10, Last_Update_By);
            ps.setInt(11, Customer_ID);
            ps.setInt(12, User_ID);
            ps.setInt(13, Contact_ID);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.getMessage();
        }

        return rowsAffected;
    }

    public static boolean deleteAppointment(Appointments appointment) {
        try {
            String sql = "DELETE FROM appointments WHERE appointment_id = " + appointment.getApptID();

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static int updateAppointments(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End,
                                         String Create_Date, String Created_By, String Last_Update, String Last_Update_By, int Customer_ID, int User_ID,
                                         int Contact_ID, int Appointment_ID) throws SQLException {

        String sql = "UPDATE appointments SET title = ?, description = ?," +
                " location = ?, type = ?, start = ?, end = ?, create_date = ?, created_by = ?, last_update = ?, last_updated_by = ?," +
                "customer_id = ?, user_id = ?, contact_id = ? where appointment_id = ?";

        PreparedStatement ps = Database.DBConnection.getConnection().prepareStatement(sql);

        LocalDate startDate = Start.toLocalDate();
        LocalTime startTime = Start.toLocalTime();
        LocalDate endDate = End.toLocalDate();
        LocalTime endTime = End.toLocalTime();

        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime localZDTStart = ZonedDateTime.of(Start, localZoneID);
        ZonedDateTime localZDTEnd = ZonedDateTime.of(End, localZoneID);

        Instant localToUTCStart = localZDTStart.toInstant();
        ZonedDateTime localToUTCStartFinal = localToUTCStart.atZone(ZoneId.of("UTC"));

        Instant localTOUTCEnd = localZDTEnd.toInstant();
        ZonedDateTime localToUTCEndFinal = localTOUTCEnd.atZone(ZoneId.of("UTC"));

        Start = localToUTCStartFinal.toLocalDateTime();
        End = localToUTCEndFinal.toLocalDateTime();

        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setString(3, Location);
        ps.setString(4, Type);
        ps.setTimestamp(5, Timestamp.valueOf(Start));
        ps.setTimestamp(6, Timestamp.valueOf(End));
        ps.setString(7, Create_Date);
        ps.setString(8, Created_By);
        ps.setString(9, Last_Update);
        ps.setString(10, Last_Update_By);
        ps.setInt(11, Customer_ID);
        ps.setInt(12, User_ID);
        ps.setInt(13, Contact_ID);
        ps.setInt(14, Appointment_ID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static boolean isAppointmentOverlap(int customer_id, LocalDateTime newApptStartTime, LocalDateTime newApptEndTime) throws SQLException {

        String sql = "SELECT c.Customer_ID, a.Appointment_ID, a.Start, a.End FROM customers c\n" +
                     "INNER JOIN appointments a on c.customer_id = a.customer_id\n" +
                     "WHERE c.Customer_ID = " + customer_id;

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            int appointmentID = rs.getInt("Appointment_ID");
            LocalDateTime dbstart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime dbend = rs.getTimestamp("End").toLocalDateTime();

            LocalTime DBStartTime = Timestamp.valueOf(dbstart).toLocalDateTime().toLocalTime();
            LocalDate DBStartDate = Timestamp.valueOf(dbstart).toLocalDateTime().toLocalDate();
            LocalTime DBEndTime = Timestamp.valueOf(dbend).toLocalDateTime().toLocalTime();
            LocalDate DBEndDate = Timestamp.valueOf(dbend).toLocalDateTime().toLocalDate();

            //Zone ID in DB
            ZoneId zoneId = ZoneId.of("UTC");
            //Zone ID of local time
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            //Create ZDT of Start
            ZonedDateTime DbZDTStart = ZonedDateTime.of(DBStartDate, DBStartTime, zoneId);
            //Create ZDT of End
            ZonedDateTime DbZDTEnd = ZonedDateTime.of(DBEndDate, DBEndTime, zoneId);

            //DB Start value to local time
            Instant DbToUTCInstantStart = DbZDTStart.toInstant();
            ZonedDateTime UTCToLocalZDTStart = DbToUTCInstantStart.atZone(localZoneId);
            //DB End value to local time
            Instant DbToUTCInstantEnd = DbZDTEnd.toInstant();
            ZonedDateTime UTCToLocalZDTEnd = DbToUTCInstantEnd.atZone(localZoneId);

           // System.out.println(UTCToLocalZDTStart);

            //Get Local Start date and time from DB UTC date and time
            DBStartDate = UTCToLocalZDTStart.toLocalDateTime().toLocalDate();
            DBStartTime = UTCToLocalZDTStart.toLocalDateTime().toLocalTime();
            //Get Local End date and time from Db UTC date and time
            DBEndDate = UTCToLocalZDTEnd.toLocalDateTime().toLocalDate();
            DBEndTime = UTCToLocalZDTEnd.toLocalDateTime().toLocalTime();

            Appointments appointment = new Appointments(appointmentID, DBStartDate, DBStartTime,
                    DBEndTime,  DBEndDate, customerID);
            appointmentsList.add(appointment);
        }

        for (Appointments appointment : appointmentsList) {
            //Start Date and Time
            LocalTime newApptStarttime = newApptStartTime.toLocalTime();
            LocalDate newApptStartDate = newApptStartTime.toLocalDate();
            //End Date and Time
            LocalTime newApptEndtime = newApptEndTime.toLocalTime();
            LocalDate newApptEndDate = newApptEndTime.toLocalDate();

            if (newApptStarttime.isBefore(appointment.getStartTime()) && newApptEndtime.isAfter(appointment.getEndTime()) ||
                newApptStarttime.equals(appointment.getStartTime()) && newApptEndtime.isAfter(appointment.getEndTime())) {
                return true;
            }
            else if (newApptStarttime.isBefore(appointment.getStartTime()) && newApptEndtime.isAfter(appointment.getEndTime()) ||
                     newApptStarttime.equals(appointment.getStartTime()) && newApptEndtime.equals(appointment.getEndTime()) ||
                     newApptStarttime.isBefore(appointment.getStartTime()) && newApptEndtime.equals(appointment.getEndTime())) {
                return true;
            }
            else if (newApptStarttime.isBefore(appointment.getEndTime()) && newApptEndtime.isAfter(appointment.getEndTime()) ||
                     newApptStarttime.isAfter(appointment.getStartTime()) && newApptEndtime.equals(appointment.getEndTime())) {
                return  true;
            }
            //System.out.println(appointment.getCustomerID() + " " + appointment.getApptID() + " " + appointment.getStartDate() + " " + appointment.getStartTime());
        }
        return false;
    }
}
