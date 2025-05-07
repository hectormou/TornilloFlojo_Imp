/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;



import aplicacion.Mecanico;
import aplicacion.Repuesto;
import aplicacion.Stock_U_A;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author alumnogreibd
 */
public class DAORepuestos extends AbstractDAO{
    
    public DAORepuestos (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Repuesto obtenerRepuesto(Integer id) {
        Repuesto resultado=null;
        Connection con;
        PreparedStatement stmRepuestos=null;
        ResultSet rsRepuestos;

        con=this.getConexion();
        
        String consulta = "select idrepuesto, nombre, descripcion, preciounidad, stock " + "from repuesto " + "where idrepuesto = ?";
        
        
        try  {
         stmRepuestos=con.prepareStatement(consulta);
         stmRepuestos.setInt(1, id);
         rsRepuestos=stmRepuestos.executeQuery();
        while (rsRepuestos.next())
        {
            resultado = new Repuesto(rsRepuestos.getInt("idrepuesto"), rsRepuestos.getString("nombre"), rsRepuestos.getString("descripcion"), 
                                        rsRepuestos.getFloat("preciounidad"), rsRepuestos.getInt("stock"));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmRepuestos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public List<Stock_U_A> obtenerStock_U_A(Integer idreparacion) {
        java.util.List<Stock_U_A> resultado = new java.util.ArrayList<Stock_U_A>();
        Connection con;
        PreparedStatement stmUA=null;
        ResultSet rsUA;

        con=this.getConexion();
        
        String consulta = "select idreparacion, idrepuesto, cantidad, stock " + "from utilizar natural join repuesto " + "where idreparacion = ? ;";
        
        
        try  {
         stmUA=con.prepareStatement(consulta);
         stmUA.setInt(1, idreparacion);
         rsUA=stmUA.executeQuery();
        while (rsUA.next())
        {
            Repuesto r = fa.obtenerRepuesto(rsUA.getInt("idrepuesto"));
            Stock_U_A ua = new Stock_U_A(rsUA.getInt("idreparacion"), rsUA.getInt("idrepuesto"), rsUA.getInt("cantidad"), rsUA.getInt("stock"), r.getNombre());
            resultado.add(ua);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUA.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
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

    public List<Repuesto> obtenerRepuestos(String id, String nombre) {
        java.util.List<Repuesto> resultado = new java.util.ArrayList<Repuesto>();
        Connection con;
        PreparedStatement stmRepuestos=null;
        ResultSet rsRepuestos;

        con=this.getConexion();
        
        String consulta = "select idrepuesto, nombre, descripcion, preciounidad, stock " + 
                "from repuesto where nombre like ? ";
        
        
        try  {
         stmRepuestos=con.prepareStatement(consulta);
         stmRepuestos.setString(1, nombre+"%");
         if(id != null && !id.isBlank()) {
             stmRepuestos = con.prepareStatement(consulta + " and idrepuesto = ?");
             stmRepuestos.setString(1, nombre+"%");
             stmRepuestos.setInt(2, Integer.parseInt(id));
         }
         rsRepuestos=stmRepuestos.executeQuery();
        while (rsRepuestos.next())
        {
            resultado.add(new Repuesto(rsRepuestos.getInt("idrepuesto"), rsRepuestos.getString("nombre"), rsRepuestos.getString("descripcion"), rsRepuestos.getFloat("preciounidad"), rsRepuestos.getInt("stock")));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmRepuestos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void nuevaSolicitud(Repuesto repuesto, String cantidad, Mecanico mecanico) {
        Connection con;
        PreparedStatement stmRepuesto=null;

        con=this.getConexion();
        try {
        stmRepuesto=con.prepareStatement("insert into solicitud (repuesto, fecha, cantidad, idmecanico)  "+
                                        " values (?, ?, ?, ?) ");
        stmRepuesto.setInt(1, repuesto.getId());
        stmRepuesto.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        stmRepuesto.setInt(3, Integer.parseInt(cantidad));
        stmRepuesto.setString(4, mecanico.getIdMecanico());
        stmRepuesto.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmRepuesto.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
}
