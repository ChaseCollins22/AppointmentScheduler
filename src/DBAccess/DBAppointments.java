package DBAccess;

import Database.DBConnection;
import Model.Appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.*;

import java.time.format.DateTimeFormatter;

import java.util.TimeZone;

/**
 * This class executes queries to the appointments table in the database.
 */
public class DBAppointments {

    /**
     * This function selects all of the appointments in the database.
     * @return An ObservableList of Appointment objects.
     */
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

    /**
     * This function selects all of the appointments in the database by the current month.
     * @return An ObservableList of Appointment objects.
     */
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

    /**
     * This function selects all of the appointments in the database by the current week.
     * @return An ObservableList of Appointment objects.
     */
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

    /**
     * This function inserts an appointment object into the database.
     * @param Title Title of the appointment.
     * @param Description Description of the appointment.
     * @param Location Location of the appointment.
     * @param Type Type of appointment.
     * @param Start Start DateTime of the appointment.
     * @param End End DateTime of the appointment.
     * @param Create_Date DateTime the appointment is created.
     * @param Created_By Who created the appointment.
     * @param Last_Update When the last update to the appointment was made.
     * @param Last_Update_By Who updated the appointment last.
     * @param Customer_ID The id of the customer related to the appointment.
     * @param User_ID The id of the user related to the appointment.
     * @param Contact_ID The contact id of the contact related to the appointment.
     * @throws SQLException
     */
    public static void addAppointment(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End,
                                     String Create_Date, String Created_By, String Last_Update, String Last_Update_By, int Customer_ID, int User_ID,
                                     int Contact_ID) throws SQLException {

        try {

            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

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
            e.printStackTrace();
        }
    }

