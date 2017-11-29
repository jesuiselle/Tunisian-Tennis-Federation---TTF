/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Main.primaryStage;
import dao.ArbitreDao;
import entities.Arbitre;
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
public class ArbitreController implements Initializable {

    @FXML
    TableView<Arbitre> arbitreTableView;
    @FXML
    TableColumn<Arbitre, Integer> idArbitreColumn;
    @FXML
    TableColumn<Arbitre, String> nomArbitreColumn;
    @FXML
    TableColumn<Arbitre, String> prenomArbitreColumn;
    @FXML
    TableColumn<Arbitre, String> categorieArbitreColumn;
    @FXML
    TableColumn<Arbitre, String> naissanceArbitreColumn;
    @FXML
    TextField nomArbitreField, prenomArbitreField, usernameArbitreField, emailArbitreField;
    @FXML
    PasswordField passwordArbitreField;
    @FXML
    DatePicker naissanceArbitreDP;
    @FXML
    ChoiceBox<String> categorieArbitreCB;
    @FXML
    ImageView arbitreImageView;

    File file;

    Arbitre arbitre;

    ObservableList<String> cat = FXCollections.observableArrayList();

    ArbitreDao dao = new ArbitreDao();
    ObservableList<Arbitre> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cat.add("Professionnel");
        cat.add("Amateur");
        categorieArbitreCB.setItems(cat);
        categorieArbitreCB.getSelectionModel().selectFirst();
        idArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, Integer>("id"));
        nomArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("nom"));
        prenomArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("prenom"));
        categorieArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("categorie"));
        naissanceArbitreColumn.setCellValueFactory(new PropertyValueFactory<Arbitre, String>("dateNaissance"));
        list.addAll(dao.findAll());
        arbitreTableView.setItems(list);;
    }

    @FXML
    public void parcourir() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        Image img = new Image(new FileInputStream(file));
        arbitreImageView.setImage(img);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (arbitre != null) {
                arbitre.setNom(nomArbitreField.getText());
                arbitre.setPrenom(prenomArbitreField.getText());
                arbitre.setUsername(usernameArbitreField.getText());
                arbitre.setEmail(emailArbitreField.getText());
                arbitre.setPassword(passwordArbitreField.getText());
                arbitre.setCategorie(categorieArbitreCB.getSelectionModel().getSelectedItem());
                arbitre.setDateNaissance(naissanceArbitreDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/arbitres/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    arbitre.setImage(fileName);
                    file = null;
                }
                if (dao.update(arbitre)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            } else {
                arbitre = new Arbitre();
                if (file != null) {
                    String ext = FilenameUtils.getExtension(file.getName());
                    String fileName = UUID.randomUUID().toString() + "." + ext;
                    File dest = new File("c://wamp/www/resources/images/arbitres/" + fileName);
                    try {
                        Files.copy(file.toPath(), dest.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    arbitre.setImage(fileName);
                    file = null;
                }
                arbitre.setNom(nomArbitreField.getText());
                arbitre.setPrenom(prenomArbitreField.getText());
                arbitre.setUsername(usernameArbitreField.getText());
                arbitre.setEmail(emailArbitreField.getText());
                arbitre.setPassword(passwordArbitreField.getText());
                arbitre.setCategorie(categorieArbitreCB.getSelectionModel().getSelectedItem());
                arbitre.setDateNaissance(naissanceArbitreDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                arbitre.setRoles("a:1:{i:0;s:12:\"ROLE_ARBITRE\";}");
                arbitre.setType("arbitre");
                if (dao.add(arbitre)) {
                    clear();
                    list.removeAll(list);
                    list.addAll(dao.findAll());
                }
            }
            arbitre = null;
        }

    }

    @FXML
    public void annuler() {
        clear();
        arbitre = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        arbitre = arbitreTableView.getSelectionModel().getSelectedItem();
        if (arbitre != null) {
            nomArbitreField.setText(arbitre.getNom());
            prenomArbitreField.setText(arbitre.getPrenom());
            usernameArbitreField.setText(arbitre.getUtilisateur().getUsername());
            emailArbitreField.setText(arbitre.getUtilisateur().getEmail());
            passwordArbitreField.setText(arbitre.getUtilisateur().getPassword());
            naissanceArbitreDP.setValue(LocalDate.parse(arbitre.getDateNaissance(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            categorieArbitreCB.getSelectionModel().select(arbitre.getCategorie());
            Image img = new Image("http://localhost/resources/images/arbitres/" + arbitre.getImage());
            arbitreImageView.setImage(img);
        }

    }

    @FXML
    public void supprimer() {
        arbitre = arbitreTableView.getSelectionModel().getSelectedItem();
        if (arbitre != null) {
            dao.removeById(arbitre.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void afficher() {
        arbitre = arbitreTableView.getSelectionModel().getSelectedItem();
        if (arbitre != null) {
            Stage myDialog = arbitreDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            arbitre = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        nomArbitreField.clear();
        prenomArbitreField.clear();
        usernameArbitreField.clear();
        emailArbitreField.clear();
        passwordArbitreField.clear();
        naissanceArbitreDP.setValue(null);
        arbitreImageView.setImage(null);
    }

    public boolean allFieldsFilled() {
        return (naissanceArbitreDP.getValue() != null && !nomArbitreField.getText().isEmpty()
                && !prenomArbitreField.getText().isEmpty() && !usernameArbitreField.getText().isEmpty()
                && !emailArbitreField.getText().isEmpty() && !passwordArbitreField.getText().isEmpty());
    }

    public Stage arbitreDetails(Stage owner) {
        
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
        Image img = new Image("http://localhost/resources/images/arbitres/" + arbitre.getImage());
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

        Label usernameLbl = new Label("Nom d'utilisateur : ");
        usernameLbl.setStyle( "-fx-font-size: 10pt;");
        usernameLbl.setTextFill(Color.web("#293539"));
        gridpane.add(usernameLbl, 0, 5);

        Label emailLbl = new Label("Email : ");
        emailLbl.setStyle( "-fx-font-size: 10pt;");
        emailLbl.setTextFill(Color.web("#293539"));
        gridpane.add(emailLbl, 0, 6);

        Label dateNaissanceLbl = new Label("Date de naissance : ");
        dateNaissanceLbl.setStyle( "-fx-font-size: 10pt;");
        dateNaissanceLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dateNaissanceLbl, 0, 7);

        Label catLbl = new Label("Catégorie : ");
        catLbl.setStyle( "-fx-font-size: 10pt;");
        catLbl.setTextFill(Color.web("#293539"));
        gridpane.add(catLbl, 0, 8);

        Label nomArbitreLbl = new Label(arbitre.getNom());
        nomArbitreLbl.setStyle( "-fx-font-size: 10pt;");
        nomArbitreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomArbitreLbl, 1, 3);

        Label prenomArbitreLbl = new Label(arbitre.getPrenom());
        prenomArbitreLbl.setStyle( "-fx-font-size: 10pt;");
        prenomArbitreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(prenomArbitreLbl, 1, 4);

        Label usernameArbitreLbl = new Label(arbitre.getUtilisateur().getUsername());
        usernameArbitreLbl.setStyle( "-fx-font-size: 10pt;");
        usernameArbitreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(usernameArbitreLbl, 1, 5);

        Label emailArbitreLbl = new Label(arbitre.getUtilisateur().getEmail());
        emailArbitreLbl.setStyle( "-fx-font-size: 10pt;");
        emailArbitreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(emailArbitreLbl, 1, 6);

        Label naissanceArbitreLbl = new Label(arbitre.getDateNaissance());
        naissanceArbitreLbl.setStyle( "-fx-font-size: 10pt;");
        naissanceArbitreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(naissanceArbitreLbl, 1, 7);

        Label catArbitreLbl = new Label(arbitre.getCategorie());
        catArbitreLbl.setStyle( "-fx-font-size: 10pt;");
        catArbitreLbl.setTextFill(Color.web("#293539"));
        gridpane.add(catArbitreLbl, 1, 8);

        
        root.getChildren().add(gridpane);
        return s;
    }

}
