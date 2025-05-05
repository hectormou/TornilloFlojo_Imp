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

    
    public Stock_U_A(Integer idreparacion, Integer idrepuesto, Integer usado, Integer almacen) {
        this.idreparacion = idreparacion;
        this.idrepuesto = idrepuesto;
        this.usado = usado;
        this.almacen = almacen;
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

    // Setters
    public void setIdreparacion(Integer idreparacion) {
        this.idreparacion = idreparacion;
    }

    public void setIdrepuesto(Integer idrepuesto) {
        this.idrepuesto = idrepuesto;
    }

    public void setUsado(Integer usado) {
        this.usado = usado;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }
}