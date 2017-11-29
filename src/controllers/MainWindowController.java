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
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author 
 */
public class MainWindowController implements Initializable{

    private Main main;

    @FXML
    AnchorPane contentPane;
    @FXML
    Accordion accordion;
    @FXML
    TitledPane userTitledPane;

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void fanClicked() throws IOException {
        navigateTo("/views/FanDetails.fxml");
    }
    
    @FXML
    public void arbitreClicked() throws IOException {
        navigateTo("/views/ArbitreWindow.fxml");
    }
    
    @FXML
    public void joueurClicked() throws IOException {
        navigateTo("/views/JoueurWindow.fxml");
    }
    
    @FXML
    public void medecinClicked() throws IOException {
        navigateTo("/views/MedecinWindow.fxml");
    }
    
    @FXML
    public void responsableClicked() throws IOException {
        navigateTo("/views/ResponsableWindow.fxml");
    }
    
    @FXML
    public void actualiteClicked() throws IOException {
        navigateTo("/views/ActualiteWindow.fxml");
    }
    
    @FXML
    public void commentaireClicked() throws IOException {
        navigateTo("/views/CommentaireWindow.fxml");
    }
    
    @FXML
    public void mediaClicked() throws IOException {
        navigateTo("/views/MediaWindow.fxml");
    }
    
    @FXML
    public void evenementClicked() throws IOException {
        navigateTo("/views/EvenementWindow.fxml");
    }
    
    @FXML
    public void matchClicked() throws IOException {
        navigateTo("/views/MatchWindow.fxml");
    }
    
    @FXML
    public void statisticClicked() throws IOException {
        navigateTo("/views/StatisticWindow.fxml");
    }
    
    @FXML
    public void clubClicked() throws IOException {
        navigateTo("/views/ClubWindow.fxml");
    }
    
    @FXML
    public void donClicked() throws IOException {
        navigateTo("/views/DonWindow.fxml");
    }
    
    @FXML
    public void formationClicked() throws IOException {
        navigateTo("/views/FormationWindow.fxml");
    }
    
    @FXML
    public void stadeClicked() throws IOException {
        navigateTo("/views/StadeWindow.fxml");
    }
    
    @FXML
    public void accueil() throws IOException {
        mainWindow("/views/AccueilWindow.fxml");
        
    }
    
    @FXML
    public void deconnecter() throws IOException {
        Utils.admin = null;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            navigateTo("/views/ArbitreWindow.fxml");
            accordion.setExpandedPane(userTitledPane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
