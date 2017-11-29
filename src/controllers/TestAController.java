/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CompteRenduTestDao;
import entities.CompteRenduTest;
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
public class TestAController implements Initializable {
   
    String res;
    
    @FXML
    ScrollPane contentPane;

    CompteRenduTestDao dao = new CompteRenduTestDao();
    ObservableList<CompteRenduTest> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<CompteRenduTest> crt = dao.findAll();
        VBox vbox = new VBox();
        
        crt.stream().map((c) -> {
            
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(50));
            //gridpane.setPrefSize(1366, 610);
            gridpane.setPrefWidth(1366);
            //gridpane.setAlignment(Pos.CENTER);
            gridpane.getStylesheets().add("path/stylesheet.css");
            gridpane.setStyle("-fx-background-color: white;");
            
            // gridpane.setHgap(5);
            // gridpane.setVgap(5);
            
            /*Label titreLbl = new Label("Résultat test d'antidopage");
            titreLbl.setStyle( "-fx-font-size: 20pt; -fx-text-align: center");
            titreLbl.setTextFill(Color.web("#0076a3"));
            //gridpane.setHalignment(titreLbl, HPos.CENTER);
            //   titreLbl.setTextAlignment(TextAlignment.CENTER);
        
            gridpane.add(titreLbl, 0, 1);*/
            
            
            
            if (c.getEtat()){
                            
            Label joueurLbl = new Label(c.getJoueur().getNom()+" "+c.getJoueur().getPrenom());
            joueurLbl.setStyle( "-fx-font-size: 40pt;");
            joueurLbl.setTextFill(Color.web("#0076a3"));
            joueurLbl.setAlignment(Pos.CENTER);
            gridpane.add(joueurLbl, 0, 1);
            
            Image img = new Image("http://localhost/resources/images/joueurs/" + c.getJoueur().getImage());
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(400);
            imgView.setFitHeight(400);
            gridpane.add(imgView, 0, 2);
            
           
            if (c.getResultat())
                res="Test antidopage négatif";
            else
                res="Test antidopage positif";
            
            Label resLbl = new Label("\n"+"\n"+"\n"+" Résultat: ");
          
            resLbl.setStyle( "-fx-font-size: 10pt;");
            resLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(resLbl, 1, 2);
            
            Label resaLbl = new Label("\n"+"\n"+"\n"+"               "+res);
            resaLbl.setStyle( "-fx-font-size: 10pt;");
            resaLbl.setTextFill(Color.web("#000000"));
            gridpane.add(resaLbl, 1, 2);
            
            Label descLbl = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+" Description: ");
            descLbl.setStyle("-fx-font-size:10pt;");
            descLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(descLbl, 1, 2);
            
            Label desLbl = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"                     "+c.getDescription());
            desLbl.setWrapText(true);            
            desLbl.setStyle("-fx-font-size:10pt;");
            desLbl.setTextFill(Color.web("#000000"));
            gridpane.add(desLbl, 1, 2);
            
            Label medecLbl = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+" Medecin: ");
           
            medecLbl.setWrapText(true);
            medecLbl.setStyle("-fx-font-size:10pt;");
            medecLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(medecLbl, 1, 2);
            
            Label medecinLbl = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"                 "+c.getMedecin().getNom()+" "+c.getMedecin().getPrenom());
            medecinLbl.setStyle("-fx-font-size:10pt;");
            medecinLbl.setTextFill(Color.web("#000000"));
            gridpane.add(medecinLbl, 1, 2);
            
            }
 
            
            return gridpane;
        }).forEach((gridpane) -> {
            vbox.getChildren().add(gridpane);
        });
        contentPane.setContent(vbox);

    }    
}
