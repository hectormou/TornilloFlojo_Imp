/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.EmpleadoPracticas;
import aplicacion.JefeTaller;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author basesdatos
 */
public class DAOPracticas extends AbstractDAO {

   public DAOPracticas (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
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
   
    public int obtenerReparacionesAsistidas(Integer idAlumno) {
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
   
   public ArrayList<EmpleadoPracticas> buscarPracticas(String nombreTutor){
        ArrayList<EmpleadoPracticas> resultado = new ArrayList<>();
        EmpleadoPracticas practicasActual;
        Integer reparacionesRealizadas;
        JefeTaller tutor;
        Connection con;
        PreparedStatement stmPracticas=null;
        ResultSet rsPracticas;

        con=this.getConexion();
        
        String consulta = "select e.idAlumno, e.nombre, j.idMecanico "+
                " from empleadoPracticas e join JefeTaller j on e.tutorID = j.idMecanico join Mecanico m on m.idMecanico = j.idMecanico"+
                " where m.nombre ILIKE ?";
                
        try  {
         stmPracticas=con.prepareStatement(consulta);
         stmPracticas.setString(1, "%" + nombreTutor + "%");
         rsPracticas=stmPracticas.executeQuery();
         while(rsPracticas.next()){
             reparacionesRealizadas = this.obtenerReparacionesAsistidas(rsPracticas.getInt("idAlumno"));
             tutor = obtenerJefeTaller(rsPracticas.getString("idMecanico"));
             practicasActual = new EmpleadoPracticas(rsPracticas.getInt("idAlumno"), rsPracticas.getString("nombre"), tutor, reparacionesRealizadas);
             resultado.add(practicasActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPracticas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
   
    public boolean despedirPracticas(Integer id){
        Connection con = this.getConexion();
        PreparedStatement stmPracticas=null;
        if(!puedeDespedir(id)) return false;
        try{
        String consulta = "delete from EmpleadoPracticas where idAlumno = ?";
        stmPracticas=con.prepareStatement(consulta);
        stmPracticas.setInt(1, id);
        stmPracticas.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPracticas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return true;
    }
   
    public boolean puedeDespedir(Integer id){
        boolean resultado = true;
        Connection con = this.getConexion();
        PreparedStatement stmPracticas=null;
        ResultSet rsPracticas;
        try  {
        String consulta = "select 1 from asistir a join reparacion r on a.idReparacion = r.idReparacion "+
                "where a.idAlumno = ? and r.fechaFin is not null";
        stmPracticas=con.prepareStatement(consulta);
        stmPracticas.setInt(1, id);
        rsPracticas=stmPracticas.executeQuery();
        if(rsPracticas.next()) resultado = false;
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPracticas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public ArrayList<JefeTaller> obtenerTutores(String nombreTutor){
        ArrayList<JefeTaller> resultado = new ArrayList<>();
        JefeTaller tutorActual;
        Connection con = this.getConexion();
        PreparedStatement stmPracticas=null;
        ResultSet rsPracticas;
        try  {
        String consulta = "select m.nombre, m.idMecanico "+
                " from EmpleadoPracticas e join JefeTaller j on e.tutorID = j.idMecanico join Mecanico m on e.idMecanico = m.idMecanico "+
                " where m.nombre ILIKE ?";
        stmPracticas=con.prepareStatement(consulta);
        stmPracticas.setString(1, "%"+nombreTutor+"%");
        rsPracticas=stmPracticas.executeQuery();
        while(rsPracticas.next()){
            tutorActual = new JefeTaller();
            //CAMBIAR EL  CONSTRUCTORS
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPracticas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}