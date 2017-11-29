/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.JoueurDao;
import entities.Joueur;
import idao.iUserDao;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bouchriha
 */
public class GestionJoueurController {

    @FXML
    TableView<Joueur> joueurTableView;
    @FXML
    TableColumn<Joueur, String> nomJoueurColumn;
    @FXML
    TableColumn<Joueur, String> prenomJoueurColumn;
    @FXML
    Label nomJoueurLabel, prenomJoueurLabel, dateNaissanceJoueurLabel, categorieJoueurLabel, clubJoueurLabel;
    @FXML
    ImageView joueurImageView;
    private Joueur joueur;

    iUserDao<Joueur> dao = new JoueurDao();
    ObservableList<Joueur> list = FXCollections.observableArrayList();

    public void initialize() {
        nomJoueurColumn.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nom"));
        prenomJoueurColumn.setCellValueFactory(new PropertyValueFactory<Joueur, String>("prenom"));
        list.addAll(dao.findAll());
        joueurTableView.setItems(list);
        joueurTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> System.out.println("salem"));
    }

    public void showDetails(Joueur joueur) {
        nomJoueurLabel.setText(joueur.getNom());
        prenomJoueurLabel.setText(joueur.getPrenom());
        dateNaissanceJoueurLabel.setText(joueur.getDateNaissance());
        categorieJoueurLabel.setText(joueur.getCategorie());
        clubJoueurLabel.setText(joueur.getClub().getNom());
        Image img;
        if(joueur.getImage() != null){
            img = new Image("http://localhost/resources/images/joueurs/"
                + joueur.getImage());
        }
        else{
            img = new Image("http://localhost/resources/images/face.jpg");
        }
        joueurImageView.setImage(img);

    }

    @FXML
    public void handleAjout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterJoueur.fxml"));
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
        Joueur joueur = joueurTableView.getSelectionModel().getSelectedItem();

        /*if (okClicked) {
            arbitreTableView.setItems(null);
            arbitreTableView.layout();
            list.addAll(dao.findAll());
            arbitreTableView.setItems(list);
        }*/
    }

    @FXML
    public void handleSupprimer() {
        Joueur joueur = joueurTableView.getSelectionModel().getSelectedItem();
        dao.removeById(joueur.getId());
        /* joueurTableView.setItems(null);
        joueurTableView.layout();
        list.removeAll(list);
        list.addAll(dao.findAll());
        joueurTableView.setItems(list);*/

    }

    @FXML
    public void handleHistorique() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HistoriqueJoueur.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            HistoriqueJoueurController controller = loader.getController();
            Stage stage = new Stage();
            controller.setJoueur(joueurTableView.getSelectionModel().getSelectedItem());

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            //return controller.isOkClicked();

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            //return false;
        }
    }

}
