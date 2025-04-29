/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
/**
 *
 * @author basesdatos
 */
public class GestionCategorias {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionCategorias(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    
    public java.util.List<Categoria> obtenerCategorias(){
        return fbd.obtenerCategorias();
    }
    
    public boolean existeCategoria(String nombre){
        return fbd.existeCategoria(nombre);
    }
    
    public void editarCategoria(String nombre, String descripcion){
        fbd.editarCategoria(nombre,descripcion);
    }
    
    public void crearCategoria(String nombre, String descripcion){
        fbd.crearCategoria(nombre,descripcion);
    }
    
    public void borrarCategoria(String nombre){
        fbd.borrarCategoria(nombre);
    }
 
}
