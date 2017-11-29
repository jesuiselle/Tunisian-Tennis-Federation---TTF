/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.MedecinDao;
import entities.Medecin;
import idao.iUserDao;
import idao.interfaceDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Aydi
 */
public class MedecinFormController {

    @FXML
    TextField NomMedecinfield, PrenomMedecinfield, LoginMedecinfield;
    @FXML
    PasswordField PasseMedecinfield;
    private MedecinMain main;
    private Stage stage;
    private Medecin medecin;
    iUserDao<Medecin> dao = new MedecinDao();

    public void setMain(MedecinMain main, Stage stage, Medecin medecin) {
        this.main = main;
        this.stage = stage;
        this.medecin = medecin;

        if (medecin != null) {
            fillMedecinDetails();
        }
    }

    public void fillMedecinDetails() {
        NomMedecinfield.setText(medecin.getNom());
        PrenomMedecinfield.setText(medecin.getPrenom());
        LoginMedecinfield.setText(medecin.getEmail());
        PasseMedecinfield.setText(medecin.getPassword());
    }

    @FXML
    public void handleOk() {
        if (medecin != null) {
            medecin.setNom(NomMedecinfield.getText());
            medecin.setPrenom(PrenomMedecinfield.getText());
            medecin.setEmail(LoginMedecinfield.getText());
            medecin.setPassword(PasseMedecinfield.getText());
            //System.out.println(arbitre);
            dao.update(medecin);
            //okClicked = true;
        } else {
            Medecin newMedecin = new Medecin(
                    NomMedecinfield.getText(),
                    PrenomMedecinfield.getText(),
                    null);

            dao.add(newMedecin);
        }
        stage.close();
    }

    @FXML
    public void handleCancel() {
        stage.close();
    }

}
