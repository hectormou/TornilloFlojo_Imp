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
    
    public java.util.List<Mecanico> consultarUsuarios(String id, String nombre){
        java.util.List<Mecanico> resultado = new java.util.ArrayList<Mecanico>();
        Mecanico usuarioActual;
        Connection con;
        PreparedStatement stmUsuarios=null;
        ResultSet rsUsuarios;

        con=this.getConexion();
        
        String consulta = "select id_usuario, clave, nombre, direccion, email, tipo_usuario " +
                                         "from usuario as u "+
                                         "where nombre like ?";
        if (!id.isEmpty())
            consulta = consulta + " and id_usuario ILIKE ?";
        
        try  {
         stmUsuarios=con.prepareStatement(consulta);
         stmUsuarios.setString(1, "%"+nombre+"%");
         if (!id.isEmpty()) stmUsuarios.setString(2, id);
         rsUsuarios=stmUsuarios.executeQuery();
        /*while (rsUsuarios.next())
        {
            usuarioActual = new Mecanico(rsUsuarios.getString("id_usuario"), rsUsuarios.getString("clave"),
                                      rsUsuarios.getString("nombre"), rsUsuarios.getString("direccion"),
                                      rsUsuarios.getString("email"), TipoUsuario.valueOf(rsUsuarios.getString("tipo_usuario")));
            resultado.add(usuarioActual);
        }*/

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public boolean existeUsuario(String id){
        boolean resultado = false;
        Connection con = this.getConexion();
        PreparedStatement stmUsuarios=null;
        ResultSet rsUsuarios;
        
        try  {
        String consulta = "select * from usuario as u where id_usuario ILIKE ?";
        stmUsuarios=con.prepareStatement(consulta);
        stmUsuarios.setString(1, id);
        rsUsuarios=stmUsuarios.executeQuery();
        if(rsUsuarios.isBeforeFirst()){
            resultado = true;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public void editarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        Connection con = this.getConexion();
        PreparedStatement stmUsuarios=null;
        
        try  {
        String consulta = "update usuario set clave = ?, nombre = ?, direccion = ? , email = ?, tipo_usuario = ? where id_usuario ILIKE ?";
        stmUsuarios=con.prepareStatement(consulta);
        stmUsuarios.setString(1, clave);
        stmUsuarios.setString(2, nombre);
        stmUsuarios.setString(3, direccion);
        stmUsuarios.setString(4, email);
        stmUsuarios.setString(5, tipo);
        stmUsuarios.setString(6, id);
        stmUsuarios.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void crearUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        Connection con = this.getConexion();
        PreparedStatement stmUsuarios=null;
        
        try  {
        String consulta = "insert into usuario (id_usuario, clave, nombre, direccion, email, tipo_usuario) values (?,?,?,?,?,?)";
        stmUsuarios=con.prepareStatement(consulta);
        stmUsuarios.setString(1, id);
        stmUsuarios.setString(2, clave);
        stmUsuarios.setString(3, nombre);
        stmUsuarios.setString(4, direccion);
        stmUsuarios.setString(5, email);
        stmUsuarios.setString(6, tipo);
        int a = stmUsuarios.executeUpdate();
        if (a>0){
            System.out.println("Usuario creado correctamente");
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void borrarUsuario(String id){
        Connection con = this.getConexion();
        PreparedStatement stmUsuarios=null;
        try  {
        String consulta = "delete from usuario where id_usuario = ?";
        stmUsuarios=con.prepareStatement(consulta);
        stmUsuarios.setString(1, id);
        int a = stmUsuarios.executeUpdate();
        if (a>0){
            System.out.println("Usuario eliminado correctamente");
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    List<String> obtenerIDsJefesTaller() {
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
        ResultSet rsUsuarios;

        con=this.getConexion();
        
        String consulta = "select * " +
                                         "from Mecanico "+
                                         "where idMecanico = ?";
        
        try  {
         stmUsuarios=con.prepareStatement(consulta);
         stmUsuarios.setString(1, id);
         rsUsuarios=stmUsuarios.executeQuery();
        if(rsUsuarios.next())
        {
            resultado = new JefeTaller(rsUsuarios.getString("idMecanico"), rsUsuarios.getString("nombre"),
                                      rsUsuarios.getString("clave"), rsUsuarios.getString("telefonoContacto"),
                                      rsUsuarios.getDate("fechaIngreso"), rsUsuarios.getInt("sueldoBase"));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuarios.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    
    }

    void cambiarContraseña(String nuevaContraseña, String idMecanico) {
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