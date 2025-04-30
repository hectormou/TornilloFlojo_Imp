/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.TipoUsuario;
import aplicacion.Mecanico;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaUsuarios extends AbstractTableModel{
    private java.util.List<Mecanico> usuarios;

    public ModeloTablaUsuarios(){
        this.usuarios=new java.util.ArrayList<Mecanico>();
    }

    public int getColumnCount (){
        return 4;
    }

    public int getRowCount(){
        return usuarios.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Id"; break;
            case 1: nombre= "Nombre"; break;
            case 2: nombre="Email"; break;
            case 3: nombre="Tipo"; break;
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
            case 3: clase=TipoUsuario.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;
/*
        switch (col){
            case 0: resultado= usuarios.get(row).getIdUsuario(); break;
            case 1: resultado= usuarios.get(row).getNombre(); break;
            case 2: resultado=usuarios.get(row).getEmail();break;
            case 3: resultado=usuarios.get(row).getTipoUsuario(); break;
        }*/
        return resultado;
    }

    public void setFilas(java.util.List<Mecanico> usuarios){
        this.usuarios=usuarios;
        fireTableDataChanged();
    }
    
    public Mecanico obtenerUsuario(int i){
        return this.usuarios.get(i);
    }

}
