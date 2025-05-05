/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Reparacion;
import aplicacion.TipoReparacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOReparaciones extends AbstractDAO {
    
    public DAOReparaciones (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Reparacion> obtenerReparaciones(String matricula) {
        java.util.List<Reparacion> resultado = new java.util.ArrayList<Reparacion>();
        Connection con;
        PreparedStatement stmReparaciones=null;
        ResultSet rsReparaciones;

        con=this.getConexion();
        
        String consulta = "select idreparacion, fechainicio, fechafin, idvehiculo, tiporeparacion, mecanico " + "from reparacion " + "where idvehiculo = ?";
        
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setString(1, matricula);
         rsReparaciones=stmReparaciones.executeQuery();
        while (rsReparaciones.next())
        {
            Reparacion reparacion = new Reparacion(rsReparaciones.getInt("idreparacion"), rsReparaciones.getString("idvehiculo"), rsReparaciones.getString("tiporeparacion"), rsReparaciones.getString("mecanico"));
            resultado.add(reparacion);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public TipoReparacion obtenerTipoReparacion(String nombre) {
        TipoReparacion resultado=null;
        Connection con;
        PreparedStatement stmTipoReparacion=null;
        ResultSet rsTipoReparacion;

        con=this.getConexion();
        try {
        stmTipoReparacion=con.prepareStatement("select nombre, descripcion, aptoparapracticas "+
                                        "from tiporeparacion "+
                                        "where nombre = ? ");
        stmTipoReparacion.setString(1, nombre);
        rsTipoReparacion=stmTipoReparacion.executeQuery();
        if (rsTipoReparacion.next())
        {
            resultado = new TipoReparacion(rsTipoReparacion.getString("nombre"), rsTipoReparacion.getString("descripcion"), rsTipoReparacion.getBoolean("aptoparapracticas"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmTipoReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public Reparacion obtenerReparacion(Integer id) {
        Reparacion resultado=null;
        Connection con;
        PreparedStatement stmReparaciones=null;
        ResultSet rsReparaciones;

        con=this.getConexion();
        
        String consulta = "select idreparacion, fechainicio, fechafin, idvehiculo, tiporeparacion, mecanico " + "from reparacion " + "where idreparacion = ?";
        
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setInt(1, id);
         rsReparaciones=stmReparaciones.executeQuery();
        while (rsReparaciones.next())
        {
            resultado = new Reparacion(rsReparaciones.getInt("idreparacion"), rsReparaciones.getString("idvehiculo"), rsReparaciones.getString("tiporeparacion"), rsReparaciones.getString("mecanico"));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
