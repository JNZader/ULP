package Vistas;

import static Conexion.Conexion.getConnection;
import java.awt.Dimension;
import java.sql.Connection;

public class menuPrincipal extends javax.swing.JFrame {

    Connection con;

    public menuPrincipal() {
        con=getConnection();

        if (con == null) {
            System.exit(0);
        }

        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMFormularioAlumno = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMFormuMateria = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemManejoInscripciones = new javax.swing.JMenuItem();
        jMenuItemManipulacionNotas = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemAlumnosMateria = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        jMFormularioAlumno.setText("Alumno");

        jMenuItem1.setText("Formulario Alumno");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMFormularioAlumno.add(jMenuItem1);

        jMenuBar1.add(jMFormularioAlumno);

        jMenu2.setText("Materia");

        jMFormuMateria.setText("Formulario Materia");
        jMFormuMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMFormuMateriaActionPerformed(evt);
            }
        });
        jMenu2.add(jMFormuMateria);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Administracion");

        jMenuItemManejoInscripciones.setText("Manejo de Inscripciones");
        jMenuItemManejoInscripciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemManejoInscripcionesActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemManejoInscripciones);

        jMenuItemManipulacionNotas.setText("Manipulacion de Notas");
        jMenuItemManipulacionNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemManipulacionNotasActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemManipulacionNotas);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Consultas");

        jMenuItemAlumnosMateria.setText("Alumnos por Materia");
        jMenuItemAlumnosMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAlumnosMateriaActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemAlumnosMateria);

        jMenuBar1.add(jMenu4);

        jMenuSalir.setText("Salir");
        jMenuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        escritorio.removeAll();
        escritorio.repaint();
        ViewGestionDeAlumnos ga = new ViewGestionDeAlumnos();
        ga.setVisible(true);
        escritorio.add(ga);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = ga.getSize();
        ga.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        escritorio.moveToFront(ga);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMFormuMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMFormuMateriaActionPerformed
        escritorio.removeAll();
        escritorio.repaint();
        ViewGestionMaterias ma = new ViewGestionMaterias();
        ma.setVisible(true);
        escritorio.add(ma);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = ma.getSize();
        ma.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        escritorio.moveToFront(ma);
    }//GEN-LAST:event_jMFormuMateriaActionPerformed

    private void jMenuItemManejoInscripcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemManejoInscripcionesActionPerformed
        escritorio.removeAll();
        escritorio.repaint();
        ViewFormularioDeInscripcion insc = new ViewFormularioDeInscripcion();
        insc.setVisible(true);
        escritorio.add(insc);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = insc.getSize();
        insc.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        escritorio.moveToFront(insc);
    }//GEN-LAST:event_jMenuItemManejoInscripcionesActionPerformed

    private void jMenuItemAlumnosMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAlumnosMateriaActionPerformed
        escritorio.removeAll();
        escritorio.repaint();
        ViewConsultDeAlumnosPorMateria alum = new ViewConsultDeAlumnosPorMateria();
        alum.setVisible(true);
        escritorio.add(alum);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = alum.getSize();
        alum.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        escritorio.moveToFront(alum);
    }//GEN-LAST:event_jMenuItemAlumnosMateriaActionPerformed

    private void jMenuItemManipulacionNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemManipulacionNotasActionPerformed
        escritorio.removeAll();
        escritorio.repaint();
        ViewActualizacionDeNotas not = new ViewActualizacionDeNotas();
        not.setVisible(true);
        escritorio.add(not);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = not.getSize();
        not.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        escritorio.moveToFront(not);
    }//GEN-LAST:event_jMenuItemManipulacionNotasActionPerformed

    private void jMenuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jMenuSalirMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem jMFormuMateria;
    private javax.swing.JMenu jMFormularioAlumno;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAlumnosMateria;
    private javax.swing.JMenuItem jMenuItemManejoInscripciones;
    private javax.swing.JMenuItem jMenuItemManipulacionNotas;
    private javax.swing.JMenu jMenuSalir;
    // End of variables declaration//GEN-END:variables
}
