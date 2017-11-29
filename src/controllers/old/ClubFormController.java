/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ClubDao;
import entities.Club;
import idao.interfaceDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author hichem
 */
public class ClubFormController {

    @FXML
    TextField nomClubTextField, adresseClubTextField;

    @FXML
    Label logoClubLabel;
    @FXML
    DatePicker dateClubPicker;
    @FXML
    ImageView myImageView;
    private mainClub main;
    private Stage stage;
    private Club club;

    interfaceDao dao = new ClubDao();

    public void setmain(mainClub main, Stage stage, Club club) {

        this.main = main;
        this.stage = stage;
        this.club = club;
        if (club != null) {
            fillClubDetails();
        }
    }

    public void fillClubDetails() {
        nomClubTextField.setText(club.getNom());
        adresseClubTextField.setText(club.getAdresse());
        dateClubPicker.getEditor().setText(club.getDateFondation());

    }

    @FXML
    public void handleOk() {
        if (club != null) {
            club.setNom(nomClubTextField.getText());
            club.setAdresse(adresseClubTextField.getText());
            club.setDateFondation(dateClubPicker.getEditor().getText());
            //travail sur un image null

            club.setLogo(logoClubLabel.getText());
            dao.update(club);
            //okClicked = true;
        } else {
            Club newClub = new Club(
                    nomClubTextField.getText(),
                    adresseClubTextField.getText(),
                    dateClubPicker.getEditor().getText(),
                    logoClubLabel.getText());

            dao.add(newClub);
        }
        stage.close();
    }

    @FXML
    public void handleCancel() {
        stage.close();
    }

    @FXML

    public void handle(ActionEvent t) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = fileChooser.showOpenDialog(null);

        String path = file.getAbsolutePath();
        logoClubLabel.setText(path);

        try {
            InputStream inputStream = new FileInputStream(path);
            Image image = new Image(inputStream);
            myImageView.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClubFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
