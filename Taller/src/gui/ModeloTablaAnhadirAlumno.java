/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.EmpleadoPracticas;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaAnhadirAlumno extends AbstractTableModel {
   
    private java.util.List<EmpleadoPracticas> subordinados;

    
    public ModeloTablaAnhadirAlumno(){
        this.subordinados =new java.util.ArrayList<EmpleadoPracticas>();
    }

    public int getColumnCount (){
        return 2;
    }

    @Override
    public int getRowCount(){
        return subordinados.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Id"; break;
            case 1: nombre= "Nombre"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
       return false;
    }

    @Override
    public Object getValueAt(int i, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= subordinados.get(i).getIdalumno(); break;
            case 1: resultado= subordinados.get(i).getNombre(); break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<EmpleadoPracticas> subordinados){
        this.subordinados=subordinados;
        fireTableDataChanged();
    }

    public void nuevoSubordinado(EmpleadoPracticas e){
        this.subordinados.add(e);
        fireTableRowsInserted(this.subordinados.size()-1, this.subordinados.size()-1);
    }

    public void borrarSubordinado(int indice){
        this.subordinados.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<EmpleadoPracticas> getFilas(){
        return this.subordinados;
    }

    public EmpleadoPracticas obtenerSubordinado(int i){
        return this.subordinados.get(i);
    }
}
