/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.FanDao;
import entities.Fan;
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
public class GestionFanController {

    @FXML
    TableView<Fan> fanTableView;
    @FXML
    TableColumn<Fan, String> nomFanColumn;
    @FXML
    TableColumn<Fan, String> prenomFanColumn;
    @FXML
    Label nomFanLabel, prenomFanLabel, loginFanLabel;
    FanDao dao = new FanDao();
    ObservableList<Fan> list = FXCollections.observableArrayList();

    private FanMain main;

    public void initialize() {
        nomFanColumn.setCellValueFactory(new PropertyValueFactory<Fan, String>("nom"));
        prenomFanColumn.setCellValueFactory(new PropertyValueFactory<Fan, String>("prenom"));

        fanTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void setMain(FanMain main) {
        this.main = main;

        list.addAll(dao.findAll());
        fanTableView.setItems(list);
    }

    public void showDetails(Fan fan) {
        nomFanLabel.setText(fan.getNom());
        prenomFanLabel.setText(fan.getPrenom());
        loginFanLabel.setText(fan.getEmail());

    }

    @FXML
    public void handleAjout() {
        main.newFanWindow(null);
    }

    @FXML
    public void handleModifier() {
        Fan fan = fanTableView.getSelectionModel().getSelectedItem();
        main.newFanWindow(fan);
        //System.out.println(fan);
        
    }

    @FXML
    public void handleSupprimer() {
        Fan fan = fanTableView.getSelectionModel().getSelectedItem();
        dao.removeById(fan.getId());
        fanTableView.setItems(null);
        fanTableView.layout();
        list.removeAll(list);
        list.addAll(dao.findAll());
        fanTableView.setItems(list);
    }

}
