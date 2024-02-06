/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modele.DAO;
import modele.Eleve;
import modele.Partitions;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class AjoutPartitionsController implements Initializable {

    @FXML
    private ListView<String> partList;
    
    @FXML
    private TextField auteurField;

    @FXML
    private TextField titreField;
    
    @FXML
    private TextField pageField;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private void ajouterPage() {
   
    try {
        String page = pageField.getText();
        String part = partList.getSelectionModel().getSelectedItem();

        if (part != null && !part.isEmpty()) {
            String partIdStr = part.split("\\.")[0];
            int partId = Integer.parseInt(partIdStr);

            Eleve eleve = App.getEleve();

            if (eleve != null) {
                String eleveId = eleve.getElenum();

                String checkQuery = "SELECT COUNT(*) FROM partitioneleve WHERE NUMEROPAGECLASSEUR = ?";
                PreparedStatement checkStatement = DAO.getConnection().prepareStatement(checkQuery);
                checkStatement.setString(1, page);

                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Erreur");
                        error.setHeaderText("Page déjà occupée");
                        error.setContentText("Une partition existe déjà sur la page " + page);
                        error.showAndWait();
                    } else {
                        String query = "INSERT INTO partitioneleve (ELENUM, PARNUM, NUMEROPAGECLASSEUR) VALUES (?, ?, ?)";
                        PreparedStatement preparedStatement = DAO.getConnection().prepareStatement(query);
                        preparedStatement.setString(1, eleveId);
                        preparedStatement.setInt(2, partId);
                        preparedStatement.setString(3, page);

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            Alert success = new Alert(Alert.AlertType.INFORMATION);
                            success.setTitle("Succès");
                            success.setHeaderText("Partition ajoutée à la page " + page + " avec succès");
                            success.setContentText("Données enregistrées avec succès dans la base de données !");
                            success.showAndWait();
                        }
                    }
                }
                
                
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Erreur");
                error.setHeaderText("Élève non trouvé");
                error.setContentText("L'élève n'a pas été trouvé dans la base de données.");
                error.showAndWait();
            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Erreur");
            error.setHeaderText("Sélection de partition invalide");
            error.setContentText("Veuillez sélectionner une partition valide.");
            error.showAndWait();
        }
        
    } catch (SQLIntegrityConstraintViolationException e) { //si violation de clé primaire
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Erreur");
        error.setHeaderText("Violation de contrainte !");
        error.setContentText("Cette association existe déja.");
        error.showAndWait();
    } catch (SQLException | NumberFormatException e) {
        e.printStackTrace();
    }
}

    
    @FXML
    private void ajouterPartitions() throws SQLException{
        
        String auteur = auteurField.getText();
        String titre = titreField.getText();
        
        
        if(!auteur.isEmpty() || !titre.isEmpty()) {
            
                ResultSet partitions = DAO.getStatement().executeQuery("SELECT COUNT(*) from partitions");
                partitions.next();

                int num = partitions.getInt(1);
                ++num;

                String query = "INSERT INTO partitions(PARNUM, PARNOM, PARAUTEUR) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = DAO.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, num);
                preparedStatement.setString(2, (titre));
                preparedStatement.setString(3, (auteur));

                int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            App.setPartitions(
                                    new Partitions(
                                            num,titre, auteur));
                            Alert success = new Alert(Alert.AlertType.INFORMATION);
                            success.setTitle("Succès");
                            success.setHeaderText("Partition : " + titre + " - " + auteur + " | ajouté avec succès");
                            success.setContentText("Données enregistrées avec succès dans la base de données !");
                            success.showAndWait();

                            try {
                            PreparedStatement preparedStatement2 = DAO.getConnection().prepareStatement("SELECT * FROM partitions");
                            ResultSet rs = preparedStatement2.executeQuery();

                            partList.getItems().clear();

                            while (rs.next()) {
                                String num2 = rs.getString("PARNUM");
                                String nom2 = rs.getString("PARNOM");
                                String auteur2 = rs.getString("PARAUTEUR");

                                String nv = num2 + ". " + nom2 + " - " + auteur2;
                                partList.getItems().add(nv);
                            }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
        } else {
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Veuillez remplir les deux champs ! ");
            erreur.show();
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
