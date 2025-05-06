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
    
    public ArrayList<Mecanico> buscarMecanicos(String id, String modo){
        ArrayList<Mecanico> resultado = new ArrayList<>();
        Mecanico mecanicoActual = null;
        Connection con;
        PreparedStatement stmMecanico=null;
        ResultSet rsMecanico;

        con=this.getConexion();
        
        String consulta = "select  idMecanico, nombre, clave, telefonoContacto, fechaIngreso, sueldoBase " +
                                         " from mecanico";
        if(id!=null && !id.trim().isEmpty()){
            consulta = consulta + " where idMecanico = ? ";
        }
        
        switch(modo){
            case "Nombre": consulta = consulta + " ORDER BY nombre"; break;
            case "Sueldo asc.": consulta = consulta + " ORDER BY sueldoBase ASC"; break;
            case "Sueldo desc.": consulta = consulta + " ORDER BY sueldoBase DESC"; break;
        }
        
        try  {
         stmMecanico=con.prepareStatement(consulta);
         if(id!=null && !id.trim().isEmpty()){
            stmMecanico.setString(1, id);
        }
         rsMecanico=stmMecanico.executeQuery();
        while(rsMecanico.next())
        {
            if(esJefeTaller(rsMecanico.getString("idMecanico"))){
                mecanicoActual = new JefeTaller(rsMecanico.getString("idMecanico"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"), rsMecanico.getInt("sueldoBase"));
            }
            else if(esSubordinado(rsMecanico.getString("idMecanico"))){
                mecanicoActual = new Subordinado(rsMecanico.getString("idMecanico"), rsMecanico.getString("clave"),
                                      rsMecanico.getString("nombre"), rsMecanico.getString("telefonoContacto"),
                                      rsMecanico.getDate("fechaIngreso"), rsMecanico.getInt("sueldoBase"));
            }
            resultado.add(mecanicoActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public boolean puedeDespedir(String id){
        boolean resultado = true;
        Connection con = this.getConexion();
        PreparedStatement stmMecanico=null;
        ResultSet rsMecanico;
        try  {
        String consulta = "select 1 where exists (select 1 from vehiculo where supervisor = ?) " +
                                         " or exists (select 1 from participar where subordinado = ?) ";
        stmMecanico=con.prepareStatement(consulta);
        stmMecanico.setString(1, id);
        stmMecanico.setString(2, id);
        rsMecanico=stmMecanico.executeQuery();
        if(rsMecanico.next()) resultado = false;
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public boolean despedirMecanico(String id){
        Connection con = this.getConexion();
        PreparedStatement stmMecanico=null;
        if(!puedeDespedir(id)) return false;
        try{
        String consulta = "delete from mecanico where idmecanico = ?";
        stmMecanico=con.prepareStatement(consulta);
        stmMecanico.setString(1, id);
        stmMecanico.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return true;
    }
    
    public void anhadirJefeDeTaller(String id, String nombre, String clave, String tlf, Integer sueldo){
        Connection con = this.getConexion();
        PreparedStatement stmMecanico=null;
        PreparedStatement stmJefeDeTaller=null;
        Date fechaHoy = Date.valueOf(java.time.LocalDate.now());
        try{
        String consulta = "insert into mecanico (idMecanico, nombre, clave, telefonoContacto, sueldoBase, fechaIngreso) "+
                "values (?, ?, ?, ?, ?, ?)";
        stmMecanico=con.prepareStatement(consulta);
        stmMecanico.setString(1, id);
        stmMecanico.setString(2, nombre);
        stmMecanico.setString(3, clave);
        stmMecanico.setString(4, tlf);
        stmMecanico.setInt(5, sueldo);
        stmMecanico.setDate(6, fechaHoy);
        consulta = "insert into JefeTaller (idMecanico) values (?)";
        stmJefeDeTaller=con.prepareStatement(consulta);
        stmJefeDeTaller.setString(1, id);
        stmMecanico.executeUpdate();
        stmJefeDeTaller.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
          try {stmJefeDeTaller.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void anhadirSubordinado(String id, String nombre, String clave, String tlf, Integer sueldo){
        Connection con = this.getConexion();
        PreparedStatement stmMecanico=null;
        PreparedStatement stmSubordinado=null;
        Date fechaHoy = Date.valueOf(java.time.LocalDate.now());
        try{
        String consulta = "insert into mecanico (idMecanico, nombre, clave, telefonoContacto, sueldoBase, fechaIngreso) "+
                "values (?, ?, ?, ?, ?, ?)";
        stmMecanico=con.prepareStatement(consulta);
        stmMecanico.setString(1, id);
        stmMecanico.setString(2, nombre);
        stmMecanico.setString(3, clave);
        stmMecanico.setString(4, tlf);
        stmMecanico.setInt(5, sueldo);
        stmMecanico.setDate(6, fechaHoy);
        consulta = "insert into Subordinado (idMecanico) values (?)";
        stmSubordinado=con.prepareStatement(consulta);
        stmSubordinado.setString(1, id);
        stmMecanico.executeUpdate();
        stmSubordinado.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
          try {stmSubordinado.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
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
    
    public boolean puedeAscender(String id){
        Connection con = this.getConexion();
        PreparedStatement stmMecanico=null;
        ResultSet rsMecanico;
        
        try{
        String consulta = "select 1 from participar where subordinado = ? ";
        stmMecanico=con.prepareStatement(consulta);
        stmMecanico.setString(1, id);
        rsMecanico = stmMecanico.executeQuery();
        if(rsMecanico.next()){
            return false;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return true;
    }
    
    public boolean updateMecanico(String id, String nombre, String clave, String tlf, Integer sueldo){
        Connection con = this.getConexion();
        PreparedStatement stmMecanico=null;
        if(!puedeAscender(id)){
            return false;
        }
        
        try{
        String consulta = "update mecanico set nombre = ?, clave = ?, telefonoContacto = ?, sueldoBase = ? "+
                "where idMecanico = ?";
        stmMecanico=con.prepareStatement(consulta);
        stmMecanico.setString(1, nombre);
        stmMecanico.setString(2, clave);
        stmMecanico.setString(3, tlf);
        stmMecanico.setInt(4, sueldo);
        stmMecanico.setString(5, id);
        stmMecanico.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmMecanico.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return true;
    }
    
    public void ascenderMecanico(String id){
        Connection con = this.getConexion();
        PreparedStatement stmSubordinado=null;
        PreparedStatement stmJefeTaller=null;
        
        try{
        String consulta = "delete from Subordinado where idMecanico = ?";
        stmSubordinado=con.prepareStatement(consulta);
        stmSubordinado.setString(1, id);
        consulta = "insert into JefeTaller(idMecanico) values(?)";
        stmJefeTaller=con.prepareStatement(consulta);
        stmJefeTaller.setString(1, id);
        stmSubordinado.executeUpdate();
        stmJefeTaller.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSubordinado.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
          try {stmJefeTaller.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
}