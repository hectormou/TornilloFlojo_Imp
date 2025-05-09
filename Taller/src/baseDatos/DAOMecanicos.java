/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.JefeTaller;
import aplicacion.Mecanico;
import aplicacion.Subordinado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List; 

/**
 *
 * @author basesdatos
 */
public class DAOMecanicos extends AbstractDAO {

   public DAOMecanicos (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
   
   
    public boolean validarMecanico(String idMecanico, String clave){
        Connection con;
        PreparedStatement stmMecanico=null;
        ResultSet rsMecanico;
        boolean resultado=false;

        con=this.getConexion();
        try {
        stmMecanico=con.prepareStatement("select idMecanico, clave, nombre, telefonoContacto, fechaIngreso, sueldoBase "+
                                        "from Mecanico m "+
                                        "where idMecanico = ? and clave = ?");
        stmMecanico.setString(1, idMecanico);
        stmMecanico.setString(2, clave);
        rsMecanico=stmMecanico.executeQuery();
        if (rsMecanico.next())
        {
            resultado= true;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
 
    public List<String> obtenerIDsJefesTaller() {
    java.util.List<String> resultado = new java.util.ArrayList<String>();
        Connection con;
        PreparedStatement stmUsuarios=null;
        ResultSet rsMecanicos;

        con=this.getConexion();
        
        String consulta = "select  idMecanico " +"from Mecanico "+"where idMecanico in (select idMecanico from JefeTaller)";
       
        
        try  {
         stmUsuarios=con.prepareStatement(consulta);
         rsMecanicos=stmUsuarios.executeQuery();
        while (rsMecanicos.next())
        {
            resultado.add(rsMecanicos.getString("idMecanico"));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public JefeTaller obtenerJefeTaller(String id) {
        JefeTaller resultado=null;
        Connection con;
        PreparedStatement stmUsuarios=null;
        ResultSet rsMecanico;

        con=this.getConexion();
        
        String consulta = "select  idMecanico, nombre, clave, telefonoContacto, fechaIngreso, sueldoBase " +
                                         "from mecanico "+
                                         "where idMecanico in (select idMecanico from JefeTaller) and idMecanico = ? ";
        
        try  {
         stmUsuarios=con.prepareStatement(consulta);
         stmUsuarios.setString(1, id);
         rsMecanico=stmUsuarios.executeQuery();
        if(rsMecanico.next())
        {
            resultado = new JefeTaller(rsMecanico.getString("idMecanico"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"), rsMecanico.getInt("sueldoBase"));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    
    }
    public Mecanico obtenerMecanico(String mecanicoid) {
        JefeTaller resultado=null;
        Connection con;
        PreparedStatement stmUsuarios=null;
        ResultSet rsMecanico;

        con=this.getConexion();
        
        String consulta = "select  idMecanico, nombre, clave, telefonoContacto, fechaIngreso, sueldoBase " +
                                         "from mecanico "+
                                         "where idMecanico = ? ";
        
        try{
         stmUsuarios=con.prepareStatement(consulta);
         stmUsuarios.setString(1, mecanicoid);
         rsMecanico=stmUsuarios.executeQuery();
        if(rsMecanico.next())
        {
            resultado = new JefeTaller(rsMecanico.getString("idMecanico"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"), rsMecanico.getInt("sueldoBase"));
        }
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }  
}