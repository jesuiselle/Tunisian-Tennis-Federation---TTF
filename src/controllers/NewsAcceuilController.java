/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ActualiteDao;
import entities.Actualite;
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
public class NewsAcceuilController implements Initializable {
   
    @FXML
    ScrollPane contentPane;

    ActualiteDao dao = new ActualiteDao();
    ObservableList<Actualite> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Actualite> actualites = dao.findAll();
        VBox vbox = new VBox();
        actualites.stream().map((a) -> {
            
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(50));
            gridpane.setPrefWidth(1366);
            gridpane.getStylesheets().add("path/stylesheet.css");
            gridpane.setStyle("-fx-background-color: white;");
            
            // gridpane.setHgap(5);
            // gridpane.setVgap(5);
            
            Label nomLbl = new Label(a.getTitre());
            nomLbl.setStyle( "-fx-font-size: 40pt;");
            nomLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(nomLbl, 0, 1);
                      
            Image img = new Image("http://localhost/resources/images/actualites/" + a.getImage());
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(1000);
            imgView.setFitHeight(600);
            gridpane.add(imgView, 0, 2);
                        
            Label contenuLbl = new Label(a.getContenu());
            contenuLbl.setWrapText(true);
            contenuLbl.setStyle( "-fx-font-size: 10pt;");
            contenuLbl.setTextFill(Color.web("#000000"));
            gridpane.add(contenuLbl, 0, 3);
                       
            Label dateLbl = new Label("Date de publication: ");
            dateLbl.setStyle( "-fx-font-size: 10pt;");
            dateLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(dateLbl, 0, 4);
            
            Label datLbl = new Label("                                 "+a.getDatePublication());
            datLbl.setStyle( "-fx-font-size: 10pt;");
            datLbl.setTextFill(Color.web("#000000"));
            gridpane.add(datLbl, 0, 4);
             
            
            
            return gridpane;
        }).forEach((gridpane) -> {
            vbox.getChildren().add(gridpane);
        });
        contentPane.setContent(vbox);

    }
}
