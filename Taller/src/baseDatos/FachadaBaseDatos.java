/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Ejemplar;
import aplicacion.Mecanico;
import aplicacion.Categoria;
import aplicacion.Libro;
import aplicacion.TipoUsuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOLibros daoLibros;
    private DAOCategorias daoCategorias;
    private DAOMecanicos daoUsuarios;
    private DAOPrestamos daoPrestamos;

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
            
            

            daoLibros = new DAOLibros(conexion, fa);
            daoCategorias = new DAOCategorias(conexion, fa);
            daoUsuarios = new DAOMecanicos(conexion, fa);
            daoPrestamos = new DAOPrestamos(conexion, fa);
          


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
    
    

    public java.util.List<Libro> consultarCatalogo(Integer id, String titulo, String isbn, String autor){
        return daoLibros.consultarCatalogo(id, titulo, isbn, autor);
    }

    public Libro consultarLibro(Integer idLibro){
        return daoLibros.consultarLibro(idLibro);
    }
    
    public java.util.List<Ejemplar> consultarEjemplaresLibro(Integer idLibro){
        return daoLibros.consultarEjemplaresLibro(idLibro);
    }

    public java.util.List<String> obtenerRestoCategorias(Integer idLibro){
        return daoLibros.obtenerRestoCategorias(idLibro);
    }
    public Integer insertarLibro(Libro libro){
       return daoLibros.insertarLibro(libro);
    }
    public void borrarLibro(Integer idLibro){
        daoLibros.borrarLibro(idLibro);
    }
    public void modificarLibro(Libro libro){
         daoLibros.modificarLibro(libro);
    }
    public void modificarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
       daoLibros.modificarCategoriasLibro(idLibro, categorias);
    }
    public void insertarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        daoLibros.insertarEjemplarLibro(idLibro, ejemplar);
    }
    public void borrarEjemplaresLibro(Integer idLibro, java.util.List<Integer> numsEjemplar){
        daoLibros.borrarEjemplaresLibro(idLibro, numsEjemplar);
    }
    public void modificarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        daoLibros.modificarEjemplarLibro(idLibro, ejemplar);
    }

    public Mecanico validarMecanico(String idUsuario, String clave){
        return daoUsuarios.validarMecanico(idUsuario, clave);
    }
        
    public java.util.List<Mecanico> consultarUsuarios(String id, String nombre){
        return daoUsuarios.consultarUsuarios(id, nombre);
    }
   
    public java.util.List<Categoria> consultarCategorias(){
        return daoCategorias.consultarCategorias();
    }
    
    public boolean existeUsuario(String id){
        return daoUsuarios.existeUsuario(id);
    }
    
public void editarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        daoUsuarios.editarUsuario(id, clave, nombre, direccion, email, tipo);
    }
    
public void crearUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        daoUsuarios.crearUsuario(id, clave, nombre, direccion, email, tipo);
    }

public void borrarUsuario(String id){
        daoUsuarios.borrarUsuario(id);
    }

public java.util.List<Categoria> obtenerCategorias(){
        return daoCategorias.consultarCategorias();
    }

public boolean existeCategoria(String nombre){
        return daoCategorias.existeCategoria(nombre);
    }

    public void editarCategoria(String nombre, String descripcion){
        daoCategorias.editarCategoria(nombre,descripcion);
    }
    
    public void crearCategoria(String nombre, String descripcion){
        daoCategorias.crearCategoria(nombre,descripcion);
    }
    
    public void borrarCategoria(String nombre){
        daoCategorias.borrarCategoria(nombre);
    }
    
    public java.util.List<Mecanico> obtenerUsuariosPrestamos(String id, String nombre){
        return daoPrestamos.obtenerUsuariosPrestamos(id, nombre);
    }
    
    public void prestar(Ejemplar e, Mecanico u){
        daoPrestamos.prestar(e,u);
    }
    
    public void devolver(Integer numEjemplar,Integer idLibro, String idUsuario, Date fechaPrestamo){
        daoPrestamos.devolver(numEjemplar, idLibro, idUsuario, fechaPrestamo);
    }
    
    public boolean ejemplarTienePrestamo(Integer idLibro, Integer numEjemplar){
        return daoPrestamos.ejemplarTienePrestamo(idLibro, numEjemplar);
    }
}
