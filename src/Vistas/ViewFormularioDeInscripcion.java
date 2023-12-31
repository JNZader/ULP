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
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ViewFormularioDeInscripcion extends javax.swing.JInternalFrame implements ItemListener {

    DefaultTableModel modelo;
    private InscripcionDAO inscripcionDAO;

    public ViewFormularioDeInscripcion() {
        initComponents();
        this.setResizable(false);
        getContentPane().setBackground(new Color(22, 151, 141));

        ListSelectionModel selectionModel = jTable2.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jButtonAnularInscripcion.setEnabled(false);
        jButtonInscribir.setEnabled(false);

        llenarComboBox();
        inscripcionDAO = new InscripcionDAO();

        jRadioButtonMateriasInscriptas.setEnabled(false);
        jRadioButtonMateriasNoInscriptas.setEnabled(false);

        jComboBoxAlumno.addItemListener(this);

        modelo = (DefaultTableModel) jTable2.getModel();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        // Establece el renderer de la columna 2 (índice 1) para alinear a la derecha
        jTable2.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);

        // Refresca la tabla para aplicar los cambios
        modelo.fireTableDataChanged();
    }

    private void actualizarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);// Mientras haya filas en el modelo de la tabla, elimina la primera
        }
        jTable2.setModel(modelo);// Establece el modelo actualizado de la tabla
    }

    private void llenarTablaMateriasNoInscriptas() {
        // verificamos si se ha seleccionado un alumno en el ComboBox
        Alumno alumno = (Alumno) jComboBoxAlumno.getSelectedItem();
//        String[] cabecera = {"id", "nombre", "año"};//define las columnas de la tabla

        //si no se ha seleccionado un alumno muestra un mensaje de advertencia y sale del metodo
        if (alumno == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno primero.");
            return;
        }

        // Obtenemos el ID del alumno seleccionado.
        int idAlu = alumno.getIdAlumno();
//        modelo.setColumnIdentifiers(cabecera);//establece las columnas de la tabla

        // Utilizamos un DAO para obtener la lista de materias en las que el alumno no está inscrito.
        ArrayList<Materia> obtenerMateriasNoCursadas = inscripcionDAO.obtenerMateriasNoCursadas(idAlu);

        // Verificamos si la lista de materias no cursadas no está vacía y no es nula.
        if (!obtenerMateriasNoCursadas.isEmpty() && obtenerMateriasNoCursadas != null) {
            // Recorremos la lista de materias y agregamos cada materia como una fila en el modelo de tabla.
            for (Materia materia : obtenerMateriasNoCursadas) {
                modelo.addRow(new Object[]{materia.getIdMateria(), materia.getNombre(), materia.getAño()});
            }
        }

        // Establecemos el modelo de la tabla 'jTable2' con el modelo actualizado.
        jTable2.setModel(modelo);
    }

    private void llenarComboBox() {
        AlumnoDAO alum = new AlumnoDAO();
        ArrayList<Alumno> alumnos = alum.listarAlumnos();//obtiene la una lista de todos los alumnos activos de la base de datos

        jComboBoxAlumno.removeAllItems();//limpia todos los elementos del combobox
        jComboBoxAlumno.addItem(null);//agrega un espacio vacio en el primer elemento del combobox
        for (Alumno aux : alumnos) {//itera a traves de la lista y agrega cada alumno al combobox
            jComboBoxAlumno.addItem(aux);
        }
    }

    private void llenarTablaMateriaCursadas() {
//        String[] cabecera = {"id", "nombre", "año"};//define las columnas de la tabla
        Alumno alumno = (Alumno) jComboBoxAlumno.getSelectedItem();//obtiene el alumno seleccionado en el combobox
        if (alumno == null) {//verifica si se selecciono un alumno en el combobox
            JOptionPane.showMessageDialog(this, "Seleccione un alumno primero.");
            return;
        }
        int idAlu = alumno.getIdAlumno();//obtiene el id del alumno seleccionado
//        modelo.setColumnIdentifiers(cabecera);//establece las columnas de la tabla en el modelo
        //usando el id del alumno obtiene las materias cursadas
        ArrayList<Materia> obtenerMateriaCursadas = inscripcionDAO.obtenerMateriasCursadas(idAlu);
        //itera a traves de la lista de materias cursadas y agrega cada una como una fila en la tabla
        for (Materia materia : obtenerMateriaCursadas) {
            modelo.addRow(new Object[]{materia.getIdMateria(), materia.getNombre(), materia.getAño()});
        }
        jTable2.setModel(modelo);// establece el modelo de tabla
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
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setTitle("Formulario de inscripcion - ULP - G73");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulario de Inscripcion");

        jLabel2.setText("Selecciona un Alumno :");

        jComboBoxAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAlumnoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Listado de Materias");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
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
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabel3))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonInscribir)
                        .addGap(85, 85, 85)
                        .addComponent(jButtonAnularInscripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jRadioButtonMateriasInscriptas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButtonMateriasNoInscriptas))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonMateriasNoInscriptas)
                    .addComponent(jRadioButtonMateriasInscriptas))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnularInscripcion)
                    .addComponent(jButtonSalir)
                    .addComponent(jButtonInscribir))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInscribirActionPerformed
        try {
            Inscripcion insc;
            Alumno alumno = (Alumno) jComboBoxAlumno.getSelectedItem();//obtengo el alumno
            if (alumno == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un alumno primero.");
                return;// Salir del método si no hay un alumno seleccionado
            }
            if (alumno != null) {
                int idAlumno = alumno.getIdAlumno();

                int selectedRow = jTable2.getSelectedRow();//obtengo la fila seleccionada

                if (selectedRow >= 0 && alumno != null) {
                    Object columna = jTable2.getValueAt(selectedRow, 0);//obtengo el objeto seleccionado
                    if (columna != null) {
                        int idMateria = (int) columna;//uso el objeto para obtener el id
                        MateriaDAO mat = new MateriaDAO();
                        Materia materia = mat.BuscarMateria(idMateria);
                        insc = new Inscripcion(alumno, materia, 0);
                        inscripcionDAO.insertar(insc);
                        modelo.removeRow(selectedRow);
                        jTable2.setModel(modelo);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "seleccione una fila");
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "seleccione un alumno");
        }
    }//GEN-LAST:event_jButtonInscribirActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jRadioButtonMateriasNoInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMateriasNoInscriptasActionPerformed
        actualizarTabla();
        llenarTablaMateriasNoInscriptas();
        jButtonAnularInscripcion.setEnabled(false);
        jButtonInscribir.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonMateriasNoInscriptasActionPerformed

    private void jRadioButtonMateriasInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMateriasInscriptasActionPerformed
        actualizarTabla();
        llenarTablaMateriaCursadas();
        jButtonAnularInscripcion.setEnabled(true);
        jButtonInscribir.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonMateriasInscriptasActionPerformed

    private void jButtonAnularInscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnularInscripcionActionPerformed
        Alumno alumno = (Alumno) jComboBoxAlumno.getSelectedItem();//obtengo el alumno
        if (alumno == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno primero.");
            return;// Salir del método si no hay un alumno seleccionado
        }
        try {
            if (alumno != null) {
                int idAlumno = alumno.getIdAlumno();

                int selectedRow = jTable2.getSelectedRow();//obtengo la fila seleccionada

                if (selectedRow >= 0 && alumno != null) {
                    Object materia = jTable2.getValueAt(selectedRow, 0);//obtengo el objeto seleccionado
                    if (materia != null) {
                        int idMateria = (int) materia;//uso el objeto para obtener el id
                        inscripcionDAO.borrarInscripcionMateriaAlumno(idAlumno, idMateria);
                        modelo.removeRow(selectedRow);
                        jTable2.setModel(modelo);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "seleccione una fila");
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "seleccione un alumno");
        }
    }//GEN-LAST:event_jButtonAnularInscripcionActionPerformed

    private void jComboBoxAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAlumnoActionPerformed
        if (jComboBoxAlumno.getSelectedIndex() > 0) {
            jRadioButtonMateriasInscriptas.setEnabled(true);
            jRadioButtonMateriasNoInscriptas.setEnabled(true);
            jRadioButtonMateriasInscriptas.setSelected(true);
            jButtonAnularInscripcion.setEnabled(true);
            jButtonInscribir.setEnabled(false);
            actualizarTabla();
            llenarTablaMateriaCursadas();
        } else {
            jRadioButtonMateriasInscriptas.setEnabled(false);
            jRadioButtonMateriasNoInscriptas.setEnabled(false);
            jButtonAnularInscripcion.setEnabled(false);
            jButtonInscribir.setEnabled(false);
            actualizarTabla();
        }
    }//GEN-LAST:event_jComboBoxAlumnoActionPerformed


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
    private javax.swing.JSeparator jSeparator1;
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
                //habilita el botón anular inscripción y deshabilita el botón inscribir
                jButtonAnularInscripcion.setEnabled(true);
                jButtonInscribir.setEnabled(false);
            } else if (jRadioButtonMateriasNoInscriptas.isSelected()) {
                // Actualiza la tabla con materias no inscriptas
                actualizarTabla();
                llenarTablaMateriasNoInscriptas();
                //habilita el botón inscribir y deshabilita el botón anular inscripcion
                jButtonAnularInscripcion.setEnabled(false);
                jButtonInscribir.setEnabled(true);
            }
        }
    }

}
