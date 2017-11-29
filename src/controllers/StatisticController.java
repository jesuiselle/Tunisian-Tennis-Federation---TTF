/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.JoueurDao;
import dao.MatchDao;
import dao.StatisticDao;
import entities.Joueur;
import entities.Partie;
import entities.Statistic;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class StatisticController implements Initializable {

    @FXML
    TableView<Statistic> statisticTableView;
    @FXML
    TableColumn<Statistic, Integer> idStatisticColumn;
    @FXML
    TableColumn<Statistic, Integer> idMatchStatisticColumn;
    @FXML
    TableColumn<Statistic, Integer> joueurStatisticColumn;
    @FXML
    TableColumn<Statistic, Integer> acesStatisticColumn;
    @FXML
    TableColumn<Statistic, Integer> serviceStatisticColumn;
    @FXML
    TableColumn<Statistic, Double> avgStatisticColumn;

    @FXML
    TextField acesField, serviceGameField, averageField, serviceWinnerField, doubleFaultsField, totalField, totalwonField, fastestField;
    @FXML
    ChoiceBox<Partie> idMatchCB;
    @FXML
    ChoiceBox<Joueur> joueurCB;

    Statistic statistic;
    StatisticDao dao = new StatisticDao();
    ObservableList<Statistic> list = FXCollections.observableArrayList();

    ObservableList<Joueur> jou = FXCollections.observableArrayList();
    ObservableList<Partie> par = FXCollections.observableArrayList();

    JoueurDao jdao = new JoueurDao();
    MatchDao mdao = new MatchDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jou.addAll(jdao.findAll());
        joueurCB.setItems(jou);
        par.addAll(mdao.findAll());
        idMatchCB.setItems(par);
        idStatisticColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idMatchStatisticColumn.setCellValueFactory(new PropertyValueFactory<>("partie"));
        joueurStatisticColumn.setCellValueFactory(new PropertyValueFactory<>("joueur"));
        acesStatisticColumn.setCellValueFactory(new PropertyValueFactory<>("aces"));
        serviceStatisticColumn.setCellValueFactory(new PropertyValueFactory<>("serviceGame"));
        avgStatisticColumn.setCellValueFactory(new PropertyValueFactory<>("averageServeSpeed"));
        list.addAll(dao.findAll());
        statisticTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (statistic != null) {
                statistic.setAces(Integer.parseInt(acesField.getText()));
                statistic.setServiceGame(Integer.parseInt(serviceGameField.getText()));
                statistic.setAverageServeSpeed(Double.parseDouble(averageField.getText()));
                statistic.setServiceWinners(Integer.parseInt(serviceWinnerField.getText()));
                statistic.setDoubleFaults(Integer.parseInt(doubleFaultsField.getText()));

                statistic.setTotalPoints(Integer.parseInt(totalField.getText()));
                statistic.setTotalPointsWon(Integer.parseInt(totalwonField.getText()));
                statistic.setFastestServeSpeed(Double.parseDouble(fastestField.getText()));
                statistic.setPartie(idMatchCB.getSelectionModel().getSelectedItem());
                statistic.setJoueur(joueurCB.getSelectionModel().getSelectedItem());
                dao.update(statistic);
            } else {
                statistic = new Statistic();
                statistic.setAces(Integer.parseInt(acesField.getText()));
                statistic.setServiceGame(Integer.parseInt(serviceGameField.getText()));
                statistic.setAverageServeSpeed(Double.parseDouble(averageField.getText()));
                statistic.setServiceWinners(Integer.parseInt(serviceWinnerField.getText()));
                statistic.setDoubleFaults(Integer.parseInt(doubleFaultsField.getText()));

                statistic.setTotalPoints(Integer.parseInt(totalField.getText()));
                statistic.setTotalPointsWon(Integer.parseInt(totalwonField.getText()));
                statistic.setFastestServeSpeed(Double.parseDouble(fastestField.getText()));
                statistic.setPartie(idMatchCB.getSelectionModel().getSelectedItem());
                statistic.setJoueur(joueurCB.getSelectionModel().getSelectedItem());
                dao.add(statistic);
            }

            statistic = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void annuler() {
        clear();
        statistic = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        statistic = statisticTableView.getSelectionModel().getSelectedItem();
        if (statistic != null) {
            acesField.setText(statistic.getAces() + "");
            serviceGameField.setText(statistic.getServiceGame() + "");
            averageField.setText(statistic.getAverageServeSpeed() + "");
            serviceWinnerField.setText(statistic.getServiceWinners() + "");
            doubleFaultsField.setText(statistic.getDoubleFaults() + "");
            totalField.setText(statistic.getTotalPoints() + "");
            totalwonField.setText(statistic.getTotalPointsWon() + "");
            fastestField.setText(statistic.getFastestServeSpeed() + "");
            joueurCB.setValue(statistic.getJoueur());
            idMatchCB.setValue(statistic.getPartie());
        }

    }

    @FXML
    public void supprimer() {
        statistic = statisticTableView.getSelectionModel().getSelectedItem();
        if (statistic != null) {
            dao.removeById(statistic.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void afficher() {
        statistic = statisticTableView.getSelectionModel().getSelectedItem();
        if (statistic != null) {
            Stage myDialog = staticDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            statistic = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        acesField.clear();
        serviceGameField.clear();
        averageField.clear();
        serviceWinnerField.clear();
        doubleFaultsField.clear();
        totalField.clear();
        totalwonField.clear();
        fastestField.clear();
        joueurCB.setValue(null);
        idMatchCB.setValue(null);

    }

    public boolean allFieldsFilled() {
        return (!acesField.getText().isEmpty() && !fastestField.getText().isEmpty()
                && !serviceGameField.getText().isEmpty() && !averageField.getText().isEmpty()
                && !serviceWinnerField.getText().isEmpty() && !doubleFaultsField.getText().isEmpty()
                && !totalField.getText().isEmpty() && !totalwonField.getText().isEmpty()
                && joueurCB.getValue() != null && idMatchCB.getValue() != null);
    }

    public Stage staticDetails(Stage owner) {
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

        Label descLbl = new Label("Genre match : ");
        descLbl.setStyle( "-fx-font-size: 10pt;");
        descLbl.setTextFill(Color.web("#293539"));
        gridpane.add(descLbl, 0, 2);
        
        Label genLbl = new Label(statistic.getPartie().getGenre());
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(genLbl, 1, 2);
        
        Label jLbl = new Label("Joueur : ");
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(jLbl, 0, 3);
        
        Label jdLbl = new Label(statistic.getJoueur().getNom()+" "+statistic.getJoueur().getPrenom());
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(jdLbl, 1, 3);
        
        Label scLbl = new Label("Service game: ");
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(scLbl, 0, 4);
        Label sccLbl = new Label(Integer.toString(statistic.getServiceGame()));
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(sccLbl, 1, 4);
        
        Label acLbl = new Label("Aces: ");
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(acLbl, 0, 5);
        Label accLbl = new Label(Double.toString(statistic.getAces()));
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(accLbl, 1, 5);
        
        Label d = new Label("Service Winners: ");
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(d, 0, 6);
        Label r = new Label(Double.toString(statistic.getServiceWinners()));
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(r, 1, 6);
        
        Label t = new Label("Total points: ");
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(t, 0, 7);
        Label k = new Label(Integer.toString(statistic.getTotalPoints()));
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(k, 1, 7);
        
        Label won = new Label("Total points won: ");
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(won, 0, 8);
        Label wonsp = new Label(Integer.toString(statistic.getTotalPointsWon()));
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(wonsp, 1, 8);
        
        Label ww = new Label("Average Serve speed: ");
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(ww, 0, 9);
        Label averag = new Label(Double.toString(statistic.getAverageServeSpeed()));
        genLbl.setStyle( "-fx-font-size: 10pt;");
        genLbl.setTextFill(Color.web("#293539"));
        gridpane.add(averag, 1, 9);

                      
                               
                                       
        
        root.getChildren().add(gridpane);
        return s;
    }

}
