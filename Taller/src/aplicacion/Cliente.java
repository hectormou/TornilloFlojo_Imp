/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Cliente {
    private String dni;
    private String nombre;
    private String telefonoContacto;
    
    public Cliente(String dni, String nombre, String telefonoContacto){
        this.dni = dni;
        this.nombre = nombre;
        this.telefonoContacto = telefonoContacto;
    }
    
    public String getDni(){
        return this.dni;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getTelefonoContacto(){
        return this.telefonoContacto;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
                
    }
    public void setTelefono(String telefono){
        this.telefonoContacto=telefono;
    }
}
