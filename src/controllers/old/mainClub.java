/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import entities.Club;
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
public class mainClub extends Application {

    Stage primaryStage;

    public static void main(String[] args) {

        launch(args);

    }

    public void gestionClubWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Clubdetails.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            GestionClubController controller = loader.getController();
            controller.setMain(this);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newClubWindow(Club club) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterClub.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            ClubFormController controller = loader.getController();
            Stage stage = new Stage();
            controller.setmain(this, stage, club);

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
        gestionClubWindow();

    }

}
