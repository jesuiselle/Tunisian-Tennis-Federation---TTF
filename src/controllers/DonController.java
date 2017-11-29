/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ClubDao;
import dao.DonDao;
import entities.Club;
import entities.Don;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class DonController implements Initializable {

    @FXML
    TableView<Don> donTableView;
    @FXML
    TableColumn<Don, Integer> idDonColumn;
    @FXML
    TableColumn<Don, String> dateDonColumn;
    @FXML
    TableColumn<Don, String> clubDonColumn;
    @FXML
    TableColumn<Don, String> descriptionDonColumn;

    @FXML
    TextArea descriptionDonField;
    @FXML
    DatePicker dateDonDP;
    @FXML
    ChoiceBox<Club> clubDonCB;

    ObservableList<Club> cl = FXCollections.observableArrayList();

    Don don;
    DonDao dao = new DonDao();
    ClubDao cdao = new ClubDao();
    ObservableList<Don> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cl.addAll(cdao.findAll());
        clubDonCB.setItems(cl);
        idDonColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateDonColumn.setCellValueFactory(new PropertyValueFactory<>("dateDon"));
        clubDonColumn.setCellValueFactory(new PropertyValueFactory<>("club"));
        descriptionDonColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        list.addAll(dao.findAll());
        donTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (don != null) {
                don.setDescription(descriptionDonField.getText());
                don.setClub(clubDonCB.getValue());
                don.setDateDon(dateDonDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                dao.update(don);
            } else {
                don = new Don();
                don.setDescription(descriptionDonField.getText());
                don.setClub(clubDonCB.getValue());
                don.setDateDon(dateDonDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                dao.add(don);

            }
            don = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        don = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        don = donTableView.getSelectionModel().getSelectedItem();
        if (don != null) {
            descriptionDonField.setText(don.getDescription());
            clubDonCB.setValue(don.getClub());
            dateDonDP.setValue(LocalDate.parse(don.getDateDon(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

    }

    @FXML
    public void supprimer() {
        don = donTableView.getSelectionModel().getSelectedItem();
        if (don != null) {
            dao.removeById(don.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void afficher() {
       don = donTableView.getSelectionModel().getSelectedItem();
        if (don != null) {
            Stage myDialog = donDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            don = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        descriptionDonField.clear();
        dateDonDP.setValue(null);
        clubDonCB.setValue(null);

    }

    public boolean allFieldsFilled() {
        return (!descriptionDonField.getText().isEmpty()
                && dateDonDP != null && clubDonCB != null);
    }

    public Stage donDetails(Stage owner) {
        
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

        Label clubLbl = new Label("Club : ");
        clubLbl.setStyle( "-fx-font-size: 10pt;");
        clubLbl.setTextFill(Color.web("#293539"));
        gridpane.add(clubLbl, 0, 2);

        Label nomclubLbl = new Label(don.getClub().getNom());
        nomclubLbl.setStyle( "-fx-font-size: 10pt;");
        nomclubLbl.setTextFill(Color.web("#293539"));
        gridpane.add(nomclubLbl, 1, 2);

        Label dateLbl = new Label("Date don : ");
        dateLbl.setStyle( "-fx-font-size: 10pt;");
        dateLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dateLbl, 0, 3);

        Label dLbl = new Label(don.getDateDon());
        dLbl.setStyle( "-fx-font-size: 10pt;");
        dLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dLbl, 1, 3);

        Label descLbl = new Label("Description : ");
        descLbl.setStyle( "-fx-font-size: 10pt;");
        descLbl.setTextFill(Color.web("#293539"));
        gridpane.add(descLbl, 0, 4);
        
        Label dscLbl = new Label(don.getDescription());
        dscLbl.setStyle( "-fx-font-size: 10pt;");
        dscLbl.setTextFill(Color.web("#293539"));
        gridpane.add(dscLbl, 1, 4);

        root.getChildren().add(gridpane);
        return s;
    }

}
