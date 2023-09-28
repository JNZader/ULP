package Conexion;

import static Conexion.Conexion.getConnection;
import Entidades.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class InscripcionDAO {

    // Consultas SQL predefinidas
    private MateriaDAO md = new MateriaDAO();
    private AlumnoDAO ad = new AlumnoDAO();
    private Connection con;

    public InscripcionDAO() {
        con = getConnection();
    }

    // Método para obtener una lista de inscripciones
    public ArrayList<Inscripcion> seleccionar() {
        String SQL_SELECT = "SELECT idInscripto, nota, idAlumno, idMateria FROM inscripcion";
        Inscripcion insc = null;
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("idInscripto"));
                insc.setNota(rs.getDouble("nota"));
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.BuscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);

                inscripciones.add(insc); // agrega la inscripción a la lista
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones");
        }
        return inscripciones; // retorna la lista de inscripciones
    }

// Método para insertar una inscripción
    public void insertar(Inscripcion insc) {
        String SQL_INSERT = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            ps.executeUpdate(); // ejecuta la inserción en la base de datos
            try (ResultSet rs = ps.getGeneratedKeys()) { // obtiene las claves generadas automáticamente
                if (rs.next()) {
                    insc.setIdInscripto(rs.getInt(1)); // establece el ID generado en el objeto Inscripcion
                    JOptionPane.showMessageDialog(null, "Inscripcion realizada");
                } else {
                    JOptionPane.showMessageDialog(null, "Inscripcion fallida");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al insertar inscripciones");
        }
    }

// Método para actualizar una inscripción existente
    public void actualizar(Inscripcion insc) {
        String SQL_UPDATE = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";

        try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            // Establece la nueva nota en la inscripción identificada por el ID del alumno y la ID de la materia
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getIdInscripto());
            int on = ps.executeUpdate(); // Ejecuta la actualización en la base de datos
            if (on > 0) {
                JOptionPane.showMessageDialog(null, "Actualizacion realizada");
            } else {
                JOptionPane.showMessageDialog(null, "Actualizacion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al actualizar inscripciones");
        }
    }

// Método para actualizar la nota de una inscripción por ID de alumno y ID de materia
    public boolean actualizarNota(double nota, int idAlumno, int idMateria) {
        String SQL_UPDATE = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
        boolean boo = false;

        try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            // establece la nueva nota en la inscripción identificada por el idAlumno y el idMateria
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int on = ps.executeUpdate(); // Ejecuta la actualización en la base de datos
            if (on > 0) {
                JOptionPane.showMessageDialog(null, "Actualizacion realizada");
                boo = true;
            } else {
                JOptionPane.showMessageDialog(null, "Actualizacion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al actualizar inscripciones");
        }
        return boo;
    }

// Método para borrar una inscripción por ID de alumno y ID de materia
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        String SQL_DELETE = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";

        try (PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            // establece los parámetros en la sentencia sql para borrar la inscripción
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            int on = ps.executeUpdate(); // ejecuta la eliminación en la base de datos
            if (on > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "Eliminacion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al borrar inscripciones");
        }
    }

// Método para obtener una lista de alumnos inscritos en una materia específica
    public ArrayList<Alumno> obtenerAlumnosXMateria(int idMateria) {
        String SQL_SELECT_ALUMNOXMATERIA = "SELECT a.* FROM alumno a INNER JOIN inscripcion i ON a.idAlumno = i.idAlumno WHERE i.idMateria = ?";
        ArrayList<Alumno> alumnosXMateria = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALUMNOXMATERIA)) {
            ps.setInt(1, idMateria);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) { //Agrega alumnos al arraylist mientras haya resultados
                    int idAlumno = rs.getInt("idAlumno");
                    int dni = rs.getInt("dni");
                    String apellido = rs.getString("apellido");
                    String nombre = rs.getString("nombre");
                    LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                    boolean estado = rs.getBoolean("estado");
                    alumnosXMateria.add(new Alumno(idAlumno, dni, apellido, nombre, fechaNacimiento, estado));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al obtener Alumnos por Materia");
        }
        return alumnosXMateria;
    }

// Método para obtener una lista de materias cursadas por un alumno específico
    public ArrayList<Materia> obtenerMateriasCursadas(int idAlumno) {
        String SQL_SELECT_MATERIASCURSADAS = "SELECT materia.*FROM materia INNER JOIN inscripcion ON materia.idMateria = inscripcion.idMateria WHERE inscripcion.idAlumno = ? AND materia.estado = 1;";
        ArrayList<Materia> materias = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_MATERIASCURSADAS)) {
            ps.setInt(1, idAlumno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    materias.add(new Materia(rs.getInt("idMateria"), rs.getInt("año"), rs.getString("nombre"), rs.getBoolean("estado")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias cursadas");
        }catch(NullPointerException e){
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error, no cursa ninguna materia");            
        }
        return materias;
    }
    // Método para obtener una lista de inscripciones por ID de alumno

    public ArrayList<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        String SQL_SELECT_INSCRIPCIONESPORALUMNO = "SELECT * FROM inscripcion INNER JOIN materia ON materia.idMateria = inscripcion.idMateria WHERE inscripcion.idAlumno = ? AND materia.estado = 1;";
        Inscripcion insc = null;
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_INSCRIPCIONESPORALUMNO)) {
            ps.setInt(1, idAlumno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    insc = new Inscripcion();

                    insc.setIdInscripto(rs.getInt("idInscripto"));
                    insc.setNota(rs.getDouble("nota"));
                    Alumno alu = ad.buscarAlumno(idAlumno);
                    Materia mat = md.BuscarMateria(rs.getInt("idMateria"));
                    insc.setAlumno(alu);
                    insc.setMateria(mat);

                    inscripciones.add(insc);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias cursadas");
        }catch(NullPointerException e){
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error, no cursa ninguna materia");            
        }
        return inscripciones;
    }
    // Método para obtener una lista de materias no cursadas por un alumno específico

    public ArrayList<Materia> obtenerMateriasNoCursadas(int idAlumno) {
        String SQL_SELECT_MATERIASNOCURSADAS = "SELECT * FROM materia WHERE estado=1 AND idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?)";//"SELECT * FROM inscripcion, materia WHERE inscripcion.idMateria = materia.idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?)";
        ArrayList<Materia> materias = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_MATERIASNOCURSADAS)) {
            ps.setInt(1, idAlumno);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    materias.add(new Materia(rs.getInt("idMateria"), rs.getInt("año"), rs.getString("nombre"), rs.getBoolean("estado")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias no cursadas");
        }
        return materias;
    }

}
