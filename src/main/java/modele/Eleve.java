
package modele;

/**
 *
 * @author Amir
 */
public class Eleve {
    
    // Variables
    private String elenum;
    private String disnum;
    private String elenom;
    private String eleprenom;
    private String elecycle;
    private String eleanneecycle;
    private String elelogin;
    


    /**
     * Constructeur
     * @param elenum
     * @param disnum
     * @param elenom
     * @param eleprenom
     * @param elecycle
     * @param eleanneecycle
     * @param elelogin 
     */
    public Eleve(String elenum, String disnum, String elenom, String eleprenom, String elecycle, String eleanneecycle, String elelogin)
    {
        this.elenum = elenum;
        this.disnum = disnum;
        this.elenom = elenom;
        this.eleprenom = eleprenom;
        this.elecycle = elecycle;
        this.eleanneecycle = eleanneecycle;
        this.elelogin = elelogin;

    }

    //GETTERS 
    
    public String getElenum() {
        return elenum;
    }

    public String getDisnum() {
        return disnum;
    }

    public String getElenom() {
        return elenom;
    }

    public String getEleprenom() {
        return eleprenom;
    }

    public String getElecycle() {
        return elecycle;
    }

    public String getEleanneecycle() {
        return eleanneecycle;
    }

    public String getElelogin() {
        return elelogin;
    }

    //SETTERS
    
    public void setElenum(String elenum) {
        this.elenum = elenum;
    }

    public void setDisnum(String disnum) {
        this.disnum = disnum;
    }

    public void setElenom(String elenom) {
        this.elenom = elenom;
    }

    public void setEleprenom(String eleprenom) {
        this.eleprenom = eleprenom;
    }

    public void setElecycle(String elecycle) {
        this.elecycle = elecycle;
    }

    public void setEleanneecycle(String eleanneecycle) {
        this.eleanneecycle = eleanneecycle;
    }

    public void setElelogin(String elelogin) {
        this.elelogin = elelogin;
    }
    
}
