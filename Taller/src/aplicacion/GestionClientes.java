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
public class GestionClientes {
    
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionClientes (FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    public Cliente obtenerCliente(String dni) {
        return fbd.obtenerClientes(dni);
    }
    
    public List<Cliente> obtenerClientes() {
        return fbd.obtenerClientes();
    }
    public void eliminarCliente(Cliente c) {
        fbd.eliminarCliente(c);
    }
    public void anhadirCliente(Cliente c) {
        fbd.anhadirCliente(c);
    }
}
