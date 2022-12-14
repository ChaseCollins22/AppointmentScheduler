package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class establishes a connection to the database.
 */
public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost:3306/";
    private static final String dbName = "client_schedule";

    private static final String username= "sqlUser";
    private static final String password= "Passw0rd!";
    private static final String driver= "com.mysql.jdbc.Driver";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;
    private static Connection conn;

    /**
     * this function opens the connection to the database.
     */
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

    /**
     * This function returns the connection.
     * @return The connection value.
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * This function closes the connection to the database.
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
        }
    }
}
