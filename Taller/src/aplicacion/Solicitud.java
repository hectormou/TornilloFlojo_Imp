/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Solicitud {
    
    private String fecha;
    private String idSolicitante;
    private Integer idRepuesto;
    private Integer cantidad;
    
    public Solicitud(String fecha, String idSolicitante, Integer idRepuesto, Integer cantidad) {
        this.fecha = fecha;
        this.idSolicitante = idSolicitante;
        this.idRepuesto = idRepuesto;
        this.cantidad = cantidad;
    }
    
    //Getters
    public String getFecha(){
        return fecha;
    }
    
    public String getIdSolicitante(){
        return idSolicitante;
    }
    
    public Integer getIdRepuesto(){
        return idRepuesto;
    }
    
    public Integer getCantidad(){
        return cantidad;
    }
    
    
    //Setters
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
    public void setIdSolicitante(String idSolicitante){
        this.idSolicitante = idSolicitante;
    }
    
    public void setIdRepuesto(Integer idRepuesto){
        this.idRepuesto = idRepuesto;
    }
    
    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }
}
