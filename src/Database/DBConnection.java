package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost:3306/";
    private static final String dbName = "client_schedule";

    private static final String username= "sqlUser";
    private static final String password= "Passw0rd!";
    private static final String driver= "com.mysql.jdbc.Driver";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;
    private static Connection conn = null;


    public static void startConnection() {
        System.out.println(jdbcURL);
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(jdbcURL, username, password);

//        conn=(Connection) DriverManager.getConnection(DB_URL,username,password);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection Closed");
        } catch (SQLException e) {
        }
    }
}
