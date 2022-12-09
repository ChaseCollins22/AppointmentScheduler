package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

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

    public Appointments(int apptID, LocalDate startDate, LocalTime startTime, LocalTime endTime,
                        LocalDate endDate, int customerID) {
        this.apptID = apptID;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.customerID = customerID;
    }

    public int getApptID() {
        return apptID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }

}
