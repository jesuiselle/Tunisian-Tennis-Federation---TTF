/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CompteRenduTestDao;
import dao.StadeDao;
import entities.CompteRenduTest;
import entities.Stade;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author 
 */
public class StadeAccueilController implements Initializable {
       
    @FXML
    ScrollPane contentPane;

    StadeDao dao = new StadeDao();
    ObservableList<Stade> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

         List<Stade> stade = dao.findAll();
        VBox vbox = new VBox();
        stade.stream().map((s) -> {
            
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(50));
            gridpane.setPrefWidth(1366);
            //gridpane.setPrefHeight(610);
            // gridpane.setHgap(5);
            // gridpane.setVgap(5);
            gridpane.getStylesheets().add("path/stylesheet.css");
            gridpane.setStyle("-fx-background-color: white;");
            
            
            Label nomLbl = new Label(s.getNom()); 
            nomLbl.setStyle( "-fx-font-size: 30pt;");
            nomLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(nomLbl, 0, 1);
            
            
            Image img = new Image("http://localhost/resources/images/stades/" + s.getImage());
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(200);
            imgView.setFitHeight(200);
            gridpane.add(imgView, 0, 2);
           
            Label descriptionLbl = new Label(" "+s.getDescription()); 
            descriptionLbl.setWrapText(true);
            descriptionLbl.setStyle("-fx-font-size:10pt;");
            descriptionLbl.setStyle( "-fx-font-size: 10pt;");
            descriptionLbl.setTextFill(Color.web("black"));
            gridpane.add(descriptionLbl, 1, 2);
            
            Label lLbl = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+" "+"Lieu :     "); 
            lLbl.setStyle( "-fx-font-size: 10pt;");
            lLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(lLbl, 1, 2);
            
            
            Label lieuLbl = new Label("\n"+"\n"+"\n"+"          "+s.getLieu()); 
            lieuLbl.setStyle( "-fx-font-size: 10pt;");
            lieuLbl.setTextFill(Color.web("black"));
            gridpane.add(lieuLbl, 1, 2);
            
           
            
             return gridpane;
        }).forEach((gridpane) -> {
            vbox.getChildren().add(gridpane);
        });
        contentPane.setContent(vbox);

    }
            
        
}

