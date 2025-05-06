/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    private final gui.FachadaGui fgui;
    private final baseDatos.FachadaBaseDatos fbd;
    
    private final GestionMecanicos cm;
    private final GestionVehiculos cv;
    private final GestionClientes cl;
    private final GestionReparaciones cr;
    private final GestionRepuestos crp;
    
    
    
    public FachadaAplicacion(){
        fgui=new gui.FachadaGui(this);
        fbd= new baseDatos.FachadaBaseDatos(this);

        cv = new GestionVehiculos(fgui, fbd);
        cm = new GestionMecanicos(fgui, fbd);
        cl = new GestionClientes(fgui, fbd);
        cr = new GestionReparaciones(fgui, fbd);
        crp = new GestionRepuestos(fgui, fbd);
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

    public void cambiarContraseña(String nuevaContraseña, String idMecanico) {
        cm.cambiarContraseña(nuevaContraseña, idMecanico);
    }

    public void editarMecanico(String clave, String nombre, Date fechaIngreso, int sueldoBase, String idMecanico) {
        cm.editarMecanico(clave, nombre, fechaIngreso, sueldoBase, idMecanico);
    }

    public Reparacion obtenerReparacion(Integer id) {
        return cr.obtenerReparacion(id);
    }
    
    public Repuesto obtenerRepuesto(Integer id) {
        return crp.obtenerRepuesto(id);
    }

    public List<Stock_U_A> obtenerStock_U_A(Integer idreparacion) {
        return crp.obtenerStock_U_A(idreparacion);
    }
    
    public List<Repuesto> getTotalRepuestos() {
        return crp.getTotalRepuestos();
    }

    public Reparacion anhadirReparacion(Vehiculo vehiculo, String tipo, Mecanico mecanico) {
        return cr.anhadirReparacion(vehiculo, tipo, mecanico);
    }

    public void anhadirRepuestoNecesarior(Integer idReparacion, Integer idRepuesto, int cantidad) {
        crp.anhadirRepuestoNecesario(idReparacion, idRepuesto, cantidad);
    }

    public List<TipoReparacion> obtenerTipoReparaciones() {
        return cr.obtenerTipoReparaciones();
    }

    public void borrarReparacion(Integer idreparacion) {
        cr.borrarReparacion(idreparacion);
    }

    public void finalizarReparacion(Integer idreparacion, List<Stock_U_A> stock) {
        cr.finalizarReparacion(idreparacion, stock);
    }

    public List<Repuesto> obtenerRepuestos(String id, String nombre) {
        return crp.obtenerRepuestos(id, nombre);
    }

    public void nuevaSolicitud(Repuesto repuesto, String cantidad, Mecanico mecanico) {
        crp.nuevaSolicitud(repuesto, cantidad, mecanico);
    }
    
    
    public ArrayList<Mecanico> buscarMecanicos(String id, String modo){
        return cm.buscarMecanicos(id, modo);
    }
    
    public boolean despedirMecanico(String id){
        return cm.despedirMecanico(id);
    }
    
    public void anhadirJefeDeTaller(String id, String nombre, String clave, String tlf, Integer sueldo){
        cm.anhadirJefeDeTaller(id, nombre, clave, tlf, sueldo);
    }
    
    public void anhadirSubordinado(String id, String nombre, String clave, String tlf, Integer sueldo){
        cm.anhadirSubordinado(id, nombre, clave, tlf, sueldo);
    }
}
