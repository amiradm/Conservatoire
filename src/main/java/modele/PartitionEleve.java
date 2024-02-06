/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author Amir
 */
public class PartitionEleve {
    
    //VARIABLES
    private int elenum;
    private int parnum;
    private int numeropageclasseur;
    
    public PartitionEleve(int elenum, int parnum, int numeropageclasseur) {
        
        this.elenum = elenum;
        this.parnum = parnum;
        this.numeropageclasseur = numeropageclasseur;
    }
    
    //GETTERS
    public int getElenum() {
        return elenum;
    }

    public int getParnum() {
        return parnum;
    }

    public int getNumeropageclasseur() {
        return numeropageclasseur;
    }
    
    //SETTERS
    public void setElenum(int elenum) {
        this.elenum = elenum;
    }

    public void setParnum(int parnum) {
        this.parnum = parnum;
    }

    public void setNumeropageclasseur(int numeropageclasseur) {
        this.numeropageclasseur = numeropageclasseur;
    }
}
