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

    boolean vehiculoTieneReparacionesPendientes(Vehiculo v) {
        List<Reparacion> reparaciones = fbd.obtenerReparaciones(v.getMatricula());
        for (Reparacion r : reparaciones) {
            if ((r.getFecha_fin()) == null) return true;
        }
        return false;
    }

    public List<Repuesto> getTotalRepuestos() {
        return fbd.getTotalRepuestos();
    }

    Reparacion anhadirReparacion(Vehiculo vehiculo, String tipo, Mecanico mecanico) {
        fbd.anhadirReparacion(vehiculo, tipo, mecanico);
        return fbd.obtenerUltimaReparacionAnhadida();
    }

    Repuesto obtenerRepuesto(String nombre) {
        return fbd.obtenerRepuesto(nombre);
    }

    void anhadirRepuestoNecesario(Integer idReparacion, Integer idRepuesto, int cantidad) {
        fbd.anhadirRepuestoNecesario(idReparacion, idRepuesto, cantidad);
    }

    List<TipoReparacion> obtenerTipoReparaciones() {
        return fbd.obtenerTipoReparaciones();
    }
}
