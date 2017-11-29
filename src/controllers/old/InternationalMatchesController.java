/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ArbitreDao;
import dao.JoueurDao;
import dao.MatchDao;
import entities.Arbitre;
import entities.Joueur;
import entities.Partie;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class InternationalMatchesController {
    
    @FXML
   TextArea pronosticTextArea,compteTextArea;
  
    @FXML
    DatePicker dateOuvertureDP,dateFermetureDP;
   MatchDao dao = new MatchDao();
  ObservableList<Partie> list = FXCollections.observableArrayList();
   
   @FXML
    public void handleAjouter() {
        
       
    }
    
   

}
