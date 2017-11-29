/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.JoueurDao;
import dao.MatchDao;
import entities.Joueur;
import entities.Partie;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class JoueursAcceuilController implements Initializable {

    @FXML
    ScrollPane contentPane;

    JoueurDao dao = new JoueurDao();
    ObservableList<Joueur> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Joueur> joueurs = dao.findAll();
        VBox vbox = new VBox();
        joueurs.stream().map((j) -> {
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(50));
            // gridpane.setHgap(5);
            // gridpane.setVgap(5);

            Label identLbl = new Label(j.getNom() + " " + j.getPrenom());
            identLbl.setStyle("-fx-font-size: 40pt;");
            identLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(identLbl, 0, 1);

            Image img = new Image("http://localhost/resources/images/joueurs/" + j.getImage());
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(1000);
            imgView.setFitHeight(600);
            gridpane.add(imgView, 0, 2);

            Label datLbl = new Label("Date de naissance:     ");
            datLbl.setStyle("-fx-font-size:10pt;");
            datLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(datLbl, 0, 3);

            Label datenaissanceLbl = new Label("                                " + j.getDateNaissance());
            datenaissanceLbl.setStyle("-fx-font-size:10pt;");
            datenaissanceLbl.setTextFill(Color.web("#000000"));
            gridpane.add(datenaissanceLbl, 0, 3);

            Label tLbl = new Label("Taille:     ");
            tLbl.setStyle("-fx-font-size:10pt;");
            tLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(tLbl, 0, 4);

            Label tailleLbl = new Label("          " + j.getTaille());
            tailleLbl.setStyle("-fx-font-size:10pt;");
            tailleLbl.setTextFill(Color.web("#000000"));
            gridpane.add(tailleLbl, 0, 4);

            Label pLbl = new Label("Poids:     ");
            pLbl.setStyle("-fx-font-size:10pt;");
            pLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(pLbl, 0, 5);

            Label poidLbl = new Label("            " + Double.toString(j.getPoids()));
            poidLbl.setStyle("-fx-font-size:10pt;");
            poidLbl.setTextFill(Color.web("#000000"));
            gridpane.add(poidLbl, 0, 5);

            Label categorieLbl = new Label("Catégorie:     ");
            categorieLbl.setStyle("-fx-font-size:10pt;");
            categorieLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(categorieLbl, 0, 6);

            Label clubLbl = new Label("                   " + j.getCategorie());
            clubLbl.setStyle("-fx-font-size:10pt;");
            clubLbl.setTextFill(Color.web("#000000"));
            gridpane.add(clubLbl, 0, 6);

            Label groupeageLbl = new Label("Age groupe: ");
            groupeageLbl.setStyle("-fx-font-size:10pt;");
            groupeageLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(groupeageLbl, 0, 7);

            Label gageLbl = new Label("                      " + j.getGroupeAge());
            gageLbl.setStyle("-fx-font-size:10pt;");
            gageLbl.setTextFill(Color.web("#000000"));
            gridpane.add(gageLbl, 0, 7);

            Label sLbl = new Label("Score: ");
            sLbl.setStyle("-fx-font-size:10pt;");
            sLbl.setTextFill(Color.web("#0076a3"));
            gridpane.add(sLbl, 0, 8);

            Label scoreLbl = new Label("           " + Double.toString(j.getScore()));
            scoreLbl.setStyle("-fx-font-size:10pt;");
            scoreLbl.setTextFill(Color.web("#000000"));
            gridpane.add(scoreLbl, 0, 8);

            Button stat = new Button("cliquez içi pour voir statistique");
            stat.setOnAction((ActionEvent e) -> {
                Stage myDialog = showStat(Main.getPrimaryStage(), j.getId());
                myDialog.sizeToScene();
                myDialog.show();
            });
            gridpane.add(stat, 0, 9);

            return gridpane;
        }).forEach((gridpane) -> {
            vbox.getChildren().add(gridpane);
        });
        contentPane.setContent(vbox);

    }

    public Stage showStat(Stage owner, int id) {
        Stage s = new Stage();
        s.initOwner(owner);
        s.setTitle("Statistique");
        s.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root, 500, 600, Color.WHITESMOKE);
        s.setScene(scene);

        VBox box = new VBox();
        box.setPadding(new Insets(5));

        PieChart piechart = new PieChart();

        MatchDao pdao = new MatchDao();
        int nbWin = 0;
        nbWin = pdao.findAll().stream()
                .filter((p) -> (isWinner(p, id)))
                .map((_item) -> 1).reduce(nbWin, Integer::sum);
        int total = pdao.findByIdJoueur(id).size();
        ObservableList<PieChart.Data> list1
                = FXCollections.observableArrayList(
                        new PieChart.Data("Loses", total - nbWin),
                        new PieChart.Data("Wins", nbWin)
                );
        piechart.setData(list1);

        Button login = new Button("Fermer");

        login.setOnAction(
                (ActionEvent event) -> {
                    s.close();
                }
        );
        Label winLbl = new Label("Wins : " + nbWin);
        Label loseLbl = new Label("Loses : " + (total - nbWin));
        box.getChildren()
                .addAll(piechart, winLbl, loseLbl, login);
        root.getChildren()
                .add(box);
        return s;
    }
    
    public boolean isWinner(Partie p , int id){
        return (!p.getScore().equals("*-*")) && 
                ((p.getJoueur().getId() == id && isFirstWInner(p.getScore())) ||
                (p.getJoueur1().getId() == id && !isFirstWInner(p.getScore())));
    }
    public boolean isFirstWInner(String str){
        int nb1 = Integer.parseInt(str.substring(0,1));
        int nb2 = Integer.parseInt(str.substring(2));
        return nb1 > nb2;
    }
}
