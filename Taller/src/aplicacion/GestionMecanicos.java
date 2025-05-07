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
    public Mecanico obtenerMecanico(String mecanicoid) {
        return fbd.obtenerMecanico(mecanicoid);
    }
    public void borrarVehiculo(String matricula) {
        fbd.eliminarVehiculo(matricula);   
    }

    boolean vehiculoTuvoReparaciones(String matricula) {
        return fbd.vehiculoTuvoReparaciones(matricula);    
    }
}
