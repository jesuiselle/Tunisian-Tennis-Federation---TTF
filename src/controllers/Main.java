/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class Main extends Application {

    static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        mainWindow();
       

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void mainWindow() {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AccueilWindow.fxml"));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            AccueilController controller = loader.getController();
            controller.setMain(this);

            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons-256/matte-white-square-icons-sports-hobbies/125846-matte-white-square-icon-sports-hobbies-ball-tennis.png",0, 0, true, true)); 
            primaryStage.setTitle("Fédération Tunisienne de Tennis");           
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
