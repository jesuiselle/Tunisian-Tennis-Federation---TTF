/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.InvitationDao;
import entities.Invitation;
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
public class JoueurWindowinvitationTestController implements Initializable {

    @FXML
    TableView<Invitation> invitationRTATableView;
    @FXML
    TableColumn<Invitation, String> medecinColumn;
    @FXML
    TableColumn<Invitation, String> dateinvitationColumn;

    InvitationDao dao = new InvitationDao();
    ObservableList<Invitation> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        medecinColumn.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        dateinvitationColumn.setCellValueFactory(new PropertyValueFactory<>("dateInvitation"));
        list.addAll(dao.findAllRecentInvitation());
        invitationRTATableView.setItems(list);
    }

}
