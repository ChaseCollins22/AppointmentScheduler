package Model;


import java.util.Date;

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

    @Override
    public String toString() {
        return (String.valueOf(getCustomer_ID()));
    }
}
