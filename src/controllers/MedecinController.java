/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MedecinDao;
import entities.Medecin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author 
 */
public class MedecinController implements Initializable {

    @FXML
    TableView<Medecin> medecinTableView;
    @FXML
    TableColumn<Medecin, Integer> idMedecinColumn;
    @FXML
    TableColumn<Medecin, String> nomMedecinColumn;
    @FXML
    TableColumn<Medecin, String> prenomMedecinColumn;
    @FXML
    TableColumn<Medecin, String> usernameMedecinColumn;
    @FXML
    TableColumn<Medecin, String> emailMedecinColumn;

    @FXML
    TextField nomMedecinField, prenomMedecinField, usernameMedecinField, emailMedecinField;
    @FXML
    PasswordField passwordMedecinField;
    @FXML
    ImageView medecinImageView;

    File file;

    Medecin medecin;
    MedecinDao dao = new MedecinDao();
    ObservableList<Medecin> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idMedecinColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomMedecinColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomMedecinColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        usernameMedecinColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailMedecinColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        list.addAll(dao.findAll());
        medecinTableView.setItems(list);
    }

    @FXML
    public void parcourir() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        Image img = new Image(new FileInputStream(file));
        medecinImageView.setImage(img);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (medecin != null) {
                medecin.setNom(nomMedecinField.getText());
                medecin.setPrenom(prenomMedecinField.getText());
                medecin.setUsername(usernameMedecinField.getText());
                medecin.setEmail(emailMedecinField.getText());
                medecin.setPassword(passwordMedecinField.getText());
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/medecins/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    medecin.setImage(fileName);
                    file = null;
                }
                if (dao.update(medecin)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            } else {
                medecin = new Medecin();
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/medecins/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    medecin.setImage(fileName);
                    file = null;
                }
                medecin.setNom(nomMedecinField.getText());
                medecin.setPrenom(prenomMedecinField.getText());
                medecin.setUsername(usernameMedecinField.getText());
                medecin.setEmail(emailMedecinField.getText());
                medecin.setPassword(passwordMedecinField.getText());
                medecin.setRoles("a:1:{i:0;s:12:\"ROLE_MEDECIN\";}");
                medecin.setType("medecin");
                if (dao.add(medecin)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            }
            medecin = null;
        }

    }

    @FXML
    public void annuler() {
        clear();
        medecin = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        medecin = medecinTableView.getSelectionModel().getSelectedItem();
        if (medecin != null) {
            nomMedecinField.setText(medecin.getNom());
            prenomMedecinField.setText(medecin.getPrenom());
            usernameMedecinField.setText(medecin.getUtilisateur().getUsername());
            emailMedecinField.setText(medecin.getUtilisateur().getEmail());
            passwordMedecinField.setText(medecin.getUtilisateur().getPassword());
            Image img = new Image("http://localhost/resources/images/medecins/" + medecin.getImage());
            medecinImageView.setImage(img);
        }

    }

    @FXML
    public void supprimer() {
        medecin = medecinTableView.getSelectionModel().getSelectedItem();
        if (medecin != null) {
            dao.removeById(medecin.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void afficher() {
        medecin = medecinTableView.getSelectionModel().getSelectedItem();
        if (medecin != null) {
            Stage myDialog = medecinDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            medecin = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        nomMedecinField.clear();
        prenomMedecinField.clear();
        usernameMedecinField.clear();
        emailMedecinField.clear();
        passwordMedecinField.clear();
        medecinImageView.setImage(null);

    }

    public boolean allFieldsFilled() {
        return (!nomMedecinField.getText().isEmpty()
                && !prenomMedecinField.getText().isEmpty() && !usernameMedecinField.getText().isEmpty()
                && !emailMedecinField.getText().isEmpty() && !passwordMedecinField.getText().isEmpty());
    }

    public Stage medecinDetails(Stage owner) {
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
        
        Image img = new Image("http://localhost/resources/images/medecins/" + medecin.getImage());
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        gridpane.add(imgView, 0,2 );
        
        Label nomLbl = new Label("Nom : ");
        nomLbl.setStyle( "-fx-font-size: 10pt;");
        nomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomLbl, 0, 3);

        Label prenomLbl = new Label("Prénom : ");
        prenomLbl.setStyle( "-fx-font-size: 10pt;");
        
        prenomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(prenomLbl, 0, 4);

        Label nomJoueurLbl = new Label(medecin.getNom());
        gridpane.add(nomJoueurLbl, 1, 3);
        
        Label prenomJoueurLbl = new Label(medecin.getPrenom());
        gridpane.add(prenomJoueurLbl, 1, 4);
        
        root.getChildren().add(gridpane);
        return s;
    }

}
