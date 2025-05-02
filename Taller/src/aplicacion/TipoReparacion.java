/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class TipoReparacion {
    private String nombre;
    private String descripcion;
    private boolean apto_practicas;
    
    public TipoReparacion (String nombre, String descripcion, boolean apto_practicas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.apto_practicas = apto_practicas;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean esApto_practicas() {
        return apto_practicas;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setApto_practicas(boolean apto_practicas) {
        this.apto_practicas = apto_practicas;
    }
}
