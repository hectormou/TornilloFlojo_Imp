/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.JefeTaller;
import aplicacion.Mecanico;
import aplicacion.Subordinado;
import java.sql.*;
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
   
   public Boolean esJefeTaller(String idMecanico){
       Boolean resultado=false;
        Connection con;
        PreparedStatement stmMecanico=null;
        ResultSet rsMecanico;

        con=this.getConexion();

        try {
        stmMecanico=con.prepareStatement("select * from JefeTaller where idMecanico = ? ");
        stmMecanico.setString(1, idMecanico);
        rsMecanico=stmMecanico.executeQuery();
        if (rsMecanico.next())
        {
            resultado = true;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
   
   public Boolean esSubordinado(String idMecanico){
       Boolean resultado=false;
        Connection con;
        PreparedStatement stmMecanico=null;
        ResultSet rsMecanico;

        con=this.getConexion();

        try {
        stmMecanico=con.prepareStatement("select * from Subordinado where idMecanico = ?");
        stmMecanico.setString(1, idMecanico);
        rsMecanico=stmMecanico.executeQuery();
        if (rsMecanico.next())
        {
            resultado = true;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
   
    public Mecanico validarMecanico(String idMecanico, String clave){
        Mecanico resultado=null;
        Connection con;
        PreparedStatement stmMecanico=null;
        ResultSet rsMecanico;

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
            if(esJefeTaller(idMecanico)){
                resultado = new JefeTaller(rsMecanico.getString("idMecanico"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"), rsMecanico.getInt("sueldoBase"));
            }
            else if(esSubordinado(idMecanico)){
                resultado = new Subordinado(rsMecanico.getString("idMecanico"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"), rsMecanico.getInt("sueldoBase"));
            }
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
                                         "where idMecanico = ?";
        
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

    public void cambiarContraseña(String nuevaContraseña, String idMecanico) {
        Connection con = this.getConexion();
        PreparedStatement stmContraseña=null;
        
        try  {
        String consulta = "update mecanico set clave = ? where idmecanico ILIKE ?";
        stmContraseña=con.prepareStatement(consulta);
        stmContraseña.setString(1, nuevaContraseña);
        stmContraseña.setString(2, idMecanico);
        stmContraseña.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmContraseña.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void editarMecanico(String clave, String nombre, Date fechaIngreso, int sueldoBase, String id){
        Connection con = this.getConexion();
        PreparedStatement stmMecanico = null;

        try {
            String consulta = "UPDATE mecanico SET clave = ?, nombre = ?, fechaingreso = ?, sueldobase = ? WHERE idmecanico ILIKE ?";
            stmMecanico = con.prepareStatement(consulta);
            stmMecanico.setString(1, clave);
            stmMecanico.setString(2, nombre);
            stmMecanico.setDate(3, new java.sql.Date(fechaIngreso.getTime()));
            stmMecanico.setInt(4, sueldoBase);
            stmMecanico.setString(5, id);

            stmMecanico.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmMecanico != null) stmMecanico.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
}