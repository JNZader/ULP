package Conexion;

import static Conexion.Conexion.*;
import Entidades.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionDAO {

    private static final String SQL_SELECT = "SELECT idInscripto, nota, idAlumno, idMateria FROM inscripcion";
    private static final String SQL_INSERT = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE inscripcion SET nota = ? WHERE idInscripto = ?";
    private static final String SQL_DELETE = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
    private static final String SQL_SELECT_ALUMNOXMATERIA = "SELECT a.* FROM alumno a INNER JOIN inscripcion i ON a.idAlumno = i.idAlumno WHERE i.idMateria = ?";
    private static final String SQL_SELECT_MATERIASCURSADAS = "SELECT DISTINCT m.* FROM materia m INNER JOIN inscripcion i ON m.idMateria = i.idMateria";
    private static final String SQL_SELECT_MATERIASNOCURSADAS = "SELECT * FROM materia WHERE idMateria NOT IN (SELECT DISTINCT idMateria FROM inscripcion)";
    private static final String SQL_SELECT_INSCRIPCIONESPORALUMNO = "SELECT * FROM inscripcion WHERE idAlumno = ?";

    public InscripcionDAO() {
    }

    public List<Inscripcion> seleccionar() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion insc = null;
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idInscripto = rs.getInt("idInscripto");
                int nota = rs.getInt("nota");
                int idAlumno = rs.getInt("idAlumno");
                int idMateria = rs.getInt("idMateria");

                insc = new Inscripcion(idInscripto, nota, idAlumno, idMateria);

                inscripciones.add(insc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones" + ex.getMessage());
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo seleccionar" + ex.getMessage());
            }
        }
        return inscripciones;
    }

    public void insertar(Inscripcion insc) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);

            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getIdAlumno());
            ps.setInt(3, insc.getIdMateria());
            int on = ps.executeUpdate();
            if (on > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion realizada");
            } else {
                JOptionPane.showMessageDialog(null, "Inscripcion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al insertar inscripciones" + ex.getMessage());
        } finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo seleccionar" + ex.getMessage());
            }
        }
    }

    public void actualizar(Inscripcion insc) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);

            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getIdInscripto());
            int on = ps.executeUpdate();
            if (on > 0) {
                JOptionPane.showMessageDialog(null, "Actualizacion realizada");
            } else {
                JOptionPane.showMessageDialog(null, "Actualizacion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al actualizar inscripciones" + ex.getMessage());
        } finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo actualizar" + ex.getMessage());
            }
        }
    }

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);

            ps.setDouble(1, idAlumno);
            ps.setInt(2, idMateria);
            int on = ps.executeUpdate();
            if (on > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "Eliminacion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al borrar inscripciones" + ex.getMessage());
        } finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones" + ex.getMessage());
            }
        }
    }

    public List<Alumno> obtenerAlumnosXMateria(int idMateria) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion insc = null;
        List<Alumno> alumnosXMateria = new ArrayList<>();
        try {
            con = getConnection();
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
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al obtener Alumnos por Materia" + ex.getMessage());
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerAlumnosXMateria" + ex.getMessage());
            }
        }
        return alumnosXMateria;
    }

    public List<Materia> obtenerMateriasCursadas() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion insc = null;
        List<Materia> materias = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_MATERIASCURSADAS);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idMateria = rs.getInt("idMateria");
                String nombre = rs.getString("nombre");
                int año = rs.getInt("año");
                boolean estado = rs.getBoolean("estado");
                materias.add(new Materia(idMateria, año, nombre, estado));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias cursadas" + ex.getMessage());
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerMateriasCursadas" + ex.getMessage());
            }
        }
        return materias;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion insc = null;
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_MATERIASCURSADAS);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idInscripto = rs.getInt("idInscripto");
                double nota = rs.getDouble("nota");
                int idMateria = rs.getInt("idMateria");
                inscripciones.add(new Inscripcion(idInscripto, idAlumno, idMateria, nota));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias cursadas" + ex.getMessage());
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerMateriasCursadas" + ex.getMessage());
            }
        }
        return inscripciones;
    }

    public List<Materia> obtenerMateriasNoCursadas() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion insc = null;
        List<Materia> materias = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_MATERIASNOCURSADAS);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idMateria = rs.getInt("idMateria");
                String nombre = rs.getString("nombre");
                int año = rs.getInt("año");
                boolean estado = rs.getBoolean("estado");
                materias.add(new Materia(idMateria, año, nombre, estado));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al obtener Materias no cursadas" + ex.getMessage());
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerMateriasNoCursadas" + ex.getMessage());
            }
        }
        return materias;
    }

}
