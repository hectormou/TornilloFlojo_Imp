/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.JefeTaller;
import aplicacion.Mecanico;
import aplicacion.Subordinado;
import aplicacion.TipoUsuario;
import java.sql.*;
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
        stmMecanico=con.prepareStatement("select * from JefeTaller where idEmpleado = ?");
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
        stmMecanico=con.prepareStatement("select * from Subordinado where idEmpleado = ?");
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
        stmMecanico=con.prepareStatement("select idEmpleado, clave, nombre, telefonoContacto, fechaIngreso "+
                                        "from Mecanico"+
                                        "where idEmpleado = ? and clave = ?");
        stmMecanico.setString(1, idMecanico);
        stmMecanico.setString(2, clave);
        rsMecanico=stmMecanico.executeQuery();
        if (rsMecanico.next())
        {
            if(esJefeTaller(idMecanico)){
                resultado = new JefeTaller(rsMecanico.getString("idEmpleado"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"));
            }
            else if(esSubordinado(idMecanico)){
                resultado = new Subordinado(rsMecanico.getString("idEmpleado"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"));
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
        while (rsUsuarios.next())
        {
            usuarioActual = new Mecanico(rsUsuarios.getString("id_usuario"), rsUsuarios.getString("clave"),
                                      rsUsuarios.getString("nombre"), rsUsuarios.getString("direccion"),
                                      rsUsuarios.getString("email"), TipoUsuario.valueOf(rsUsuarios.getString("tipo_usuario")));
            resultado.add(usuarioActual);
        }

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

   
}
