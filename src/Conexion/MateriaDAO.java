package Conexion;

import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MateriaDAO {

    private Connection con;

    public MateriaDAO() {
        con = Conexion.getConnection();
    }

    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materia(año,nombre,estado)VALUES(?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, materia.getAño());
            ps.setString(2, materia.getNombre());
            ps.setBoolean(3, materia.isEstado());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia añadida con exito");
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
        }

    }

    public Materia BuscarMateria(int id) {
        Materia materia = null;
        String sql = "SELECT año,nombre FROM materia WHERE idMateria=? AND estado=1";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();

                materia.setIdMateria(id);
                materia.setAño(rs.getInt("año"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(true);

            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
        }
        return materia;
    }

    public void modificarMateria(Materia materia) {
        String sql = "UPDATE materia SET año=?,nombre=? WHERE idMateria=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, materia.getAño());
            ps.setString(2, materia.getNombre());

            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Modificado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
        }

    }

    public void eliminarMateria(int id) {
        String sql = "UPDATE materia SET estado=0 WHERE idMateria=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó la materia");
            } else {
                JOptionPane.showMessageDialog(null, " La materia no existe");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
        }

    }

    public ArrayList<Materia> listarMaterias() {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado=1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();

                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setAño(rs.getInt("año"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(true);
                materias.add(materia);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
        }

        return materias;
    }

}
