/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import aplicacion.Mecanico;

/**
 *
 * @author alumno
 */
public class FachadaGui {
    private final aplicacion.FachadaAplicacion fa;
    private final VPrincipal vp;
    
   public FachadaGui(aplicacion.FachadaAplicacion fa){
     this.fa=fa;
     this.vp = new VPrincipal(fa);
   } 
    
    
    
    public void iniciaVista(){
      VAutentificacion va;
      va = new VAutentificacion(vp, true, fa);
      vp.setVisible(true);
      va.setVisible(true);
    }
    
    public void comprobarMecanico(Mecanico m){
        vp.comprobarMecanico(m);
    }
    public void muestraExcepcion(String txtExcepcion){
       VAviso va;
       
       va = new VAviso(vp, true, txtExcepcion);
       va.setVisible(true);
    }

    
   
}
