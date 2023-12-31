package Vistas;

import Conexion.InscripcionDAO;
import Conexion.MateriaDAO;
import Entidades.Alumno;
import Entidades.Materia;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ViewConsultDeAlumnosPorMateria extends javax.swing.JInternalFrame {

    DefaultTableModel mod;

    public ViewConsultDeAlumnosPorMateria() {
        initComponents();
        getContentPane().setBackground(new Color(22,151,141));
        this.setResizable(false);

        ListSelectionModel selectionModel = tabla.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        llenarComboBox();
        mod = (DefaultTableModel) tabla.getModel();
    }

    private void llenarComboBox() {
        MateriaDAO mat = new MateriaDAO();
        ArrayList<Materia> materias = mat.listarMaterias();//obtiene la una lista de materias activas de la base de datos
        jComboBoxMaterias.addItem(null);//agrega un espacio vacio en el primer elemento del combobox
        for (Materia aux : materias) {//itera a traves de la lista y agrega cada materia al combobox
            jComboBoxMaterias.addItem(aux);
        }
    }

    private void llenarTabla() {
        InscripcionDAO ins = new InscripcionDAO();

//        String[] columnas = {"ID", "DNI", "Apellido", "Nombre"};
//        // obtiene la materia seleccionada en el combobox
        Materia materiaSeleccionada = (Materia) jComboBoxMaterias.getSelectedItem();

        if (materiaSeleccionada != null) {
            // obtiene la lista de alumnos inscritos en la materia seleccionada
            ArrayList<Alumno> alumnosXMateria = ins.obtenerAlumnosXMateria(materiaSeleccionada.getIdMateria());

//            mod.setColumnIdentifiers(columnas);//establece las columnas en el modelo de la tabla
            String[] filas = new String[4];//arreglo para guardar los datos de cada fila
            if (alumnosXMateria.size() > 0) {
                for (Alumno aux : alumnosXMateria) {
                    //llena el arreglo con los datos del alumno
                    filas[0] = aux.getIdAlumno() + "";
                    filas[1] = aux.getDni() + "";
                    filas[2] = aux.getApellido() + "";
                    filas[3] = aux.getNombre() + "";
                    mod.addRow(filas);//agrega la fila al modelo de la tabla
                }
            }
        }
        tabla.setModel(mod);//establece el modelo de datos en la tabla
    }

    private void actualizarTabla() {
        while (mod.getRowCount() > 0) {
            mod.removeRow(0);   //elimina la primera fila del modelo hasta que no queden filas
        }
        tabla.setModel(mod);//establece el modelo vacio
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        seleccione = new javax.swing.JLabel();
        jComboBoxMaterias = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        salir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(0, 102, 0));
        setClosable(true);
        setTitle("Listado de alumnos por materia - ULP - G73");

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Listado de Alumnos por Materia");

        seleccione.setText("Seleccione una materia");

        jComboBoxMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMateriasActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "DNI", "Nombre", "Apellido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(salir)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(seleccione)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccione))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(salir)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void jComboBoxMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMateriasActionPerformed
        actualizarTabla();
        llenarTabla();
    }//GEN-LAST:event_jComboBoxMateriasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Materia> jComboBoxMaterias;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton salir;
    private javax.swing.JLabel seleccione;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
