/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MediaDao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author 
 */
public class VideoController implements Initializable {
   
    @FXML
    ScrollPane contentPane;

    MediaDao dao = new MediaDao();
    ObservableList<Media> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<entities.Media> medias = dao.findAllByGenreV();
        HBox hbox = new HBox(3);
        //medias.stream().map((g) -> {
         medias.stream().forEach((file) -> {
            
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            String lien = file.getSource().substring(0, 4) + file.getSource().substring(5, 23)
                    + "/embed/" + file.getSource().substring(32);
            /*System.out.println(lien);*/
            webEngine.load(lien);
            hbox.getChildren().add(browser);
        });
            contentPane.setContent(hbox);

    }

}
