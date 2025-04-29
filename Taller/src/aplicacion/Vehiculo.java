package aplicacion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author basesdatos
 */
public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private String combustible;
    private Integer kilometraje;
    private Cliente propietario;
    private JefeTaller supervisor;

    public Vehiculo (String matricula, String marca, String modelo, String combustible, Integer kilometraje){
        this.matricula=matricula;
        this.marca=marca;
        this.modelo=modelo;
        this.combustible=combustible;
        this.kilometraje=kilometraje;
    }

    public String getMatricula (){
        return this.matricula;
    }

    public String getMarca (){
        return this.marca;
    }

    public String getModelo (){
        return this.modelo;
    }

    public String getCombustible (){
        return this.combustible;
    }

    public Integer getKilometraje(){
        return this.kilometraje;
    }
    
    public Cliente getPropietario(){
        return this.propietario;
    }
    
    public JefeTaller getSupervisor(){
        return this.supervisor;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public void setPropietario(Cliente propietario){
        this.propietario = propietario;
    }
    
    public void setSupervisor(JefeTaller supervisor){
        this.supervisor = supervisor;
    }
}
