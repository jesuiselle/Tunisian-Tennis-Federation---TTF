/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ArbitreDao;
import dao.EvenementDao;
import dao.JoueurDao;
import dao.MatchDao;
import dao.StadeDao;
import entities.Arbitre;
import entities.Evenement;
import entities.Joueur;
import entities.Partie;
import entities.Stade;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author 
 */
public class MatchController implements Initializable {

    @FXML
    TableView<Partie> matchTableView;
    @FXML
    TableColumn<Partie, Integer> idMatchColumn;
    @FXML
    TableColumn<Partie, String> descriptionMatchColumn;
    @FXML
    TableColumn<Partie, String> typeMatchColumn;
    @FXML
    TableColumn<Partie, String> dateMatchColumn;
    @FXML
    TableColumn<Partie, String> scoreMatchColumn;
    @FXML
    TableColumn<Partie, String> stadeMatchColumn;

    @FXML
    TextField lienMatchField;
    @FXML
    DatePicker dateMatchDP;
    @FXML
    ChoiceBox<String> descriptionMatchCB, genreMatchCB, typeMatchCB, scoreMatchCB, setUnMatchCB,
            setDeuxMatchCB, setTroisMatchCB, setQuatreMatchCB, setCinqMatchCB;
    @FXML
    ChoiceBox<Stade> stadeMatchCB;
    @FXML
    ChoiceBox<Arbitre> arbitreMatchCB;
    @FXML
    ChoiceBox<Evenement> tournoiMatchCB;
    @FXML
    ChoiceBox<Joueur> joueurUnMatchCB, joueurDeuxMatchCB;

    ObservableList<String> des = FXCollections.observableArrayList();
    ObservableList<String> genre = FXCollections.observableArrayList();
    ObservableList<String> type = FXCollections.observableArrayList();
    ObservableList<String> score = FXCollections.observableArrayList();
    ObservableList<String> set = FXCollections.observableArrayList();
    ObservableList<Stade> stade = FXCollections.observableArrayList();
    ObservableList<Arbitre> arb = FXCollections.observableArrayList();
    ObservableList<Evenement> evt = FXCollections.observableArrayList();
    ObservableList<Joueur> jou = FXCollections.observableArrayList();

