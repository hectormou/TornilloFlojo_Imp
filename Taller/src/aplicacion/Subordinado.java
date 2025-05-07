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
    private float bonusSubordinado=0;
    public Subordinado(String idMecanico, String clave, String nombre, String telefonoContacto, Date fechaIngreso, int sueldoBase) {
        super(idMecanico, clave, nombre, telefonoContacto, fechaIngreso, sueldoBase);
    }
    
    public Subordinado(String idMecanico, String clave, String nombre, String telefonoContacto, Date fechaIngreso, int sueldoBase,float bonusSubordinado) {
        super(idMecanico, clave, nombre, telefonoContacto, fechaIngreso, sueldoBase);
        this.bonusSubordinado=bonusSubordinado;
    }
    
    public float getBonusSubordinado(){
        return this.bonusSubordinado;
    }
    
    public void setBonusSubordinado(float f){
        this.bonusSubordinado=f;
    }
}
