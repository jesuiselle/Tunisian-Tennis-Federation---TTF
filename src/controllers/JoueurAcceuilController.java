/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Main.primaryStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import utils.Utils;

/**
 *
 * @author 
 */
public class JoueurAcceuilController {
     private Main main;

    @FXML
    AnchorPane contentPane;

    public void setMain(Main main) {
        this.main = main;
    }
    /**
     * Initializes the controller class.
     */
    
    /*@FXML
    public void logout() throws IOException {
        navigateTo("/views/AccueilWindow.fxml");
    }
    
     @FXML
    public void acceuil() throws IOException {
        navigateTo("/views/AccueilWindow.fxml");
    }*/

    @FXML
    public void invitationTestClicked() throws IOException {
        navigateTo("/views/JoueurWindowinvitationTest.fxml");
    }
    @FXML
    public void resultatTest() throws IOException {
        navigateTo("/views/JoueurWindowresultatTest.fxml");
        
    }
    
    @FXML
    public void accueil() throws IOException {
        mainWindow("/views/AccueilWindow.fxml");
        
    }
    
    @FXML
    public void deconnecter() throws IOException {
        Utils.joueur = null;
        mainWindow("/views/AccueilWindow.fxml");
        
    }
    
    public  void navigateTo(String view) throws IOException{
        Node root = (Node) FXMLLoader.load(getClass().getResource(view));
        contentPane.getChildren().clear();
        contentPane.getChildren().add(root);
    }
    
    private void mainWindow(String view) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
