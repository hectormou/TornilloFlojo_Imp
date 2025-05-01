/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
/**
 *
 * @author basesdatos
 */
public class GestionVehiculos{
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionVehiculos(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public java.util.List<Vehiculo> obtenerVehiculos(String matricula, String cliente, String marca, String modelo, String supervisor, String combustible){
        return fbd.consultarCatalogo(matricula, cliente, marca, modelo, supervisor, combustible);
    }

    public void visualizarLibro(Integer idLibro){
        java.util.List<String> restoCategorias;
        java.util.List<Ejemplar> ejemplares;
        Vehiculo l;
        l=fbd.consultarLibro(idLibro);
        restoCategorias=fbd.obtenerRestoCategorias(idLibro);
        fgui.visualizaLibro(l, restoCategorias);
    }

    public void nuevoLibro(){
        java.util.List<String> restoCategorias = new java.util.ArrayList<String> ();

        for(Categoria c:fbd.consultarCategorias()){
            restoCategorias.add(c.getNombre());
        }

        fgui.nuevoLibro(restoCategorias);
    }
    
    public void actualizarVehiculo(Vehiculo v){
        

       
          fbd.modificarVehiculo(v);
         

    }

    public void borrarLibro(Integer idLibro){
       fbd.borrarLibro(idLibro);
    }

    public void actualizarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
       fbd.modificarCategoriasLibro(idLibro, categorias);
    }

    public java.util.List<Ejemplar> actualizarEjemplaresLibro(Integer idLibro, java.util.List<Ejemplar> ejemplares, java.util.List<Integer> borrar){

       for (Ejemplar e:ejemplares){
        if (e.getNumEjemplar()==null){
            fbd.insertarEjemplarLibro(idLibro, e);
        }
        else fbd.modificarEjemplarLibro(idLibro, e);
       }
       
       fbd.borrarEjemplaresLibro(idLibro, borrar);

       return fbd.consultarEjemplaresLibro(idLibro);
    }

    void nuevoVehiculo(Vehiculo v) {
          fbd.nuevoVehiculo(v);
    }
}
