/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionReparaciones {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionReparaciones (FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    
    public List<Reparacion> obtenerReparaciones(String matricula) {
        return fbd.obtenerReparaciones(matricula);
    }

    public TipoReparacion obtenerTipoReparacion(String nombre) {
        return fbd.obtenerTipoReparacion(nombre);
    }

    public boolean vehiculoTieneReparacionesPendientes(Vehiculo v) {
        List<Reparacion> reparaciones = fbd.obtenerReparaciones(v.getMatricula());
        for (Reparacion r : reparaciones) {
            if ((r.getFecha_fin()) == null) return true;
        }
        return false;
    }


    public Reparacion obtenerReparacion(Integer id) {
        return fbd.obtenerReparacion(id);
    }

    public Reparacion anhadirReparacion(Vehiculo vehiculo, String tipo, Mecanico mecanico) {
        fbd.anhadirReparacion(vehiculo, tipo, mecanico);
        return fbd.obtenerUltimaReparacionAnhadida();
    }

    public List<TipoReparacion> obtenerTipoReparaciones() {
        return fbd.obtenerTipoReparaciones();
    }

    public void borrarReparacion(Integer idreparacion) {
        if(fbd.esFinalizadaReparacion(idreparacion))
            fgui.muestraExcepcion("No se puede borrar, ya es finalizada!!");
        else fbd.borrarReparacion(idreparacion);
    }

    public void finalizarReparacion(Integer idreparacion, List<Stock_U_A> stock) {
        fbd.finalizarReparacion(idreparacion, stock);
    }
}
