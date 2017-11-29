/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bouchriha
 */
public class ContactController implements MapComponentInitializedListener {
    
    @FXML
    AnchorPane gMapPane;
    GoogleMapView mapView;
    GoogleMap map;

    private ContactUsMain main;
    
    public void setMain(ContactUsMain main){
        this.main = main;
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
        gMapPane.getChildren().add(mapView);
        
    }
    
    
    @FXML
    public void handleEnvoyer(){
        
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.857573, 10.182920))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(new LatLong(36.857573, 10.182920))
                .visible(Boolean.TRUE)
                .title("TFT");

        Marker marker = new Marker(markerOptions);

        map.addMarker(marker);

    }
    
}
