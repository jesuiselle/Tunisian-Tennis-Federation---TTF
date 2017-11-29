/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ArbitreDao;
import entities.Arbitre;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Bouchriha
 */
public class JoueurFormController {
    
    @FXML
    TextField nomArbitreField, prenomArbitreField, loginField, passwordField;
    @FXML
    ChoiceBox<String> categorieCB;
    @FXML
    DatePicker DateNaissanceDP;

    ObservableList<String> cat = FXCollections.observableArrayList();
    private Tft main;
    private Stage stage;
    private Arbitre arbitre;
    //private boolean okClicked = false;
    ArbitreDao dao = new ArbitreDao();
    
    public void setMain(Tft main, Stage stage, Arbitre arbitre){
        this.main = main;
        this.stage = stage;
        this.arbitre = arbitre;
        cat.add("Professionnel");
        cat.add("Amateur");
        categorieCB.setItems(cat);
        categorieCB.getSelectionModel().selectFirst();
        if (arbitre != null) {
            fillArbitreDetails();
        }
    }
    
    public void fillArbitreDetails() {
        nomArbitreField.setText(arbitre.getNom());
        prenomArbitreField.setText(arbitre.getPrenom());
        loginField.setText(arbitre.getEmail());
        passwordField.setText(arbitre.getPassword());
        categorieCB.getSelectionModel().select(arbitre.getCategorie());
        DateNaissanceDP.setValue(LocalDate.MAX);
    }
    
    @FXML
    public void handleOk(){
        if (arbitre != null) {
            arbitre.setNom(nomArbitreField.getText());
            arbitre.setPrenom(prenomArbitreField.getText());
            arbitre.setEmail(loginField.getText());
            arbitre.setPassword(passwordField.getText());
            arbitre.setCategorie(categorieCB.getSelectionModel().getSelectedItem());
            arbitre.setDateNaissance(DateNaissanceDP.getValue().toString());
            //System.out.println(arbitre);
            dao.update(arbitre);
            //okClicked = true;
        } else {
            Arbitre newArbitre = new Arbitre(
                    nomArbitreField.getText(),
                    prenomArbitreField.getText(),
                    categorieCB.getSelectionModel().getSelectedItem(),
                    DateNaissanceDP.getValue().toString(),
                    null);
            
            dao.add(newArbitre);
        }
        stage.close();
    }
    
    @FXML
    public void handleCancel(){
        stage.close();
    }
/*
    boolean isOkClicked() {
        return okClicked;
    }
    */
}
