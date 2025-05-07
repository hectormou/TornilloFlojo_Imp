/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Cliente;
import aplicacion.JefeTaller;
import aplicacion.Vehiculo;
import java.sql.*;

/**
 *
 * @author basesdatos
 */
public class DAOVehiculos extends AbstractDAO {

    public DAOVehiculos (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    
    public JefeTaller obtenerJefeTaller(String id){
        JefeTaller resultado=null;
        Connection con;
        PreparedStatement stmSupervisor=null;
        ResultSet rsSupervisor;

        con=this.getConexion();
        try {
        stmSupervisor=con.prepareStatement("select j.idMecanico, nombre, clave, telefonoContacto, fechaIngreso "+
                                        "from JefeTaller j join Mecanico m on j.idMecanico = m.idMecanico "+
                                        "where j.idMecanico = ? ");
        stmSupervisor.setString(1, id);
        rsSupervisor=stmSupervisor.executeQuery();
        if (rsSupervisor.next())
        {
            resultado = new JefeTaller(rsSupervisor.getString("idMecanico"), rsSupervisor.getString("clave"), rsSupervisor.getString("nombre"),
                                       rsSupervisor.getString("telefonoContacto"), rsSupervisor.getDate("fechaingreso"), rsSupervisor.getInt("sueldoBase"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSupervisor.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public Vehiculo obtenerVehiculo(String matricula) {
        Vehiculo resultado=null;
        Connection con;
        PreparedStatement stmSupervisor=null;
        ResultSet rsVehiculo;

        con=this.getConexion();
        try {
        stmSupervisor=con.prepareStatement("select matricula, marca, modelo, kilometraje, combustible, supervisor, clientedni "+
                                        "from vehiculo "+
                                        "where matricula = ? ");
        stmSupervisor.setString(1, matricula);
        rsVehiculo=stmSupervisor.executeQuery();
        if (rsVehiculo.next())
        {
            resultado = new Vehiculo(rsVehiculo.getString("matricula"), rsVehiculo.getString("marca"), rsVehiculo.getString("modelo"), rsVehiculo.getString("combustible"),
                                        rsVehiculo.getInt("kilometraje"), rsVehiculo.getString("clientedni"), rsVehiculo.getString("supervisor"));
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSupervisor.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public java.util.List<Vehiculo> consultarCatalogo(String matricula, String cliente, String marca, String modelo, String supervisor, String combustible){
        java.util.List<Vehiculo> resultado = new java.util.ArrayList<Vehiculo>();
        int i = 1;
        Vehiculo vehiculoActual;
        Connection con;
        PreparedStatement stmVehiculos=null;
        ResultSet rsVehiculos;

        con=this.getConexion();
        
        String consulta = "select matricula, marca, modelo, combustible, kilometraje, clienteDNI, supervisor " +
                                         "from vehiculo as v "+
                                         "where modelo like ?"+
                                         "  and marca like ?";
        if (!matricula.isEmpty())
            consulta += "AND matricula = ? ";
        if (!cliente.isEmpty())
            consulta += "AND clienteDNI = ? ";
        if (!supervisor.isEmpty())
            consulta += "AND supervisor = ? ";
        if (!combustible.isEmpty())
            consulta += "AND combustible = ? ";

        try {
            stmVehiculos = con.prepareStatement(consulta);
            stmVehiculos.setString(i++, "%" + modelo + "%");
            stmVehiculos.setString(i++, "%" + marca + "%");

            if (!matricula.isEmpty()) {
                 stmVehiculos.setString(i++, matricula);
            }
            if (!cliente.isEmpty()) {
                stmVehiculos.setString(i++, cliente);
            }
            if (!supervisor.isEmpty()) {
                stmVehiculos.setString(i++, supervisor);
            }
            if (!combustible.isEmpty()) {
                stmVehiculos.setString(i++, combustible);
            }

         rsVehiculos=stmVehiculos.executeQuery();
        while (rsVehiculos.next())
        {
            vehiculoActual = new Vehiculo(rsVehiculos.getString("matricula"), rsVehiculos.getString("marca"), rsVehiculos.getString("modelo"), rsVehiculos.getString("combustible"),
                                            rsVehiculos.getInt("kilometraje"), rsVehiculos.getString("clientedni"), rsVehiculos.getString("supervisor"));
            
            resultado.add(vehiculoActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }



    public void modificarVehiculo(Vehiculo vehiculo){
        Connection con;
        PreparedStatement stmVehiculo=null;
        PreparedStatement stmVehiculoAux=null;
        ResultSet rsVehiculoAux=null;

        con=super.getConexion();

        try {
            con.setAutoCommit(false);
            stmVehiculoAux=con.prepareStatement("select * from vehiculo where matricula = ? for update");
            stmVehiculoAux.setString(1,vehiculo.getMatricula());
            rsVehiculoAux=stmVehiculoAux.executeQuery();
           
        if(rsVehiculoAux.next()){
        stmVehiculo=con.prepareStatement("update vehiculo "+
                                    "set marca=?, "+
                                    "    modelo=?, "+
                                    "    kilometraje=?, "+
                                    "    combustible=? ,"+
                                    "   supervisor=? ,"+
                                    "   clientedni=? " +
                                    "where matricula=?");
        stmVehiculo.setString(1, vehiculo.getMarca());
        stmVehiculo.setString(2, vehiculo.getModelo());
        stmVehiculo.setInt(3, vehiculo.getKilometraje());
        stmVehiculo.setString(4, vehiculo.getCombustible());
        stmVehiculo.setString(5, vehiculo.getSupervisorID());
        stmVehiculo.setString(6, vehiculo.getCliente());
        stmVehiculo.setString(7,vehiculo.getMatricula());
        stmVehiculo.executeUpdate();
        
        con.commit();
        }else con.rollback();
        }  catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculoAux.close();
              stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void nuevoVehiculo(Vehiculo vehiculo) {
        Connection con;
        PreparedStatement stmVehiculo=null;
        
        ResultSet rsIdLibro;

        con=super.getConexion();

        try {
        stmVehiculo=con.prepareStatement("insert into vehiculo(matricula, marca, modelo, kilometraje, combustible,supervisor,clienteDNI) "+
                                      "values (?,?,?,?,?,?,?)");
    stmVehiculo.setString(1, vehiculo.getMatricula());
    stmVehiculo.setString(2, vehiculo.getMarca());
    stmVehiculo.setString(3, vehiculo.getModelo());
    stmVehiculo.setInt(4, vehiculo.getKilometraje());
     stmVehiculo.setString(5,vehiculo.getCombustible());
          stmVehiculo.setString(6,vehiculo.getSupervisorID());
               stmVehiculo.setString(7,vehiculo.getCliente());


        stmVehiculo.executeUpdate();

        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void eliminarVehiculo(String matricula) {
            Connection con;
        PreparedStatement stmVehiculo=null;

        con=super.getConexion();

        try {
        stmVehiculo=con.prepareStatement("delete from vehiculo where matricula=? ");
            stmVehiculo.setString(1, matricula);
            stmVehiculo.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
    }
    
    public java.util.List<Vehiculo> obtenerVehiculosCliente(Cliente c) {
        Connection con;
        PreparedStatement stmVehiculo=null;
        ResultSet rsVehiculos = null;
        java.util.List<Vehiculo> resultado = new java.util.ArrayList<Vehiculo>();
        Vehiculo vehiculoActual;
        con=super.getConexion();

        try {
        stmVehiculo=con.prepareStatement("select matricula, marca, modelo, combustible, kilometraje, clienteDNI, supervisor " + "from vehiculo where clienteDNI=? ");
            stmVehiculo.setString(1, c.getDni());
            rsVehiculos = stmVehiculo.executeQuery();
        
            while (rsVehiculos.next()) {
                vehiculoActual = new Vehiculo(rsVehiculos.getString("matricula"), rsVehiculos.getString("marca"), rsVehiculos.getString("modelo"), rsVehiculos.getString("combustible"),
                                            rsVehiculos.getInt("kilometraje"), rsVehiculos.getString("clientedni"), rsVehiculos.getString("supervisor"));
            
                resultado.add(vehiculoActual);
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }


    boolean vehiculoTuvoReparaciones(String matricula) {
        Connection con;
        PreparedStatement stmVehiculo=null;
        boolean resultado=false;
        con=super.getConexion();
        ResultSet rsVehiculo=null;
        try {
        stmVehiculo=con.prepareStatement("select* " + "from vehiculo where matricula=? AND matricula in(select idVehiculo from reparacion)");
            stmVehiculo.setString(1, matricula);
            rsVehiculo=stmVehiculo.executeQuery();
            if(rsVehiculo.next()){
                resultado=true;
            }
            
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    boolean vehiculoTieneReparacionesPendientes(String matricula) {
Connection con;
        PreparedStatement stmVehiculo=null;
        boolean resultado=false;
        con=super.getConexion();
        ResultSet rsVehiculo=null;
        try {
        stmVehiculo=con.prepareStatement("select* " + "from vehiculo where matricula=? AND matricula in(select idVehiculo from reparacion where fechaFin is null)");
            stmVehiculo.setString(1, matricula);
            rsVehiculo=stmVehiculo.executeQuery();
            if(rsVehiculo.next()){
                resultado=true;
            }
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public void sacarTaller(String matricula) {
        Connection con;
        PreparedStatement stmVehiculo=null;

        con=super.getConexion();

        try {
        stmVehiculo=con.prepareStatement("Update vehiculo set supervisor=null where matricula=? ");
            stmVehiculo.setString(1, matricula);
            stmVehiculo.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

    }
}
