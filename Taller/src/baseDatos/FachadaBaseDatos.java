/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Ejemplar;
import aplicacion.Mecanico;
import aplicacion.Categoria;
import aplicacion.Cliente;
import aplicacion.JefeTaller;
import aplicacion.Reparacion;
import aplicacion.TipoReparacion;
import aplicacion.Vehiculo;
import aplicacion.TipoUsuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOVehiculos daoVehiculos;
    private DAOMecanicos daoMecanicos;
    private DAOClientes daoClientes;
    private DAOReparaciones daoReparaciones;

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
        
    public java.util.List<Mecanico> consultarUsuarios(String id, String nombre){
        return daoMecanicos.consultarUsuarios(id, nombre);
    }
    
    public boolean existeUsuario(String id){
        return daoMecanicos.existeUsuario(id);
    }
    
    public void editarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        daoMecanicos.editarUsuario(id, clave, nombre, direccion, email, tipo);
    }
    
    public void crearUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        daoMecanicos.crearUsuario(id, clave, nombre, direccion, email, tipo);
    }

    public void borrarUsuario(String id){
        daoMecanicos.borrarUsuario(id);
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
}
