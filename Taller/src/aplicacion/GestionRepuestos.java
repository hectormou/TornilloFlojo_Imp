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
public class GestionRepuestos {
     FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionRepuestos (FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public Repuesto obtenerRepuesto(Integer id) {
        return fbd.obtenerRepuesto(id);
    }
    
    public List<Repuesto> getTotalRepuestos() {
        return fbd.getTotalRepuestos();
    }

    public List<Stock_U_A> obtenerStock_U_A(Integer idreparacion) {
        return fbd.obtenerStock_U_A(idreparacion);
    }
    /*
    public Repuesto obtenerRepuesto(String nombre) {
        return fbd.obtenerRepuesto(nombre);
    }
*/
    public void anhadirRepuestoNecesario(Integer idReparacion, Integer idRepuesto, int cantidad) {
        fbd.anhadirRepuestoNecesario(idReparacion, idRepuesto, cantidad);
    }
}
