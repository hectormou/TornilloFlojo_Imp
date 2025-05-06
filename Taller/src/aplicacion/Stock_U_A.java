/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Stock_U_A {

    private Integer idreparacion;
    private Integer idrepuesto;
    private Integer usado;
    private Integer almacen;
    private String nombre;
    
    public Stock_U_A(Integer idreparacion, Integer idrepuesto, Integer usado, Integer almacen, String nombre) {
        this.idreparacion = idreparacion;
        this.idrepuesto = idrepuesto;
        this.usado = usado;
        this.almacen = almacen;
        this.nombre = nombre;
    }

    // Getters
    public Integer getIdreparacion() {
        return idreparacion;
    }

    public Integer getIdrepuesto() {
        return idrepuesto;
    }

    public Integer getUsado() {
        return usado;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public String getNombre() {
        return nombre;
    }
}