package Vistas;

import Conexion.*;
import Entidades.Alumno;
import Entidades.Inscripcion;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ViewActualizacionDeNotas extends javax.swing.JInternalFrame {

    DefaultTableModel mod;
    private ArrayList<Inscripcion> inscripciones;
    private int nRow;
    NumericRangeFilter rangeFilter;

    public ViewActualizacionDeNotas() {
        initComponents();
        getContentPane().setBackground(new Color(22, 151, 141));
        this.setResizable(false);

        ListSelectionModel selectionModel = jTAlumnos.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        llenarComboBox();

        mod = (DefaultTableModel) jTAlumnos.getModel();

        rangeFilter = new NumericRangeFilter();
        ((AbstractDocument) jTextFieldNota.getDocument()).setDocumentFilter(rangeFilter);
    }

    private void llenarComboBox() {
        AlumnoDAO alum = new AlumnoDAO();
        ArrayList<Alumno> materias = alum.listarAlumnos();

        jComboBoxAlumno.addItem(null);//para que no llene la tabla al abrir la ventana

        for (Alumno aux : materias) {
            jComboBoxAlumno.addItem(aux);
        }
    }

    private void llenarTabla() {
        //String[] columnas = {"Codigo", "Nombre", "Nota"};
        //mod.setColumnIdentifiers(columnas);
        InscripcionDAO inscripcionDAO = new InscripcionDAO();

        Alumno alumnoSeleccionado = (Alumno) jComboBoxAlumno.getSelectedItem();
        if (alumnoSeleccionado != null) {//para que si se selecciona el objeto null no intente llenar la tabla
            inscripciones = inscripcionDAO.obtenerInscripcionesPorAlumno(alumnoSeleccionado.getIdAlumno());

            String[] filas = new String[3];
            if (inscripciones != null && !inscripciones.isEmpty()) {//para que si se selecciona el objeto null no intente llenar la tabla
                for (Inscripcion inscripcion : inscripciones) {
                    filas[0] = inscripcion.getMateria().getIdMateria() + "";
                    filas[1] = inscripcion.getMateria().getNombre();
                    filas[2] = inscripcion.getNota() + "";

                    mod.addRow(filas);
                }
            }
        }
        jTAlumnos.setModel(mod);
    }

    private void actualizarTabla() {
//        while (mod.getRowCount() > 0) {//elimina la primera fila del modelo hasta que no queden filas
//            mod.removeRow(0);
//        }
//        jTAlumnos.setModel(mod);//establece el modelo vacio
        mod.setRowCount(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxAlumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAlumnos = new javax.swing.JTable();
        jButtonGuardar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldNota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonEditar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(0, 102, 0));
        setClosable(true);
        setTitle("Carga de notas - ULP - G73");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Carga de notas");

        jLabel2.setText("<html><center>Seleccione un alumno<html>");

        jComboBoxAlumno.setMaximumSize(new java.awt.Dimension(255, 25));
        jComboBoxAlumno.setMinimumSize(new java.awt.Dimension(255, 25));
        jComboBoxAlumno.setPreferredSize(new java.awt.Dimension(255, 25));
        jComboBoxAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAlumnoActionPerformed(evt);
            }
        });

        jTAlumnos.setModel(new javax.swing.table.DefaultTableModel(
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
                "Codigo", "Nombre", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTAlumnos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTAlumnos.getTableHeader().setResizingAllowed(false);
        jTAlumnos.getTableHeader().setReorderingAllowed(false);
        jTAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTAlumnosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTAlumnos);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jTextFieldCodigo.setEditable(false);
        jTextFieldCodigo.setText(" ");

        jTextFieldNombre.setEditable(false);
        jTextFieldNombre.setText(" ");

        jTextFieldNota.setText(" ");
        jTextFieldNota.setToolTipText("ingrese nota del 0 al 10");
        jTextFieldNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNotaKeyTyped(evt);
            }
        });

        jLabel3.setText("Codigo");

        jLabel4.setText("Nombre");

        jLabel5.setText("Nota");

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonEditar)
                        .addGap(95, 95, 95)
                        .addComponent(jButtonGuardar)
                        .addGap(92, 92, 92)
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextFieldNota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jBSalir)
                    .addComponent(jButtonEditar))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jComboBoxAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAlumnoActionPerformed
        actualizarTabla();
        llenarTabla();
        jButtonEditar.setEnabled(false);

    }//GEN-LAST:event_jComboBoxAlumnoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        int sRow = jTAlumnos.getSelectedRow();//obtiene el índice de la fila seleccionada y lo guarda en nRow

        if (sRow != -1 && sRow < mod.getRowCount()) {//getRowCount=devuelve el número total de filas en el modelo de la tabla
            // Verifica si las celdas de la fila seleccionada no están vacías
            if (mod.getValueAt(sRow, 0) != null
                    && mod.getValueAt(sRow, 1) != null
                    && mod.getValueAt(sRow, 2) != null) {

                int nnRow = sRow;
                jTextFieldCodigo.setText(mod.getValueAt(nnRow, 0).toString());
                jTextFieldNombre.setText(mod.getValueAt(nnRow, 1).toString());
                jTextFieldNota.setText(mod.getValueAt(nnRow, 2).toString());
            } else {
                JOptionPane.showMessageDialog(null, "La fila seleccionada no contiene información completa", "ERROR AL MODIFICAR EL REGISTRO", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla", "ERROR AL MODIFICAR REGISTRO", JOptionPane.WARNING_MESSAGE);
        }
        jComboBoxAlumno.setEnabled(false);
        jTAlumnos.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        InscripcionDAO act = new InscripcionDAO();
        boolean aux;
        try {
            Alumno alumnoSeleccionado = (Alumno) jComboBoxAlumno.getSelectedItem();//obtiene el alumno seleccionado en el combobox
            // Verifica que la tabla tenga datos
            if (nRow >= 0 && mod.getRowCount() > 0) {
                //actualiza la nota usando
                aux = act.actualizarNota(Double.parseDouble(jTextFieldNota.getText()), alumnoSeleccionado.getIdAlumno(), Integer.parseInt(jTextFieldCodigo.getText()));

                if (aux) {
                    mod.setValueAt(jTextFieldCodigo.getText().trim(), nRow, 0);//trim elimina los espacios vacios al principio y al final del texto
                    mod.setValueAt(jTextFieldNombre.getText().trim(), nRow, 1);//actualiza la tabla con los valores delos textfield
                    mod.setValueAt(jTextFieldNota.getText().trim(), nRow, 2);
                }
            } else {
                JOptionPane.showMessageDialog(this, "La tabla está vacía o no se ha seleccionado una fila.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debes ingresar datos validos");
        }
        jComboBoxAlumno.setEnabled(true);
        jTAlumnos.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonEditar.setEnabled(true);
        jTextFieldCodigo.setText("");
        jTextFieldNombre.setText("");
        ((AbstractDocument) jTextFieldNota.getDocument()).setDocumentFilter(null);
        jTextFieldNota.setText("");
        ((AbstractDocument) jTextFieldNota.getDocument()).setDocumentFilter(rangeFilter);
        actualizarTabla();
        llenarTabla();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jTAlumnosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTAlumnosMouseReleased
        int sRow = jTAlumnos.getSelectedRow();//obtiene el índice de la fila seleccionada y lo guarda en nRow

        if (sRow != -1 && sRow < mod.getRowCount()) {//getRowCount=devuelve el número total de filas en el modelo de la tabla
            jButtonEditar.setEnabled(true);
        }
        if (jButtonGuardar.isEnabled()) {
            jButtonEditar.setEnabled(false);
        }
    }//GEN-LAST:event_jTAlumnosMouseReleased

    private void jTextFieldNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNotaKeyTyped
        if(jTextFieldNota.getText().length()>3){
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldNotaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JComboBox<Alumno> jComboBoxAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTAlumnos;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNota;
    // End of variables declaration//GEN-END:variables

}

class NumericRangeFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());//obtiene el texto actual del jtf

        String nextText = currentText.substring(0, i) + string + currentText.substring(i + i1);//concatena el texto a insertar con el texto acutal

        try {
            double num = Double.parseDouble(nextText);//intenta convertir el texto en numero

            if (num >= 0.0 && num <= 10.0) {//verifica si el numero esta en el rango de 0.0 a 10.0
                super.replace(fb, i, i1, string, as);
            } else {
                //fuera de rango
                Toolkit.getDefaultToolkit().beep();//sonido de error
            }
        } catch (NumberFormatException e) {
            Toolkit.getDefaultToolkit().beep(); //El texto no es un número válido...Emite un sonido de error.
        }
    }
}
