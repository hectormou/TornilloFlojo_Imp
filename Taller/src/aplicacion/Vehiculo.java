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
    private String propietariodni;
    private String supervisorid;
    
    public Vehiculo (String matricula, String marca, String modelo, String combustible, Integer kilometraje, String propietario, String supervisor){
        this.matricula=matricula;
        this.marca=marca;
        this.modelo=modelo;
        this.combustible=combustible;
        this.kilometraje=kilometraje;
        this.propietariodni=propietario;
        this.supervisorid=supervisor;
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
    
    public String getCliente(){
        return this.propietariodni;
    }
    
    public String getSupervisorID(){
        return this.supervisorid;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public void setMarca (String marca){
         this.marca=marca;
    }

    public void setModelo (String modelo){
        this.modelo=modelo;
    }

    public void setCombustible (String combustible){
        this.combustible=combustible;
    }

    public void setKilometraje(int kilometraje){
        this.kilometraje=kilometraje;
    }
    
    public void setPropietarioDNI(String propietario){
        this.propietariodni = propietario;
    }
    
    public void setSupervisorID(String supervisor){
        this.supervisorid = supervisor;
    }
}
