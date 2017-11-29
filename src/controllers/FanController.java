/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FanDao;
import entities.Fan;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 
 */
public class FanController implements Initializable {

    @FXML
    TableView<Fan> fanTableView;
    @FXML
    TableColumn<Fan, Integer> idFanColumn;
    @FXML
    TableColumn<Fan, String> nomFanColumn;
    @FXML
    TableColumn<Fan, String> prenomFanColumn;
    @FXML
    TableColumn<Fan, String> usernameFanColumn;
    @FXML
    TableColumn<Fan, String> emailFanColumn;

    ObservableList<String> cat = FXCollections.observableArrayList();

    FanDao dao = new FanDao();
    ObservableList<Fan> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idFanColumn.setCellValueFactory(new PropertyValueFactory<Fan, Integer>("id"));
        nomFanColumn.setCellValueFactory(new PropertyValueFactory<Fan, String>("nom"));
        prenomFanColumn.setCellValueFactory(new PropertyValueFactory<Fan, String>("prenom"));
        usernameFanColumn.setCellValueFactory(new PropertyValueFactory<Fan, String>("categorie"));
        emailFanColumn.setCellValueFactory(new PropertyValueFactory<Fan, String>("dateNaissance"));
        list.addAll(dao.findAll());
        fanTableView.setItems(list);
    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

}
