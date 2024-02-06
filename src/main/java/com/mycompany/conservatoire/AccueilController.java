package com.mycompany.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class AccueilController implements Initializable {
       
    @FXML
    private ImageView loginImg;

    @FXML
    private ImageView logoutImg;
    
    @FXML
    private void goCnx() throws IOException{
        App.setRoot("connexion");
    }
    
    private boolean estConnecte() {
        return App.getEleve() != null;
    }
    
    @FXML
    private void logout() throws IOException {
        App.setEleve(null); 
        App.setRoot("accueil"); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(estConnecte()) {
            loginImg.setVisible(false);
            logoutImg.setVisible(true);
        } 
    
    }    
    
}
