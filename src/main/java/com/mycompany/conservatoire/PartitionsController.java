/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import modele.DAO;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class PartitionsController implements Initializable {

    @FXML
    private ListView<String> partList;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;
    
    
    @FXML
    public void rechercheList() {

            try {
            PreparedStatement preparedStatement = DAO.getConnection().prepareStatement("SELECT * FROM partitions"); 
            ResultSet rs = preparedStatement.executeQuery();

            partList.getItems().clear();

            String recherche = searchField.getText().trim();
            while (rs.next()) {
                String num = rs.getString("PARNUM");
                String nom = rs.getString("PARNOM");
                String auteur = rs.getString("PARAUTEUR");

                String nv = num + ". " + nom + " - " + auteur ;
                String auteurLower = auteur.toLowerCase();

                if (!recherche.isEmpty()) {
                    if (auteur.contains(recherche) || auteurLower.contains(recherche)) {
                        partList.getItems().add(nv);
                    }
                } else {
                    partList.getItems().add(nv);
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
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
            searchButton.setTooltip(new Tooltip("recherche par auteur uniquement !"));

            try {
                    PreparedStatement preparedStatement = DAO.getConnection().prepareStatement("SELECT * FROM partitions");
                    ResultSet rs = preparedStatement.executeQuery();

                    partList.getItems().clear();

                    while (rs.next()) {
                        String num = rs.getString("PARNUM");
                        String nom = rs.getString("PARNOM");
                        String auteur = rs.getString("PARAUTEUR");

                        String nv = num + ". " + nom + " - " + auteur;
                        partList.getItems().add(nv);
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }    
    
}
