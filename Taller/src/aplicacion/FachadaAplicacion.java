/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.util.Date;


/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionVehiculos cl;
    GestionUsuarios cu;
    GestionCategorias cc;
    GestionPrestamos cp;
    
    
 public FachadaAplicacion(){
   fgui=new gui.FachadaGui(this);
   fbd= new baseDatos.FachadaBaseDatos(this);
   cl= new GestionVehiculos(fgui, fbd);
   cu= new GestionUsuarios(fgui, fbd);
   cc = new GestionCategorias(fgui, fbd);
   cp = new GestionPrestamos(fgui, fbd);
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
 
public java.util.List<Vehiculo> obtenerVehiculos(String matricula, String cliente, String marca, String modelo, String supervisor, String combustible){
  return cl.obtenerVehiculos(matricula, cliente,  marca,  modelo, supervisor, combustible);
}

public void visualizarLibro(Integer idLibro){
 cl.visualizarLibro(idLibro);
}

public void nuevoLibro(){
 cl.nuevoLibro();
}

public Integer actualizarLibro(Vehiculo l){
  return cl.actualizarLibro(l);
}

public void borrarLibro(Integer idLibro){
   cl.borrarLibro(idLibro);
}

public void actualizarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
 cl.actualizarCategoriasLibro(idLibro, categorias);
}

public java.util.List<Ejemplar> actualizarEjemplaresLibro(Integer idLibro, java.util.List<Ejemplar> ejemplares, java.util.List<Integer> borrar){
  return cl.actualizarEjemplaresLibro(idLibro, ejemplares, borrar);
}


public Mecanico validarMecanico(String idMecanico, String clave){
  return cu.validarMecanico(idMecanico, clave);
}
 
public java.util.List<Mecanico> obtenerUsuarios(String id, String nombre){
  return cu.obtenerUsuarios(id, nombre);
}

public boolean existeUsuario(String id){
    return cu.existeUsuario(id);
}
public void editarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        cu.editarUsuario(id, clave, nombre, direccion, email, tipo);
    }

public void crearUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        cu.crearUsuario(id, clave, nombre, direccion, email, tipo);
    }

public void borrarUsuario(String id){
        cu.borrarUsuario(id);
    }

    public java.util.List<Categoria> obtenerCategorias(){
        return cc.obtenerCategorias();
    }
    
    public boolean existeCategoria(String nombre){
        return cc.existeCategoria(nombre);
    }
    
    public void editarCategoria(String nombre, String descripcion){
        cc.editarCategoria(nombre,descripcion);
    }
    
    public void crearCategoria(String nombre, String descripcion){
        cc.crearCategoria(nombre,descripcion);
    }
    
    public void borrarCategoria(String nombre){
        cc.borrarCategoria(nombre);
    }
    
    public java.util.List<Mecanico> obtenerUsuariosPrestamos(String id, String nombre){
        return cp.obtenerUsuariosPrestamos(id, nombre);
    }
    
    public void prestar(Ejemplar e, Mecanico u){
        cp.prestar(e,u);
    }
    
    public void devolver(Integer numEjemplar,Integer idLibro, String idUsuario, Date fechaPrestamo){
        cp.devolver(numEjemplar, idLibro, idUsuario, fechaPrestamo);
    }
    
    public boolean ejemplarTienePrestamo(Integer idLibro, Integer numEjemplar){
        return cp.ejemplarTienePrestamo(idLibro, numEjemplar);
    }
}
