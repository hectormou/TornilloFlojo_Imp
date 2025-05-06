/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import aplicacion.Stock_U_A;
import javax.swing.table.*;
/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaStock_U_A extends AbstractTableModel {
    private java.util.List<Stock_U_A> ua;

    public ModeloTablaStock_U_A(){
        this.ua =new java.util.ArrayList<Stock_U_A>();
    }

    
    public int getColumnCount (){
        return 3;
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
            case 1: nombre= "Cantidad"; break;
            case 2: nombre="En almacén"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.Integer.class; break;
            case 2: clase=java.lang.Integer.class; break;
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
            case 1: resultado= ua.get(i).getUsado(); break;
            case 2: resultado=ua.get(i).getAlmacen();break;
        }
        return resultado;
    }

    

    public void setFilas(java.util.List<Stock_U_A> ua){
        this.ua=ua;
        fireTableDataChanged();
    }

    public void nuevoCliente(Stock_U_A e){
        this.ua.add(e);
        fireTableRowsInserted(this.ua.size()-1, this.ua.size()-1);
    }

    public void borrarCliente(int indice){
        this.ua.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Stock_U_A> getFilas(){
        return this.ua;
    }

    public Stock_U_A obtenerCliente(int i){
        return this.ua.get(i);
    }
    
    public boolean hayStock() {
        for(Stock_U_A s : ua)
            if(s.getAlmacen()-s.getUsado() < 0) return false;
        return true;
    }
}
