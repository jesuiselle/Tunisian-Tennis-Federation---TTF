/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.StadeDao;
import dao.JoueurDao;
import dao.MedecinDao;
import entities.Club;
import entities.Joueur;
import entities.Medecin;
import entities.Stade;
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
import javafx.scene.control.TextArea;
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
public class StadeController implements Initializable {

    @FXML
    TableView<Stade> stadeTableView;
    @FXML
    TableColumn<Stade, Integer> idStadeColumn;
    @FXML
    TableColumn<Stade, String> nomStadeColumn;
    @FXML
    TableColumn<Stade, String> lieuStadeColumn;
    @FXML
    TableColumn<Medecin, String> descriptionStadeColumn;

    @FXML
    TextField nomStadeField, lieuStadeField;
    @FXML
    TextArea descriptionStadeTA;
    @FXML
    ImageView stadeImageView;

    File file;

    Stade stade;
    StadeDao dao = new StadeDao();
    ObservableList<Stade> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idStadeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomStadeColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieuStadeColumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        descriptionStadeColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        list.addAll(dao.findAll());
        stadeTableView.setItems(list);
    }

    @FXML
    public void parcourir() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        Image img = new Image(new FileInputStream(file));
        stadeImageView.setImage(img);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (stade != null) {
                stade.setNom(nomStadeField.getText());
                stade.setLieu(lieuStadeField.getText());
                stade.setDescription(descriptionStadeTA.getText());
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/stades/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stade.setImage(fileName);
                    file = null;
                }
                dao.update(stade);
            } else {
                stade = new Stade();
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/stades/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    stade.setImage(fileName);
                    file = null;
                }
                stade.setNom(nomStadeField.getText());
                stade.setLieu(lieuStadeField.getText());
                stade.setDescription(descriptionStadeTA.getText());
                dao.add(stade);
            }
            stade = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        stade = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        stade = stadeTableView.getSelectionModel().getSelectedItem();
        if (stade != null) {
            nomStadeField.setText(stade.getNom());
            lieuStadeField.setText(stade.getLieu());
            descriptionStadeTA.setText(stade.getDescription());
            Image img = new Image("http://localhost/resources/images/stades/" + stade.getImage());
            stadeImageView.setImage(img);
        }

    }

    @FXML
    public void supprimer() {
        stade = stadeTableView.getSelectionModel().getSelectedItem();
        if (stade != null) {
            dao.removeById(stade.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void afficher() {
        stade = stadeTableView.getSelectionModel().getSelectedItem();
        if (stade != null) {
            Stage myDialog = stadeDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            stade = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        nomStadeField.clear();
        lieuStadeField.clear();
        descriptionStadeTA.clear();
        stadeImageView.setImage(null);

    }

    public boolean allFieldsFilled() {
        return (!nomStadeField.getText().isEmpty()
                && !lieuStadeField.getText().isEmpty() && !descriptionStadeTA.getText().isEmpty());
    }

    public Stage stadeDetails(Stage owner) {
        Stage s = new Stage();
        s.initStyle(StageStyle.DECORATED);
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
        Image img = new Image("http://localhost/resources/images/stades/" + stade.getImage());
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        gridpane.add(imgView, 0, 2);

        
        Label nomLbl = new Label("Nom : ");
        nomLbl.setStyle( "-fx-font-size: 10pt;");
        nomLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomLbl, 0, 3);

        Label nomsLbl = new Label(stade.getNom());
        nomsLbl.setStyle( "-fx-font-size: 10pt;");
        nomsLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomsLbl, 1, 3);

        Label lLbl = new Label("Lieu : ");
        lLbl.setStyle( "-fx-font-size: 10pt;");
        lLbl.setTextFill(Color.web("#293539"));
        gridpane.add(lLbl, 0, 4);

        Label lsLbl = new Label(stade.getLieu());
        lsLbl.setStyle( "-fx-font-size: 10pt;");
        lsLbl.setTextFill(Color.web("#293539"));
        gridpane.add(lsLbl, 1, 4);

        Label dessanceLbl = new Label("Description : ");
        dessanceLbl.setStyle( "-fx-font-size: 10pt;");
        dessanceLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dessanceLbl, 0, 5);
        
        Label dessancesLbl = new Label(stade.getDescription());
        dessancesLbl.setStyle( "-fx-font-size: 10pt;");
        dessancesLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dessancesLbl, 1, 5);

        root.getChildren().add(gridpane);
        return s;
    }

}
