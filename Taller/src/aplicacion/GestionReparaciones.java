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
}