    Partie match;
    MatchDao dao = new MatchDao();
    JoueurDao jdao = new JoueurDao();
    ArbitreDao adao = new ArbitreDao();
    EvenementDao edao = new EvenementDao();
    StadeDao sdao = new StadeDao();
    ObservableList<Partie> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        des.addAll("1/32 Final", "1/16 Final", "1/8 Final", "1/4 Final", "1/2 Final", "Final");
        descriptionMatchCB.setItems(des);
        genre.addAll("Single", "Double");
        genreMatchCB.setItems(genre);
        type.addAll("Men", "Women");
        typeMatchCB.setItems(type);
        score.addAll("*-*", "3-0", "3-1", "3-2", "0-3", "1-3", "2-3");
        scoreMatchCB.setItems(score);
        set.addAll("*-*", "6-0", "6-1", "6-2", "6-3", "6-4", "7-5", "7-6",
                "0-6", "1-6", "2-6", "3-6", "4-6", "5-7", "6-7");
        setUnMatchCB.setItems(set);
        setDeuxMatchCB.setItems(set);
        setTroisMatchCB.setItems(set);
        setQuatreMatchCB.setItems(set);
        setCinqMatchCB.setItems(set);
        stade.addAll(sdao.findAll());
        stadeMatchCB.setItems(stade);
        arb.addAll(adao.findAll());
        arbitreMatchCB.setItems(arb);
        evt.addAll(edao.findAll());
        tournoiMatchCB.setItems(evt);
        jou.addAll(jdao.findAll());
        joueurUnMatchCB.setItems(jou);
        joueurDeuxMatchCB.setItems(jou);
        idMatchColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionMatchColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeMatchColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateMatchColumn.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
        scoreMatchColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        stadeMatchColumn.setCellValueFactory(new PropertyValueFactory<>("stade"));
        list.addAll(dao.findAll());
        matchTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (match != null) {
                match.setLien(lienMatchField.getText());
                match.setDateMatch(dateMatchDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                match.setDescription(descriptionMatchCB.getValue());
                match.setGenre(genreMatchCB.getValue());
                match.setType(typeMatchCB.getValue());
                match.setScore(scoreMatchCB.getValue());
                match.setSetUn(setUnMatchCB.getValue());
                match.setSetDeux(setDeuxMatchCB.getValue());
                match.setSetTrois(setTroisMatchCB.getValue());
                match.setSetQuatre(setQuatreMatchCB.getValue());
                match.setSetCinq(setCinqMatchCB.getValue());
                match.setStade(stadeMatchCB.getValue());
                match.setArbitre(arbitreMatchCB.getValue());
                match.setEvenement(tournoiMatchCB.getValue());
                match.setJoueur(joueurUnMatchCB.getValue());
                match.setJoueur1(joueurDeuxMatchCB.getValue());
                dao.update(match);

            } else {
                match = new Partie();
                match.setLien(lienMatchField.getText());
                match.setDateMatch(dateMatchDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                match.setDescription(descriptionMatchCB.getValue());
                match.setGenre(genreMatchCB.getValue());
                match.setType(typeMatchCB.getValue());
                match.setScore(scoreMatchCB.getValue());
                match.setSetUn(setUnMatchCB.getValue());
                match.setSetDeux(setDeuxMatchCB.getValue());
                match.setSetTrois(setTroisMatchCB.getValue());
                match.setSetQuatre(setQuatreMatchCB.getValue());
                match.setSetCinq(setCinqMatchCB.getValue());
                match.setStade(stadeMatchCB.getValue());
                match.setArbitre(arbitreMatchCB.getValue());
                match.setEvenement(tournoiMatchCB.getValue());
                match.setJoueur(joueurUnMatchCB.getValue());
                match.setJoueur1(joueurDeuxMatchCB.getValue());
                dao.add(match);

            }
            match = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        match = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        match = matchTableView.getSelectionModel().getSelectedItem();
        if (match != null) {
            lienMatchField.setText(match.getLien());
            dateMatchDP.setValue(LocalDate.parse(match.getDateMatch(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            descriptionMatchCB.setValue(match.getDescription());
            genreMatchCB.setValue(match.getGenre());
            typeMatchCB.setValue(match.getType());
            scoreMatchCB.setValue(match.getScore());
            setUnMatchCB.setValue(match.getSetUn());
            setDeuxMatchCB.setValue(match.getSetDeux());
            setTroisMatchCB.setValue(match.getSetTrois());
            setQuatreMatchCB.setValue(match.getSetQuatre());
            setCinqMatchCB.setValue(match.getSetCinq());
            stadeMatchCB.setValue(match.getStade());
            arbitreMatchCB.setValue(match.getArbitre());
            tournoiMatchCB.setValue(match.getEvenement());
            joueurUnMatchCB.setValue(match.getJoueur());
            joueurDeuxMatchCB.setValue(match.getJoueur1());
            
        }

    }

    @FXML
    public void supprimer() {
        match = matchTableView.getSelectionModel().getSelectedItem();
        if (match != null) {
            dao.removeById(match.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void afficher() {
       match = matchTableView.getSelectionModel().getSelectedItem();
        if (match != null) {
            Stage myDialog = matchDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            match = null;
        }
        
    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        lienMatchField.clear();
        dateMatchDP.setValue(null);
        descriptionMatchCB.setValue(null);
        genreMatchCB.setValue(null);
        typeMatchCB.setValue(null);
        scoreMatchCB.setValue(null);
        setUnMatchCB.setValue(null);
        setDeuxMatchCB.setValue(null);
        setTroisMatchCB.setValue(null);
        setQuatreMatchCB.setValue(null);
        setCinqMatchCB.setValue(null);
        stadeMatchCB.setValue(null);
        arbitreMatchCB.setValue(null);
        tournoiMatchCB.setValue(null);
        joueurUnMatchCB.setValue(null);
        joueurDeuxMatchCB.setValue(null);

    }

    public boolean allFieldsFilled() {
        return (!lienMatchField.getText().isEmpty() && dateMatchDP.getValue() != null
                && descriptionMatchCB.getValue() != null && genreMatchCB.getValue() != null
                && typeMatchCB.getValue() != null && scoreMatchCB.getValue() != null
                && setUnMatchCB.getValue() != null && setDeuxMatchCB.getValue() != null
                && stadeMatchCB.getValue() != null
                && arbitreMatchCB.getValue() != null && tournoiMatchCB.getValue() != null
                && joueurUnMatchCB.getValue() != null && joueurDeuxMatchCB.getValue() != null);
    }

    public Stage matchDetails(Stage owner) {
        Stage s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        s.initOwner(owner);
        s.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root,Color.rgb(1,205,230,0.3));
        s.setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setVgap(5);

        Button login = new Button("Fermer");
        login.setStyle( "-fx-font-size: 10pt;");
        login.setTextFill(Color.web("#fe3554"));
        login.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                s.close();
            }
        });
        login.setBackground(Background.EMPTY);
        gridpane.add(login, 1, 1);
        GridPane.setHalignment(login, HPos.RIGHT);
        
        
        

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        String lien = match.getLien().substring(0, 4) + match.getLien().substring(5, 23) 
                + "/embed/" + match.getLien().substring(32);
            //System.out.println(lien);
        webEngine.load(lien);
        gridpane.add(browser, 0, 3);
        root.getChildren().add(gridpane);
        return s;
    }

}
