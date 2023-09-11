package Conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mariadb://localhost/uniulp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    private static  Connection conexion;
    
    private Conexion(){}

    public static Connection getConnection() throws SQLException {
        
        if (conexion == null) {

            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "error de driver");
            }

        }
        return conexion;
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(Statement sttm) throws SQLException {
        sttm.close();
    }

    public static void close(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
