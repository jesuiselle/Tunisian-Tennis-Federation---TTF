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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Utils;

/**
 *
 * @author 
 */
public class AccueilController implements Initializable{
     private Main main;
    @FXML
    AnchorPane contentPane;
    @FXML
    Button btn;

    public void setMain(Main main) {
       
          try {
             navigateTo("/views/acc.fxml");
         } catch (IOException ex) {
             Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        this.main = main;
    }

    /**
     * Initializes the controller class.
     * @throws java.io.IOException
     */
    
    @FXML
    public void connecterClicked() throws IOException, Exception {
        if(Utils.admin != null){
            mainWindow("/views/MainWindow.fxml");
        }else if(Utils.arbitre != null){
            mainWindow("/views/ArbitreAcceuil.fxml");
        }else if(Utils.joueur != null){
            mainWindow("/views/JoueurAcceuil.fxml");
        }else if(Utils.medecin != null){
            mainWindow("/views/MedecinAcceuil.fxml");
        }else if(Utils.responsable != null){
            mainWindow("/views/ResponsableAcceuil.fxml");
        }else if(Utils.fan != null){
            Utils.fan = null;
            mainWindow("/views/AccueilWindow.fxml");
        }else{
            mainWindow("/views/Connexion.fxml");
        }
        
    }
   
    @FXML
    public void news() throws IOException {
        navigateTo("/views/NewsAcceuil.fxml");
    }
    
    @FXML
    public void match() throws IOException {
        
        navigateTo("/views/MatchAcceuil.fxml");
    }
    
    @FXML
    public void jacceuil() throws IOException {
        navigateTo("/views/JoueursAcceuil.fxml");
    }
    
    @FXML
    public void stade() throws IOException {
        navigateTo("/views/stadeAcceuil.fxml");
    }
    
    @FXML
    public void tournoiAccueil() throws IOException {
        navigateTo("/views/TournoiAccueil.fxml");
    }
            
    @FXML
    public void galerieAccueil() throws IOException {
        navigateTo("/views/GalerieAccueil.fxml");
    }
    
   
    @FXML
    public void testAcceuil() throws IOException {
        navigateTo("/views/TestAntidoageAcceuil.fxml");
    }
    
    @FXML
    public void club() throws IOException {
        navigateTo("/views/ClubAccueil.fxml");
    }
    
    @FXML
    public void video() throws IOException {
        navigateTo("/views/video.fxml");
    }
    
    @FXML
    public void contacter() throws IOException {
        navigateTo("/views/Contacteznous.fxml");
    }
    
    public  void navigateTo(String view) throws IOException{
        Node root = (Node) FXMLLoader.load(getClass().getResource(view));
        contentPane.getChildren().clear();
        contentPane.getChildren().add(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       
        if(Utils.admin != null){
            btn.setText("Mon espace");
        }else if(Utils.arbitre != null){
            btn.setText("Mon espace");
        }else if(Utils.joueur != null){
            btn.setText("Mon espace");
        }else if(Utils.medecin != null){
            btn.setText("Mon espace");
        }else if(Utils.responsable != null){
            btn.setText("Mon espace");
        }else if(Utils.fan != null){
            btn.setText("Déconnecter");
        }
    }
    
    private void mainWindow(String view) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
