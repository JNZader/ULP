package Conexion;

import java.sql.*;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mariadb://localhost/uniulp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    private static Connection connection;
    
    private Conexion(){}
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection==null){
            Class.forName("org.mariadb.jdbc.Driver");
            connection=DriverManager.getConnection(JDBC_URL, JDBC_URL, JDBC_URL);
        }
        return connection;
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
