package Conexion;

import java.sql.*;
import javax.swing.JOptionPane;

// Clase que gestiona la conexión a la base de datos
public class Conexion {
    private static final String JDBC_URL = "jdbc:mariadb://localhost/uniulp"; // URL de la base de datos
    private static final String JDBC_USER = "root"; // Usuario de la base de datos
    private static final String JDBC_PASSWORD = ""; // Contraseña del usuario
    private static Connection conexion; // Objeto de conexión a la base de datos

    // Constructor privado para evitar instanciar esta clase
    private Conexion() {}

    // Método estático para obtener una conexión a la base de datos
    public static Connection getConnection(){
        if (conexion == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver"); // Carga del controlador de la base de datos
                conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); // Establece la conexión
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "error de driver"); // Mensaje de error si no se encuentra el controlador
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error de conexion en la sentencia sql"); // Mensaje de error si no se encuentra el controlador
            }
        }
        return conexion; // Devuelve la conexión
    }

    // Métodos estáticos para cerrar objetos de base de datos
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
