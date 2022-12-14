package Model;


import java.util.Date;

/**
 * This class is the outline for a customer object.
 */
public class Customers {
    public int Customer_ID;
    public String Customer_Name;
    public String Address;
    public String Postal_Code;
    public String Phone;
    public Date Create_Date;
    public String Created_By;
    public Date Last_Update;
    public String Last_Updated_By;
    public int Division_ID;
    public String apptType;

    /**
     * This function creates a customer object.
     * @param customer_ID Id of the customer.
     * @param customer_Name Name of the customer.
     * @param address Address of the customer.
     * @param postal_Code Postal code of the customer.
     * @param phone Phone number of the customer.
     * @param create_Date The date the customer was created.
     * @param created_By Who the customer was created by.
     * @param last_Update When the customer was last updated.
     * @param last_Updated_By Who the customer was last updated by.
     * @param division_ID The state/province where the customer is located.
     */
    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone,
                     Date create_Date, String created_By, Date last_Update, String last_Updated_By, int division_ID) {
        this.Customer_ID = customer_ID;
        this.Customer_Name = customer_Name;
        this.Address = address;
        this.Postal_Code = postal_Code;
        this.Phone = phone;
        this.Create_Date = create_Date;
        this.Created_By = created_By;
        this.Last_Update = last_Update;
        this.Last_Updated_By = last_Updated_By;
        this.Division_ID = division_ID;
    }

    /**
     * This function creates a customer object.
     * @param customer_ID Id of the customer.
     * @param customer_Name Name of the customer.
     * @param address Address of the customer.
     * @param postal_Code Postal code of the customer.
     * @param phone Phone number of the customer.
     * @param create_Date The date the customer was created.
     * @param created_By Who the customer was created by.
     * @param last_Update When the customer was last updated.
     * @param last_Updated_By Who the customer was last updated by.
     * @param division_ID The state/province where the customer is located.
     * @param apptType The type of the appointment related to the customer.
     */
    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone,
                     Date create_Date, String created_By, Date last_Update, String last_Updated_By, int division_ID, String apptType) {
        this.Customer_ID = customer_ID;
        this.Customer_Name = customer_Name;
        this.Address = address;
        this.Postal_Code = postal_Code;
        this.Phone = phone;
        this.Create_Date = create_Date;
        this.Created_By = created_By;
        this.Last_Update = last_Update;
        this.Last_Updated_By = last_Updated_By;
        this.Division_ID = division_ID;
        this.apptType = apptType;
    }

    /**
     * This function gets the customer id of a customer object.
     * @return Customer id integer.
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * This function gets the name of a customer object.
     * @return Customer name.
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     * This function gets the address of a customer object.
     * @return Address of a customer.
     */
    public String getAddress() {
        return Address;
    }

    /**
     * This function gets the postal code of customer object.
     * @return The postal code of a customer.
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     * This function gets the phone number of a customer object.
     * @return The phone number of a customer.
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * This function gets the state/province id number of a customer object.
     * @return The state/province id number.
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * This function overrides the toString method.
     * @return A string of the customer id.
     */
    @Override
    public String toString() {
        return (String.valueOf(getCustomer_ID()));
    }
}
