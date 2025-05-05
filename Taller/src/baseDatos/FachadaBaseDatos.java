/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Mecanico;
import aplicacion.Cliente;
import aplicacion.JefeTaller;
import aplicacion.Reparacion;
import aplicacion.Repuesto;
import java.util.List;
import aplicacion.Stock_U_A;
import aplicacion.TipoReparacion;
import aplicacion.Vehiculo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private  aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOVehiculos daoVehiculos;
    private DAOMecanicos daoMecanicos;
    private DAOClientes daoClientes;
    private DAOReparaciones daoReparaciones;
    private DAORepuestos daoRepuestos;

    public FachadaBaseDatos (aplicacion.FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
     

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            
            System.out.println("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"));
            
            System.out.println(usuario);
            
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);
            
            

            daoVehiculos = new DAOVehiculos(conexion, fa);
            daoMecanicos = new DAOMecanicos(conexion, fa);
            daoClientes = new DAOClientes(conexion, fa);
            daoReparaciones = new DAOReparaciones(conexion, fa);
            daoRepuestos = new DAORepuestos(conexion, fa);
          


        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
            System.out.println("ERROR");
            System.out.flush();
        }
        
        
        
    }
    
    public Vehiculo obtenerVehiculo(String matricula) {
        return daoVehiculos.obtenerVehiculo(matricula);
    }

    public java.util.List<Vehiculo> consultarCatalogo(String matricula, String cliente, String marca, String modelo, String supervisor, String combustible){
        return daoVehiculos.consultarCatalogo(matricula, cliente, marca, modelo, supervisor, combustible);
    }

    public void modificarVehiculo(Vehiculo v){
         daoVehiculos.modificarVehiculo(v);
    }

    public Mecanico validarMecanico(String idUsuario, String clave){
        return daoMecanicos.validarMecanico(idUsuario, clave);
    }

    public List<String> obtenerIDsJefesTaller() {
        return daoMecanicos.obtenerIDsJefesTaller();
    }

    public JefeTaller obtenerJefesTaller(String id) {
        return daoMecanicos.obtenerJefeTaller(id);
    }
    
    public Cliente obtenerClientes(String dni) {
        return daoClientes.obtenerCliente(dni);
    }

    public List<Cliente> obtenerClientes() {
        return daoClientes.obtenerClientes();
    }

    public void nuevoVehiculo(Vehiculo v) {
        daoVehiculos.nuevoVehiculo(v);
    }

    public void eliminarVehiculo(String matricula) {
        daoVehiculos.eliminarVehiculo(matricula);
    }

    public List<Reparacion> obtenerReparaciones(String matricula) {
        return daoReparaciones.obtenerReparaciones(matricula);
    }

    public TipoReparacion obtenerTipoReparacion(String nombre) {
        return daoReparaciones.obtenerTipoReparacion(nombre);
    }
    public List<Vehiculo> obtenerVehiculosCliente(Cliente c) {
        return daoVehiculos.obtenerVehiculosCliente(c);
    }
    public void eliminarCliente(Cliente c) {
        daoClientes.eliminarCliente(c);
    }
    public void anhadirCliente(Cliente c) {
        daoClientes.anhadirCliente(c);
    }

    public void cambiarContraseña(String nuevaContraseña, String idMecanico) {
        daoMecanicos.cambiarContraseña(nuevaContraseña, idMecanico);
    }

    public void editarMecanico(String clave, String nombre, Date fechaIngreso, int sueldoBase, String idMecanico) {
        daoMecanicos.editarMecanico(clave, nombre, (java.sql.Date) fechaIngreso, sueldoBase, idMecanico);
    }


    public Reparacion obtenerReparacion(Integer id) {
        return daoReparaciones.obtenerReparacion(id);
    }
    
    public Repuesto obtenerRepuesto(Integer id) {
        return daoRepuestos.obtenerRepuesto(id);
    }

    public List<Stock_U_A> obtenerStock_U_A(Integer idreparacion) {
        return daoRepuestos.obtenerStock_U_A(idreparacion);
    }
    
    public List<Repuesto> getTotalRepuestos() {
        return daoReparaciones.getTotalRepuestos();
    }

    public void anhadirReparacion(Vehiculo vehiculo, String tipo, Mecanico mecanico) {
         daoReparaciones.anhadirReparacion(vehiculo, tipo, mecanico);
    }
    
    public Reparacion obtenerUltimaReparacionAnhadida() {
        return daoReparaciones.obtenerUltimaReparacionAnhadida();
    }
/*
    public Repuesto obtenerRepuesto(String nombre) {
        return daoReparaciones.obtenerRepuesto(nombre);
    }
*/
    public void anhadirRepuestoNecesario(Integer idReparacion, Integer idRepuesto, int cantidad) {
        daoRepuestos.anhadirRepuestoNecesario(idReparacion, idRepuesto, cantidad);
    }

    public List<TipoReparacion> obtenerTipoReparaciones() {
        return daoReparaciones.obtenerTipoReparaciones();
    }

    public boolean esFinalizadaReparacion(Integer idreparacion) {
        return daoReparaciones.esFinalizada(idreparacion);
    }

    public void borrarReparacion(Integer idreparacion) {
        daoReparaciones.borrarReparacion(idreparacion);
    }

    public List<Repuesto> obtenerRepuestos(String id, String nombre) {
        return daoRepuestos.obtenerRepuestos(id, nombre);
    }

    public void nuevaSolicitud(Repuesto repuesto, String cantidad, Mecanico mecanico) {
        daoRepuestos.nuevaSolicitud(repuesto, cantidad, mecanico);
    }
    
    public ArrayList<Mecanico> buscarMecanicos(String id, String modo){
        return daoMecanicos.buscarMecanicos(id, modo);
    }
    
    public boolean despedirMecanico(String id){
        return daoMecanicos.despedirMecanico(id);
    }
}
