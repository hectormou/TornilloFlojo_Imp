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
public class GestionSolicitudes {
     FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionSolicitudes (FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    Float obtenerPrecioSolicitud(Integer idRepuesto) {
        return fbd.obtenerPrecioSolicitud(idRepuesto);
    }

    List<Solicitud> obtenerSolicitudes() {
        return fbd.obtenerSolicitudes();
    }

    void eliminarSolicitud(Solicitud solicitudSeleccionada) {
        fbd.eliminarSolicitud(solicitudSeleccionada);
    }

    void aumentarStockPorSolicitud(Solicitud solicitudSeleccionada) {
        fbd.aumentarStockPorSolicitud(solicitudSeleccionada);
    }
    
    
}
