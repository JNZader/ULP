package Conexion;

import static Conexion.Conexion.getConnection;
import Entidades.Alumno;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class AlumnoDAO {

    private Connection con;

    public AlumnoDAO() {
        con = getConnection();
    }

    // Método para guardar un alumno en la base de datos.
    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (dni,apellido,nombre,fechaNacimiento,estado) VALUES(?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        alumno.setIdAlumno(rs.getInt(1));
                        JOptionPane.showMessageDialog(null, "Alumno añadido con éxito");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al añadir el alumno. No se realizaron cambios en la base de datos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }

    // Método para buscar un alumno por su ID.
    public Alumno buscarAlumno(int id) {
        String sql = "SELECT dni,apellido,nombre,fechaNacimiento FROM alumno WHERE idAlumno=? AND estado=1";
        Alumno alumno = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id); // establece el valor del parametro ID en la consulta sql

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno();
                    alumno.setIdAlumno(id);
                    alumno.setDni(rs.getInt("dni"));
                    alumno.setApellido(rs.getString("apellido"));
                    // convierte la fecha de nacimiento java.sql.Date a LocalDate.
                    alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                    alumno.setEstado(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el alumno");
                    rs.close();
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder la tabla alumnos");
        }
        return alumno;
    }
    // Método para buscar un alumno por su DNI.

    public Alumno buscarAlumnoPorDni(int dni) {
        String sql = "SELECT dni,apellido,nombre,fechaNacimiento FROM alumno WHERE dni=? AND estado=1";
        Alumno alumno = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);//asigna el valor del parametro dni a la consulta sql

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt(1));
                    alumno.setDni(dni);
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setNombre(rs.getString("nombre"));
                    // convierte la fecha de nacimiento java.sql.Date a LocalDate.
                    alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                    alumno.setEstado(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el alumno");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder la tabla alumnos");
        }

        return alumno;
    }

    // Método para listar todos los alumnos activos
    public ArrayList<Alumno> listarAlumnos() {
        String sql = "SELECT * FROM alumno WHERE estado=1";
        ArrayList<Alumno> alumnos = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            // iterar a través de los resultados
            while (rs.next()) {

                Alumno alumno = new Alumno();

                // setea los atributos del alumno
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                alumnos.add(alumno);// Agregar el alumno a la lista
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno ");
        }
        return alumnos;
    }

    // Método para modificar los datos de un alumno
    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET dni=?,apellido=?,nombre=?,fechaNacimiento=? WHERE idAlumno=? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));

            // Verificación del resultado de la ejecución
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no existe");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno ");
        }
    }

    // metodo para eliminar lógicamente un alumno
    public void eliminarAlumno(int id) {
        String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            // Verificación del resultado de la ejecución
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó el alumno.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no existe");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }
    }
}
