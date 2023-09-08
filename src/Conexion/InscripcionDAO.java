package Conexion;

import Entidades.Inscripcion;
import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InscripcionDAO {
    
    Connection con;
    MateriaDAO matDAO;
    AlumnoDAO aluDAO;

    public InscripcionDAO() {
    }
    
    public void guardarInscripcion(Inscripcion insc){
    
    }
    
//    public List<Inscripcion> obtenerInscripciones(){}
//    
//    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){}
    
    public List<Materia> obtenerMateriasCursadas(int id){
    ArrayList<Materia> materias=new ArrayList<>();
    
   
    
        try {
             String sql = "SELECT inscripcion.idMateria,nombre,año FROM inscripcion"
            + "JOIN materia ON (inscripcion.idMateria=materia.idMateria)"
            + "AND inscripcion.idAlumno=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            Materia materia;
            
            while (rs.next()) {
                materia = new Materia();
                
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materias.add(materia);
                   
            }
            ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones"+ex.getMessage());
        }
        
        return materias;
    
    }
    
//    public List<Materia> obtenerMateriasNoCursadas(int id){} 
    
    public void borrarInscripcionMateriaAlumno(int idAlumno ,int idMateria){}
    
    public void actualizarNota(int idAlumno,int idMateria,double nota){}
    
//    public List<Alumno> obtenerAlumnosXMateria(int idMateria){}
    
}
