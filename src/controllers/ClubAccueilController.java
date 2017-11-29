/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ClubDao;
import entities.Club;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author 
 */
public class ClubAccueilController  implements Initializable {
   
    @FXML
    ScrollPane contentPane;

    ClubDao dao = new ClubDao();
    ObservableList<Club> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Club> club = dao.findAll();
        VBox vbox = new VBox();
        club.stream().map((c) -> {
            
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(5));
            gridpane.setPrefWidth(1366);
            //gridpane.setPrefHeight(610);
            // gridpane.setHgap(5);
            // gridpane.setVgap(5);
            gridpane.getStylesheets().add("path/stylesheet.css");
            gridpane.setStyle("-fx-background-color: white;");
            
            Image img = new Image("http://localhost/resources/images/clubs/" + c.getLogo());
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(200);
            imgView.setFitHeight(200);
            gridpane.add(imgView, 0, 1);

            Label nomLbl = new Label(" Nom: "); 
            nomLbl.setStyle( "-fx-font-size: 10pt;");
            nomLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(nomLbl, 1, 1);
            
            Label nomcLbl = new Label("           "+c.getNom()); 
            nomcLbl.setStyle( "-fx-font-size: 10pt;");
            nomcLbl.setTextFill(Color.web("black"));
            gridpane.add(nomcLbl, 1, 1);
            
            Label lLbl = new Label("\n"+"\n"+"\n"+" Date de fondation:     "); 
            lLbl.setStyle( "-fx-font-size: 10pt;");
            lLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(lLbl, 1, 1);
    
            Label lieuLbl = new Label("\n"+"\n"+"\n"+"                                "+c.getDateFondation()); 
            lieuLbl.setStyle( "-fx-font-size: 10pt;");
            lieuLbl.setTextFill(Color.web("black"));
            gridpane.add(lieuLbl, 1, 1);
            
            Label adrLbl = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+" Adresse:     "); 
            adrLbl.setStyle( "-fx-font-size: 10pt;");
            adrLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(adrLbl, 1, 1);
            
            Label adresseLbl = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"                 "+c.getAdresse()); 
            adresseLbl.setStyle( "-fx-font-size: 10pt;");
            adresseLbl.setTextFill(Color.web("black"));
            gridpane.add(adresseLbl, 1, 1);
   
             return gridpane;
        }).forEach((gridpane) -> {
            vbox.getChildren().add(gridpane);
        });
        contentPane.setContent(vbox);

    }}
