package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

/**
 * This class creates an Appointment object.
 */
public class Appointments {
    public int apptID;
    public String title;
    public String description;
    public String location;
    public String contact;
    public String type;
    public LocalDate startDate;
    public LocalTime startTime;
    public LocalTime endTime;
    public LocalDate endDate;
    public int customerID;
    public int userID;

    /**
     * This function is the constructor for an appountment object.
     * @param apptID Id number for the appointment.
     * @param title Title of the appointment.
     * @param description Description of the appointment.
     * @param location Location of the appointment.
     * @param contact Contact Id related to the appointment.
     * @param type Type of the appointment.
     * @param startDate Start Date of the appointment.
     * @param startTime Start time of the appointment.
     * @param endTime End time of the appointment.
     * @param endDate End date of the appointment.
     * @param customerID Customer ID related to the appointment.
     * @param userID User ID related to the appointment.
     */
    public Appointments(int apptID, String title, String description, String location, String contact, String type,
                        LocalDate startDate, LocalTime startTime, LocalTime endTime, LocalDate endDate, int customerID, int userID) {
        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.customerID = customerID;
        this.userID = userID;
    }

    /**
     * Secondary constructor for an appointment object.
     * @param apptID Id number for the appointment.
     * @param startDate Start date for the appointment.
     * @param startTime Start time for the appointment.
     * @param endTime End time for the appointment.
     * @param endDate End date for the appointment.
     * @param customerID customer ID related to the appointment.
     */
    public Appointments(int apptID, LocalDate startDate, LocalTime startTime, LocalTime endTime,
                        LocalDate endDate, int customerID) {
        this.apptID = apptID;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.customerID = customerID;
    }

    /**
     * This function gets the appointment ID.
     * @return appointment ID integer.
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * This function gets the appointment title.
     * @return Appointment title string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This function gets the appointment description.
     * @return Appointment description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This function gets the appointment location
     * @return appointment location string.
     */
    public String getLocation() {
        return location;
    }

    /**
     * This function gets the appointment contact id.
     * @return String contact id.
     */
    public String getContact() {
        return contact;
    }

    /**
     * This function gets the appointment type.
     * @return The type of appointment in a string.
     */
    public String getType() {
        return type;
    }

    /**
     * This function gets the appointment start date.
     * @return LocalDate of the start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * This function gets the appointment start time.
     * @return LocalTime of the appointment start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * This function gets the appointment end time.
     * @return LocalTime of the appointment end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * This function gets the appointment end date.
     * @return LocalDate of the appointment end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * This function gets the appointment's customer id.
     * @return customer id integer related to the appointment.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This function gets the appointment's user id.
     * @return User id integer related to the appointment.
     */
    public int getUserID() {
        return userID;
    }

}
