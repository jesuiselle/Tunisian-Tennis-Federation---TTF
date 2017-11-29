/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ResponsableDao;
import entities.Responsable;
import idao.interfaceDao;
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
public class GestionResponsableController {

    @FXML
    TableView<Responsable> responsableTableView;
    @FXML
    TableColumn<Responsable, String> nomResponsableColumn;
    @FXML
    TableColumn<Responsable, String> prenomResponsableColumn;
    @FXML
    Label nomResponsableLabel, prenomResponsableLabel, loginResponsableLabel;
    ResponsableDao dao = new ResponsableDao();
    ObservableList<Responsable> list = FXCollections.observableArrayList();

    private ResponsableMain main;

    public void initialize() {
        nomResponsableColumn.setCellValueFactory(new PropertyValueFactory<Responsable, String>("nom"));
        prenomResponsableColumn.setCellValueFactory(new PropertyValueFactory<Responsable, String>("prenom"));

        responsableTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void setMain(ResponsableMain main) {
        this.main = main;

        list.addAll(dao.findAll());
        responsableTableView.setItems(list);
    }

    public void showDetails(Responsable responsable) {
        nomResponsableLabel.setText(responsable.getNom());
        prenomResponsableLabel.setText(responsable.getPrenom());
        loginResponsableLabel.setText(responsable.getEmail());

    }

    @FXML
    public void handleAjout() {
        main.newResponsableWindow(null);
    }

    @FXML
    public void handleModifier() {
        Responsable responsable = responsableTableView.getSelectionModel().getSelectedItem();
        main.newResponsableWindow(responsable);
        //System.out.println(arbitre);
        /*if (okClicked) {
            arbitreTableView.setItems(null);
            arbitreTableView.layout();
            list.addAll(dao.findAll());
            arbitreTableView.setItems(list);
        }*/
    }

    @FXML
    public void handleSupprimer() {
        Responsable responsable = responsableTableView.getSelectionModel().getSelectedItem();
        dao.removeById(responsable.getId());
        responsableTableView.setItems(null);
        responsableTableView.layout();
        list.removeAll(list);
        list.addAll(dao.findAll());
        responsableTableView.setItems(list);
    }

}
