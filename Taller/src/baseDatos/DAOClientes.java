/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Cliente;
import java.sql.*;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOClientes extends AbstractDAO {
    
    public DAOClientes (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public Cliente obtenerCliente(String dni) {
        Cliente resultado=null;
        Connection con;
        PreparedStatement stmCliente=null;
        ResultSet rsCliente;

        con=this.getConexion();
        
        String consulta = "select dni, nombre, telefonocontacto " +
                                         "from Cliente "+
                                         "where dni = ?";
        
        try  {
         stmCliente=con.prepareStatement(consulta);
         stmCliente.setString(1, dni);
         rsCliente=stmCliente.executeQuery();
        if(rsCliente.next())
        {
            resultado = new Cliente(rsCliente.getString("dni"), rsCliente.getString("nombre"), rsCliente.getString("telefonocontacto"));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCliente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public List<Cliente> obtenerClientes() {
        java.util.List<Cliente> resultado = new java.util.ArrayList<Cliente>();
        Connection con;
        PreparedStatement stmClientes=null;
        ResultSet rsClientes;

        con=this.getConexion();
        
        String consulta = "select  dni, nombre, telefonocontacto " + "from cliente ";
        
        
        try  {
         stmClientes=con.prepareStatement(consulta);
         rsClientes=stmClientes.executeQuery();
        while (rsClientes.next())
        {
            Cliente cliente = new Cliente(rsClientes.getString("DNI"), rsClientes.getString("nombre"), rsClientes.getString("telefonoContacto"));
            resultado.add(cliente);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmClientes.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public void eliminarCliente(Cliente c) {
        Connection con;
        PreparedStatement stmCliente=null;

        con=this.getConexion();
        
        try  {
            stmCliente=con.prepareStatement("delete " + "from cliente "+" where dni=? ");
            stmCliente.setString(1, c.getDni());
            stmCliente.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCliente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void anhadirCliente(Cliente c) {
        Connection con;
        PreparedStatement stmCliente=null;

        con=this.getConexion();
        
        try  {
            stmCliente=con.prepareStatement("insert " + "into cliente "+" values(?, ?, ?) ");
            stmCliente.setString(1, c.getDni());
            stmCliente.setString(2, c.getNombre());
            stmCliente.setString(3, c.getTelefonoContacto());
            stmCliente.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCliente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

}
