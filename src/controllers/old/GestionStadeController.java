/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.StadeDao;
import entities.Stade;
import idao.interfaceDao;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author hichem
 */
public class GestionStadeController {
     @FXML
    TableView<Stade> stadeTableView;
     @FXML
      TableColumn<Stade, String> nomStadeColumn;
     @FXML
      TableColumn<Stade, String> lieuStadeColumn;
      @FXML
    Label nomStadeLabelDetails, lieuStadeLabelDetails,descriptionStadeLabelDeatils,imageStadeLabelDetails;
    interfaceDao dao = new StadeDao();
    ObservableList<Stade> list = FXCollections.observableArrayList();

    private mainStade main;
    
    
      public void initialize() {
        nomStadeColumn.setCellValueFactory(new PropertyValueFactory<Stade, String>("nom"));
        lieuStadeColumn.setCellValueFactory(new PropertyValueFactory<Stade, String>("lieu"));

        stadeTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }
     public void setMain(mainStade main) {
        this.main = main;

        list.addAll(dao.findAll());
        stadeTableView.setItems(list);
    }
    public void showDetails(Stade stade) {
        nomStadeLabelDetails.setText(stade.getNom());
        lieuStadeLabelDetails.setText(stade.getLieu());
        descriptionStadeLabelDeatils.setText(stade.getDescription());
imageStadeLabelDetails.setText(stade.getImage().toString());


    }
      @FXML
    public void handleAjout() {
        main.newStadeWindow(null);
    }
     @FXML
    public void handleModifier() {
      Stade stade = stadeTableView.getSelectionModel().getSelectedItem();
        main.newStadeWindow(stade);
        
    }

    @FXML
    public void handleSupprimer() {
        Stade stade = stadeTableView.getSelectionModel().getSelectedItem();
       /*   Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
                  alert1.setTitle("Confirmation de Supprimer");
                  alert1.setHeaderText(null);
                  alert1.setContentText("Are You Sure to Delete ?");
                  Optional<ButtonType> action=alert1.showAndWait(); // action pour avant la supprimer 
                  if(action.get()==ButtonType.OK){
        dao.removeById(stade.getId());
        stadeTableView.setItems(null);
        stadeTableView.layout();
        list.removeAll(list);
        list.addAll(dao.findAll());
        stadeTableView.setItems(list);
    }
*/
    }
    
    
    
    
     
}
