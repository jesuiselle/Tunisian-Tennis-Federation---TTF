/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ActualiteDao;
import entities.Actualite;
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
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author 
 */
public class ActualiteController implements Initializable {

    @FXML
    TableView<Actualite> actualiteTableView;
    @FXML
    TableColumn<Actualite, Integer> idActualiteColumn;
    @FXML
    TableColumn<Actualite, String> titreActualiteColumn;
    @FXML
    TableColumn<Actualite, String> publicationActualiteColumn;
    @FXML
    TableColumn<Actualite, String> contenuActualiteColumn;

    @FXML
    TextField titreActualiteField;
    @FXML
    DatePicker publicationDP;
    @FXML
    TextArea contenuActualiteField;
    @FXML
    ImageView actualiteImageView;

    File file;

    Actualite actualite;
    ActualiteDao dao = new ActualiteDao();
    ObservableList<Actualite> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idActualiteColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreActualiteColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        publicationActualiteColumn.setCellValueFactory(new PropertyValueFactory<>("datePublication"));
        contenuActualiteColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        list.addAll(dao.findAll());
        actualiteTableView.setItems(list);
    }

    @FXML
    public void parcourir() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        Image img = new Image(new FileInputStream(file));
        actualiteImageView.setImage(img);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (actualite != null) {
                actualite.setTitre(titreActualiteField.getText());
                actualite.setDatePublication(publicationDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                actualite.setContenu(contenuActualiteField.getText());
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/actualites/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    actualite.setImage(fileName);
                    file = null;
                }
                dao.update(actualite);
            } else {
                actualite = new Actualite();
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/actualites/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    actualite.setImage(fileName);
                    file = null;
                }
                actualite.setTitre(titreActualiteField.getText());
                actualite.setDatePublication(publicationDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                actualite.setContenu(contenuActualiteField.getText());
                dao.add(actualite);
            }
            actualite = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        actualite = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        actualite = actualiteTableView.getSelectionModel().getSelectedItem();
        if (actualite != null) {
            titreActualiteField.setText(actualite.getTitre());
            contenuActualiteField.setText(actualite.getContenu());
            publicationDP.setValue(LocalDate.parse(actualite.getDatePublication(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            Image img = new Image("http://localhost/resources/images/actualites/" + actualite.getImage());
            actualiteImageView.setImage(img);
        }

    }

    @FXML
    public void supprimer() {
        actualite = actualiteTableView.getSelectionModel().getSelectedItem();
        if (actualite != null) {
            dao.removeById(actualite.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void afficher() {
        actualite = actualiteTableView.getSelectionModel().getSelectedItem();
        if (actualite != null) {
            Stage myDialog = actualiteDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            actualite = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        titreActualiteField.clear();
        publicationDP.setValue(null);
        contenuActualiteField.clear();
        actualiteImageView.setImage(null);

    }

    public boolean allFieldsFilled() {
        return (!titreActualiteField.getText().isEmpty()
                && publicationDP.getValue() != null && !contenuActualiteField.getText().isEmpty());
    }

    public Stage actualiteDetails(Stage owner) {
        Stage s = new Stage();
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
 
        Label titreLbl = new Label("Titre: ");
        titreLbl.setStyle( "-fx-font-size: 10pt;");
        titreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(titreLbl, 0, 3);
        
        Image img = new Image("http://localhost/resources/images/actualites/" + actualite.getImage());
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        gridpane.add(imgView, 0, 2);

        Label conLbl = new Label("Contenu: ");
        conLbl.setStyle( "-fx-font-size: 10pt;");
        conLbl.setTextFill(Color.web("#293539"));
        gridpane.add(conLbl, 0, 4);

        Label dateLbl = new Label("Date de publication: ");
        dateLbl.setStyle( "-fx-font-size: 10pt;");
        dateLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dateLbl, 0, 5);

        Label nomJoueurLbl = new Label(actualite.getTitre());
        gridpane.add(nomJoueurLbl, 1, 3);
        Label usernameJoueurLabel = new Label(actualite.getContenu());
        gridpane.add(usernameJoueurLabel, 1, 4);
        Label prenomJoueurLbl = new Label(actualite.getDatePublication());
        gridpane.add(prenomJoueurLbl, 1, 5);
       


        root.getChildren().add(gridpane);
        return s;
    }

}
