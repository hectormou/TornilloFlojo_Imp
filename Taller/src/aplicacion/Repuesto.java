/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Repuesto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Float preciounidad;
    private Integer stock;


    public Repuesto(Integer id, String nombre, String descripcion, Float preciounidad, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.preciounidad = preciounidad;
        this.stock = stock;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Float getPreciounidad() {
        return preciounidad;
    }

    public Integer getStock() {
        return stock;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPreciounidad(Float precio) {
        this.preciounidad=precio;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}