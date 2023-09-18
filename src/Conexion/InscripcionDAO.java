package Conexion;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Entidades.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class InscripcionDAO {

    // Consultas SQL predefinidas
    private final String SQL_SELECT = "SELECT idInscripto, nota, idAlumno, idMateria FROM inscripcion";
    private final String SQL_INSERT = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
    private final String SQL_DELETE = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
    private final String SQL_SELECT_ALUMNOXMATERIA = "SELECT a.* FROM alumno a INNER JOIN inscripcion i ON a.idAlumno = i.idAlumno WHERE i.idMateria = ?";
    private final String SQL_SELECT_MATERIASCURSADAS = "SELECT * FROM inscripcion, materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno = ?";
    private final String SQL_SELECT_MATERIASNOCURSADAS = "SELECT * FROM materia WHERE estado=1 AND idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?)";//"SELECT * FROM inscripcion, materia WHERE inscripcion.idMateria = materia.idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?)";
    private final String SQL_SELECT_INSCRIPCIONESPORALUMNO = "SELECT * FROM inscripcion WHERE idAlumno = ?";
    private MateriaDAO md = new MateriaDAO();
    private AlumnoDAO ad = new AlumnoDAO();
    private Connection con;

    public InscripcionDAO() {
        try {
            con = getConnection(); // Obtiene una conexión a la base de datos
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    // Método para obtener una lista de inscripciones
    public ArrayList<Inscripcion> seleccionar() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion insc = null;
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQL_SELECT); // Prepara la consulta SQL
            rs = ps.executeQuery(); // Ejecuta la consulta y obtiene un conjunto de resultados

            while (rs.next()) {
                insc = new Inscripcion();

                // Obtiene los datos de la inscripción y crea objetos Alumno y Materia asociados
                insc.setIdInscripto(rs.getInt("idInscripto"));
                insc.setNota(rs.getDouble("nota"));
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.BuscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);

                inscripciones.add(insc); // Agrega la inscripción a la lista
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones" + ex.getMessage());
        } finally {
            try {
                close(rs); // Cierra el conjunto de resultados
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo seleccionar" + ex.getMessage());
            }
        }
        return inscripciones; // Retorna la lista de inscripciones
    }

    // Método para insertar una inscripción
    public void insertar(Inscripcion insc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Establece los valores de los parámetros en la sentencia SQL
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            ps.executeUpdate(); // Ejecuta la inserción en la base de datos
            rs = ps.getGeneratedKeys(); // Obtiene las claves generadas automáticamente
            if (rs.next()) {
                insc.setIdInscripto(rs.getInt(1)); // Establece el ID generado en el objeto Inscripcion
                JOptionPane.showMessageDialog(null, "Inscripcion realizada");
            } else {
                JOptionPane.showMessageDialog(null, "Inscripcion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar inscripciones" + ex.getMessage());
        } finally {
            try {
                close(rs);
                close(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo seleccionar" + ex.getMessage());
            }
        }
    }

// Método para actualizar una inscripción existente
    public void actualizar(Inscripcion insc) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL_UPDATE);

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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar inscripciones" + ex.getMessage());
        } finally {
            try {
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo actualizar" + ex.getMessage());
            }
        }
    }

// Método para actualizar la nota de una inscripción por ID de alumno y ID de materia
    public boolean actualizarNota(double nota, int idAlumno, int idMateria) {
        PreparedStatement ps = null;
        boolean boo = false;
        try {
            ps = con.prepareStatement(SQL_UPDATE);

            // Establece la nueva nota en la inscripción identificada por el ID del alumno y la ID de la materia
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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar inscripciones" + ex.getMessage());
        } finally {
            try {
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo actualizar" + ex.getMessage());
            }
        }
        return boo;
    }

// Método para borrar una inscripción por ID de alumno y ID de materia
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL_DELETE);

            // Establece los parámetros en la sentencia SQL para borrar la inscripción
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            int on = ps.executeUpdate(); // Ejecuta la eliminación en la base de datos
            if (on > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "Eliminacion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al borrar inscripciones" + ex.getMessage());
        } finally {
            try {
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones" + ex.getMessage());
            }
        }
    }

// Método para obtener una lista de alumnos inscritos en una materia específica
    public ArrayList<Alumno> obtenerAlumnosXMateria(int idMateria) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Alumno> alumnosXMateria = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQL_SELECT_ALUMNOXMATERIA);
            ps.setInt(1, idMateria);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idAlumno = rs.getInt("idAlumno");
                int dni = rs.getInt("dni");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                boolean estado = rs.getBoolean("estado");
                alumnosXMateria.add(new Alumno(idAlumno, dni, apellido, nombre, fechaNacimiento, estado));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener Alumnos por Materia" + ex.getMessage());
        } finally {
            try {
                close(rs); // Cierra el conjunto de resultados
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerAlumnosXMateria" + ex.getMessage());
            }
        }
        return alumnosXMateria;
    }

// Método para obtener una lista de materias cursadas por un alumno específico
    public ArrayList<Materia> obtenerMateriasCursadas(int idAlumno) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Materia> materias = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQL_SELECT_MATERIASCURSADAS);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                materias.add(new Materia(rs.getInt("idMateria"), rs.getInt("año"), rs.getString("nombre"), rs.getBoolean("estado")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener Materias cursadas" + ex.getMessage());
        } finally {
            try {
                close(rs); // Cierra el conjunto de resultados
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerMateriasCursadas" + ex.getMessage());
            }
        }
        return materias;
    }

// Método para obtener una lista de inscripciones por ID de alumno
    public ArrayList<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion insc = null;
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQL_SELECT_INSCRIPCIONESPORALUMNO);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                insc = new Inscripcion();

                insc.setIdInscripto(rs.getInt("idInscripto"));
                insc.setNota(rs.getInt("nota"));
                Alumno alu = ad.buscarAlumno(idAlumno);
                Materia mat = md.BuscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);

                inscripciones.add(insc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias cursadas" + ex.getMessage());
        } finally {
            try {
                close(rs); // Cierra el conjunto de resultados
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerMateriasCursadas" + ex.getMessage());
            }
        }
        return inscripciones;
    }

// Método para obtener una lista de materias no cursadas por un alumno específico
    public ArrayList<Materia> obtenerMateriasNoCursadas(int idAlumno) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Materia> materias = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQL_SELECT_MATERIASNOCURSADAS);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                materias.add(new Materia(rs.getInt("idMateria"), rs.getInt("año"), rs.getString("nombre"), rs.getBoolean("estado")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias no cursadas" + ex.getMessage());
        } finally {
            try {
                close(rs); // Cierra el conjunto de resultados
                close(ps); // Cierra la sentencia SQL
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerMateriasNoCursadas" + ex.getMessage());
            }
        }
        return materias;
    }

}
