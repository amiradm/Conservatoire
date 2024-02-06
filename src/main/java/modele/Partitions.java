/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author Amir
 */
public class Partitions {
   
    // VARIABLES
    private int parnum;
    private String parnom;
    private String parauteur;
    
    /**
     * 
     * @param parnum
     * @param parnom
     * @param parauteur 
     */
    public Partitions(int parnum, String parnom, String parauteur) {
        
        this.parnum = parnum;
        this.parnom = parnom;
        this.parauteur = parauteur;
    
    }
   
    //GETTERS
    public int getParnum() {
        return parnum;
    }

    public String getParnom() {
        return parnom;
    }

    public String getParauteur() {
        return parauteur;
    }
    
    //SETTERS
    public void setParnum(int parnum) {
        this.parnum = parnum;
    }

    public void setParnom(String parnom) {
        this.parnom = parnom;
    }

    public void setParauteur(String parauteur) {
        this.parauteur = parauteur;
    }
    
}
