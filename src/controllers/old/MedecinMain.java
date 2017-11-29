/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import entities.Medecin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Aydi
 */
public class MedecinMain  extends Application {

    /**
     * @param args the command line arguments
     */
    Stage primaryStage;
    public static void main(String[] args) {
       launch(args);
    }
        public void gestionMedecinWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MedecinDetails.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            
            GestionMedecinController controller = loader.getController();
           controller.setMain(this);
            
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public void newMedecinWindow(Medecin medecin){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterMedecin.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            
            MedecinFormController controller = loader.getController();
            Stage stage = new Stage();
           controller.setMain(this, stage, medecin);   
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            //return controller.isOkClicked();
            
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            //return false;
        }
        
    }
    @Override
       public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gestionMedecinWindow();
    }
     
  
     
}
