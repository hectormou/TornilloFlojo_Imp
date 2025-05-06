/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import aplicacion.Repuesto;
import aplicacion.Stock_U_A;
import javax.swing.table.*;
/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaAlmacen extends AbstractTableModel {
    private java.util.List<Repuesto> ua;

    public ModeloTablaAlmacen(){
        this.ua =new java.util.ArrayList<Repuesto>();
    }

    
    public int getColumnCount (){
        return 2;
    }

        @Override

    public int getRowCount(){
        return ua.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Nombre"; break;
            case 1: nombre= "Stock"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.Integer.class; break;
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
            case 0: resultado= ua.get(i).getNombre(); break;
            case 1: resultado= ua.get(i).getStock(); break;
        }
        return resultado;
    }

    

    public void setFilas(java.util.List<Repuesto> ua){
        this.ua=ua;
        fireTableDataChanged();
    }


    public java.util.List<Repuesto> getFilas(){
        return this.ua;
    }

    public Repuesto obtenerRepuesto(int i){
        return this.ua.get(i);
    }
}
