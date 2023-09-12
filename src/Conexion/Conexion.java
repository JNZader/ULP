package Conexion;

import java.sql.*;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mariadb://localhost/uniulp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    private static Connection conex;

    private Conexion() throws ClassNotFoundException{
        Class.forName("org.mariadb.jdbc.Driver");
    }
    
    public static Connection getConnection() throws SQLException {
        if(conex==null){
        conex=DriverManager.getConnection(JDBC_URL, JDBC_URL, JDBC_URL);
        }
        return conex;
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
