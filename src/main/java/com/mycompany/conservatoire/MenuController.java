package com.mycompany.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;


/**
 * FXML Controller class
 *
 * @author Amir
 */
public class MenuController implements Initializable {

    @FXML
    private Hyperlink rechercheLink;
    
    @FXML
    private Hyperlink ajoutLink;
        

    private boolean estConnecte() { 
        return App.getEleve() != null;
    }
    public void goPartitions() throws IOException{
        App.setRoot("partitions");
    }
    
    public void goAjoutPartitions() throws IOException{
        App.setRoot("ajoutPartitions");
    }
    
    public void goHoraires() throws IOException{
        App.setRoot("horaire");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!estConnecte()) { // si utilisateur non connecté 
            rechercheLink.setDisable(true); // lien inaccesible
            ajoutLink.setDisable(true);
        }
    }    
    
}
