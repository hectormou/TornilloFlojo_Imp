package aplicacion;

import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author basesdatos
 */
public abstract class Mecanico {
    private String idMecanico;
    private String clave;
    private String nombre;
    private String telefonoContacto;
    private Date fechaIngreso;
    private int sueldoBase;

   public Mecanico (String idMecanico, String clave, String nombre, String telefonoContacto, Date fechaIngreso, int sueldoBase){
    this.idMecanico=idMecanico;
    this.clave=clave;
    this.nombre=nombre;
    this.telefonoContacto=telefonoContacto;
    this.fechaIngreso=fechaIngreso;
    this.sueldoBase = sueldoBase;
   }
   
   public Mecanico(String idMecanico, String nombre){
       this.idMecanico = idMecanico;
       this.nombre = nombre;
   }

   public String getIdMecanico(){
       return this.idMecanico;
   }

   public String getClave(){
       return this.clave;
   }

   public String getNombre(){
       return this.nombre;
   }

   public String getTelefonoContacto(){
       return this.telefonoContacto;
   }

   public Date getFechaIngreso(){
       return this.fechaIngreso;
   }
   
   public int getSueldoBase(){
       return this.sueldoBase;
   }
}
