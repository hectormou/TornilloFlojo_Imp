/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

/**
 *
 * @author basesdatos
 */
public class ModeloListaStrings extends javax.swing.AbstractListModel {
    java.util.List<String> elementos;
    java.util.List<Integer> ids;
    java.util.List<Integer> cantidades;

    public ModeloListaStrings(){
        this.elementos=new java.util.ArrayList<String>();
        this.ids=new java.util.ArrayList<Integer>();
        this.cantidades=new java.util.ArrayList<Integer>();
    }

    @Override
    public int getSize(){
        return this.elementos.size();
    }

    public String getElementAt(int i){
        return elementos.get(i);
    }
    public Integer getIdAt(int i){
        return ids.get(i);
    }
    public Integer getCantidadAt(int i){
        return cantidades.get(i);
    }
    
    public void nuevoElemento(String e){
        this.elementos.add(e);
        fireIntervalAdded(this, this.elementos.size()-1, this.elementos.size()-1);
    }
    
    public void nuevoId(Integer e){
        this.ids.add(e);
        fireIntervalAdded(this, this.ids.size()-1, this.ids.size()-1);
    }
    
    public void nuevaCantidad(Integer c) {
        this.cantidades.add(c);
        fireIntervalAdded(this, this.cantidades.size()-1, this.cantidades.size()-1);
    }
    
    public void borrarElemento(int i){
        this.elementos.remove(i);
        this.ids.remove(i);
        this.cantidades.remove(i);
        fireIntervalRemoved(this,i,i);
    }

    public void setElementos(java.util.List<String> elementos, java.util.List<Integer> ids){
        this.elementos=elementos;
        this.ids=ids;
        fireContentsChanged(this, 0, elementos.size()-1);
    }
    public void setElementos(java.util.List<String> elementos, java.util.List<Integer> ids, java.util.List<Integer> cantidades){
        this.elementos=elementos;
        this.ids=ids;
        this.cantidades=cantidades;
        fireContentsChanged(this, 0, elementos.size()-1);
    }

    public java.util.List<String> getElementos(){
        return this.elementos;
    }
    public java.util.List<Integer> getIds(){
        return this.ids;
    }
    public java.util.List<Integer> getCantidades(){
        return this.cantidades;
    }
}
