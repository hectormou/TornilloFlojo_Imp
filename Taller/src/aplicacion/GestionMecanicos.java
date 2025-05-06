/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author basesdatos
 */
public class GestionMecanicos {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionMecanicos(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    
    public Mecanico validarMecanico(String idMecanico, String clave){
        return fbd.validarMecanico(idMecanico, clave);
    }

    public List<String> obtenerIDsJefesTaller() {
        return fbd.obtenerIDsJefesTaller();
    }

    public JefeTaller obtenerJefeTaller(String id) {
        return fbd.obtenerJefesTaller(id);
    }

    public void cambiarContraseña(String nuevaContraseña, String idMecanico) {
        fbd.cambiarContraseña(nuevaContraseña, idMecanico);
    }

    public void editarMecanico(String clave, String nombre, Date fechaIngreso, int sueldoBase, String idMecanico) {
        fbd.editarMecanico(clave, nombre, fechaIngreso, sueldoBase, idMecanico);
    }
    
    public ArrayList<Mecanico> buscarMecanicos(String id, String modo){
        return fbd.buscarMecanicos(id, modo);
    }
    
    public boolean despedirMecanico(String id){
        return fbd.despedirMecanico(id);
    }
    
    public void anhadirJefeDeTaller(String id, String nombre, String clave, String tlf, Integer sueldo){
        fbd.anhadirJefeDeTaller(id, nombre, clave, tlf, sueldo);
    }
 
    public void anhadirSubordinado(String id, String nombre, String clave, String tlf, Integer sueldo){
        fbd.anhadirSubordinado(id, nombre, clave, tlf, sueldo);
    }
}
