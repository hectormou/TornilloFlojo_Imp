/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

/**
 *
 * @author basesdatos
 */
public abstract class AbstractDAO {
   protected aplicacion.FachadaAplicacion fa;
   private java.sql.Connection conexion;
   
    protected java.sql.Connection getConexion(){
        return this.conexion;
    }
    
    protected void setConexion(java.sql.Connection conexion){
        this.conexion=conexion;
    }
   
   protected void setFachadaAplicacion(aplicacion.FachadaAplicacion fa){
       this.fa=fa;
   }
   
   protected aplicacion.FachadaAplicacion getFachadaAplicacion(){
       return fa;
   }
   
   
}
