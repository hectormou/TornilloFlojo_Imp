/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.util.List;


/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    
    //
    GestionCategorias cc;
    GestionPrestamos cp;
    //
    //NECESARIOS
    GestionMecanicos cm;
    GestionVehiculos cv;
    GestionClientes cl;
    GestionReparaciones cr;
    
    
    
    public FachadaAplicacion(){
        fgui=new gui.FachadaGui(this);
        fbd= new baseDatos.FachadaBaseDatos(this);

         //
        cc = new GestionCategorias(fgui, fbd);
        cp = new GestionPrestamos(fgui, fbd);
        //
        //NECESARIOS
        cv = new GestionVehiculos(fgui, fbd);
        cm = new GestionMecanicos(fgui, fbd);
        cl = new GestionClientes(fgui, fbd);
        cr = new GestionReparaciones(fgui, fbd);
    }

    public static void main(String args[]) {
        FachadaAplicacion fa;

        fa = new FachadaAplicacion();
        fa.iniciaInterfazMecanico();
    }
 
    public void iniciaInterfazMecanico(){
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e){
        fgui.muestraExcepcion(e);
    }
 
    public void comprobarMecanico(Mecanico m){
        fgui.comprobarMecanico(m);
    }
 
    public Vehiculo obtenerVehiculo(String matricula) {
        return cv.obtenerVehiculo(matricula);
    }
 
    public java.util.List<Vehiculo> obtenerVehiculos(String matricula, String cliente, String marca, String modelo, String supervisor, String combustible){
        return cv.obtenerVehiculos(matricula, cliente,  marca,  modelo, supervisor, combustible);
    }

    public void actualizarVehiculo(Vehiculo v){
        cv.actualizarVehiculo(v);
    }

    public Mecanico validarMecanico(String idMecanico, String clave){
        return cm.validarMecanico(idMecanico, clave);
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public java.util.List<Mecanico> obtenerUsuarios(String id, String nombre){
        return cm.obtenerUsuarios(id, nombre);
    }

    public boolean existeUsuario(String id){
        return cm.existeUsuario(id);
    }
    
    public void editarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        cm.editarUsuario(id, clave, nombre, direccion, email, tipo);
    }

    public void crearUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        cm.crearUsuario(id, clave, nombre, direccion, email, tipo);
    }

    public void borrarUsuario(String id){
        cm.borrarUsuario(id);
    }
/////////////////////////////////////////////////////////////////////////////////////

    public List<String> obtenerIDsJefesTaller() {
        return cm.obtenerIDsJefesTaller();
    }

    public JefeTaller obtenerJefeTaller(String id) {
        return cm.obtenerJefeTaller(id);
    }
    
    public Cliente obtenerCliente(String propietarioDNI) {
        return cl.obtenerCliente(propietarioDNI);
    }

    public List<Cliente> obtenerClientes() {
        return cl.obtenerClientes();
    }
    
    public void nuevoVehiculo(Vehiculo v) {
        cv.nuevoVehiculo(v);
    }

    public void eliminarVehiculo(String matricula) {
        cv.eliminarVehiculo(matricula);
    }

    public List<Reparacion> obtenerReparaciones(String matricula) {
        return cr.obtenerReparaciones(matricula);
    }
    
    public TipoReparacion obtenerTipoReparacion(String nombre) {
        return cr.obtenerTipoReparacion(nombre);
    }
    public boolean vehiculoTieneReparacionesPendientes(Vehiculo v) {
        return cr.vehiculoTieneReparacionesPendientes(v);
    }
    public List<Vehiculo> obtenerVehiculosCliente(Cliente c) {
        return cv.obtenerVehiculosCliente(c);
    }
    public void eliminarCliente(Cliente c) {
        cl.eliminarCliente(c);
    }
    public void anhadirCliente(Cliente c) {
        cl.anhadirCliente(c);
    }
}
