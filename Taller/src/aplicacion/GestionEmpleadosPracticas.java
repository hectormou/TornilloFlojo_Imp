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
public class GestionEmpleadosPracticas {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionEmpleadosPracticas(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    public ArrayList<EmpleadoPracticas> buscarPracticas(String nombreTutor){
        return fbd.buscarPracticas(nombreTutor);
    }
}
