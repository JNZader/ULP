/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Conexion.AlumnoDAO;
import Conexion.InscripcionDAO;
import Conexion.MateriaDAO;
import Entidades.Alumno;
import Entidades.Materia;
import Entidades.Inscripcion;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author IvanMoreno
 */
public class formularioDeInscripcion extends javax.swing.JInternalFrame implements ItemListener {

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }

    };

    private InscripcionDAO inscripcionDAO;

    /**
     * Creates new form formularioDeInscripcion
     */
    public formularioDeInscripcion() {
        initComponents();
        llenarComboBox();
        getContentPane().setBackground(new Color(75,141,88));
        inscripcionDAO = new InscripcionDAO();
        jRadioButtonMateriasInscriptas.setSelected(true);
        jComboBoxAlumno.addItemListener(this);
    }

    private void actualizarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        jTable2.setModel(modelo);
    }

    private void llenarTablaMateriasNoInscriptas() {
        String[] cabecera = {"id", "nombre", "año"};
        Alumno alumno = (Alumno) jComboBoxAlumno.getSelectedItem();
        //Alumno alu = (Alumno) alumno;
        int idAlu = alumno.getIdAlumno();
        modelo.setColumnIdentifiers(cabecera);
        //List<Materia> materias = new ArrayList<>();
        List<Materia> obtenerMateriasNoCursadas = inscripcionDAO.obtenerMateriasNoCursadas(idAlu);

        for (Materia materia : obtenerMateriasNoCursadas) {
            modelo.addRow(new Object[]{materia.getIdMateria(), materia.getNombre(), materia.getAño()});

        }
        jTable2.setModel(modelo);

    }

    private void llenarComboBox() {
        AlumnoDAO alum = new AlumnoDAO();
        ArrayList<Alumno> alumnos = alum.listarAlumnos();

        jComboBoxAlumno.removeAllItems();

        for (Alumno aux : alumnos) {
            jComboBoxAlumno.addItem(aux);

        }
    }

    private void llenarTablaMateriaCursadas() {
        String[] cabecera = {"id", "nombre", "año"};
        Alumno alumno = (Alumno) jComboBoxAlumno.getSelectedItem();
        int idAlu = alumno.getIdAlumno();
        modelo.setColumnIdentifiers(cabecera);
        List<Materia> obtenerMateriaCursadas = inscripcionDAO.obtenerMateriasCursadas(idAlu);

        for (Materia materia : obtenerMateriaCursadas) {
            modelo.addRow(new Object[]{materia.getIdMateria(), materia.getNombre(), materia.getAño()});

        }
        jTable2.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxAlumno = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButtonInscribir = new javax.swing.JButton();
        jButtonAnularInscripcion = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jRadioButtonMateriasNoInscriptas = new javax.swing.JRadioButton();
        jRadioButtonMateriasInscriptas = new javax.swing.JRadioButton();

        jLabel1.setText("Formulario de Inscripcion");

        jLabel2.setText("Selecciona un Alumno :");

        jComboBoxAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAlumnoActionPerformed(evt);
            }
        });

        jLabel3.setText("Listado de Materias");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Año"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setName(""); // NOI18N
        jScrollPane2.setViewportView(jTable2);

        jButtonInscribir.setText("Inscribir");
        jButtonInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInscribirActionPerformed(evt);
            }
        });

        jButtonAnularInscripcion.setText("Anular Inscripcion");
        jButtonAnularInscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnularInscripcionActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonMateriasNoInscriptas);
        jRadioButtonMateriasNoInscriptas.setText("Materias no Inscriptas");
        jRadioButtonMateriasNoInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMateriasNoInscriptasActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonMateriasInscriptas);
        jRadioButtonMateriasInscriptas.setText("Materias Inscriptas");
        jRadioButtonMateriasInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMateriasInscriptasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel2))
                                    .addComponent(jRadioButtonMateriasInscriptas))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioButtonMateriasNoInscriptas))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonInscribir)
                        .addGap(85, 85, 85)
                        .addComponent(jButtonAnularInscripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonMateriasNoInscriptas)
                    .addComponent(jRadioButtonMateriasInscriptas))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnularInscripcion)
                    .addComponent(jButtonSalir)
                    .addComponent(jButtonInscribir))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInscribirActionPerformed
        // TODO add your handling code here:
        Inscripcion insc;
         Alumno alumno =(Alumno) jComboBoxAlumno.getSelectedItem();//obtengo el alumno
        int idAlumno = alumno.getIdAlumno();
        
        int selectedRow = jTable2.getSelectedRow();//obtengo la fila seleccionada
        
        if (selectedRow>=0) {
            Object columna =  jTable2.getValueAt(selectedRow, 0);//obtengo el objeto seleccionado
            if (columna!=null) {
                int idMateria = (int) columna;//uso el objeto para obtener el id
                MateriaDAO mat = new MateriaDAO();
                Materia materia=mat.BuscarMateria(idMateria);
                insc = new Inscripcion(alumno, materia, 0);
                inscripcionDAO.insertar(insc);
                modelo.removeRow(selectedRow);
                jTable2.setModel(modelo);
                
                
            }
        }else{
            JOptionPane.showMessageDialog(this, "seleccione una fila");
        }
    }//GEN-LAST:event_jButtonInscribirActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jRadioButtonMateriasNoInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMateriasNoInscriptasActionPerformed
        // TODO add your handling code here:
        actualizarTabla();
        llenarTablaMateriasNoInscriptas();
        jButtonAnularInscripcion.setEnabled(false);
        jButtonInscribir.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonMateriasNoInscriptasActionPerformed

    private void jComboBoxAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAlumnoActionPerformed

    private void jRadioButtonMateriasInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMateriasInscriptasActionPerformed
        // TODO add your handling code here:
        actualizarTabla();
        llenarTablaMateriaCursadas();
        jButtonAnularInscripcion.setEnabled(true);
        jButtonInscribir.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonMateriasInscriptasActionPerformed

    private void jButtonAnularInscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnularInscripcionActionPerformed
        // TODO add your handling code here:
        Alumno alumno =(Alumno) jComboBoxAlumno.getSelectedItem();//obtengo el alumno
        int idAlumno = alumno.getIdAlumno();
        
        int selectedRow = jTable2.getSelectedRow();//obtengo la fila seleccionada
        
        if (selectedRow>=0) {
            Object materia =  jTable2.getValueAt(selectedRow, 0);//obtengo el objeto seleccionado
            if (materia!=null) {
                int idMateria = (int) materia;//uso el objeto para obtener el id
                inscripcionDAO.borrarInscripcionMateriaAlumno(idAlumno,idMateria );
                modelo.removeRow(selectedRow);
                jTable2.setModel(modelo);
            }
        }else{
            JOptionPane.showMessageDialog(this, "seleccione una fila");
        }
        
        
        
        
        
    }//GEN-LAST:event_jButtonAnularInscripcionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAnularInscripcion;
    private javax.swing.JButton jButtonInscribir;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<Alumno> jComboBoxAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButtonMateriasInscriptas;
    private javax.swing.JRadioButton jRadioButtonMateriasNoInscriptas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Verifica cuál de los dos JRadioButton está seleccionado
            if (jRadioButtonMateriasInscriptas.isSelected()) {
                // Actualiza la tabla con materias inscriptas
                actualizarTabla();
                llenarTablaMateriaCursadas();
                jButtonAnularInscripcion.setEnabled(true);
                jButtonInscribir.setEnabled(false);
            } else if (jRadioButtonMateriasNoInscriptas.isSelected()) {
                // Actualiza la tabla con materias no inscriptas
                actualizarTabla();
                llenarTablaMateriasNoInscriptas();
                jButtonAnularInscripcion.setEnabled(false);
                jButtonInscribir.setEnabled(true);
            }
        }
    }

}
