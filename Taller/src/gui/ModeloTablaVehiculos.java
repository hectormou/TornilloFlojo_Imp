/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.Vehiculo;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaVehiculos extends AbstractTableModel{
    private java.util.List<Vehiculo> vehiculos;

    public ModeloTablaVehiculos(){
        this.vehiculos=new java.util.ArrayList<Vehiculo>();
    }

    public int getColumnCount (){
        return 7;
    }

    public int getRowCount(){
        return vehiculos.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Matricula"; break;
            case 1: nombre= "Marca"; break;
            case 2: nombre="Modelo"; break;
            case 3: nombre="Combustible"; break;
            case 4: nombre="Kilometraje"; break;
            case 5: nombre="Propietario"; break;
            case 6: nombre="Supervisor"; break;
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
            case 3: clase=java.lang.String.class; break;
            case 4: clase=java.lang.Integer.class; break;
            case 5: clase=java.lang.String.class; break;
            case 6: clase=java.lang.String.class; break;
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
            case 0: resultado= vehiculos.get(row).getMatricula(); break;
            case 1: resultado= vehiculos.get(row).getMarca(); break;
            case 2: resultado=vehiculos.get(row).getModelo();break;
            case 3: resultado=vehiculos.get(row).getCombustible(); break;
            case 4: resultado=vehiculos.get(row).getKilometraje(); break;
            case 5: if(vehiculos.get(row).getPropietario() == null)resultado= "-"; else{resultado = vehiculos.get(row).getPropietario().getNombre();} break;
            case 6: if(vehiculos.get(row).getSupervisor() == null)resultado= "-"; else{resultado = vehiculos.get(row).getSupervisor().getNombre();} break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Vehiculo> vehiculos){
        this.vehiculos=vehiculos;
        fireTableDataChanged();
    }

    public Vehiculo obtenerVehiculo(int i){
        return this.vehiculos.get(i);
    }

}