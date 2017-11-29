/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import dao.MessageDao;
import entities.Message;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author 
 */
public class ContactController implements MapComponentInitializedListener, Initializable {
    
    @FXML
    AnchorPane gMapPane;
    GoogleMapView mapView;
    GoogleMap map;
    @FXML
    TextField nom;
    @FXML
    TextField adresse;
    @FXML 
    TextArea msg;
    
    Message message;
    MessageDao dao = new MessageDao();
    ObservableList<Message> list = FXCollections.observableArrayList();
    
    
    @FXML
    public void handleEnvoyer(){
    /*    
            if (msg != null && adresse != null &&  nom != null ) {
                message.setNom(nom.getText());
                message.setEmail(adresse.getText());
                message.setMessage(msg.getText());
                
                dao.add(message);
            }       */ 
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(36.857573, 10.182920))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(10);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(new LatLong(36.857573, 10.182920))
                .visible(Boolean.TRUE)
                .title("TFT");

        Marker marker = new Marker(markerOptions);

        map.addMarker(marker);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView = new GoogleMapView();
       
        mapView.addMapInializedListener(this);
        
        AnchorPane.setTopAnchor(mapView, 0d);
        AnchorPane.setRightAnchor(mapView, 0d);
        AnchorPane.setBottomAnchor(mapView, 0d);
        AnchorPane.setLeftAnchor(mapView, 0d);
        
        gMapPane.getChildren().add(mapView);
    }
    
}
