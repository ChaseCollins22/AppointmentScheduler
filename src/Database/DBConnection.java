package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost:3306/";
    private static final String dbName = "client_schedule";

    private static final String username= "root";
    private static final String password= "Scea9919";
    private static final String driver= "com.mysql.jdbc.Driver";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;
    private static Connection conn;


    public static void startConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
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
        } catch (SQLException e) {
        }
    }
}
