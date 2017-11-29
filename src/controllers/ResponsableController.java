/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ResponsableDao;
import entities.Responsable;
import java.io.FileNotFoundException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class ResponsableController implements Initializable {

    @FXML
    TableView<Responsable> responsableTableView;
    @FXML
    TableColumn<Responsable, Integer> idResponsableColumn;
    @FXML
    TableColumn<Responsable, String> nomResponsableColumn;
    @FXML
    TableColumn<Responsable, String> prenomResponsableColumn;
    @FXML
    TableColumn<Responsable, String> usernameResponsableColumn;
    @FXML
    TableColumn<Responsable, String> emailResponsableColumn;

    @FXML
    TextField nomResponsableField, prenomResponsableField, usernameResponsableField, emailResponsableField;
    @FXML
    PasswordField passwordResponsableField;

    Responsable responsable;
    ResponsableDao dao = new ResponsableDao();
    ObservableList<Responsable> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idResponsableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomResponsableColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomResponsableColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        usernameResponsableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailResponsableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        list.addAll(dao.findAll());
        responsableTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (responsable != null) {
                responsable.setNom(nomResponsableField.getText());
                responsable.setPrenom(prenomResponsableField.getText());
                responsable.setUsername(usernameResponsableField.getText());
                responsable.setEmail(emailResponsableField.getText());
                responsable.setPassword(passwordResponsableField.getText());
                if (dao.update(responsable)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            } else {
                responsable = new Responsable();
                responsable.setNom(nomResponsableField.getText());
                responsable.setPrenom(prenomResponsableField.getText());
                responsable.setUsername(usernameResponsableField.getText());
                responsable.setEmail(emailResponsableField.getText());
                responsable.setPassword(passwordResponsableField.getText());
                responsable.setRoles("a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}");
                responsable.setType("responsable");
                if (dao.add(responsable)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            }
            responsable = null;
        }

    }

    @FXML
    public void annuler() {
        clear();
        responsable = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        responsable = responsableTableView.getSelectionModel().getSelectedItem();
        if (responsable != null) {
            nomResponsableField.setText(responsable.getNom());
            prenomResponsableField.setText(responsable.getPrenom());
            usernameResponsableField.setText(responsable.getUtilisateur().getUsername());
            emailResponsableField.setText(responsable.getUtilisateur().getEmail());
            passwordResponsableField.setText(responsable.getUtilisateur().getPassword());
        }

    }

    @FXML
    public void supprimer() {
        responsable = responsableTableView.getSelectionModel().getSelectedItem();
        if (responsable != null) {
            dao.removeById(responsable.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void afficher() {
        responsable = responsableTableView.getSelectionModel().getSelectedItem();
        if (responsable != null) {
            Stage myDialog = responsableDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            responsable = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        nomResponsableField.clear();
        prenomResponsableField.clear();
        usernameResponsableField.clear();
        emailResponsableField.clear();
        passwordResponsableField.clear();

    }

    public boolean allFieldsFilled() {
        return (!nomResponsableField.getText().isEmpty()
                && !prenomResponsableField.getText().isEmpty() && !usernameResponsableField.getText().isEmpty()
                && !emailResponsableField.getText().isEmpty() && !passwordResponsableField.getText().isEmpty());
    }

    public Stage responsableDetails(Stage owner) {
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
        

        Label nomLbl = new Label("Nom : ");
        nomLbl.setStyle( "-fx-font-size: 10pt;");
        nomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomLbl, 0, 2);

        Label prenomLbl = new Label("Prénom : ");
        prenomLbl.setStyle( "-fx-font-size: 10pt;");
        
        prenomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(prenomLbl, 0, 3);

        Label nomJoueurLbl = new Label(responsable.getNom());
        gridpane.add(nomJoueurLbl, 1, 2);
        
        Label prenomJoueurLbl = new Label(responsable.getPrenom());
        gridpane.add(prenomJoueurLbl, 1, 3);
        
        root.getChildren().add(gridpane);
        return s;}

}
