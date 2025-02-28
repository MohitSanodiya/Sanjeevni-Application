package sanjeevaniapp.dbutil;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    private static Connection conn = null;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//ROCK-STAR:1521/XE", "project1", "java");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Cannot load driver:" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DB Error:" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                JOptionPane.showMessageDialog(null, "Disconnected Succesfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "DB Error:" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}