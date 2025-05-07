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
public class JefeTaller extends Mecanico{
    private float bonusUsual=0;
    private float bonusJefe=0;
    private int practicasTutela;
    
    public JefeTaller(String idMecanico, String clave, String nombre, String telefonoContacto, Date fechaIngreso, int sueldoBase) {
        super(idMecanico, clave, nombre, telefonoContacto, fechaIngreso, sueldoBase);
    }
    
    public JefeTaller(String idMecanico, String clave, String nombre, String telefonoContacto, Date fechaIngreso, int sueldoBase, float bU, float bJ) {
        super(idMecanico, clave, nombre, telefonoContacto, fechaIngreso, sueldoBase);
        this.bonusJefe=bJ;
        this.bonusUsual=bU;
    }
    
    public JefeTaller(String idMecanico, String nombre, Integer practicasTutela){
        super(idMecanico, nombre);
        this.practicasTutela = practicasTutela;
    }
    
    public float getBonusUsual(){
        return this.bonusUsual;
        
    }
    
    public float getBonusJefe(){
        return this.bonusJefe;
        
    }
    
    public Integer getPracticasTutela(){
        return this.practicasTutela;
    }
    
    public void setBonusUsual(float f){
        this.bonusUsual=f;
    }
    public void setBonusJefe(float f){
        this.bonusJefe=f;
    }
    
    public void setPracticasTutela(Integer i){
        this.practicasTutela = i;
    }
}
