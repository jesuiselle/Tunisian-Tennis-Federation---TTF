/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MediaDao;
import entities.Joueur;
import entities.Media;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MediaController implements Initializable {

    @FXML
    TableView<Media> mediaTableView;
    @FXML
    TableColumn<Media, Integer> idMediaColumn;
    @FXML
    TableColumn<Media, String> genreColumn;
    @FXML
    TableColumn<Media, String> TitreColumn;
    @FXML
    TableColumn<Media, String> sourceColumn;

    @FXML
    TextField titreMediaField, sourceMediaField;

    @FXML
    ChoiceBox< String> GenreCB;

    ObservableList<Joueur> jou = FXCollections.observableArrayList();

    ObservableList<String> gen = FXCollections.observableArrayList();
    Media media;
    MediaDao dao = new MediaDao();
    ObservableList<Media> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gen.addAll("Image", "Video");
        GenreCB.setItems(gen);
        idMediaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        TitreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
        list.addAll(dao.findAll());
        mediaTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (media != null) {
                media.setTitre(titreMediaField.getText());
                media.setSource(sourceMediaField.getText());
                media.setGenre(GenreCB.getValue());
                dao.update(media);

            } else {
                media = new Media();

                media.setTitre(titreMediaField.getText());
                media.setSource(sourceMediaField.getText());
                media.setGenre(GenreCB.getValue());

                dao.add(media);
            }

            media = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void annuler() {
        clear();
        media = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        media = mediaTableView.getSelectionModel().getSelectedItem();
        if (media != null) {
            titreMediaField.setText(media.getTitre());
            sourceMediaField.setText(media.getSource());
            GenreCB.setValue(media.getGenre());
        }

    }

    @FXML
    public void supprimer() {
        media = mediaTableView.getSelectionModel().getSelectedItem();
        if (media != null) {
            dao.removeById(media.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void afficher() {
        
        media = mediaTableView.getSelectionModel().getSelectedItem();
        if (media != null) {
            Stage myDialog = mediaDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            media = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        sourceMediaField.clear();
        titreMediaField.clear();
        GenreCB.setValue(null);
    }

    public boolean allFieldsFilled() {
        return (!sourceMediaField.getText().isEmpty()
                && !sourceMediaField.getText().isEmpty()
                && GenreCB.getValue() != null);
    }

     public Stage mediaDetails(Stage owner) {
        Stage s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        s.initOwner(owner);
        s.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root,Color.rgb(1,205,230,0.3));
        s.setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setVgap(5);

        Button login = new Button("Fermer");
        login.setStyle( "-fx-font-size: 10pt;");
        login.setTextFill(Color.web("#fe3554"));
        login.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                s.close();
            }
        });
        login.setBackground(Background.EMPTY);
        gridpane.add(login, 1, 1);
        GridPane.setHalignment(login, HPos.RIGHT);
        
        
        
        if (media.getGenre().equals("Video")){
            WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        String lien = media.getSource().substring(0, 4) + media.getSource().substring(5, 23) 
                + "/embed/" + media.getSource().substring(32);
            //System.out.println(lien);
        webEngine.load(lien);
        gridpane.add(browser, 0, 3);
        }
        else if (media.getGenre().equals("Image")){
         
        Label titreLbl = new Label("Titre : ");
        titreLbl.setStyle( "-fx-font-size: 10pt;");
        titreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(titreLbl, 0, 2);
        Label mLbl = new Label("        "+media.getTitre());
        mLbl.setStyle( "-fx-font-size: 10pt;");
        mLbl.setTextFill(Color.web("#293539"));
        gridpane.add(mLbl , 0, 2);
            
         Integer i=media.getSource().length();
            String str = media.getSource().substring(24, i);    
             //  System.out.println(str);
            Image img = new Image("http://localhost/resources/images/"+str);
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(300);
        imgView.setFitHeight(300);
        gridpane.add(imgView, 0, 3);
       
        }
        else {
            Label exceptionLbl = new Label(" Ressource non trouvé ");
            gridpane.add(exceptionLbl, 0, 4);
        }
        

        root.getChildren().add(gridpane);
        return s;
}
}