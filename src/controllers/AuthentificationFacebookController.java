/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.AppRequest.Application;
import com.restfb.types.User;
import dao.UtilisateurDao;
import entities.Utilisateur;
import idao.iUserDao;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author hichem
 */
public class AuthentificationFacebookController extends javafx.application.Application {

    String token;
    String appSecret = "a33a40217590239be35bf1c20780e07b";
    String appId = "958943674224202";
    Utilisateur user = new Utilisateur();
    UtilisateurDao userDAO;
    //LoginWindowController loginWindowController;

    public void getName(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, appSecret, Version.VERSION_2_5);
        User me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "email"), Parameter.with("fields", "birthday"));
        user.setId(Integer.parseInt(me.getId().substring(10)));
        System.out.println(me.getEmail());
//        if (me.getEmail() == null) {
//            me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "email"));
//            user.setEmail(me.getEmail());
//            user.setUsername(me.getEmail());
//        }
//        if (me.getFirstName() == null) {
//            me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "first_name"));
//            user.setUsername(me.getFirstName());
//        }
//
//        if (me.getLastName() == null) {
//            me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "last_name"));
//            user.setUsernameCanonical(me.getLastName());
//        }
//
//        userDAO = new UtilisateurDao();
//
//        if (!userDAO.searchByLogin(user.getUsername())) {
//            user.setPassword("facebook");
//            userDAO.add(user);
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Felicitation");
//            alert.setHeaderText("Felicitation, Votre demande a ete envoyer au administrateur");
//            alert.setContentText("Une email de validation va etre envoyer par l'administrateur ...");
//
//            primaryStage.close();
//            alert.showAndWait();
//            primaryStage.close();

//        } else {
//            client = (Client)userDAO.findByUsername(client.getUsername());
//            this.primaryStage.close();
//            if (client.getStatus().equals(UserStatus.PENDING)) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setContentText("Votre demande n'est encore traite par l'administrateur");
//                alert.showAndWait();
//                return;
//            }
//            if (client.getStatus().equals(UserStatus.REFUSED)) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Votre demande et refuser");
//                alert.showAndWait();
//                return;
//            }
//            if (client.getStatus().equals(UserStatus.DELETED)
//                    || client.getStatus().equals(UserStatus.BLOCKED)) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Votre compte et soit blocquer ou sumpprier par l'administrateur!");
//                alert.showAndWait();
//                return;
//            }
//            TunisiaMall.currentUser = client;
//            loginWindowController.animateWhenLoginSuccess();
  //      }

        //List u = facebookClient.executeFqlQuery("SELECT uid, name, email FROM user WHERE uid = me()", User.class);
        //User m = facebookClient.fetchObject(me.getId(), User.class, Parameter.with("fields", "email"));
        //System.out.println(m);
    }

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
        String url = "https://www.facebook.com/dialog/oauth?client_id=" + appId + "&redirect_uri=https://www.facebook.com/connect/login_success.html&response_type=token&scope=email,public_profile,user_birthday";
        engine.load(url);
        engine.getLoadWorker().stateProperty().addListener((ob, oldState, newState) -> {
            if (engine.getLocation().contains("access_token")) {
                token = engine.getLocation().substring(engine.getLocation().indexOf("access_token") + "access_token=".length(), engine.getLocation().length());
                token = token.substring(0, token.indexOf("&"));
                if (!token.isEmpty()) {
                    getName(token);
                }
            }
        });
        VBox root = new VBox();
        root.getChildren().addAll(webView);

        Scene scene = new Scene(root, 500, 600);
        this.primaryStage.setTitle("Login to facebook");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

//    public AuthentificationFacebookController(LoginController controller) {
//        this.loginWindowController = controller;
//    }
//
//    public void setLoginWindowController(LoginController loginWindowController) {
//        this.loginWindowController = loginWindowController;
//    }
    public AuthentificationFacebookController() {
        
    }

}
