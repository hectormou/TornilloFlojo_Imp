/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.Mecanico;
import aplicacion.Reparacion;
import aplicacion.Solicitud;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaSolicitudes extends AbstractTableModel {
    FachadaAplicacion fa;
    private java.util.List<Solicitud> solicitudes;

    public ModeloTablaSolicitudes(aplicacion.FachadaAplicacion fa){
        this.fa = fa;
        this.solicitudes = new java.util.ArrayList<Solicitud>();
    }

    public int getColumnCount (){
        return 4;
    }

    public int getRowCount(){
        return solicitudes.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Id"; break;
            case 1: nombre= "Unidades"; break;
            case 2: nombre="Mecánico Solicitante"; break;
            case 3: nombre= "Precio Total"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.Integer.class; break;
            case 1: clase= java.lang.Integer.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase= java.lang.Float.class; break;
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
            case 0: resultado = solicitudes.get(row).getIdRepuesto(); break;
            case 1: resultado = solicitudes.get(row).getCantidad(); break;
            case 2: resultado = solicitudes.get(row).getIdSolicitante(); break;
            case 3: resultado = solicitudes.get(row).getCantidad() * (fa.obtenerPrecioSolicitud(solicitudes.get(row).getIdRepuesto()));break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Solicitud> solicitudes){
        this.solicitudes=solicitudes;
        fireTableDataChanged();
    }

    public void nuevaSolicitud(Solicitud u){
        this.solicitudes.add(u);
        fireTableRowsInserted(this.solicitudes.size()-1, this.solicitudes.size()-1);
    }

    public void borrarSolicitud(int indice){
        this.solicitudes.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Solicitud> getFilas(){
        return this.solicitudes;
    }

    public Solicitud obtenerSolicitud(int i){
        return this.solicitudes.get(i);
    }
}
