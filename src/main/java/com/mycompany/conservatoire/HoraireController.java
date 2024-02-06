/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class HoraireController implements Initializable {

     public void goAccueil() throws IOException{
        App.setRoot("accueil");
    }
    
    @FXML
    private void logout() throws IOException {
        App.setEleve(null); 
        App.setRoot("accueil"); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
