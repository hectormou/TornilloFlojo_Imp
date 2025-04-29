/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.Ejemplar;
import aplicacion.Mecanico;
import aplicacion.TipoUsuario;
import java.sql.*;
import java.util.Date;
import java.util.HashSet;
/**
 *
 * @author basesdatos
 */
public class DAOPrestamos extends AbstractDAO {

   public DAOPrestamos (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
   
   public java.util.List<Mecanico> obtenerUsuariosPrestamos(String id, String nombre){
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
            usuarioActual.setPrestamosVencidos(NPrestamos(usuarioActual));
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
   
   public int NPrestamos(Mecanico u){
        Connection con = this.getConexion();
        PreparedStatement stmPrestamos=null;
        ResultSet rs;
        int resultado = 0;
        
        try  {
        String consulta = "SELECT count(*) as cantidad_vencidos " +
                  "FROM prestamos " +
                  "WHERE usuario ILIKE ? " +
                  "AND fecha_devolucion IS NULL " +
                  "AND fecha_vencimiento < CURRENT_DATE;";
        stmPrestamos=con.prepareStatement(consulta);
        stmPrestamos.setString(1, u.getIdUsuario());
        rs = stmPrestamos.executeQuery();
        if(rs.next()){
            resultado = rs.getInt("cantidad_vencidos");
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }   
   
   
   public void prestar(Ejemplar ej, Mecanico u){
        Connection con = this.getConexion();
        PreparedStatement stmPrestamos=null;
        
        try  {
        String consulta = "insert into prestamos (usuario, libro, ejemplar) values (?,?,?)";
        stmPrestamos=con.prepareStatement(consulta);
        stmPrestamos.setString(1, u.getIdUsuario());
        stmPrestamos.setInt(2, ej.getLibro().getIdLibro());
        stmPrestamos.setInt(3, ej.getNumEjemplar());
        int a = stmPrestamos.executeUpdate();
        if (a>0){
            System.out.println("Prestamo creada correctamente");
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }   
   
    public void devolver(Integer numEjemplar,Integer idLibro, String idUsuario, Date fechaPrestamo){
        Connection con = this.getConexion();
        PreparedStatement stmPrestamos=null;
        
        try  {
        String consulta = "update prestamos set fecha_devolucion = CURRENT_DATE where usuario = ? and libro = ? and ejemplar = ? and fecha_prestamo = ?";
        stmPrestamos=con.prepareStatement(consulta);
        stmPrestamos.setString(1, idUsuario);
        stmPrestamos.setInt(2, idLibro);
        stmPrestamos.setInt(3, numEjemplar);
        stmPrestamos.setDate(4, new java.sql.Date(fechaPrestamo.getTime()));
        int a = stmPrestamos.executeUpdate();
        if (a>0){
            System.out.println("Devolución realizada correctamente");
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public boolean ejemplarTienePrestamo(Integer idLibro, Integer numEjemplar){
        Connection con = this.getConexion();
        PreparedStatement stmPrestamos=null;
        ResultSet rsPrestamos;
        boolean resultado = false;
        
        try  {
        String consulta = "select * from prestamos where libro = ? and ejemplar = ?";
        stmPrestamos=con.prepareStatement(consulta);
        stmPrestamos.setInt(1, idLibro);
        stmPrestamos.setInt(2, numEjemplar);
        rsPrestamos = stmPrestamos.executeQuery();
        if(rsPrestamos.next()) resultado = true;
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
