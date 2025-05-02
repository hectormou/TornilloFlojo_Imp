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
public class GestionVehiculos{
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionVehiculos(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public Vehiculo obtenerVehiculo(String matricula) {
        return fbd.obtenerVehiculo(matricula);
    }
    
    public java.util.List<Vehiculo> obtenerVehiculos(String matricula, String cliente, String marca, String modelo, String supervisor, String combustible){
        return fbd.consultarCatalogo(matricula, cliente, marca, modelo, supervisor, combustible);
    }

    public void actualizarVehiculo(Vehiculo v){
          fbd.modificarVehiculo(v);
    }

    public void nuevoVehiculo(Vehiculo v) {
          fbd.nuevoVehiculo(v);
    }

    public void eliminarVehiculo(String matricula) {
        fbd.eliminarVehiculo(matricula);
    }
}
