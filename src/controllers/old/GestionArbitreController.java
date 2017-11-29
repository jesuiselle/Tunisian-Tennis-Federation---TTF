/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ArbitreDao;
import dao.JoueurDao;
import entities.Arbitre;
import entities.Joueur;
import idao.iUserDao;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bouchriha
 */
public class GestionArbitreController {

    @FXML
    TableView<Arbitre> arbitreTableView;
    @FXML
    TableColumn<Arbitre, String> nomArbitreColumn;
    @FXML
    TableColumn<Arbitre, String> prenomArbitreColumn;
    @FXML
    Label nomArbitreLabel, prenomArbitreLabel, categorieArbitreLabel, dateArbitreLabel;
    

   ArbitreDao dao = new ArbitreDao();
    ObservableList<Arbitre> list = FXCollections.observableArrayList();

   

    public void initialize() {
        nomArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("nom"));
        prenomArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("prenom"));

        arbitreTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void setMain(Tft main) {
        
        

        list.addAll(dao.findAll());
        arbitreTableView.setItems(list);
    }

    public void showDetails(Arbitre arbitre) {
        nomArbitreLabel.setText(arbitre.getNom());
        prenomArbitreLabel.setText(arbitre.getPrenom());
     
        categorieArbitreLabel.setText(arbitre.getCategorie());
        dateArbitreLabel.setText(arbitre.getDateNaissance());

    }

    @FXML
    public void handleAjout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterArbitre.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            //return controller.isOkClicked();
            
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            //return false;
        }
    }

    @FXML
    public void handleModifier() {
        Arbitre arbitre = arbitreTableView.getSelectionModel().getSelectedItem();
      
        System.out.println(arbitre);
        /*if (okClicked) {
            arbitreTableView.setItems(null);
            arbitreTableView.layout();
            list.addAll(dao.findAll());
            arbitreTableView.setItems(list);
        }*/
    }

    @FXML
    public void handleSupprimer() {
        Arbitre arbitre = arbitreTableView.getSelectionModel().getSelectedItem();
        dao.removeById(arbitre.getId());
        arbitreTableView.setItems(null);
        arbitreTableView.layout();
        list.removeAll(list);
        list.addAll(dao.findAll());
        arbitreTableView.setItems(list);
    }
    public void handleHistorique() {

        
        
        
        
        
        
        
        
}




}
