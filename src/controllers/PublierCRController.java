/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CompteRenduTestDao;
import entities.CompteRenduTest;
import entities.Medecin;
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
import utils.Utils;

/**
 *
 * @author 
 */
public class PublierCRController implements Initializable {

    @FXML
    TableView<CompteRenduTest> testTableView;
    @FXML
    TableColumn<CompteRenduTest, String> nomMedecinColumn;
    @FXML
    TableColumn<CompteRenduTest, String> nomJoueurColumn;
    @FXML
    TableColumn<CompteRenduTest, String> resultatTestColumn;
    @FXML
    TableColumn<CompteRenduTest, String> descriptionTestColumn;

    
    CompteRenduTest publierCT;
    CompteRenduTestDao dao = new CompteRenduTestDao();
    ObservableList<CompteRenduTest> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nomMedecinColumn.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        nomJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("joueur"));
        resultatTestColumn.setCellValueFactory(new PropertyValueFactory<>("resultat"));
        descriptionTestColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        list.addAll(dao.findAllForPub());
        testTableView.setItems(list);
    }

    @FXML
    public void publier() {
        
        publierCT = testTableView.getSelectionModel().getSelectedItem();
        if (publierCT != null) {
            publierCT.setResponsable(Utils.responsable);
            dao.updateEtat(publierCT);
            list.removeAll(list);
            list.addAll(dao.findAllForPub());
        }
    }

}
