/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Solicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOSolicitudes extends AbstractDAO {
    
    public DAOSolicitudes (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Float obtenerPrecioSolicitud(Integer idRepuesto) {
        Float resultado = null;
        Connection con;
        PreparedStatement stmPrecioSolicitud=null;
        ResultSet rsPrecioSolicitud;

        con=this.getConexion();
        
        String consulta = "select preciounidad " + "from repuesto " + "where idrepuesto = ?";
        
        
        try  {
         stmPrecioSolicitud=con.prepareStatement(consulta);
         stmPrecioSolicitud.setInt(1, idRepuesto);
         rsPrecioSolicitud=stmPrecioSolicitud.executeQuery();
         
        if (rsPrecioSolicitud.next()) {
            resultado = rsPrecioSolicitud.getFloat("preciounidad");
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrecioSolicitud.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    List<Solicitud> obtenerSolicitudes() {

        java.util.List<Solicitud> resultado = new java.util.ArrayList<Solicitud>();
        int i = 1;
        Solicitud solicitudActual;
        Connection con;
        PreparedStatement stmSolicitudes=null;
        ResultSet rsSolicitudes;

        con=this.getConexion();
        
        String consulta = "select repuesto, fecha, cantidad, idmecanico " +
                                         "from solicitud as s ";

        try {
            stmSolicitudes = con.prepareStatement(consulta);
            rsSolicitudes=stmSolicitudes.executeQuery();
        while (rsSolicitudes.next())
        {
            solicitudActual = new Solicitud(rsSolicitudes.getString("fecha"), rsSolicitudes.getString("idmecanico"),
                    rsSolicitudes.getInt("repuesto"), rsSolicitudes.getInt("cantidad"));
            
            resultado.add(solicitudActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSolicitudes.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }    

    void eliminarSolicitud(Solicitud solicitudSeleccionada) {
        Connection con;
        PreparedStatement stmSolicitud=null;

        con=super.getConexion();

        try {
            String fechaString = solicitudSeleccionada.getFecha();
            java.sql.Date fechaSql = java.sql.Date.valueOf(fechaString);
            stmSolicitud=con.prepareStatement("delete from solicitud where repuesto=? and fecha = ? and cantidad = ? and idmecanico = ? ");
            stmSolicitud.setInt(1, solicitudSeleccionada.getIdRepuesto());
            stmSolicitud.setDate(2, fechaSql);
            stmSolicitud.setInt(3, solicitudSeleccionada.getCantidad());
            stmSolicitud.setString(4, solicitudSeleccionada.getIdSolicitante());
            stmSolicitud.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSolicitud.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }    }

    void aumentarStockPorSolicitud(Solicitud solicitudSeleccionada) {
        Connection con;
        PreparedStatement stmSolicitud=null;

        con=super.getConexion();
        
        try{
            stmSolicitud = con.prepareStatement("update repuesto set stock = stock + ? where idrepuesto = ?");
            stmSolicitud.setInt(1, solicitudSeleccionada.getCantidad());
            stmSolicitud.setInt(2, solicitudSeleccionada.getIdRepuesto());
            stmSolicitud.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSolicitud.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }  

    }
}
