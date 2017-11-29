package controllers.old;

import controllers.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class AdminController implements Initializable {
    
  
    @FXML
    private VBox hBoxMenu;
    @FXML
    private AnchorPane ContentAnchor;
    @FXML
    private AnchorPane parentAnchor;
    
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            navigateTo("/views/JoueurDetails.fxml");
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void passwordClicked(ActionEvent event) throws Exception {
        navigateTo("/views/ProfilAdmin.fxml");
    }
    
    @FXML
    private void deconnexionClicked(ActionEvent event) throws Exception {
        navigateTo("/views/Connexion.fxml");
    }
    
    @FXML
    private void joueurClicked(ActionEvent event) throws Exception {
        navigateTo("/views/JoueurDetails.fxml");
    }
    
    @FXML
    private void medecinClicked(ActionEvent event) throws Exception {
        navigateTo("/views/MedecinDetails.fxml");
    }
    
    @FXML
    private void arbitreDetailClicked(ActionEvent event) throws Exception {
        navigateTo("/views/ArbitreDetails.fxml");
    }
    
    @FXML
    private void responsableClicked(ActionEvent event) throws Exception {
        navigateTo("/views/RADetails.fxml");
    }
    
    @FXML
    private void stadeClicked(ActionEvent event) throws Exception {
        navigateTo("/views/StadeDetails.fxml");
    }
    
    @FXML
    private void competitionClicked(ActionEvent event) throws Exception {
        navigateTo("/views/ProfilAdmin.fxml");
    }
    
    @FXML
    private void matchClicked(ActionEvent event) throws Exception {
        navigateTo("/views/ProfilAdmin.fxml");
    }
    
    @FXML
    private void matchInternationalClicked(ActionEvent event) throws Exception {
        navigateTo("/views/InternationalMatches.fxml");
    }
    
    @FXML
    private void clubDetailsClicked(ActionEvent event) throws Exception {
        navigateTo("/views/ClubDetails.fxml");
    }
    @FXML
    private void donClicked(ActionEvent event) throws Exception {
        navigateTo("/views/donDetails.fxml");
    }
    @FXML
    private void ticketClicked(ActionEvent event) throws Exception {
        navigateTo("/views/InternationalMatches.fxml");
    }
    
    public  void navigateTo(String view) throws IOException{
        Node root =(Node) FXMLLoader.load(getClass().getResource(view));
        ContentAnchor.getChildren().clear();
        ContentAnchor.getChildren().add(root);
    }
    
}
