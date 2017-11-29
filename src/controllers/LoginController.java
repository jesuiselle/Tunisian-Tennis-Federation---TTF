/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AdminDao;
import dao.ArbitreDao;
import dao.FanDao;
import dao.JoueurDao;
import dao.MedecinDao;
import dao.ResponsableDao;
import dao.UtilisateurDao;
import entities.Utilisateur;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.Utils;

/**
 *
 * @author 
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
    UtilisateurDao udao = new UtilisateurDao();
    
    
    @FXML
    public void handleConnexion() throws IOException{
     //   Utilisateur u = udao.login(loginField.getText(), passwordField.getText());
        if(admindao.login(loginField.getText(), passwordField.getText()) != null){
            Utils.admin = admindao.login(loginField.getText(), passwordField.getText());
            navigateTo("/views/MainWindow.fxml");
        }
        else if(adao.login(loginField.getText(), passwordField.getText()) != null){
            Utils.arbitre = adao.login(loginField.getText(), passwordField.getText());
            navigateTo("/views/ArbitreAcceuil.fxml");
        }
        else if(fdao.login(loginField.getText(), passwordField.getText()) != null){
            Utils.fan = fdao.login(loginField.getText(), passwordField.getText());
            navigateTo("/views/AccueilWindow.fxml");
        }
        else if(jdao.login(loginField.getText(), passwordField.getText()) != null){
            Utils.joueur = jdao.login(loginField.getText(), passwordField.getText());
            navigateTo("/views/JoueurAcceuil.fxml");
        }
        else if(mdao.login(loginField.getText(), passwordField.getText()) != null){
            Utils.medecin = mdao.login(loginField.getText(), passwordField.getText());
            navigateTo("/views/MedecinAcceuil.fxml");
        }
        else if(rdao.login(loginField.getText(), passwordField.getText()) != null){
            Utils.responsable = rdao.login(loginField.getText(), passwordField.getText());
            navigateTo("/views/ResponsableAcceuil.fxml");
        }
        else{
            navigateTo("/views/Connexion.fxml");
        }
    }
    
      @FXML
    public void accede() throws IOException{
        navigateTo("/views/AccueilWindow.fxml");
    }
    public  void navigateTo(String view) throws IOException{
        Node root =(Node) FXMLLoader.load(getClass().getResource(view));
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
    }
    
}
