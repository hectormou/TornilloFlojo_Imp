/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class EmpleadoPracticas {
    private int idalumno;
    private String nombre;
    private String tutorid;

    // Constructor
    public EmpleadoPracticas(int idalumno, String nombre, String tutorid) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.tutorid = tutorid;
    }

    // Getters
    public int getIdalumno() {
        return idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTutorid() {
        return tutorid;
    }

    // Setters
    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTutorid(String tutorid) {
        this.tutorid = tutorid;
    }
}