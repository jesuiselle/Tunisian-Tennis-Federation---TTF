/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.StadeDao;
import entities.Stade;
import idao.interfaceDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author hichem
 */
public class StadeFormController {
   @FXML
   TextField nomStadeTextField,lieuStadeTextField;
   @FXML
   TextArea descriptionStadeTextField;
   @FXML
   Label imageStadeLabel;
   @FXML
       ImageView  imageStadeView;
    private mainStade main;
     private Stage stage;
     private Stade stade;
     
     interfaceDao dao=new StadeDao();
       
     public void setmain(mainStade main, Stage stage, Stade stade)
     {
     
      this.main = main;
        this.stage = stage;
        this.stade = stade;
        if (stade != null) {
            fillStadeDetails();
        }
     }
     
     public void fillStadeDetails() {
        nomStadeTextField.setText(stade.getNom());
        lieuStadeTextField.setText(stade.getLieu());
descriptionStadeTextField.setText(stade.getDescription());

        
    }
     
     @FXML
    public void handleOk(){
        if (stade != null) {
            stade.setNom(nomStadeTextField.getText());
            stade.setLieu(lieuStadeTextField.getText());
            stade.setDescription(descriptionStadeTextField.getText());
            //travail sur un image null
           
            stade.setImage(imageStadeLabel.getText());
            dao.update(stade);
            //okClicked = true;
        } else {
            Stade newStade = new Stade(
                    nomStadeTextField.getText(),
                    lieuStadeTextField.getText(),
                    descriptionStadeTextField.getText(),
                    imageStadeLabel.getText());
            
            dao.add(newStade);
        }
        stage.close();
    }
      @FXML
    public void handleCancel(){
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
            imageStadeLabel.setText(path);
             
            try {
                InputStream inputStream = new FileInputStream(path);
                Image image = new Image(inputStream);
              imageStadeView.setImage(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClubFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
    
    
}
