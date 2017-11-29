/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CompteRenduMatchDao;
import dao.MatchDao;
import entities.CompteRenduMatch;
import entities.Partie;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author 
 */
public class ArbitreWindowCRMController implements Initializable {

    @FXML
    TableView<CompteRenduMatch> crmTableView;
    @FXML
    TableColumn<CompteRenduMatch, String> descriptionColumn;
    @FXML
    TableColumn<CompteRenduMatch, String> idMatchCRColumn;

    @FXML
    ChoiceBox<Partie> idmatchGroup;
    @FXML
    TextArea descriptionMatch;

    CompteRenduMatch matchcr;
    ObservableList<Partie> idmatchCR = FXCollections.observableArrayList();

    CompteRenduMatchDao dao = new CompteRenduMatchDao();
    MatchDao mdao = new MatchDao();

    ObservableList<CompteRenduMatch> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idmatchCR.addAll(mdao.findAll());
        idmatchGroup.setItems(idmatchCR);
        idMatchCRColumn.setCellValueFactory(new PropertyValueFactory<>("partie"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        list.addAll(dao.findAllByArbitre());
        crmTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {

            matchcr = new CompteRenduMatch();
            matchcr.setDescription(descriptionMatch.getText());
            matchcr.setPartie(idmatchGroup.getValue());
            matchcr.setArbitre(Utils.arbitre);
            dao.add(matchcr);

            matchcr = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        matchcr = null;
    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        descriptionMatch.clear();
        idmatchGroup.setValue(null);

    }

    public boolean allFieldsFilled() {
        return (idmatchGroup.getValue() != null && !descriptionMatch.getText().isEmpty());
    }

}
