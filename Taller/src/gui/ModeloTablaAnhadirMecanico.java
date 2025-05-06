/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaAnhadirMecanico {
    private java.util.List<Cliente> clientes;

    public ModeloTablaClientes(){
        this.clientes =new java.util.ArrayList<Cliente>();
    }

    
    public int getColumnCount (){
        return 3;
    }

        @Override

    public int getRowCount(){
        return clientes.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "DNI"; break;
            case 1: nombre= "Nombre"; break;
            case 2: nombre="Teléfono de contacto"; break;
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
            case 0: resultado= clientes.get(i).getDni(); break;
            case 1: resultado= clientes.get(i).getNombre(); break;
            case 2: resultado=clientes.get(i).getTelefonoContacto();break;
        }
        return resultado;
    }

    

    public void setFilas(java.util.List<Cliente> clientes){
        this.clientes=clientes;
        fireTableDataChanged();
    }

    public void nuevoCliente(Cliente e){
        this.clientes.add(e);
        fireTableRowsInserted(this.clientes.size()-1, this.clientes.size()-1);
    }

    public void borrarCliente(int indice){
        this.clientes.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Cliente> getFilas(){
        return this.clientes;
    }

    public Cliente obtenerCliente(int i){
        return this.clientes.get(i);
    }
}
