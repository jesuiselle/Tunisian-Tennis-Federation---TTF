/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.InvitationDao;
import dao.JoueurDao;
import entities.Invitation;
import entities.Joueur;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class MedecinWindowIJTAController implements Initializable {

    @FXML
    TableView<Invitation> invitationTATableView;
    @FXML
    TableColumn<Invitation, String> joueurColumn;
    @FXML
    TableColumn<Invitation, String> dateinvitationColumn;
    @FXML
    DatePicker dateinv;

    JoueurDao jdao = new JoueurDao();
    Invitation inv;

    ObservableList<Joueur> jCB = FXCollections.observableArrayList();

    InvitationDao dao = new InvitationDao();
    ObservableList<Invitation> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        joueurColumn.setCellValueFactory(new PropertyValueFactory<>("joueur"));
        dateinvitationColumn.setCellValueFactory(new PropertyValueFactory<>("dateInvitation"));
        list.addAll(dao.findAllInvitation());
        invitationTATableView.setItems(list);
    }

    @FXML
    public void inviter() {
        inv = new Invitation();
        inv.setMedecin(Utils.medecin);
        List<Joueur> l = jdao.findAll();
        Joueur j = l.get((int) (Math.random() * l.size()));
        inv.setJoueur(j);
        inv.setDateInvitation(dateinv.getValue()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        dao.add(inv);
        inv = null;
        dateinv.setValue(null);
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

}
