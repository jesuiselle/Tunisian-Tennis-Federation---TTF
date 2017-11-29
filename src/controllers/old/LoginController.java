/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.AdminDao;
import dao.ArbitreDao;
import dao.FanDao;
import dao.JoueurDao;
import dao.MedecinDao;
import dao.ResponsableDao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Bouchriha
 */
public class LoginController {
    
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    private AnchorPane anchor;
    
    AdminDao admindao = new AdminDao();
    ArbitreDao adao = new ArbitreDao();
    FanDao fdao = new FanDao();
    JoueurDao jdao = new JoueurDao();
    MedecinDao mdao = new MedecinDao();
    ResponsableDao rdao = new ResponsableDao();
    
    private LoginMain main;
    
    public void setMain(LoginMain main){
        this.main = main;
    }
    
    
    @FXML
    public void handleConnexion() throws IOException{
        if(admindao.login(loginField.getText(), passwordField.getText()) != null){
            navigateTo("/views/AdminMain.fxml");
        }
        else if(adao.login(loginField.getText(), passwordField.getText()) != null){
            navigateTo("/views/AccueilArbitre.fxml");
        }
        else if(fdao.login(loginField.getText(), passwordField.getText()) != null){
            navigateTo("/views/Accueil.fxml");
        }
        else if(jdao.login(loginField.getText(), passwordField.getText()) != null){
            navigateTo("/views/AccueilJoueur.fxml");
        }
        else if(mdao.login(loginField.getText(), passwordField.getText()) != null){
            navigateTo("/views/AccueilMedecin.fxml");
        }
        else if(rdao.login(loginField.getText(), passwordField.getText()) != null){
            navigateTo("/views/AcceuilResponsableAntidopage.fxml");
        }
        else{
            navigateTo("/views/Connexion.fxml");
        }
    }
    public  void navigateTo(String view) throws IOException{
        Node root =(Node) FXMLLoader.load(getClass().getResource(view));
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
    }
    
}
