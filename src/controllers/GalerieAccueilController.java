/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MediaDao;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;

/**
 *
 * @author 
 */
public class GalerieAccueilController implements Initializable {
   
    @FXML
    ScrollPane contentPane;

    MediaDao dao = new MediaDao();
    ObservableList<Media> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<entities.Media> medias = dao.findAllByGenre();
        HBox hbox = new HBox(3);
        //medias.stream().map((g) -> {
            for (final entities.Media file : medias) {
            Integer i=file.getSource().length();
            String str = file.getSource().substring(24, i);    
             //  System.out.println(str);
            Image img = new Image("http://localhost/resources/images/"+str);
            ImageView imgView = new ImageView(img);
            //imgView.screenToLocal(50.0, 70.0);
            imgView.setFitWidth(1366);
            imgView.setFitHeight(610);
           
           hbox.getChildren().add(imgView);
        }
        contentPane.setContent(hbox);

    }}

