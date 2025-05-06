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
    private JefeTaller tutor;
    private Integer reparacionesAsistidas;

    // Constructor
    public EmpleadoPracticas(int idalumno, String nombre, JefeTaller tutor, Integer reparacionesAsistidas) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.tutor = tutor;
        this.reparacionesAsistidas = reparacionesAsistidas;
    }

    // Getters
    public int getIdalumno() {
        return idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public JefeTaller getTutor() {
        return tutor;
    }
    
    public Integer getReparacionesAsistidas() {
        return this.reparacionesAsistidas;
    }

    // Setters
    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTutor(JefeTaller tutor) {
        this.tutor = tutor;
    }
    
    public void setReparacionesAsistidas(Integer reparacionesAsistidas) {
        this.reparacionesAsistidas = reparacionesAsistidas;
    }
}