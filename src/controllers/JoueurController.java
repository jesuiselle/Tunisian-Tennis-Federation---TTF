/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ClubDao;
import dao.JoueurDao;
import entities.Club;
import entities.Joueur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
public class JoueurController implements Initializable {

    @FXML
    TableView<Joueur> joueurTableView;
    @FXML
    TableColumn<Joueur, Integer> clubJoueurColumn;
    @FXML
    TableColumn<Joueur, String> nomJoueurColumn;
    @FXML
    TableColumn<Joueur, String> prenomJoueurColumn;
    @FXML
    TableColumn<Joueur, String> categorieJoueurColumn;
    @FXML
    TableColumn<Joueur, String> naissanceJoueurColumn;
    @FXML
    TableColumn<Joueur, Double> tailleJoueurColumn;
    @FXML
    TableColumn<Joueur, Double> poidsJoueurColumn;
    @FXML
    TableColumn<Joueur, Double> scoreJoueurColumn;

    @FXML
    TextField nomJoueurField, prenomJoueurField, usernameJoueurField, emailJoueurField, tailleJoueurField,
            poidsJoueurField, scoreJoueurField, nationaliteJoueurField;
    @FXML
    PasswordField passwordJoueurField;
    @FXML
    DatePicker naissanceJoueurDP;
    @FXML
    ChoiceBox<String> categorieJoueurCB, ageGroupJoueurCB;
    @FXML
    ChoiceBox<Club> clubJoueurCB;
    @FXML
    ImageView joueurImageView;

    File file;

    Joueur joueur;

    ObservableList<String> cat = FXCollections.observableArrayList();
    ObservableList<String> age = FXCollections.observableArrayList();
    ObservableList<Club> club = FXCollections.observableArrayList();

    JoueurDao dao = new JoueurDao();
    ClubDao cdao = new ClubDao();
    ObservableList<Joueur> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cat.add("Professionnel");
        cat.add("Amateur");
        age.add("Senior");
        age.add("Junior");
        club.addAll(cdao.findAll());
        categorieJoueurCB.setItems(cat);
        categorieJoueurCB.getSelectionModel().selectFirst();
        ageGroupJoueurCB.setItems(age);
        ageGroupJoueurCB.getSelectionModel().selectFirst();
        clubJoueurCB.setItems(club);
        
        clubJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("club"));
        nomJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        categorieJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        naissanceJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        tailleJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("taille"));
        poidsJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));
        scoreJoueurColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        list.addAll(dao.findAll());
        joueurTableView.setItems(list);
    }

    @FXML
    public void parcourir() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        Image img = new Image(new FileInputStream(file));
        joueurImageView.setImage(img);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (joueur != null) {
                joueur.setNom(nomJoueurField.getText());
                joueur.setPrenom(prenomJoueurField.getText());
                joueur.setUsername(usernameJoueurField.getText());
                joueur.setEmail(emailJoueurField.getText());
                joueur.setPassword(passwordJoueurField.getText());
                joueur.setCategorie(categorieJoueurCB.getSelectionModel().getSelectedItem());
                joueur.setDateNaissance(naissanceJoueurDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                joueur.setTaille(Double.parseDouble(tailleJoueurField.getText()));
                joueur.setPoids(Double.parseDouble(poidsJoueurField.getText()));
                joueur.setScore(Double.parseDouble(scoreJoueurField.getText()));
                joueur.setNationalite(nationaliteJoueurField.getText());
                joueur.setGroupeAge(ageGroupJoueurCB.getValue());
                joueur.setClub(clubJoueurCB.getValue());
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/joueurs/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    joueur.setImage(fileName);
                    file = null;
                }
                if (dao.update(joueur)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            } else {
                joueur = new Joueur();
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/joueurs/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    joueur.setImage(fileName);
                    file = null;
                }
                joueur.setNom(nomJoueurField.getText());
                joueur.setPrenom(prenomJoueurField.getText());
                joueur.setUsername(usernameJoueurField.getText());
                joueur.setEmail(emailJoueurField.getText());
                joueur.setPassword(passwordJoueurField.getText());
                joueur.setCategorie(categorieJoueurCB.getSelectionModel().getSelectedItem());
                joueur.setDateNaissance(naissanceJoueurDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                joueur.setTaille(Double.parseDouble(tailleJoueurField.getText()));
                joueur.setPoids(Double.parseDouble(poidsJoueurField.getText()));
                joueur.setScore(Double.parseDouble(scoreJoueurField.getText()));
                joueur.setNationalite(nationaliteJoueurField.getText());
                joueur.setGroupeAge(ageGroupJoueurCB.getValue());
                joueur.setClub(clubJoueurCB.getValue());
                joueur.setRoles("a:1:{i:0;s:11:\"ROLE_JOUEUR\";}");
                joueur.setType("joueur");
                if (dao.add(joueur)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            }
            joueur = null;
        }

    }

    @FXML
    public void annuler() {
        clear();
        joueur = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        joueur = joueurTableView.getSelectionModel().getSelectedItem();
        if (joueur != null) {
            nomJoueurField.setText(joueur.getNom());
            prenomJoueurField.setText(joueur.getPrenom());
            usernameJoueurField.setText(joueur.getUtilisateur().getUsername());
            emailJoueurField.setText(joueur.getUtilisateur().getEmail());
            passwordJoueurField.setText(joueur.getUtilisateur().getPassword());
            naissanceJoueurDP.setValue(LocalDate.parse(joueur.getDateNaissance(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            tailleJoueurField.setText(joueur.getTaille() + "");
            poidsJoueurField.setText(joueur.getPoids() + "");
            scoreJoueurField.setText(joueur.getScore() + "");
            nationaliteJoueurField.setText(joueur.getNationalite());
            ageGroupJoueurCB.setValue(joueur.getGroupeAge());
            clubJoueurCB.setValue(joueur.getClub());
            categorieJoueurCB.getSelectionModel().select(joueur.getCategorie());
            Image img = new Image("http://localhost/resources/images/joueurs/" + joueur.getImage());
            joueurImageView.setImage(img);
        }

    }

    @FXML
    public void supprimer() {
        joueur = joueurTableView.getSelectionModel().getSelectedItem();
        if (joueur != null) {
            dao.removeById(joueur.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void afficher() {
        joueur = joueurTableView.getSelectionModel().getSelectedItem();
        if (joueur != null) {
            Stage myDialog = joueurDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            joueur = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        nomJoueurField.clear();
        prenomJoueurField.clear();
        usernameJoueurField.clear();
        emailJoueurField.clear();
        passwordJoueurField.clear();
        naissanceJoueurDP.setValue(null);
        tailleJoueurField.clear();
        poidsJoueurField.clear();
        scoreJoueurField.clear();
        nationaliteJoueurField.clear();
        clubJoueurCB.setValue(null);
        joueurImageView.setImage(null);

    }

    public boolean allFieldsFilled() {
        return (naissanceJoueurDP.getValue() != null && !nomJoueurField.getText().isEmpty()
                && !prenomJoueurField.getText().isEmpty() && !usernameJoueurField.getText().isEmpty()
                && !emailJoueurField.getText().isEmpty() && !passwordJoueurField.getText().isEmpty());
    }

    public Stage joueurDetails(Stage owner) {
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
        
        Image img = new Image("http://localhost/resources/images/joueurs/" + joueur.getImage());
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        gridpane.add(imgView, 0, 2);


        Label nomLbl = new Label("Nom : ");
        nomLbl.setStyle( "-fx-font-size: 10pt;");
        nomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomLbl, 0, 3);

        Label prenomLbl = new Label("Prénom : ");
        prenomLbl.setStyle( "-fx-font-size: 10pt;");
        prenomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(prenomLbl, 0, 4);
        
        Label dateNaissanceLbl = new Label("Date de naissance : ");
        dateNaissanceLbl.setStyle( "-fx-font-size: 10pt;");
        dateNaissanceLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dateNaissanceLbl, 0, 5);
        
        Label tailleLbl = new Label("Taille : ");
        tailleLbl.setStyle( "-fx-font-size: 10pt;");
        tailleLbl.setTextFill(Color.web("#293539"));
        gridpane.add(tailleLbl, 0, 6);

        Label poidsLbl = new Label("Poids : ");
        poidsLbl.setStyle( "-fx-font-size: 10pt;");
        poidsLbl.setTextFill(Color.web("#293539"));
        gridpane.add(poidsLbl, 0, 7);
        
        Label nationaliteLbl = new Label("Nationalité : ");
        nationaliteLbl.setStyle( "-fx-font-size: 10pt;");
        nationaliteLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nationaliteLbl, 0, 8);

        Label emailLbl = new Label("Email : ");
        emailLbl.setStyle( "-fx-font-size: 10pt;");
        emailLbl.setTextFill(Color.web("#293539"));
        gridpane.add(emailLbl, 0, 9);
        
        Label catLbl = new Label("Catégorie : ");
        catLbl.setStyle( "-fx-font-size: 10pt;");
        catLbl.setTextFill(Color.web("#293539"));
        gridpane.add(catLbl, 0, 10);
        
        Label clubLbl = new Label("Club : ");
        clubLbl.setStyle( "-fx-font-size: 10pt;");
        clubLbl.setTextFill(Color.web("#293539"));
        gridpane.add(clubLbl, 0, 11);
      
        Label scoreLbl = new Label("Score : ");
        scoreLbl.setStyle( "-fx-font-size: 10pt;");
        scoreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(scoreLbl, 0, 12);

      
        Label nomJoueurLbl = new Label(joueur.getNom());
        gridpane.add(nomJoueurLbl, 1, 3);
        
        Label prenomJoueurLbl = new Label(joueur.getPrenom());
        gridpane.add(prenomJoueurLbl, 1, 4);
        
        Label naissanceJoueurLbl = new Label(joueur.getDateNaissance());
        gridpane.add(naissanceJoueurLbl, 1, 5);
        
        Label tailleJoueurLbl = new Label(Double.toString(joueur.getTaille()));
        gridpane.add(tailleJoueurLbl, 1, 6);

        Label poidsJoueurLbl = new Label(Double.toString(joueur.getPoids()));
        gridpane.add(poidsJoueurLbl, 1, 7);
        
        Label nationaliteJoueurLbl = new Label(joueur.getNationalite());
        gridpane.add(nationaliteJoueurLbl, 1, 8);
        
        Label emailJoueurLbl = new Label(joueur.getUtilisateur().getEmail());
        gridpane.add(emailJoueurLbl, 1, 9);
              
        Label catJoueurLbl = new Label(joueur.getCategorie());
        gridpane.add(catJoueurLbl, 1, 10);
        
        Label clubJoueurLbl = new Label(joueur.getClub().getNom());
        gridpane.add(clubJoueurLbl, 1, 11);
        
        Label scoreJoueurLbl = new Label(Double.toString(joueur.getScore()));
        gridpane.add(scoreJoueurLbl, 1, 12);
        
        root.getChildren().add(gridpane);
        return s;
    }

}
