/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.JoueurDao;
import dao.MatchDao;
import entities.Joueur;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 *
 * @author Bouchriha
 */
public class HistoriqueJoueurController {

    @FXML
    Label nomJoueurLabel, prenomJoueurLabel, dateNaissanceJoueurLabel, tailleJoueurLabel,
            poidsJoueurLabel, categorieJoueurLabel, clubJoueurLabel, groupeAgeJoueurLabel;
    @FXML
    AnchorPane participationPane, invitationPane;
    @FXML
    ImageView joueurImageView;
    //@FXML
    //PieChart piechart;

    MatchDao pdao = new MatchDao();

    Joueur joueur;
    JoueurDao dao = new JoueurDao();
    ObservableList<Joueur> list = FXCollections.observableArrayList();

    public void initialize() {

        showDetails(joueur);
        int total = pdao.findAll().size();
        int par = 0;//pdao.findByIdJoueur(1).size();
        int gagne = 0;//(int) pdao.findByIdJoueur(1).stream().filter(e -> e.getGagnant() == true).count();

        ObservableList<PieChart.Data> list
                = FXCollections.observableArrayList(
                        new PieChart.Data("Reste", total - par),
                        new PieChart.Data("Gagné", gagne),
                        new PieChart.Data("Perdu", par - gagne)
                );
        PieChart pieChart = new PieChart(list);
        participationPane.getChildren().add(pieChart);

    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void showDetails(Joueur joueur) {
        nomJoueurLabel.setText(joueur.getNom());
        prenomJoueurLabel.setText(joueur.getPrenom());
        dateNaissanceJoueurLabel.setText(joueur.getDateNaissance());
        tailleJoueurLabel.setText(String.valueOf(joueur.getTaille()));
        poidsJoueurLabel.setText(String.valueOf(joueur.getPoids()));
        categorieJoueurLabel.setText(joueur.getCategorie());
        clubJoueurLabel.setText(joueur.getClub().getNom());
        groupeAgeJoueurLabel.setText(joueur.getGroupeAge());
        joueurImageView.setImage(new Image(joueur.getImage()));

    }

}
