/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ArbitreDao;
import dao.CompteRenduTestDao;
import entities.Arbitre;
import entities.CompteRenduTest;
import idao.interfaceDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bouchriha
 */
public class CompteRenduTestController {

    @FXML
    TableView<CompteRenduTest> compteRenduTableView;
    @FXML
    TableColumn<CompteRenduTest, String> nomCompteRenduColumn;
    @FXML
    TableColumn<CompteRenduTest, String> prenomCompteRenduColumn;
    @FXML
    TextArea descriptionCompteRenduArea;
    CompteRenduTestDao dao = new CompteRenduTestDao();
    ObservableList<CompteRenduTest> list = FXCollections.observableArrayList();

    public void initialize() {
        nomCompteRenduColumn.setCellValueFactory(new PropertyValueFactory<CompteRenduTest, String>("nom"));
       prenomCompteRenduColumn.setCellValueFactory(new PropertyValueFactory<CompteRenduTest, String>("prenom"));

        compteRenduTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }


    public void showDetails(CompteRenduTest compteRendu) {
      descriptionCompteRenduArea.setText(compteRendu.getDescription());
    
     
      


    }

    @FXML
    public void handleAjout() {
      
    }
 @FXML
  public void handleCancel(){
    }

}
