/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.Date;
/**
 *
 * @author basesdatos
 */
public class GestionPrestamos {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionPrestamos(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    public java.util.List<Mecanico> obtenerUsuariosPrestamos(String id, String nombre){
        return null;
        //return fbd.obtenerUsuariosPrestamos(id, nombre);
    }
    
    public void prestar(Ejemplar e, Mecanico u){
        //fbd.prestar(e,u);
    }
    
    public void devolver(Integer numEjemplar,Integer idLibro, String idUsuario, Date fechaPrestamo){
        //fbd.devolver(numEjemplar, idLibro, idUsuario, fechaPrestamo);
    }
    
    public boolean ejemplarTienePrestamo(Integer idLibro, Integer numEjemplar){
        return false;
        //return fbd.ejemplarTienePrestamo(idLibro, numEjemplar);
    }
}
