/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.time.LocalDate;
/**
 *
 * @author alumnogreibd
 */
public class Reparacion {
    private Integer idreparacion;
    private String fecha_inicio;
    private String fecha_fin;
    private String idvehiculo;
    private String tipo;
    private String supervisorid;
    
    public Reparacion(Integer idreparacion, String fechainicio, String fechafin, String idvehiculo, String tipo, String supervisorid) {
        this.idreparacion = idreparacion;
        if (fechainicio == null) this.fecha_inicio = LocalDate.now().toString();
        else  this.fecha_inicio = fechainicio;
        this.fecha_fin=fechafin;
        this.idvehiculo = idvehiculo;
        this.tipo = tipo;
        this.supervisorid = supervisorid;
    }
    
    // Getters
    public Integer getIdreparacion() {
        return idreparacion;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public String getIdvehiculo() {
        return idvehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSupervisorid() {
        return supervisorid;
    }

    // Setters
    public void setIdreparacion(Integer idreparacion) {
        this.idreparacion = idreparacion;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setIdvehiculo(String idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSupervisorid(String supervisorid) {
        this.supervisorid = supervisorid;
    }
}
