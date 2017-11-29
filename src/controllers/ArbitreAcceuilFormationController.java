/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import dao.FormationDao;
import entities.Formation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class ArbitreAcceuilFormationController implements Initializable {
    @FXML
    TableView<Formation> arbitreFTableView;
    @FXML
    TableColumn<Formation, String> descriptionFColumn;
    @FXML
    TableColumn<Formation, String> datedebutFColumn;
    @FXML
    TableColumn<Formation, String> datefinFColumn;
    
    Formation formation;
    FormationDao dao = new FormationDao();
    ObservableList<Formation> list = FXCollections.observableArrayList();

     @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        datedebutFColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        datefinFColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        descriptionFColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        list.addAll(dao.findAll());
        arbitreFTableView.setItems(list);
    }


}
