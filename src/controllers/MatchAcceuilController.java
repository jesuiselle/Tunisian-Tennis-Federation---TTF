/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MatchDao;
import entities.Evenement;
import entities.Partie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author 
 */
public class MatchAcceuilController  implements Initializable {

    @FXML
    ScrollPane contentPane;
    Evenement ev;
    MatchDao dao = new MatchDao();
    ObservableList<Partie> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Partie> matchacceuil = dao.findAll();
        VBox vbox = new VBox();
        matchacceuil.stream().map((Partie m) -> {
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(20));
           // gridpane.setAlignment(Pos.CENTER);
            gridpane.getStylesheets().add("path/stylesheet.css");
            // gridpane.setHgap(5);
            // gridpane.setVgap(5);
            
         
            
        /*
        //MediaView
            // Create the media source.
             String VID_URL=m.getLien();
             Media media = new Media("https://www.youtube.com/embed/cBDF9zTWaLQ");
           

     // Create the player and set to play automatically.
              MediaPlayer mediaPlayer = new MediaPlayer(media);
              mediaPlayer.setAutoPlay(false);
              MediaView mediaView = new MediaView(mediaPlayer);
              gridpane.add(mediaView, 0, 11);
              mediaPlayer.play();
              mediaPlayer.pause();
          */

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        String lien = m.getLien().substring(0, 4) + m.getLien().substring(5, 23) 
                + "/embed/" + m.getLien().substring(32);
            //System.out.println(lien);
        webEngine.load(lien);
        browser.resize(200, 200);
        gridpane.add(browser, 0, 1);
        
            Label datembl = new Label("Date match:                 ");
            
            datembl.setTextFill(Color.web("#0076a3"));
            
            gridpane.add(datembl, 0, 2);
            
             Label datmbl = new Label("                    "+m.getDateMatch());
            datmbl.setStyle( "-fx-font-size: 10pt;");
            gridpane.add(datmbl, 0, 2);
            
            Label nomStadebl = new Label("Nom de stade:             ");
            nomStadebl.setTextFill(Color.web("#0076a3"));
            gridpane.add(nomStadebl, 0, 3);
            
            Label nomStdebl = new Label("                         "+m.getStade().getNom()+" "+m.getStade().getLieu());
            nomStadebl.setTextFill(Color.web("#0076a3"));
            gridpane.add(nomStdebl, 0, 3);
            
            Label lieuStadebl = new Label("Lieu de stade:             ");
            lieuStadebl.setTextFill(Color.web("#0076a3"));
            gridpane.add(lieuStadebl, 0, 4);
            
             
            Label lStadebl = new Label("                       "+m.getStade().getLieu());
            gridpane.add(lStadebl, 0, 4);
            
            Label evenementbl = new Label("Nom évenement:                      ");
            evenementbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(evenementbl, 0, 5);
            
            Label evnemntbl = new Label("                            "+m.getEvenement().getNom());
            gridpane.add(evnemntbl, 0, 5);
            
            Label arbitrebl = new Label("Arbitre:                               ");
            arbitrebl.setTextFill(Color.web("#0076a3"));
            gridpane.add(arbitrebl, 0, 6);
            
            Label arbtrbl = new Label("             "+m.getArbitre().getNom()+" "+m.getArbitre().getPrenom());
            gridpane.add(arbtrbl, 0, 6);
            
            Label descriptionbl = new Label("Description match:                       ");
            descriptionbl.setStyle("-fx-font-size:10pt;");
            descriptionbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(descriptionbl, 0, 7);
            Label descbl = new Label("                                   "+m.getDescription());
            descbl.setWrapText(true);
            gridpane.add(descbl, 0, 7);
            
            Label genrebl = new Label("Genre de match:                                 ");
            genrebl.setTextFill(Color.web("#0076a3"));
            gridpane.add(genrebl, 0, 8);
            Label gnrbl = new Label("                            "+m.getGenre());
            gridpane.add(gnrbl, 0, 8);
            
            Label typebl = new Label("Type de match:                                    ");
            typebl.setTextFill(Color.web("#0076a3"));
            gridpane.add(typebl, 0, 9);
            Label tbl = new Label("                          "+m.getType());
            gridpane.add(tbl, 0, 9);
            
            Label scorebl = new Label("Score:                                            ");
            scorebl.setTextFill(Color.web("#0076a3"));
            gridpane.add(scorebl, 0, 10);
            Label screbl = new Label("           "+m.getScore());
            gridpane.add(screbl, 0, 10);
            
            Label premmbl = new Label("Score | Première mi temps:                          ");
            premmbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(premmbl, 0, 11);
            Label prmbl = new Label("                                            "+m.getSetUn());
            gridpane.add(prmbl, 0, 11);
            
            Label deuximbl = new Label("Score | Deuxième mi temps:                          ");
            deuximbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(deuximbl, 0, 12);
            Label deuxibl = new Label("                                              "+m.getSetDeux());
            gridpane.add(deuxibl, 0, 12);
            
            Label troismbl = new Label("Score | Troisième mi temps:                          ");
            troismbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(troismbl, 0, 13);
            Label troisbl = new Label("                                             "+m.getSetTrois());
            gridpane.add(troisbl, 0, 13);
            
            Label quatrmbl = new Label("Score | Quatrième mi temps:                           ");
            quatrmbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(quatrmbl, 0, 14);
            Label quatrbl = new Label("                                               "+m.getSetQuatre());
            gridpane.add(quatrbl, 0, 14);
            
            Label cinqmbl = new Label("Score | Cinquième mi temps:                            ");
            cinqmbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(cinqmbl, 0, 15);
            Label cinqbl = new Label("                                               "+m.getSetCinq());
            gridpane.add(cinqbl, 0, 15);
        
        
        return gridpane;
        }).forEach((gridpane) -> {
            vbox.getChildren().add(gridpane);
        });
        
        contentPane.setContent(vbox);

    }
}
