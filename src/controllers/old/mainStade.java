/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import entities.Stade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author hichem
 */
public class mainStade extends Application {
 Stage primaryStage;
    public static void main(String[] args) {

        launch(args);
    
    }
    
     public void gestionStadeWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StadeDetails.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            
            GestionStadeController controller = loader.getController();
          controller.setMain(this);
            
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
     }
         public void newStadeWindow(Stade stade){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterStade.fxml"));
            AnchorPane pane = loader.load();
           Scene scene = new Scene(pane);
            
           StadeFormController controller= loader.getController();
            Stage stage = new Stage();
            controller.setmain(this, stage, stade);
            
           stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

     this.primaryStage = primaryStage;
        gestionStadeWindow();
    
    }
    
}
