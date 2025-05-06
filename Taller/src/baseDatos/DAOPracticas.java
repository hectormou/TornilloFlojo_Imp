/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.EmpleadoPracticas;
import aplicacion.JefeTaller;
import aplicacion.Practicas;
import aplicacion.Subordinado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author basesdatos
 */
public class DAOPracticas extends AbstractDAO {

   public DAOPracticas (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
   
   public ArrayList<EmpleadoPracticas> buscarPracticas(String nombreTutor){
        ArrayList<EmpleadoPracticas> resultado = new ArrayList<>();
        EmpleadoPracticas practicasActual = null;
        Integer reparacionesRealizadas;
        Connection con;
        PreparedStatement stmPracticas=null;
        ResultSet rsPracticas;

        con=this.getConexion();
        
        String consulta = "select e.idAlumno, e.nombre, m.nombre"+
                "from (empleadoPracticas as e join JefeTaller as j on e.tutorID = j.idMecanico) join Mecanico as m on m.idMecanico = j.idMecanico"+
                "where m.nombre ILIKE ?";
                
        try  {
         stmPracticas=con.prepareStatement(consulta);
         stmPracticas.setString(1, "%" + nombreTutor + "%");
         rsPracticas=stmPracticas.executeQuery();
         while(rsPracticas.next()){
             reparacionesRealizadas = ;
             practicasActual = new EmpleadoPracticas
             resultado.add(mecanicoActual);
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