/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ClubDao;
import entities.Club;
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
public class ClubController implements Initializable {

    @FXML
    TableView<Club> clubTableView;
    @FXML
    TableColumn<Club, Integer> idClubColumn;
    @FXML
    TableColumn<Club, String> nomClubColumn;
    @FXML
    TableColumn<Club, String> adresseClubColumn;
    @FXML
    TableColumn<Club, String> fondationClubColumn;

    @FXML
    TextField nomClubField, adresseClubField;
    @FXML
    DatePicker fondationClubDP;
    @FXML
    ImageView clubImageView;

    File file;

    Club club;
    ClubDao dao = new ClubDao();
    ObservableList<Club> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idClubColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomClubColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseClubColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        fondationClubColumn.setCellValueFactory(new PropertyValueFactory<>("dateFondation"));
        list.addAll(dao.findAll());
        clubTableView.setItems(list);
    }

    @FXML
    public void parcourir() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        Image img = new Image(new FileInputStream(file));
        clubImageView.setImage(img);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (club != null) {
                club.setNom(nomClubField.getText());
                club.setAdresse(adresseClubField.getText());
                club.setDateFondation(fondationClubDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/clubs/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    club.setLogo(fileName);
                    file = null;
                }
                dao.update(club);
            } else {
                club = new Club();
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/clubs/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    club.setLogo(fileName);
                    file = null;
                }
                club.setNom(nomClubField.getText());
                club.setAdresse(adresseClubField.getText());
                club.setDateFondation(fondationClubDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                dao.add(club);

            }
            club = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        club = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        club = clubTableView.getSelectionModel().getSelectedItem();
        if (club != null) {
            nomClubField.setText(club.getNom());
            adresseClubField.setText(club.getAdresse());
            fondationClubDP.setValue(LocalDate.parse(club.getDateFondation(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            Image img = new Image("http://localhost/resources/images/clubs/" + club.getLogo());
            clubImageView.setImage(img);
        }

    }

    @FXML
    public void supprimer() {
        club = clubTableView.getSelectionModel().getSelectedItem();
        if (club != null) {
            dao.removeById(club.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void afficher() {
       club = clubTableView.getSelectionModel().getSelectedItem();
        if (club != null) {
            Stage myDialog = clubDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            club = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        nomClubField.clear();
        adresseClubField.clear();
        fondationClubDP.setValue(null);
        clubImageView.setImage(null);

    }

    public boolean allFieldsFilled() {
        return (!nomClubField.getText().isEmpty()
                && !adresseClubField.getText().isEmpty() && fondationClubDP.getValue() != null);
    }

    public Stage clubDetails(Stage owner) {
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

        /*Label imageLbl = new Label("Image : ");
        gridpane.add(imageLbl, 0, 7);*/
        Image img = new Image("http://localhost/resources/images/clubs/" + club.getLogo());
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        gridpane.add(imgView, 0, 2);

        
        Label nomLbl = new Label("Nom : ");
        nomLbl.setStyle( "-fx-font-size: 10pt;");
        nomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomLbl, 0, 3);

        Label nomcLbl = new Label(club.getNom());
        nomcLbl.setStyle( "-fx-font-size: 10pt;");
        nomcLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomcLbl, 1, 3);

        Label dateLbl = new Label("Date de fondation : ");
        dateLbl.setStyle( "-fx-font-size: 10pt;");
        dateLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dateLbl, 0, 4);

        Label dLbl = new Label(club.getDateFondation());
        dLbl.setStyle( "-fx-font-size: 10pt;");
        dLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dLbl, 1, 4);

        Label adrLbl = new Label("Adresse : ");
        adrLbl.setStyle( "-fx-font-size: 10pt;");
        adrLbl.setTextFill(Color.web("#293539"));
        gridpane.add(adrLbl, 0, 5);
        
        Label adLbl = new Label(club.getAdresse());
        adLbl.setStyle( "-fx-font-size: 10pt;");
        adLbl.setTextFill(Color.web("#293539"));
        gridpane.add(adLbl, 1, 5);

        root.getChildren().add(gridpane);
        return s;
    }

}
