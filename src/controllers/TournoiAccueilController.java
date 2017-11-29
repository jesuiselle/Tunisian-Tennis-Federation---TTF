/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.EvenementDao;
import entities.Evenement;
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
public class TournoiAccueilController implements Initializable {
   
    @FXML
    ScrollPane contentPane;

    EvenementDao dao = new EvenementDao();
    ObservableList<Evenement> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Evenement> evnm = dao.findAll();
        VBox vbox = new VBox();
        evnm.stream().map((e) -> {
            
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(50));
            // gridpane.setHgap(5);
            // gridpane.setVgap(5);
            
            Label nomLbl = new Label(e.getNom()); 
            nomLbl.setStyle( "-fx-font-size: 40pt;");
            nomLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(nomLbl, 0, 1);

            Image img = new Image("http://localhost/resources/images/evenements/" + e.getImage());
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(1000);
            imgView.setFitHeight(600);
            gridpane.add(imgView, 0, 2);
            
            Label descriptionLbl = new Label("Description: "); 
            
            descriptionLbl.setStyle( "-fx-font-size: 10pt;");
            descriptionLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(descriptionLbl, 0, 3);
            
            Label descrLbl = new Label("                    "+e.getDescription());  
            descrLbl.setStyle("-fx-font-size:10pt;");
            descrLbl.setStyle( "-fx-font-size: 10pt;");
            descrLbl.setTextFill(Color.web("#000000"));
            gridpane.add(descrLbl, 0, 3);
            
            Label prixLbl = new Label("Prix: ");
            prixLbl.setStyle( "-fx-font-size: 10pt;");
            prixLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(prixLbl, 0, 4);
            
            Label plbl = new Label("        "+Double.toString(e.getPrix()));
            plbl.setStyle( "-fx-font-size: 10pt;");
            plbl.setTextFill(Color.web("#000000"));
            gridpane.add(plbl, 0, 4);
            
            /*if (!e.getGagnant().equals(""))
            {*/
            
            Label gagnantLbl = new Label("Gagnant: ");
            gagnantLbl.setStyle( "-fx-font-size: 10pt;");
            gagnantLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(gagnantLbl, 0, 5);
            
            Label gLbl = new Label("                "+e.getGagnant());
            gLbl.setStyle( "-fx-font-size: 10pt;");
            gLbl.setTextFill(Color.web("#000000"));
            gridpane.add(gLbl, 0, 5);
            //}
            
            Label datedebutLbl = new Label("Date debut: ");
            datedebutLbl.setStyle( "-fx-font-size: 10pt;");
            datedebutLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(datedebutLbl, 0, 6);
            
            Label ddLbl = new Label("                    "+e.getDateDebut());
            ddLbl.setStyle( "-fx-font-size: 10pt;");
            ddLbl.setTextFill(Color.web("#000000"));
            gridpane.add(ddLbl, 0, 6);
            
            
            Label datefinLbl = new Label("date fin: ");
            datefinLbl.setStyle( "-fx-font-size: 10pt;");
            datefinLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(datefinLbl, 0, 7);
            
            Label dfLbl = new Label("              "+e.getDateFin());
            dfLbl.setStyle( "-fx-font-size: 10pt;");
            dfLbl.setTextFill(Color.web("#000000"));            
            gridpane.add(dfLbl, 0, 7);
            
            return gridpane;
        }).forEach((gridpane) -> {
            vbox.getChildren().add(gridpane);
        });
        contentPane.setContent(vbox);

    }

}
