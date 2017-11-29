/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ArbitreDao;
import entities.Arbitre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Bouchriha
 */
public class HistoriqueStadeController {

    @FXML
    TableView<Arbitre> arbitreTableView;
    @FXML
    TableColumn<Arbitre, String> nomArbitreColumn;
    @FXML
    TableColumn<Arbitre, String> prenomArbitreColumn;
    @FXML
    Label nomArbitreLabel, prenomArbitreLabel, loginArbitreLabel, categorieArbitreLabel, dateArbitreLabel;
    ArbitreDao dao = new ArbitreDao();
    ObservableList<Arbitre> list = FXCollections.observableArrayList();

    private Tft main;

    public void initialize() {
        nomArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("nom"));
        prenomArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("prenom"));

        arbitreTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void setMain(Tft main) {
        this.main = main;

        list.addAll(dao.findAll());
        arbitreTableView.setItems(list);
    }

    public void showDetails(Arbitre arbitre) {
        nomArbitreLabel.setText(arbitre.getNom());
        prenomArbitreLabel.setText(arbitre.getPrenom());
        loginArbitreLabel.setText(arbitre.getEmail());
        categorieArbitreLabel.setText(arbitre.getCategorie());
        dateArbitreLabel.setText(arbitre.getDateNaissance());

    }

    @FXML
    public void handleAjout() {
        main.newArbitreWindow(null);
    }

    @FXML
    public void handleModifier() {
        Arbitre arbitre = arbitreTableView.getSelectionModel().getSelectedItem();
        main.newArbitreWindow(arbitre);
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

}
