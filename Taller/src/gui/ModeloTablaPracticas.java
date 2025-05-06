/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.EmpleadoPracticas;
import aplicacion.JefeTaller;
import aplicacion.EmpleadoPracticas;
import aplicacion.Subordinado;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaPracticas extends AbstractTableModel{
    private java.util.List<EmpleadoPracticas> practicas;

    public ModeloTablaPracticas(){
        this.practicas =new java.util.ArrayList<EmpleadoPracticas>();
    }

    
    public int getColumnCount (){
        return 4;
    }

        @Override

    public int getRowCount(){
        return practicas.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Id"; break;
            case 1: nombre= "Nombre"; break;
            case 2: nombre= "Tutor"; break;
            case 3: nombre= "Reparaciones asistidas"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase=java.lang.Integer.class; break;
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
            case 0: resultado= practicas.get(i).getIdalumno(); break;
            case 1: resultado= practicas.get(i).getNombre(); break;
            case 2: resultado=practicas.get(i).getTutor().getNombre();break;
            case 3: resultado=practicas.get(i).getReparacionesAsistidas();break;
        }
        return resultado;
    }

    

    public void setFilas(java.util.List<EmpleadoPracticas> practicas){
        this.practicas=practicas;
        fireTableDataChanged();
    }

    public void nuevoEmpleadoPracticas(EmpleadoPracticas e){
        this.practicas.add(e);
        fireTableRowsInserted(this.practicas.size()-1, this.practicas.size()-1);
    }

    public void borrarEmpleadoPracticas(int indice){
        this.practicas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<EmpleadoPracticas> getFilas(){
        return this.practicas;
    }

    public EmpleadoPracticas obtenerEmpleadoPracticas(int i){
        return this.practicas.get(i);
    }

    
}
