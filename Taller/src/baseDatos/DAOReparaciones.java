/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos; 

import aplicacion.EmpleadoPracticas;
import aplicacion.JefeTaller;
import aplicacion.Mecanico;
import aplicacion.Reparacion;
import aplicacion.Repuesto;
import aplicacion.Stock_U_A;
import aplicacion.Subordinado;
import aplicacion.TipoReparacion;
import aplicacion.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


    public void anhadirReparacion(Vehiculo vehiculo, String tipo, List<Repuesto> repuestos, List<Integer> cantidades) {
        Connection con;
        PreparedStatement stmReparaciones=null;
        con=this.getConexion();
        
        String transaccion = "Begin; ";
        transaccion = transaccion + "insert into reparacion(idvehiculo, tiporeparacion) "
                + " values(?, ?); ";
        transaccion = transaccion + "insert into utilizar (idrepuesto, idreparacion, cantidad) values ";
        for (int i=0; i<repuestos.size()-1; i++) 
            transaccion = transaccion + " (?, ?, ?), ";
        transaccion = transaccion + " (?, ?, ?); ";
        transaccion = transaccion + " commit;";
        
        try  {
         stmReparaciones=con.prepareStatement(transaccion);
         stmReparaciones.setString(1, vehiculo.getMatricula());
         stmReparaciones.setString(2, tipo);
         for(int i = 0; i<repuestos.size(); i++) {
             stmReparaciones.setInt(3 + 3*i, repuestos.get(i).getId());
             stmReparaciones.setInt(4 + 3*i, obtenerUltimaReparacionAnhadida().getIdreparacion());
             stmReparaciones.setInt(5 + 3*i, cantidades.get(i));
         }
         stmReparaciones.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    private Reparacion obtenerUltimaReparacionAnhadida() {
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

    public boolean esFinalizada(Integer idreparacion) {
        boolean resultado=true;
        Connection con;
        PreparedStatement stmReparaciones=null;
        ResultSet rsReparaciones;

        con=this.getConexion();
        
        String consulta = "select fechafin " + "from reparacion " + "where idreparacion = ?";
        
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setInt(1, idreparacion);
         rsReparaciones=stmReparaciones.executeQuery();
        while (rsReparaciones.next())
        {
            resultado = rsReparaciones.getString("fechafin")!=null;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void borrarReparacion(Integer idreparacion) {
        Connection con;
        PreparedStatement stmReparaciones=null;

        con=this.getConexion();
        
        String consulta = "delete " + "from reparacion " + "where idreparacion = ?";
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setInt(1, idreparacion);
         stmReparaciones.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void finalizarReparacion(Integer idreparacion, List<Stock_U_A> stock) {
        Connection con;
        PreparedStatement stmReparaciones=null;
        ArrayList<Integer> idsrepuestos = new ArrayList<Integer>();
        ArrayList<Integer> cantidades = new ArrayList<Integer>();
        con=this.getConexion();
        
        String transaccion = "Begin; ";
        for (Stock_U_A s : stock) {
            transaccion = transaccion + "Update repuesto set stock = ? " + "where idrepuesto = ?; ";
            idsrepuestos.add(s.getIdrepuesto());
            cantidades.add(s.getAlmacen()-s.getUsado());
        }
        transaccion = transaccion + "Update reparacion set fechafin = current_date " + "where idreparacion = ?; ";
        transaccion = transaccion + "commit;";
        try  {
         stmReparaciones=con.prepareStatement(transaccion);
         for(int i = 0; i<stock.size(); i++) {
             stmReparaciones.setInt(2*(i+1)-1, cantidades.get(i));
             stmReparaciones.setInt(2*(i+1), idsrepuestos.get(i));
         }
         stmReparaciones.setInt(stock.size()*2+1, idreparacion);
         stmReparaciones.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public List<Subordinado> obtenerMecanicosDisp(Integer idreparacion) {
        java.util.List<Subordinado> resultado = new java.util.ArrayList<Subordinado>();
        Connection con;
        PreparedStatement stmReparaciones=null;
        ResultSet rsAlumnos;

        con=this.getConexion();
        
        String consulta = "select idmecanico, nombre, clave, telefonocontacto, fechaingreso, sueldobase " +
                            "from (SELECT idmecanico " +
                            "	from subordinado " +
                            "	except " +
                            "	select subordinado " +
                            "	from participar " +
                            "	where participar.idreparacion = ?)as sin_participar natural join mecanico as m; ";
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setInt(1, idreparacion);
         rsAlumnos=stmReparaciones.executeQuery();
        while (rsAlumnos.next())
        {
            Subordinado a = new Subordinado(rsAlumnos.getString("idmecanico"), rsAlumnos.getString("clave"), rsAlumnos.getString("nombre"),
                                            rsAlumnos.getString("telefonocontacto"), rsAlumnos.getDate("fechaingreso"), rsAlumnos.getInt("sueldobase"));
            resultado.add(a);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public List<Subordinado> obtenerMecanicosOcup(Integer idreparacion) {
        java.util.List<Subordinado> resultado = new java.util.ArrayList<Subordinado>();
        Connection con;
        PreparedStatement stmReparaciones=null;
        ResultSet rsAlumnos;

        con=this.getConexion();
        
        String consulta = "SELECT idmecanico, nombre, clave, telefonocontacto, fechaingreso, sueldobase, idreparacion " +
                            "FROM participar, mecanico " +
                            "WHERE participar.idreparacion = ? and participar.subordinado = mecanico.idmecanico; ";
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setInt(1, idreparacion);
         rsAlumnos=stmReparaciones.executeQuery();
        while (rsAlumnos.next())
        {
            Subordinado a = new Subordinado(rsAlumnos.getString("idmecanico"), rsAlumnos.getString("clave"), rsAlumnos.getString("nombre"),
                                            rsAlumnos.getString("telefonocontacto"), rsAlumnos.getDate("fechaingreso"), rsAlumnos.getInt("sueldobase"));
            resultado.add(a);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public List<EmpleadoPracticas> obtenerAlumnosDisp(Integer idreparacion, String supervisor) {
        java.util.List<EmpleadoPracticas> resultado = new java.util.ArrayList<EmpleadoPracticas>();
        Connection con;
        PreparedStatement stmReparaciones=null;
        ResultSet rsAlumnos;

        con=this.getConexion();
        
        String consulta = "select idalumno, nombre, tutorid " +
                            "from (SELECT idalumno " +
                            "	from empleadopracticas e  " +
                            "	except " +
                            "	select idalumno " +
                            "	from asistir " +
                            "	where asistir.idreparacion = ?)as sin_participar natural join empleadopracticas e " +
                            "where tutorid = ?; ";
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setInt(1, idreparacion);
         stmReparaciones.setString(2, supervisor);
         rsAlumnos=stmReparaciones.executeQuery();
        while (rsAlumnos.next())
        {
            JefeTaller tutor = obtenerJefeTaller(rsAlumnos.getString("tutorid"));
            int asistidas = obtenerReparacionesAsistidas(rsAlumnos.getInt("idalumno"));
            EmpleadoPracticas a = new EmpleadoPracticas(rsAlumnos.getInt("idalumno"), rsAlumnos.getString("nombre"), tutor, asistidas);
            resultado.add(a);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public List<EmpleadoPracticas> obtenerAlumnosOcup(Integer idreparacion) {
        java.util.List<EmpleadoPracticas> resultado = new java.util.ArrayList<EmpleadoPracticas>();
        Connection con;
        PreparedStatement stmReparaciones=null;
        ResultSet rsAlumnos;

        con=this.getConexion();
        
        String consulta = "SELECT asistir.idalumno, empleadopracticas.nombre, empleadopracticas.tutorid, asistir.idreparacion " +
                            "FROM asistir JOIN empleadopracticas ON asistir.idalumno = empleadopracticas.idalumno " +
                            "WHERE asistir.idreparacion = ?; ";
        
        try  {
         stmReparaciones=con.prepareStatement(consulta);
         stmReparaciones.setInt(1, idreparacion);
         rsAlumnos=stmReparaciones.executeQuery();
        while (rsAlumnos.next())
        {
            JefeTaller tutor = obtenerJefeTaller(rsAlumnos.getString("tutorid"));
            int asistidas = obtenerReparacionesAsistidas(rsAlumnos.getInt("idalumno"));
            EmpleadoPracticas a = new EmpleadoPracticas(rsAlumnos.getInt("idalumno"), rsAlumnos.getString("nombre"), tutor, asistidas);
            resultado.add(a);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparaciones.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void quitarSubordinadoReparacion(Integer idreparacion, String idMecanico) {
        Connection con;
        PreparedStatement stmReparacion=null;

        con=this.getConexion();
        try {
        stmReparacion=con.prepareStatement("delete " + "from participar " + "where idreparacion = ?  and subordinado = ?; ");
        stmReparacion.setInt(1, idreparacion);
        stmReparacion.setString(2, idMecanico);
        stmReparacion.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void quitarAlumnoReparacion(Integer idreparacion, int idalumno) {
        Connection con;
        PreparedStatement stmReparacion=null;

        con=this.getConexion();
        try {
        stmReparacion=con.prepareStatement("delete " + "from asistir " + "where idreparacion = ?  and idalumno = ?; ");
        stmReparacion.setInt(1, idreparacion);
        stmReparacion.setInt(2, idalumno);
        stmReparacion.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void anhadirSubordinadoReparacion(Integer idreparacion, String idMecanico) {
        Connection con;
        PreparedStatement stmReparacion=null;

        con=this.getConexion();
        try {
        stmReparacion=con.prepareStatement("insert into participar (subordinado, idreparacion)  "+
                                        " values (?,?); ");
        stmReparacion.setString(1, idMecanico);
        stmReparacion.setInt(2, idreparacion);
        stmReparacion.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void anhadirAlumnoReparacion(Integer idreparacion, int idalumno) {
        Connection con;
        PreparedStatement stmReparacion=null;

        con=this.getConexion();
        try {
        stmReparacion=con.prepareStatement("insert into asistir (idalumno, idreparacion)  "+
                                        " values (?,?); ");
        stmReparacion.setInt(1, idalumno);
        stmReparacion.setInt(2, idreparacion);
        stmReparacion.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmReparacion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    private JefeTaller obtenerJefeTaller(String id) {
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
    
    private int obtenerReparacionesAsistidas(Integer idAlumno) {
        Connection con = this.getConexion();
        PreparedStatement stmMecanico = null;
        ResultSet rsBonus;
        int resultado=0;

        try {
            String consulta = "SELECT COUNT(*) as practicas FROM Asistir WHERE idAlumno = ?";
            stmMecanico = con.prepareStatement(consulta);
            
            stmMecanico.setInt(1, idAlumno);

            rsBonus=stmMecanico.executeQuery();
            if(rsBonus.next()){
                resultado= rsBonus.getInt("practicas");
            }
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
        return resultado;
    }
}
