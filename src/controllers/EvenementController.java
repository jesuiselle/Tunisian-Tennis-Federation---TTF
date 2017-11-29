/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.EvenementDao;
import entities.Evenement;
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
public class EvenementController implements Initializable {

    @FXML
    TableView<Evenement> evenementTableView;
    @FXML
    TableColumn<Evenement, Integer> idEvenementColumn;
    @FXML
    TableColumn<Evenement, String> nomEvenementColumn;
    @FXML
    TableColumn<Evenement, String> descriptionEvenementColumn;
    @FXML
    TableColumn<Evenement, String> prixEvenementColumn;
    @FXML
    TableColumn<Evenement, String> debutEvenementColumn;
    @FXML
    TableColumn<Evenement, String> finEvenementColumn;

    @FXML
    TextField nomEvenementField, prixEvenementField, gagnantEvenementField;
    @FXML
    TextArea descriptionEvenementField;
    @FXML
    DatePicker debutEvenementDP, finEvenementDP;
    @FXML
    ImageView evenementImageView;

    File file;

    Evenement evenement;
    EvenementDao dao = new EvenementDao();
    ObservableList<Evenement> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idEvenementColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomEvenementColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionEvenementColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixEvenementColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        debutEvenementColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        finEvenementColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        list.addAll(dao.findAll());
        evenementTableView.setItems(list);
    }

    @FXML
    public void parcourir() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        Image img = new Image(new FileInputStream(file));
        evenementImageView.setImage(img);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (evenement != null) {
                evenement.setNom(nomEvenementField.getText());
                evenement.setPrix(Double.parseDouble(prixEvenementField.getText()));
                evenement.setGagnant(gagnantEvenementField.getText());
                evenement.setDescription(descriptionEvenementField.getText());
                evenement.setDateDebut(debutEvenementDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                evenement.setDateFin(finEvenementDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/evenements/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    evenement.setImage(fileName);
                    file = null;
                }
                dao.update(evenement);
            } else {
                evenement = new Evenement();
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/evenements/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    evenement.setImage(fileName);
                    file = null;
                }
                evenement.setNom(nomEvenementField.getText());
                evenement.setPrix(Double.parseDouble(prixEvenementField.getText()));
                evenement.setGagnant(gagnantEvenementField.getText());
                evenement.setDescription(descriptionEvenementField.getText());
                evenement.setDateDebut(debutEvenementDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                evenement.setDateFin(finEvenementDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                dao.add(evenement);
            }
            evenement = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        evenement = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        evenement = evenementTableView.getSelectionModel().getSelectedItem();
        if (evenement != null) {
            nomEvenementField.setText(evenement.getNom());
            prixEvenementField.setText(evenement.getPrix() + "");
            gagnantEvenementField.setText(evenement.getGagnant());
            descriptionEvenementField.setText(evenement.getDescription());
            debutEvenementDP.setValue(LocalDate.parse(evenement.getDateDebut(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            finEvenementDP.setValue(LocalDate.parse(evenement.getDateFin(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            Image img = new Image("http://localhost/resources/images/evenements/" + evenement.getImage());
            evenementImageView.setImage(img);
        }

    }

    @FXML
    public void supprimer() {
        evenement = evenementTableView.getSelectionModel().getSelectedItem();
        if (evenement != null) {
            dao.removeById(evenement.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void afficher() {
        evenement = evenementTableView.getSelectionModel().getSelectedItem();
        if (evenement != null) {
            Stage myDialog = evnDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            evenement = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        nomEvenementField.clear();
        prixEvenementField.clear();
        gagnantEvenementField.clear();
        debutEvenementDP.setValue(null);
        finEvenementDP.setValue(null);
        descriptionEvenementField.clear();
        evenementImageView.setImage(null);

    }

    public boolean allFieldsFilled() {
        return (!nomEvenementField.getText().isEmpty()
                && !prixEvenementField.getText().isEmpty() && debutEvenementDP.getValue() != null
                && finEvenementDP.getValue() != null && !descriptionEvenementField.getText().isEmpty());
    }

    public Stage evnDetails(Stage owner) {
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
        Image img = new Image("http://localhost/resources/images/evenements/" +evenement.getImage());
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        gridpane.add(imgView, 0, 2);

        
        Label descLbl = new Label("Description : ");
        descLbl.setStyle( "-fx-font-size: 10pt;");
        descLbl.setTextFill(Color.web("#293539"));
        gridpane.add(descLbl, 0, 3);
        
        Label desccLbl = new Label(evenement.getDescription());
          desccLbl.setWrapText(true);
        descLbl.setStyle( "-fx-font-size: 10pt;");
        descLbl.setTextFill(Color.web("#293539"));
        gridpane.add(desccLbl, 1, 3);

        Label datedebLbl = new Label("Date début : ");
        datedebLbl.setStyle( "-fx-font-size: 10pt;");
        datedebLbl.setTextFill(Color.web("#293539"));
        gridpane.add(datedebLbl, 0, 4);
        
        Label datedebbLbl = new Label(evenement.getDateDebut());
        datedebLbl.setStyle( "-fx-font-size: 10pt;");
        datedebLbl.setTextFill(Color.web("#293539"));
        gridpane.add(datedebbLbl, 1, 4);

        Label datefinLbl = new Label("Date fin : ");
        datefinLbl.setStyle( "-fx-font-size: 10pt;");
        datefinLbl.setTextFill(Color.web("#293539"));
        gridpane.add(datefinLbl, 0, 5);
        
        Label datefinnLbl = new Label(evenement.getDateFin());
        datefinLbl.setStyle( "-fx-font-size: 10pt;");
        datefinLbl.setTextFill(Color.web("#293539"));
        gridpane.add(datefinnLbl, 1, 5);
        
        Label prixLbl = new Label("Prix : ");
        prixLbl.setStyle( "-fx-font-size: 10pt;");
        prixLbl.setTextFill(Color.web("#293539"));
        gridpane.add(prixLbl, 0, 6);
        
        Label liLbl = new Label(Double.toString(evenement.getPrix()));
        prixLbl.setStyle( "-fx-font-size: 10pt;");
        prixLbl.setTextFill(Color.web("#293539"));
        gridpane.add(liLbl, 1, 6);
        
        Label gagnatbl = new Label("Gagnant : ");
        prixLbl.setStyle( "-fx-font-size: 10pt;");
        prixLbl.setTextFill(Color.web("#293539"));
        gridpane.add(gagnatbl, 0, 7);
   
        Label gtbl = new Label(evenement.getGagnant());
        prixLbl.setStyle( "-fx-font-size: 10pt;");
        prixLbl.setTextFill(Color.web("#293539"));
        gridpane.add(gtbl, 1, 7);

        root.getChildren().add(gridpane);
        return s;
    }

}
