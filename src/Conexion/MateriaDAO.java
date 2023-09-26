package Conexion;

import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MateriaDAO {

    private Connection con;

    public MateriaDAO() {
        con = Conexion.getConnection();
    }

    public void guardarMateria(Materia materia) {
        //Se agrego la sentencia where not exists para que no intente guardar dos materias con el mismo nombre
        String sql = "INSERT INTO materia (año, nombre, estado) SELECT ?, ?, ? WHERE NOT EXISTS (SELECT 2 FROM materia WHERE nombre = ?)";
        if(BuscarMateriaxNombre(materia.getNombre())){
            JOptionPane.showMessageDialog(null, "Imposible guardar la materia por nombre duplicado");
            return;
        }
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Establece los valores de los parámetros en la consulta sql
            ps.setInt(1, materia.getAño());
            ps.setString(2, materia.getNombre());
            ps.setBoolean(3, materia.isEstado());
            ps.setString(4, materia.getNombre());
            int filasAfectadas = ps.executeUpdate();// Ejecuta la consulta y almacena el número de filas afectadas

            if (filasAfectadas == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {//si se inserta la materia correctamente obtenemos la clave generada (id) de la materia
                    if (rs.next()) {
                        materia.setIdMateria(rs.getInt(1));
                        JOptionPane.showMessageDialog(null, "Materia añadida con éxito");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al añadir la materia. No se realizaron cambios en la base de datos");
            }
        } catch (SQLException d) {
            d.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
    }

    public Materia BuscarMateria(int id) {
        Materia materia = null;
        String sql = "SELECT año,nombre FROM materia WHERE idMateria=? AND estado=1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);// establece el valor del parametro ID en la consulta sql

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // si encuentra una materia crea un objeto materia y setea sus atributos
                    materia = new Materia();
                    materia.setIdMateria(id);
                    materia.setAño(rs.getInt("año"));
                    materia.setNombre(rs.getString("nombre"));
                    materia.setEstado(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe la materia");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
        return materia;
    }

    public boolean BuscarMateriaxNombre(String nombre) {
        Materia materia = null;
        String sql = "SELECT * FROM materia WHERE nombre=?";
        boolean boo=false;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);// establece el valor del parametro ID en la consulta sql

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    boo=true;
                    // si encuentra una materia crea un objeto materia y setea sus atributos
                    materia = new Materia();
                    materia.setIdMateria(rs.getInt("idMateria"));
                    materia.setAño(rs.getInt("año"));
                    materia.setNombre(nombre);
                    materia.setEstado(rs.getBoolean("estado"));
                } else {
                    JOptionPane.showMessageDialog(null, "No existe la materia");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
        return boo;
    }
    
    
    public void modificarMateria(Materia materia) {
        String sql = "UPDATE materia SET año=?,nombre=? WHERE idMateria=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, materia.getAño());
            ps.setString(2, materia.getNombre());

            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Modificado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

    }

    public void eliminarMateria(int id) {
        String sql = "UPDATE materia SET estado=0 WHERE idMateria=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);// establece el valor del parametro ID en la consulta sql

            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó la materia");
            } else {
                JOptionPane.showMessageDialog(null, " La materia no existe");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

    }

    public ArrayList<Materia> listarMaterias() {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado=1";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // si se encuentra una materia crea un objeto materia y setea sus atributos
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setAño(rs.getInt("año"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(true);
                materias.add(materia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
        return materias;
    }

}
