/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CompteRenduTestDao;
import dao.JoueurDao;
import entities.CompteRenduTest;
import entities.Joueur;
import entities.Medecin;
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
public class MedecinWindowCRTAController implements Initializable {

    @FXML
    TableView<CompteRenduTest> medecinCRTableView;
    @FXML
    TableColumn<CompteRenduTest, String> joueurColumn;
    @FXML
    TableColumn<CompteRenduTest, String> descriptionColumn;
    @FXML
    TableColumn<CompteRenduTest, String> resultatColumn;
    @FXML
    TableColumn<CompteRenduTest, String> etatColumn;
    @FXML
    ChoiceBox<Joueur> joueurCB;
    @FXML
    ChoiceBox<String> resultatCB;
    @FXML
    TextArea descriptionTA;

    boolean b;
    JoueurDao jdao = new JoueurDao();
    CompteRenduTest testcr;
    ObservableList<String> resultat = FXCollections.observableArrayList();
    ObservableList<Joueur> jCB = FXCollections.observableArrayList();

    CompteRenduTestDao dao = new CompteRenduTestDao();
    ObservableList<CompteRenduTest> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jCB.addAll(jdao.findAll());
        joueurCB.setItems(jCB);
        resultat.add("True");
        resultat.add("False");
        resultatCB.setItems(resultat);
        joueurColumn.setCellValueFactory(new PropertyValueFactory<>("joueur"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        resultatColumn.setCellValueFactory(new PropertyValueFactory<>("resultat"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        list.addAll(dao.findAllMedecinCRTA());
        medecinCRTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            testcr = new CompteRenduTest();
            testcr.setDescription(descriptionTA.getText());
            //testcr.setResultat(resultatCB.getValue());
            testcr.setJoueur(joueurCB.getValue());
            b = resultatCB.getSelectionModel().getSelectedItem().equals("True");
            testcr.setResultat(b);
            testcr.setMedecin(Utils.medecin);
            dao.add(testcr);
            testcr = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        testcr = null;
    }

    public void clear() {
        descriptionTA.clear();
        joueurCB.setValue(null);
        resultatCB.setValue(null);

    }

    public boolean allFieldsFilled() {
        return (joueurCB.getValue() != null && !descriptionTA.getText().isEmpty()
                && resultatCB.getValue() != null);
    }
}