    /**
     * This function deletes an appointment from the database.
     * @param appointment An Appointment object.
     */
    public static void deleteAppointment(Appointments appointment) {
        try {
            String sql = "DELETE FROM appointments WHERE appointment_id = " + appointment.getApptID();

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function updates an existing appointment object in the database.
     * @param Title Title of the appointment.
     * @param Description Description of the appointment.
     * @param Location Location of the appointment.
     * @param Type Type of appointment.
     * @param Start Start DateTime of the appointment.
     * @param End End DateTime of the appointment.
     * @param Create_Date DateTime the appointment is created.
     * @param Created_By Who created the appointment.
     * @param Last_Update When the last update to the appointment was made.
     * @param Last_Update_By Who updated the appointment last.
     * @param Customer_ID The id of the customer related to the appointment.
     * @param User_ID The id of the user related to the appointment.
     * @param Contact_ID The contact id of the contact related to the appointment.
     * @throws SQLException
     */
    public static void updateAppointments(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End,
                                         String Create_Date, String Created_By, String Last_Update, String Last_Update_By, int Customer_ID, int User_ID,
                                         int Contact_ID, int Appointment_ID) throws SQLException {

        String sql = "UPDATE appointments SET title = ?, description = ?," +
                " location = ?, type = ?, start = ?, end = ?, create_date = ?, created_by = ?, last_update = ?, last_updated_by = ?," +
                "customer_id = ?, user_id = ?, contact_id = ? where appointment_id = ?";

        PreparedStatement ps = Database.DBConnection.getConnection().prepareStatement(sql);

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

    }


    /**
     * This function checks existing appointments for any appointment overlap.
     * @param Title The title of the appointment.
     * @param Description The description of the appointment.
     * @param Location The location of the appointment.
     * @param Type The type of the appointment.
     * @param Start The start DateTime of the appointment.
     * @param End The end DateTime of the appointment.
     * @param Customer_ID The id of the customer related to the appointment.
     * @param User_ID The id of the user related to the appointment.
     * @param Contact_ID The id of the contact realted to the appointment.
     * @param Appointment_ID The existing appointment ID number.
     * @return true if appointment overlap exists and false if not.
     * @throws SQLException
     */
    public static boolean isAppointmentOverlap(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End,
                                               int Customer_ID, int User_ID, int Contact_ID, int Appointment_ID) throws SQLException {

        String sql = "SELECT c.Customer_ID, a.Appointment_ID, a.Start, a.End FROM customers c\n" +
                "INNER JOIN appointments a on c.customer_id = a.customer_id\n" +
                "WHERE c.Customer_ID = " + Customer_ID;

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

            //Get Local Start date and time from DB UTC date and time
            DBStartDate = UTCToLocalZDTStart.toLocalDateTime().toLocalDate();
            DBStartTime = UTCToLocalZDTStart.toLocalDateTime().toLocalTime();
            //Get Local End date and time from Db UTC date and time
            DBEndDate = UTCToLocalZDTEnd.toLocalDateTime().toLocalDate();
            DBEndTime = UTCToLocalZDTEnd.toLocalDateTime().toLocalTime();

            Appointments appointment = new Appointments(appointmentID, DBStartDate, DBStartTime,
                    DBEndTime, DBEndDate, customerID);
            appointmentsList.add(appointment);
        }

        for (Appointments appt : appointmentsList) {
            //Start Date and Time
            LocalDateTime newStart = Start;
            //End Date and Time
            LocalDateTime newEnd = End;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String s = appt.getStartDate().toString() + " " + appt.getStartTime().toString();
                LocalDateTime existingStartTime = LocalDateTime.parse(s, formatter);
                String e = appt.getEndDate().toString() + " " + appt.getEndTime().toString();
                LocalDateTime existingEndTime = LocalDateTime.parse(e, formatter);
                System.out.println("Appt ID: " + appt.getApptID() + " Start Date: " +appt.getStartDate() + " Start Time " + appt.getStartTime() + " End Time: " + appt.getEndTime() + "Customer ID" + appt.getCustomerID());
                //For Modify appointment when appointment times remain unchanged
                if (appt.getApptID() == Appointment_ID && (newStart.isEqual(existingStartTime) && newEnd.isEqual(existingEndTime)) && appt.getCustomerID() == Customer_ID) {
                    System.out.println("First");
                    return false;
                }
                if (newStart.isEqual(existingStartTime) && newEnd.isAfter(existingEndTime) ||
                        newStart.isBefore(existingEndTime) && newEnd.isAfter(existingEndTime)) {
                    System.out.println("Second");
                    return true;
                } else if (newStart.isEqual(existingStartTime) && newEnd.isEqual(existingEndTime) ||
                        newStart.isBefore(existingEndTime) && newEnd.isEqual(existingEndTime)) {
                    System.out.println("Third");
                    return true;
                } else if (newStart.isBefore(existingStartTime) && newEnd.isEqual(existingEndTime) ||
                        newStart.isBefore(existingStartTime) && newEnd.isAfter(existingStartTime) ||
                        newStart.isBefore(existingStartTime) && newEnd.isAfter(existingEndTime)) {
                    System.out.println("Fourth");
                    return true;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * This function selects all appointment by user ID.
     * @param loginID The id of the user logged in.
     * @return An ObservableList of appointments.
     */
    public static ObservableList<Appointments> getAppointmentsByUserID(int loginID) {

        ObservableList<Appointments> appointmentsByUser = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE User_ID = " + loginID;
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact = rs.getString("Contact_ID");
                String type = rs.getString("Type");
                String start = rs.getString("Start");
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

                Duration timeElapsed = Duration.between(LocalTime.now(), startTime);
                if (timeElapsed.toMinutes() <= 15 && timeElapsed.toMinutes() >= 0) {
                    appointmentsByUser.add(appointment);
                    System.out.println(timeElapsed.toMinutes());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsByUser;
    }

    /**
     * This function selects all existing appointments by one contactID.
     * @param contactID The contact ID integer used in the query.
     * @return ObservableList of Appointment objects.
     */
    public static ObservableList<Appointments> getAppointmentsByContactID(int contactID) {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments\n" +
                    "WHERE contact_id = " + contactID;

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

    /**
     * This function selects all existing appointments by a specific type.
     * @param Type A type of appointment used in the query.
     * @return An ObservableList of Appointment objects.
     */
    public static ObservableList<Appointments> getAppointmentsByType(String Type) {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments\n" +
                    "WHERE type = '" + Type + "'";

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
    // How to create hashtable: https://www.educative.io/answers/how-to-create-a-dictionary-in-java
    // Create TextArea for alert: https://stackoverflow.com/questions/43740494/javafx-alert-cannot-fit-content
}
