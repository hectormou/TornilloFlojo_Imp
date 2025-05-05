/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Mecanico;
import aplicacion.Reparacion;
import aplicacion.Repuesto;
import aplicacion.TipoReparacion;
import aplicacion.Vehiculo;
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
            Reparacion reparacion = new Reparacion(rsReparaciones.getInt("idreparacion"), rsReparaciones.getString("fechainicio"), rsReparaciones.getString("fechafin"), rsReparaciones.getString("idvehiculo"), rsReparaciones.getString("tiporeparacion"), rsReparaciones.getString("mecanico"));
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
    
    public List<TipoReparacion> obtenerTipoReparaciones() {
        java.util.List<TipoReparacion> resultado = new java.util.ArrayList<TipoReparacion>();
        Connection con;
        PreparedStatement stmTipoReparacion=null;
        ResultSet rsTipoReparacion;

        con=this.getConexion();
        try {
        stmTipoReparacion=con.prepareStatement("select nombre, descripcion, aptoparapracticas "+
                                        "from tiporeparacion ");
        rsTipoReparacion=stmTipoReparacion.executeQuery();
        while (rsTipoReparacion.next())
        {
            resultado.add(new TipoReparacion(rsTipoReparacion.getString("nombre"), rsTipoReparacion.getString("descripcion"), rsTipoReparacion.getBoolean("aptoparapracticas")));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmTipoReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
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
            resultado = new Reparacion(rsReparaciones.getInt("idreparacion"), rsReparaciones.getString("fechainicio"), rsReparaciones.getString("fechafin"), rsReparaciones.getString("idvehiculo"), rsReparaciones.getString("tiporeparacion"), rsReparaciones.getString("mecanico"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
        }


    public List<Repuesto> getTotalRepuestos() {
        java.util.List<Repuesto> resultado = new java.util.ArrayList<Repuesto>();
        Connection con;
        PreparedStatement stmRepuestos=null;
        ResultSet rsRepuestos;

        con=this.getConexion();
        try {
        stmRepuestos=con.prepareStatement("select idrepuesto, nombre, descripcion, preciounidad, stock "+
                                        "from repuesto ");
        rsRepuestos=stmRepuestos.executeQuery();
        while (rsRepuestos.next())
        {
            resultado.add(new Repuesto(rsRepuestos.getInt("idrepuesto") , rsRepuestos.getString("nombre"), rsRepuestos.getString("descripcion"), rsRepuestos.getFloat("preciounidad"), rsRepuestos.getInt("stock")));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmRepuestos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
   }

    public void anhadirReparacion(Vehiculo vehiculo, String tipo, Mecanico mecanico) {
        Connection con;
        PreparedStatement stmReparacion=null;

        con=this.getConexion();
        try {
        stmReparacion=con.prepareStatement("insert into reparacion (idVehiculo, tipoReparacion, mecanico)  "+
                                        " values (?,?,?) ");
        stmReparacion.setString(1, vehiculo.getMatricula());
        stmReparacion.setString(2, tipo);
        stmReparacion.setString(3, mecanico.getIdMecanico());
        stmReparacion.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public Reparacion obtenerUltimaReparacionAnhadida() {
        Reparacion resultado=null;
        Connection con;
        PreparedStatement stmUltimaReparacion=null;
        ResultSet rsUltimaReparacion;

        con=this.getConexion();
        try {
        stmUltimaReparacion=con.prepareStatement("select idreparacion, fechainicio, fechafin, idvehiculo, tiporeparacion, mecanico "+
                                        "from reparacion "+
                                        "where idreparacion = (select max(idreparacion) from reparacion) ");
        rsUltimaReparacion=stmUltimaReparacion.executeQuery();
        if (rsUltimaReparacion.next())
        {
            resultado = new Reparacion(rsUltimaReparacion.getInt("idreparacion"), rsUltimaReparacion.getString("fechainicio"), rsUltimaReparacion.getString("fechafin"), rsUltimaReparacion.getString("idvehiculo"), rsUltimaReparacion.getString("tiporeparacion"), rsUltimaReparacion.getString("mecanico"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUltimaReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
/*
    public Repuesto obtenerRepuesto(String nombre) {
        Repuesto resultado=null;
        Connection con;
        PreparedStatement stmRepuesto=null;
        ResultSet rsRepuesto;

        con=this.getConexion();
        try {
        stmRepuesto=con.prepareStatement("select idrepuesto, nombre, descripcion, preciounidad, stock "+
                                        "from repuesto "+
                                        "where nombre = ? ");
        stmRepuesto.setString(1, nombre);
        rsRepuesto=stmRepuesto.executeQuery();
        if (rsRepuesto.next())
        {
            resultado = new Repuesto(rsRepuesto.getInt("idrepuesto"), rsRepuesto.getString("nombre"), rsRepuesto.getString("descripcion"), rsRepuesto.getFloat("preciounidad"), rsRepuesto.getInt("stock"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
          try {stmRepuesto.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

*/

    public void anhadirRepuestoNecesario(Integer idReparacion, Integer idRepuesto, int cantidad) {
        Connection con;
        PreparedStatement stmReparacion=null;

        con=this.getConexion();
        try {
        stmReparacion=con.prepareStatement("insert into utilizar (idrepuesto, idreparacion, cantidad)  "+
                                        " values (?,?,?) ");
        stmReparacion.setInt(1, idRepuesto);
        stmReparacion.setInt(2, idReparacion);
        stmReparacion.setInt(3, cantidad);
        stmReparacion.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
}
