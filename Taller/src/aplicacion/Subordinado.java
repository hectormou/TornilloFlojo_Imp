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
public class Subordinado extends Mecanico {
    
    public Subordinado(String idMecanico, String clave, String nombre, String telefonoContacto, Date fechaIngreso, int sueldoBase) {
        super(idMecanico, clave, nombre, telefonoContacto, fechaIngreso, sueldoBase);
    }
}
