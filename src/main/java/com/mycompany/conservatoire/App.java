package com.mycompany.conservatoire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import modele.Eleve;
import modele.Partitions;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    private static Eleve eleve;
    private static Partitions partitions;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("accueil"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static Eleve getEleve() {
        return eleve;
    }

    public static Partitions getPartitions() {
        return partitions;
    }
    
    public static void setEleve(Eleve eleve) {
        App.eleve = eleve;
    }

    public static void setPartitions(Partitions partitions) {
        App.partitions = partitions;
    }
    
    
}