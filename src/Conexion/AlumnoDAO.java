package Conexion;

import Entidades.Alumno;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AlumnoDAO {

    private Connection con;

    public AlumnoDAO() {
        try {
            con = Conexion.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (dni,apellido,nombre,fechaNacimiento,estado) VALUES(?,?,?,?,?)";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno añadido con exito");
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno" + ex.getMessage());
        }

    }

    public Alumno buscarAlumno(int id) {

        Alumno alumno = null;
        String sql = "SELECT dni,apellido,nombre,fechaNacimiento FROM alumno WHERE idAlumno=? AND estado=1";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno");
                rs.close();
                ps.close();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder la tabla alumnos" + ex.getMessage());
        }

        return alumno;
    }

    public Alumno buscarAlumnoPorDni(int dni) {
        Alumno alumno = null;
        String sql = "SELECT dni,apellido,nombre,fechaNacimiento FROM alumno WHERE dni=? AND estado=1";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);

            rs = ps.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt(1));
                alumno.setDni(dni);
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno");
                rs.close();
                ps.close();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder la tabla alumnos" + ex.getMessage());
        }

        return alumno;

    }

    public ArrayList<Alumno> listarAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno WHERE estado=1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Alumno alumno = new Alumno();

                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                alumnos.add(alumno);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno " + ex.getMessage());
        }
        return alumnos;
    }

    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET dni=?,apellido=?,nombre=?,fechaNacimiento=? WHERE idAlumno=? ";

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));

            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno " + ex.getMessage());
        }

    }

    public void eliminarAlumno(int id) {
        String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno=?";
        PreparedStatement ps =null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó el alumno.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no existe");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }

    }
}
