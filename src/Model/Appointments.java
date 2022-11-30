package Model;

import javafx.scene.control.TableColumn;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Appointments {
    public int apptID;
    public String title;
    public String description;
    public String location;
    public String contact;
    public String type;
    public Date startDate;
    public Time startTime;
    public Time endTime;
    public Date endDate;
    public int customerID;
    public int userID;

    public Appointments(int apptID, String title, String description, String location, String contact, String type,
                        Date startDate, Time startTime, Time endTime, Date endDate, int customerID, int userID) {
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

    public Date getStartDate() {
        return startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }
}
