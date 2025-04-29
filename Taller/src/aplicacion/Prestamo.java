/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.util.Date;

/**
 *
 * @author alumnogreibd
 */
public class Prestamo {
    
    private String usuario;
    private Integer libro;
    private Integer ejemplar;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private Date fecha_vencimiento;
    
    public Prestamo (String usuario, Integer libro, Integer ejemplar, Date fecha_prestamo, Date fecha_devolucion, Date fecha_vencimiento){
        this.usuario = usuario;
        this.libro = libro;
        this.ejemplar = ejemplar;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.fecha_vencimiento = fecha_vencimiento;
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public Integer getLibro(){
        return libro;
    }
    
    public Integer getEjemplar(){
        return ejemplar;
    }
    
    public Date getFecha_Prestamo(){
        return fecha_prestamo;
    }
    
    public Date getFecha_Devolucion(){
        return fecha_devolucion;
    }
    
    public Date getFecha_Vencimiento(){
        return fecha_vencimiento;
    }
    
}
