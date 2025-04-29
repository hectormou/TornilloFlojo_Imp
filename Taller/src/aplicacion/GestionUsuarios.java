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
public class GestionUsuarios {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    
  public Mecanico validarMecanico(String idMecanico, String clave){
      return fbd.validarMecanico(idMecanico, clave);
  }
  
 public java.util.List<Mecanico> obtenerUsuarios(String id, String nombre){
    return fbd.consultarUsuarios(id, nombre);
 }
  
 public boolean existeUsuario(String id){
     return fbd.existeUsuario(id);
 }
 
public void editarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        fbd.editarUsuario(id, clave, nombre, direccion, email, tipo);
    }

public void crearUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        fbd.crearUsuario(id, clave, nombre, direccion, email, tipo);
    }

public void borrarUsuario(String id){
        fbd.borrarUsuario(id);
    }
 
}
