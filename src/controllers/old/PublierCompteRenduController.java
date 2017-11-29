/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.FanDao;
import entities.Fan;
import idao.interfaceDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Bouchriha
 */
public class PublierCompteRenduController {
    
    @FXML
    TextField nomFanField, prenomFanField, loginFanField, passwordFanField;
    
    private FanMain main;
    private Stage stage;
    private Fan fan;
    //private boolean okClicked = false;
    FanDao dao = new FanDao();
    
    public void setMain(FanMain main, Stage stage, Fan fan){
        this.main = main;
        this.stage = stage;
        this.fan = fan;
        if (fan != null) {
            fillFanDetails();
        }
    }
    
    public void fillFanDetails() {
        nomFanField.setText(fan.getNom());
        prenomFanField.setText(fan.getPrenom());
        loginFanField.setText(fan.getEmail());
        passwordFanField.setText(fan.getPassword());
    }
    
    @FXML
    public void handleOk(){
        if (fan != null) {
            fan.setNom(nomFanField.getText());
            fan.setPrenom(prenomFanField.getText());
            fan.setEmail(loginFanField.getText());
            fan.setPassword(passwordFanField.getText());
            dao.update(fan);
            //okClicked = true;
        } else {
            Fan newFan = new Fan(
                    nomFanField.getText(),
                    prenomFanField.getText());
            
            dao.add(newFan);
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
