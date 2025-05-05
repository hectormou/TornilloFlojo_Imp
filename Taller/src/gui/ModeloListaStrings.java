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

    public ModeloListaStrings(){
        this.elementos=new java.util.ArrayList<String>();
        this.ids=new java.util.ArrayList<Integer>();
    }

    public int getSize(){
        return this.elementos.size();
    }

    public String getElementAt(int i){
        return elementos.get(i);
    }
    public Integer getIdAt(int i){
        return ids.get(i);
    }

    public void nuevoElemento(String e){
        this.elementos.add(e);
        fireIntervalAdded(this, this.elementos.size()-1, this.elementos.size()-1);
    }
    
    public void nuevoId(Integer e){
        this.ids.add(e);
        fireIntervalAdded(this, this.elementos.size()-1, this.elementos.size()-1);
    }
    
    public void borrarElemento(int i){
        String e;
        Integer u;
        e=this.elementos.get(i);
        u=this.ids.get(i);
        this.elementos.remove(i);
        this.ids.remove(i);
        fireIntervalRemoved(this,i,i);
    }

    public void setElementos(java.util.List<String> elementos, java.util.List<Integer> ids){
        this.elementos=elementos;
        this.ids=ids;
        fireContentsChanged(this, 0, elementos.size()-1);
    }

    public java.util.List<String> getElementos(){
        return this.elementos;
    }
    public java.util.List<Integer> getIds(){
        return this.ids;
    }
}
