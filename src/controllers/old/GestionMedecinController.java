/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.MedecinDao;
import entities.Medecin;
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
 * @author Aydi
 */
public class GestionMedecinController {

    @FXML
    TableView<Medecin> medecinTableView;
    @FXML
    TableColumn<Medecin, String> nomMedecinColumn;
    @FXML
    TableColumn<Medecin, String> prenomMedecinColumn;
    @FXML
    Label loginMedecinLabel, nomMedecinLabel, prenomMedecinLabel;
    iUserDao dao = new MedecinDao();
    ObservableList<Medecin> list = FXCollections.observableArrayList();
    private MedecinMain main;

    public void initialize() {
        nomMedecinColumn.setCellValueFactory(new PropertyValueFactory<Medecin, String>("nom"));
        prenomMedecinColumn.setCellValueFactory(new PropertyValueFactory<Medecin, String>("prenom"));

        medecinTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
        list.addAll(dao.findAll());
        medecinTableView.setItems(list);
    }

    public void setMain(MedecinMain main) {
        this.main = main;
        
    }

    private void showDetails(Medecin medecin) {
        loginMedecinLabel.setText(medecin.getEmail());
        nomMedecinLabel.setText(medecin.getNom());
        prenomMedecinLabel.setText(medecin.getPrenom());
    }

    @FXML
    public void handleAjout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterMedecin.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            
            //MedecinFormController controller = loader.getController();
            Stage stage = new Stage();
           //controller.setMain(this, stage, null);   
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
        Medecin medecin = medecinTableView.getSelectionModel().getSelectedItem();
        main.newMedecinWindow(medecin);
        System.out.println(medecin);
        /*if (okClicked) {
            arbitreTableView.setItems(null);
            arbitreTableView.layout();
            list.addAll(dao.findAll());
            arbitreTableView.setItems(list);
        }*/
    }

  @FXML
    public void handleSupprimer() {
        Medecin medecin = medecinTableView.getSelectionModel().getSelectedItem();
        dao.removeById(medecin.getId());
        medecinTableView.setItems(null);
        medecinTableView.layout();
        list.removeAll(list);
        list.addAll(dao.findAll());
        medecinTableView.setItems(list);
    }

}
