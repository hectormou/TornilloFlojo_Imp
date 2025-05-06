/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.FachadaAplicacion;
import aplicacion.Mecanico;
import aplicacion.Reparacion;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */ 
public class ModeloTablaReparaciones extends AbstractTableModel{
    private FachadaAplicacion fa;
    private java.util.List<Reparacion> reparaciones;

    public ModeloTablaReparaciones(FachadaAplicacion fa){
        this.reparaciones = new java.util.ArrayList<Reparacion>();
        this.fa=fa;
    }

    public int getColumnCount (){
        return 5;
    }

    public int getRowCount(){
        return reparaciones.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Id Reparacion"; break;
            case 1: nombre= "Fecha_inicio"; break;
            case 2: nombre="Fecha_fin"; break;
            case 3: nombre= "Tipo"; break;
            case 4: nombre= "Supervisor"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.Integer.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase= java.lang.String.class; break;
            case 4: clase = java.lang.String.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
       return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= reparaciones.get(row).getIdreparacion(); break;
            case 1: resultado= reparaciones.get(row).getFecha_inicio(); break;
            case 2: if(reparaciones.get(row).getFecha_fin()==null) resultado = "-";
                    else resultado = reparaciones.get(row).getFecha_fin();
                    break;
            case 3: resultado=reparaciones.get(row).getTipo();break;
            case 4: if(reparaciones.get(row).getSupervisorid().isBlank()) resultado = "-";
                    else {
                        Mecanico mecanico=fa.obtenerMecanico(reparaciones.get(row).getSupervisorid());
                        resultado = mecanico.getNombre();
                    }
                    break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Reparacion> reparaciones){
        this.reparaciones=reparaciones;
        fireTableDataChanged();
    }

    public void nuevaReparacion(Reparacion u){
        this.reparaciones.add(u);
        fireTableRowsInserted(this.reparaciones.size()-1, this.reparaciones.size()-1);
    }

    public void borrarReparacion(int indice){
        this.reparaciones.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Reparacion> getFilas(){
        return this.reparaciones;
    }

    public Reparacion obtenerReparacion(int i){
        return this.reparaciones.get(i);
    }
    
}
