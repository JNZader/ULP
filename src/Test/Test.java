/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Conexion.AlumnoDAO;
import Conexion.MateriaDAO;
import Entidades.Alumno;
import Entidades.Materia;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Test {
    
    public static void main(String[] args) {
      
        //Alumno pedro = new Alumno(8969887, "noriega", "juanjo", LocalDate.of(2012, Month.AUGUST, 28), true);
//        AlumnoDAO alum = new AlumnoDAO();
        
        //alum.guardarAlumno(pedro);
        //alum.modificarAlumno(pedro);
        //alum.eliminarAlumno(3);1
//        Alumno alumEncontrado =alum.buscarAlumno(10);
//        System.out.println("dni "+alumEncontrado.getDni());
//        System.out.println("nombre "+alumEncontrado.getNombre());
//          Alumno alumEncontrado =alum.buscarAlumnoPorDni(189);
//          System.out.println("dni "+alumEncontrado.getDni());
//          System.out.println("nombre "+alumEncontrado.getNombre());

//        AlumnoDAO alu = new AlumnoDAO();
//        
//        for (Alumno alumno : alu.listarAlumnos()) {
//            System.out.println(alumno.toString());
//        }


//test materia

            Materia materia = new Materia(2,1, "fundamentos I", true);
            MateriaDAO mat = new MateriaDAO();
            
            
            
            //mat.guardarMateria(materia);
//            Materia m =mat.BuscarMateria(1);
//            System.out.println("nombre "+m);

            //mat.modificarMateria(materia);
//            for (Materia i : mat.listarMaterias()) {
//                System.out.println(i.toString());
//        }

          mat.eliminarMateria(2);
            
            
            
        
    }
}
