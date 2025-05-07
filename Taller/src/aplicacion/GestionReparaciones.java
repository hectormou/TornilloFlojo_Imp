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

    public void anhadirReparacion(Vehiculo vehiculo, String tipo, List<Repuesto> repuestos, List<Integer> cantidades) {
        fbd.anhadirReparacion(vehiculo, tipo, repuestos, cantidades);
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

    public List<Subordinado> obtenerMecanicosDisp(Integer idreparacion) {
        return fbd.obtenerMecanicosDisp(idreparacion);
    }

    public List<Subordinado> obtenerMecanicosOcup(Integer idreparacion) {
        return fbd.obtenerMecanicosOcup(idreparacion);    }

    public List<EmpleadoPracticas> obtenerAlumnosDisp(Integer idreparacion, String supervisor) {
        return fbd.obtenerAlumnosDisp(idreparacion, supervisor);
    }

    public List<EmpleadoPracticas> obtenerAlumnosOcup(Integer idreparacion) {
        return fbd.obtenerAlumnosOcup(idreparacion);    
    }

    public void quitarSubordinadoReparacion(Integer idreparacion, String idMecanico) {
        if(!fbd.esFinalizadaReparacion(idreparacion)) {
            fbd.quitarSubordinadoReparacion(idreparacion, idMecanico);
        }
    }

    public void quitarAlumnoReparacion(Integer idreparacion, int idalumno) {
        if(!fbd.esFinalizadaReparacion(idreparacion)) {
            fbd.quitarAlumnoReparacion(idreparacion, idalumno);
        }
    }

    public void anhadirSubordinadoReparacion(Integer idreparacion, String idMecanico) {
        if(!fbd.esFinalizadaReparacion(idreparacion)) {
            fbd.anhadirSubordinadoReparacion(idreparacion, idMecanico);
        }
    }

    public void anhadirAlumnoReparacion(Integer idreparacion, int idalumno) {
        if(!fbd.esFinalizadaReparacion(idreparacion)) {
            fbd.anhadirAlumnoReparacion(idreparacion, idalumno);
        }
    }
}
