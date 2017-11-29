package controllers.old;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;



public class AcceuilController implements Initializable {
    
  
     
    @FXML
    private AnchorPane ContentAnchor;
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void InvitClicked(ActionEvent event) throws Exception {
        navigateTo("/views/InviterJoueur.fxml");
    }
              
    public  void navigateTo(String view) throws IOException{
        Node root =(Node) FXMLLoader.load(getClass().getResource(view));
        ContentAnchor.getChildren().clear();
        ContentAnchor.getChildren().add(root);
    }
    
}
