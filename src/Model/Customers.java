package Model;


import java.util.Date;

public class Customers {
    int Customer_ID;
    String Customer_Name;
    String Address;
    String Postal_Code;
    String Phone;
    Date Create_Date;
    String Created_By;
    Date Last_Update;
    String Last_Updated_By;
    int Division_ID;

    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone,
                     Date create_Date, String created_By, Date last_Update, String last_Updated_By, int division_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Division_ID = division_ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public String getPhone() {
        return Phone;
    }

    public Date getCreate_Date() {
        return Create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public Date getLast_Update() {
        return Last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public int getDivision_ID() {
        return Division_ID;
    }
}
