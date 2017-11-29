/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ResponsableDao;
import entities.Responsable;
import idao.interfaceDao;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Bouchriha
 */
public class ResponsableFormController {
    
    @FXML
    TextField nomResponsableField, prenomResponsableField, loginResponsableField;
    @FXML
    PasswordField mdpResponsableField;
    
    private ResponsableMain main;
    private Stage stage;
    private Responsable responsable;
    //private boolean okClicked = false;
    ResponsableDao dao = new ResponsableDao();
    
    public void setMain(ResponsableMain main, Stage stage, Responsable responsable){
        this.main = main;
        this.stage = stage;
        this.responsable = responsable;
        if (responsable != null) {
            fillResponsableDetails();
        }
    }
    
    public void fillResponsableDetails() {
        nomResponsableField.setText(responsable.getNom());
        prenomResponsableField.setText(responsable.getPrenom());
        loginResponsableField.setText(responsable.getEmail());
        mdpResponsableField.setText(responsable.getPassword());
    }
    
    @FXML
    public void handleOk(){
        if (responsable != null) {
            responsable.setNom(nomResponsableField.getText());
            responsable.setPrenom(prenomResponsableField.getText());
            responsable.setEmail(loginResponsableField.getText());
            responsable.setPassword(mdpResponsableField.getText());
            dao.update(responsable);
            //okClicked = true;
        } else {
            Responsable newResponsable = new Responsable(
                    nomResponsableField.getText(),
                    prenomResponsableField.getText());
            
            dao.add(newResponsable);
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
