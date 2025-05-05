/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Reparacion;
import aplicacion.Repuesto;
import aplicacion.Stock_U_A;
import aplicacion.TipoReparacion;
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
        
        String consulta = "select idreparacion, idrepuesto, cantidad, stock " + "from utilizar natural join repuesto " + "where idreparacion = ?";
        
        
        try  {
         stmUA=con.prepareStatement(consulta);
         stmUA.setInt(1, idreparacion);
         rsUA=stmUA.executeQuery();
        while (rsUA.next())
        {
            Stock_U_A ua = new Stock_U_A(rsUA.getInt("idreparacion"), rsUA.getInt("idrepuesto"), rsUA.getInt("cantidad"), rsUA.getInt("stock"));
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
    
}
