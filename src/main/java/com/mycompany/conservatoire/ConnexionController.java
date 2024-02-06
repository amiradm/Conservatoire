/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modele.DAO;
import modele.Eleve;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class ConnexionController implements Initializable {
    
    @FXML
    private TextField eleid;

    @FXML
    private PasswordField elemdp;
    
    @FXML
    private Label errorLabel;
       
    @FXML
    private ImageView acc;

    
    private boolean estConnecte() {
        return App.getEleve() != null;
    }
    
    @FXML
    private void login() throws SQLException, IOException {
        String idString = eleid.getText();
        String mdpString = Util.sansAccents(elemdp.getText());

        if(idString.isEmpty() || mdpString.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs");
            return;
        }
        
        CallableStatement callableStatement = DAO.getConnection().prepareCall("call getLoginEleve(?);");
        callableStatement.setString(1, eleid.getText());
        ResultSet resultSet = callableStatement.executeQuery();

          //changer la ligne ci dessous selon le hashage etc
          if (resultSet.next() && Objects.equals(elemdp.getText(), resultSet.getString("ELEMDP"))) {  
            App.setEleve(
                    new Eleve(
                            resultSet.getString("ELENUM"),
                            resultSet.getString("DISNUM"),
                            resultSet.getString("ELENOM"),
                            resultSet.getString("ELEPRENOM"),
                            resultSet.getString("ELECYCLE"),
                            resultSet.getString("ELEANNEECYCLE"),
                            resultSet.getString("ELELOGIN")));
            App.setRoot("partitions");
        } else errorLabel.setText("/!\\ Votre id ou mot de passe est incorrect");
    }
    
    public void goAccueil() throws IOException{
        App.setRoot("accueil");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    
}
