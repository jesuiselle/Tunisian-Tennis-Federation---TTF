/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ArbitreDao;
import dao.JoueurDao;
import entities.Arbitre;
import entities.Joueur;
import idao.iUserDao;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class ArbitreFormController {
    
    @FXML
    TextField nomArbitreField, prenomArbitreField, loginField, passwordField;
    @FXML
    ChoiceBox<String> categorieCB;
    @FXML
    DatePicker DateNaissanceDP;
    iUserDao<Arbitre> dao = new ArbitreDao();
  ObservableList<Arbitre> list = FXCollections.observableArrayList();
   
   @FXML
    public void handleAjout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterArbitre.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            //return controller.isOkClicked();

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            //return false;
        }
    }
    
    @FXML
    public void handleCancel(){
        //stage.close();
    }

@FXML
    public void handleImage(){

    }

}
