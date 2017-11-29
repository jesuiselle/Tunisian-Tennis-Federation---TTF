/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CommentaireDao;
import entities.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author 
 */
public class CommentaireController implements Initializable {

    @FXML
    TableView<Commentaire> commentaireTableView;
    @FXML
    TableColumn<Commentaire, Integer> idCommentaireColumn;
    @FXML
    TableColumn<Commentaire, String> contenuCommentaireColumn;
    @FXML
    TableColumn<Commentaire, String> dateCommentaireColumn;
    @FXML
    TableColumn<Commentaire, String> actualiteCommentaireColumn;
    @FXML
    TableColumn<Commentaire, String> utilisateurCommentaireColumn;

    ObservableList<String> cat = FXCollections.observableArrayList();

    Commentaire commentaire;

    CommentaireDao dao = new CommentaireDao();
    ObservableList<Commentaire> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        contenuCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        actualiteCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("actualite"));
        utilisateurCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("fan"));
        list.addAll(dao.findAll());
        commentaireTableView.setItems(list);
    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    @FXML
    public void supprimer() {
        commentaire = commentaireTableView.getSelectionModel().getSelectedItem();
        if (commentaire != null) {
            dao.removeById(commentaire.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void afficher() {
        commentaire = commentaireTableView.getSelectionModel().getSelectedItem();
        if (commentaire != null) {
            Stage myDialog = commentaireDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            commentaire = null;
        }

    }

    public Stage commentaireDetails(Stage owner) {
        Stage s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        s.initOwner(owner);
        s.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root,Color.rgb(1,205,230,0.3));
        s.setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setVgap(5);

        
        Button login = new Button("Fermer");
        login.setStyle( "-fx-font-size: 10pt;");
        login.setTextFill(Color.web("#fe3554"));
        login.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                s.close();
            }
        });
        login.setBackground(Background.EMPTY);
        gridpane.add(login, 1, 1);
        GridPane.setHalignment(login, HPos.RIGHT);

        Label dateLbl = new Label("Date : ");
        dateLbl.setStyle( "-fx-font-size: 10pt;");
        dateLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dateLbl, 0, 5);
        
        Label ufLbl = new Label("Commenter par : ");
        ufLbl.setStyle( "-fx-font-size: 10pt;");
        ufLbl.setTextFill(Color.web("#293539"));
        gridpane.add(ufLbl, 0, 4);
        
        Label aLbl = new Label("Actualite : ");
        aLbl.setStyle( "-fx-font-size: 10pt;");
        aLbl.setTextFill(Color.web("#293539"));
        gridpane.add(aLbl, 0, 2);


        Label contenuLbl = new Label("Contenu : ");
        contenuLbl.setStyle( "-fx-font-size: 10pt;");
        contenuLbl.setTextFill(Color.web("#293539"));
        gridpane.add(contenuLbl, 0, 3);

        Label actCommentaireLbl = new Label(commentaire.getActualite().toString());
        gridpane.add(actCommentaireLbl, 1, 2);
        Label fanCommentaireLbl = new Label(commentaire.getFan().toString());
        gridpane.add(fanCommentaireLbl, 1, 4);
        Label dateCommentaireLbl = new Label(commentaire.getDate());
        gridpane.add(dateCommentaireLbl, 1, 5);
        Label contenuCommentaireLbl = new Label(commentaire.getContenu());
        gridpane.add(contenuCommentaireLbl, 1, 3);
        
        root.getChildren().add(gridpane);
        return s;
    }
}
