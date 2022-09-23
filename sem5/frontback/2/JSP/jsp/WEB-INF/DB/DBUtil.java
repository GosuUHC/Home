package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    static Connection conn = null;

    public static void dbConnect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "system", "system");
        } catch (SQLException e) {
            System.out.println("FAILED! " + e.getMessage());

        }
    }

    public static void dbDisconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            dbConnect();
            return conn;
        } finally {

        }
    }

    
    
}
