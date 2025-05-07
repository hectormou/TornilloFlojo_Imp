/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.JefeTaller;
import aplicacion.Mecanico;
import aplicacion.Vehiculo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author basesdatos
 */
public class VPrincipal_1 extends javax.swing.JFrame {
  
    private final aplicacion.FachadaAplicacion fa;
    private Mecanico mecanicoAplicacion;
    private Vehiculo vehiculoSeleccionado;
    
    /** Creates new form VPrincipal */
    public VPrincipal_1(aplicacion.FachadaAplicacion fa) {
        this.fa=fa;
        initComponents();
        
        inicializarBotones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        matriculaTextField = new javax.swing.JTextField();
        etiquetaTitulo = new javax.swing.JLabel();
        marcaTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        modeloTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVehiculos = new javax.swing.JTable();
        buscarBoton = new javax.swing.JButton();
        salirBoton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        clienteTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combustibleTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        supervisorTextField = new javax.swing.JTextField();
        anhadirVehiculoBoton = new javax.swing.JButton();
        modificarVehiculoBoton = new javax.swing.JButton();
        eliminarVehiculoBoton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuGestion = new javax.swing.JMenu();
        menuEmpleados = new javax.swing.JMenuItem();
        menuSolicitudes = new javax.swing.JMenuItem();
        menuPrincipal = new javax.swing.JMenu();
        menuPersonal = new javax.swing.JMenuItem();
        menuStock = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taller");
        setName("vPrincipal"); // NOI18N
        setResizable(false);

        matriculaTextField.setToolTipText("Titulo a buscar");

        etiquetaTitulo.setText("Matrícula:");

        jLabel1.setText("Marca:");

        jLabel2.setText("Modelo:");

        tablaVehiculos.setModel(new gui.ModeloTablaVehiculos());
        tablaVehiculos.setColumnSelectionAllowed(true);
        tablaVehiculos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaVehiculos.getTableHeader().setReorderingAllowed(false);
        tablaVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVehiculosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVehiculos);
        tablaVehiculos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        buscarBoton.setText("Buscar");
        buscarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBotonActionPerformed(evt);
            }
        });

        salirBoton.setText("Salir");
        salirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBotonActionPerformed(evt);
            }
        });

        jLabel3.setText("Cliente:");

        jLabel4.setText("Combustible:");

        jLabel5.setText("Supervisor:");

        anhadirVehiculoBoton.setText("Añadir");
        anhadirVehiculoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anhadirVehiculoBotonActionPerformed(evt);
            }
        });

        modificarVehiculoBoton.setText("Modificar");
        modificarVehiculoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarVehiculoBotonActionPerformed(evt);
            }
        });

        eliminarVehiculoBoton.setText("Eliminar");
        eliminarVehiculoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarVehiculoBotonActionPerformed(evt);
            }
        });

        menuGestion.setText("Gestión");

        menuEmpleados.setText("Empleados");
        menuEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmpleadosActionPerformed(evt);
            }
        });
        menuGestion.add(menuEmpleados);

        menuSolicitudes.setText("Solicitudes");
        menuSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSolicitudesActionPerformed(evt);
            }
        });
        menuGestion.add(menuSolicitudes);

        jMenuBar1.add(menuGestion);

        menuPrincipal.setText("Principal");

        menuPersonal.setText("Personal");
        menuPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPersonalActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuPersonal);

        menuStock.setText("Stock");
        menuStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStockActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuStock);

        jMenuBar1.add(menuPrincipal);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(anhadirVehiculoBoton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modificarVehiculoBoton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarVehiculoBoton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salirBoton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(511, 511, 511)
                                .addComponent(buscarBoton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(matriculaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combustibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(marcaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modeloTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supervisorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo)
                    .addComponent(matriculaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(combustibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(marcaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(modeloTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(supervisorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buscarBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salirBoton)
                    .addComponent(eliminarVehiculoBoton)
                    .addComponent(modificarVehiculoBoton)
                    .addComponent(anhadirVehiculoBoton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Biblioteca Informática");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBotonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirBotonActionPerformed

    private void buscarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBotonActionPerformed
        buscarVehiculo();
    }//GEN-LAST:event_buscarBotonActionPerformed

    private void menuEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmpleadosActionPerformed
        VEmpleados ve = new VEmpleados(this, true, fa);
        ve.setVisible(true);
    }//GEN-LAST:event_menuEmpleadosActionPerformed

    private void menuSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSolicitudesActionPerformed
        
    }//GEN-LAST:event_menuSolicitudesActionPerformed

    private void anhadirVehiculoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anhadirVehiculoBotonActionPerformed
        VVehiculo vnv= new VVehiculo(this, true, fa, mecanicoAplicacion);
        vnv.setVisible(true);
        
        buscarVehiculo();
    }//GEN-LAST:event_anhadirVehiculoBotonActionPerformed

    private void modificarVehiculoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarVehiculoBotonActionPerformed
        VVehiculo vv= new VVehiculo(this, true, fa, vehiculoSeleccionado.getMatricula());
        vv.setVisible(true);
        
        buscarVehiculo();
    }//GEN-LAST:event_modificarVehiculoBotonActionPerformed

    private void eliminarVehiculoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarVehiculoBotonActionPerformed
        if (vehiculoSeleccionado != null) {
            fa.eliminarVehiculo(vehiculoSeleccionado.getMatricula());
            vehiculoSeleccionado = null;
        }
        
        buscarVehiculo();
    }//GEN-LAST:event_eliminarVehiculoBotonActionPerformed

    private void tablaVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVehiculosMouseClicked
        ModeloTablaVehiculos mtl = (ModeloTablaVehiculos) tablaVehiculos.getModel();
        vehiculoSeleccionado = mtl.obtenerVehiculo(tablaVehiculos.getSelectedRow());

        if(vehiculoSeleccionado != null) {
            setTextFields();
            modificarVehiculoBoton.setEnabled(true);
            if (fa.vehiculoTieneReparacionesPendientes(vehiculoSeleccionado)) 
                eliminarVehiculoBoton.setEnabled(false);
            else 
                eliminarVehiculoBoton.setEnabled(true);
        }
    }//GEN-LAST:event_tablaVehiculosMouseClicked

    private void menuPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPersonalActionPerformed
        VPersonal vp = new VPersonal(this, true, fa, mecanicoAplicacion);
        vp.setVisible(true);
    }//GEN-LAST:event_menuPersonalActionPerformed

    private void menuStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStockActionPerformed
        VStock vs = new VStock(this, true, fa, mecanicoAplicacion);
        vs.setVisible(true);
    }//GEN-LAST:event_menuStockActionPerformed
    
    
    /**
    * @param args the command line arguments
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anhadirVehiculoBoton;
    private javax.swing.JButton buscarBoton;
    private javax.swing.JTextField clienteTextField;
    private javax.swing.JTextField combustibleTextField;
    private javax.swing.JButton eliminarVehiculoBoton;
    private javax.swing.JLabel etiquetaTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField marcaTextField;
    private javax.swing.JTextField matriculaTextField;
    private javax.swing.JMenuItem menuEmpleados;
    private javax.swing.JMenu menuGestion;
    private javax.swing.JMenuItem menuPersonal;
    private javax.swing.JMenu menuPrincipal;
    private javax.swing.JMenuItem menuSolicitudes;
    private javax.swing.JMenuItem menuStock;
    private javax.swing.JTextField modeloTextField;
    private javax.swing.JButton modificarVehiculoBoton;
    private javax.swing.JButton salirBoton;
    private javax.swing.JTextField supervisorTextField;
    private javax.swing.JTable tablaVehiculos;
    // End of variables declaration//GEN-END:variables

    private void inicializarBotones() {
        eliminarVehiculoBoton.setEnabled(false);
        modificarVehiculoBoton.setEnabled(false);
    }
    
     private void setTextFields(){
        modificarVehiculoBoton.setEnabled(true);
        
        matriculaTextField.setText(vehiculoSeleccionado.getMatricula());
        clienteTextField.setText(vehiculoSeleccionado.getPropietarioDNI());   
        combustibleTextField.setText(vehiculoSeleccionado.getCombustible());
        marcaTextField.setText(vehiculoSeleccionado.getMarca());
        modeloTextField.setText(vehiculoSeleccionado.getModelo());
        
        if(vehiculoSeleccionado.getSupervisorID() != null)
            supervisorTextField.setText(fa.obtenerJefeTaller(vehiculoSeleccionado.getSupervisorID()).getIdMecanico());
        else 
            supervisorTextField.setText("");
    }
    
    private void vaciarTextFields( ){
        matriculaTextField.setText("");
        clienteTextField.setText(""); 
        combustibleTextField.setText("");
        marcaTextField.setText("");
        modeloTextField.setText("");
        supervisorTextField.setText("");
    }
    
    public void esMecanicoJefe(Mecanico mecanico){
        mecanicoAplicacion = mecanico;
        if(mecanico instanceof JefeTaller) menuGestion.setEnabled(true);
        else menuGestion.setEnabled(false);
    }
    
    private void buscarVehiculo(){
        inicializarBotones();
        vaciarTextFields();
                
        ModeloTablaVehiculos mtv;
        mtv=(ModeloTablaVehiculos) tablaVehiculos.getModel();
        mtv.setFilas(fa.obtenerVehiculos(matriculaTextField.getText(), clienteTextField.getText(), marcaTextField.getText(), modeloTextField.getText(), supervisorTextField.getText(), combustibleTextField.getText()));
        if (mtv.getRowCount() > 0) { //escoller o primeiro sempre na búsqueda
            tablaVehiculos.setRowSelectionInterval(0, 0);
            tablaVehiculos.requestFocus();
            
            vehiculoSeleccionado= mtv.obtenerVehiculo(0);
            setTextFields();
        }
    }
    
    public Mecanico getMecanicoAplicacion() { return mecanicoAplicacion; } //quitar e pasar por argumento ao contrusctor

}