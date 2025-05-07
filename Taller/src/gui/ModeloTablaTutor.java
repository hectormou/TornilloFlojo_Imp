/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.JefeTaller;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaTutor extends AbstractTableModel{
    private java.util.List<JefeTaller> tutores;

    public ModeloTablaTutor(){
        this.tutores =new java.util.ArrayList<>();
    }

    
    public int getColumnCount (){
        return 2;
    }

        @Override

    public int getRowCount(){
        return tutores.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Tutores disponibles"; break;
            case 1: nombre= "Nº de empleados en tutela"; break;
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
            case 0: resultado= tutores.get(i).getNombre(); break;
            case 1: resultado= ((JefeTaller) tutores.get(i)).getPracticasTutela(); break;
        }
        return resultado;
    }

    

    public void setFilas(java.util.List<JefeTaller> tutores){
        this.tutores=tutores;
        fireTableDataChanged();
    }

    public void nuevoTutor(JefeTaller e){
        this.tutores.add(e);
        fireTableRowsInserted(this.tutores.size()-1, this.tutores.size()-1);
    }

    public void borrarTutor(int indice){
        this.tutores.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<JefeTaller> getFilas(){
        return this.tutores;
    }

    public JefeTaller obtenerTutor(int i){
        return this.tutores.get(i);
    }

    
}
