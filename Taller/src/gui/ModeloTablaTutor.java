/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.JefeTaller;
import aplicacion.Mecanico;
import aplicacion.Subordinado;
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
            case 1: resultado= tutores.get(i).getNombre(); break;
            case 2: 
                if(mecanicos.get(i) instanceof JefeTaller){ resultado = "Jefe de taller";}
                else if(mecanicos.get(i) instanceof Subordinado){ resultado = "Subordinado";}
                else{resultatdo = '-';}
                break;
            case 3: resultado=mecanicos.get(i).getTelefonoContacto();break;
            case 4: resultado=mecanicos.get(i).getSueldoBase();break;
            case 5:
                if(mecanicos.get(i) instanceof JefeTaller){ 
                    JefeTaller j=(JefeTaller)mecanicos.get(i);
                    resultado=j.getBonusUsual();
                }
                else if(mecanicos.get(i) instanceof Subordinado){
                    Subordinado s=(Subordinado)mecanicos.get(i);
                    resultado= s.getBonusSubordinado();
                }
                else{
                    resultado = 0;}
                break;
            case 6:
                if(mecanicos.get(i) instanceof JefeTaller){ 
                    JefeTaller j=(JefeTaller)mecanicos.get(i);
                    resultado=j.getBonusJefe();
                }else resultado=0;
        }
        return resultado;
    }

    

    public void setFilas(java.util.List<Mecanico> mecanicos){
        this.mecanicos=mecanicos;
        fireTableDataChanged();
    }

    public void nuevoMecanico(Mecanico e){
        this.mecanicos.add(e);
        fireTableRowsInserted(this.mecanicos.size()-1, this.mecanicos.size()-1);
    }

    public void borrarMecanico(int indice){
        this.mecanicos.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Mecanico> getFilas(){
        return this.mecanicos;
    }

    public Mecanico obtenerMecanico(int i){
        return this.mecanicos.get(i);
    }

    
}
