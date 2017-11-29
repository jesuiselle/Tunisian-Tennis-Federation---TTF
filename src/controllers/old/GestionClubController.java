/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.ClubDao;
import entities.Club;
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
import javafx.scene.image.ImageView;

/**
 *
 * @author hichem
 */
public class GestionClubController {
     @FXML
    TableView<Club> tableClubView;
     
      @FXML
    TableColumn<Club, String> nomClubColumn;
    @FXML
    TableColumn<Club, String> adresseClubColumn;
    @FXML
     ImageView imageClubView;
      @FXML
    Label nomClubLabelDetails, adresseClubLabelDetails,imageClubLabelDeatils,dateClubLabelDetails;
    interfaceDao dao = new ClubDao();
    ObservableList<Club> list = FXCollections.observableArrayList();

     private mainClub main;
     
     
      public void initialize() {
        nomClubColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("nom"));
        adresseClubColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("adresse"));

        tableClubView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }
    
    public void setMain(mainClub main) {
        this.main = main;

        list.addAll(dao.findAll());
        tableClubView.setItems(list);
    }
  public void showDetails(Club club) {
        nomClubLabelDetails.setText(club.getNom());
        adresseClubLabelDetails.setText(club.getAdresse());
        dateClubLabelDetails.setText(club.getDateFondation().toString());
imageClubLabelDeatils.setText(club.getLogo().toString());

    }
      @FXML
    public void handleAjout() {
        main.newClubWindow(null);
    }
     @FXML
    public void handleModifier() {
      Club club = tableClubView.getSelectionModel().getSelectedItem();
        main.newClubWindow(club);
        
    }

    @FXML
    public void handleSupprimer() {
        Club club = tableClubView.getSelectionModel().getSelectedItem();
         /* Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
                  alert1.setTitle("Confirmation de Supprimer");
                  alert1.setHeaderText(null);
                  alert1.setContentText("Are You Sure to Delete ?");
                  Optional<ButtonType> action=alert1.showAndWait(); // action pour avant la supprimer 
                  if(action.get()==ButtonType.OK){
        dao.removeById(club.getId());
        tableClubView.setItems(null);
        tableClubView.layout();
        list.removeAll(list);
        list.addAll(dao.findAll());
        tableClubView.setItems(list);
    }*/

    }
    
     
}
