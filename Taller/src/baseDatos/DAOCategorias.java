/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.Categoria;
import java.sql.*;
/**
 *
 * @author basesdatos
 */
public class DAOCategorias extends AbstractDAO {
   
    
    public DAOCategorias (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public java.util.List<Categoria> consultarCategorias(){
        java.util.List<Categoria> resultado = new java.util.ArrayList<Categoria>();
        Categoria categoriaActual;
        Connection con;
        PreparedStatement stmCategorias=null;
        ResultSet rsCategorias;

        con=this.getConexion();

        try  {
        stmCategorias=con.prepareStatement("select nombre, descripcion from categoria");
        rsCategorias=stmCategorias.executeQuery();
        while (rsCategorias.next())
        {
            categoriaActual = new Categoria(rsCategorias.getString("nombre"), rsCategorias.getString("descripcion"));
            resultado.add(categoriaActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public boolean existeCategoria(String nombre){
        boolean resultado = false;
        Connection con = this.getConexion();
        PreparedStatement stmCategorias=null;
        ResultSet rsCategorias;
        
        try  {
        String consulta = "select * from categoria as c where nombre ILIKE ?";
        stmCategorias=con.prepareStatement(consulta);
        stmCategorias.setString(1, nombre);
        rsCategorias=stmCategorias.executeQuery();
        if(rsCategorias.isBeforeFirst()){
            resultado = true;
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
     
    public void editarCategoria(String nombre, String descripcion){
        Connection con = this.getConexion();
        PreparedStatement stmCategoria=null;
        
        try  {
        String consulta = "update categoria set descripcion = ? where nombre ILIKE ?";
        stmCategoria=con.prepareStatement(consulta);
        stmCategoria.setString(1, descripcion);
        stmCategoria.setString(2, nombre);
        stmCategoria.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategoria.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void crearCategoria(String nombre, String descripcion){
        Connection con = this.getConexion();
        PreparedStatement stmCategorias=null;
        
        try  {
        String consulta = "insert into categoria (nombre, descripcion) values (?,?)";
        stmCategorias=con.prepareStatement(consulta);
        stmCategorias.setString(1, nombre);
        stmCategorias.setString(2, descripcion);
        int a = stmCategorias.executeUpdate();
        if (a>0){
            System.out.println("Categoria creada correctamente");
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void borrarCategoria(String nombre){
        Connection con = this.getConexion();
        PreparedStatement stmCategorias=null;
        try  {
        String consulta = "delete from categoria where nombre ILIKE ?";
        stmCategorias=con.prepareStatement(consulta);
        stmCategorias.setString(1, nombre);
        int a = stmCategorias.executeUpdate();
        if (a>0){
            System.out.println("Categoria eliminada correctamente");
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

}
