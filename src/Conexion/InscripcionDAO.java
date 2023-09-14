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
    private static final String SQL_UPDATE = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
    private static final String SQL_DELETE = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
    private static final String SQL_SELECT_ALUMNOXMATERIA = "SELECT a.* FROM alumno a INNER JOIN inscripcion i ON a.idAlumno = i.idAlumno WHERE i.idMateria = ?";
    private static final String SQL_SELECT_MATERIASCURSADAS = "SELECT inscripcion.idMateria, nombre, año FROM inscripcion, materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno = ?";
    private static final String SQL_SELECT_MATERIASNOCURSADAS = "SELECT iidInscripto, nota, idAlumno, idMateria WHERE estado=1 AND idMateria NOT IN SELECT idMateria FROM inscripcion WHERE idAlumno=?";
    private static final String SQL_SELECT_INSCRIPCIONESPORALUMNO = "SELECT * FROM inscripcion WHERE idAlumno = ?";
    private MateriaDAO md = new MateriaDAO();
    private AlumnoDAO ad = new AlumnoDAO();

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
                insc = new Inscripcion();

                insc.setIdInscripto(rs.getInt("idInscripto"));
                insc.setNota(rs.getInt("nota"));
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.BuscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);

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
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);

            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setIdInscripto(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion realizada");
            } else {
                JOptionPane.showMessageDialog(null, "Inscripcion fallida");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al insertar inscripciones" + ex.getMessage());
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

    public void actualizarNota(double nota, int idAlumno, int idMateria) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);

            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
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

    public ArrayList<Alumno> obtenerAlumnosXMateria(int idMateria) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Alumno> alumnosXMateria = new ArrayList<>();
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
//                close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones en metodo obtenerAlumnosXMateria" + ex.getMessage());
            }
        }
        return alumnosXMateria;
    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Materia> materias = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_MATERIASCURSADAS);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                materias.add(new Materia(rs.getInt("idMateria"), rs.getInt("año"), rs.getString("nombre"), rs.getBoolean("estado")));
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
            ps = con.prepareStatement(SQL_SELECT_INSCRIPCIONESPORALUMNO);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                insc = new Inscripcion();

                insc.setIdInscripto(rs.getInt("idInscripto"));
                insc.setNota(rs.getInt("nota"));
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
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

    public List<Materia> obtenerMateriasNoCursadas(int idAlumno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Materia> materias = new ArrayList<>();
        try {
            con = getConnection();
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
