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
    private Integer idRepuesto;
    private String nombre;
    private String descripcion;
    private float precioUnidad;
    private Integer cantidadStock;
    
    public Repuesto(Integer id, String nombre, String descripcion, float precioUnidad, Integer cantidadStock) {
        this.idRepuesto=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precioUnidad=precioUnidad;
        this.cantidadStock=cantidadStock;
    }
    
    //GETTERS
    public Integer getIdRepuesto() { return idRepuesto; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public float getPrecioUnidad() { return precioUnidad; }
    public Integer getCantidadStock() { return cantidadStock; }
    
    //SETTERS
    public void setIdRepuesto(Integer id) { this.idRepuesto=id; }
    public void setNombre(String nombre) { this.nombre=nombre; }
    public void setDescripcio(String descripcion) { this.descripcion=descripcion; }
    public void setPrecioUnidad(float precioUnidad) { this.precioUnidad=precioUnidad; }
    public void setCantidadStock(Integer cantidadStock) { this.cantidadStock=cantidadStock; }
    
    
}
