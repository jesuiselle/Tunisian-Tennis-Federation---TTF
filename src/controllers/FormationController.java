/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FormationDao;
import entities.Formation;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class FormationController implements Initializable {

    @FXML
    TableView<Formation> formationTableView;
    @FXML
    TableColumn<Formation, Integer> idFormationColumn;
    @FXML
    TableColumn<Formation, String> datedebutFormationColumn;
    @FXML
    TableColumn<Formation, String> datefinFormationColumn;
    @FXML
    TableColumn<Formation, String> descriptionFormationColumn;

    @FXML
    DatePicker datefinDP, datedebutDP;
    @FXML
    TextArea descriptionTA;

    Formation formation;
    FormationDao dao = new FormationDao();
    ObservableList<Formation> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idFormationColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        datedebutFormationColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        datefinFormationColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        descriptionFormationColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        list.addAll(dao.findAll());
        formationTableView.setItems(list);
    }

    @FXML
    public void valider() {
        if (allFieldsFilled()) {
            if (formation != null) {
                formation.setDateFin(datefinDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                formation.setDateDebut(datedebutDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                formation.setDescription(descriptionTA.getText());
                dao.update(formation);

            } else {
                formation = new Formation();
                formation.setDateFin(datefinDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                formation.setDateDebut(datedebutDP.getValue()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                formation.setDescription(descriptionTA.getText());
                dao.add(formation);
            }
            formation = null;
            clear();
            list.removeAll(list);
            list.addAll(dao.findAll());
        }

    }

    @FXML
    public void annuler() {
        clear();
        formation = null;
    }

    @FXML
    public void modifier() throws FileNotFoundException {
        formation = formationTableView.getSelectionModel().getSelectedItem();
        if (formation != null) {
            datefinDP.setValue(LocalDate.parse(formation.getDateFin(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            datedebutDP.setValue(LocalDate.parse(formation.getDateDebut(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            descriptionTA.setText(formation.getDescription());
        }

    }

    @FXML
    public void supprimer() {
        formation = formationTableView.getSelectionModel().getSelectedItem();
        if (formation != null) {
            dao.removeById(formation.getId());
            list.removeAll(list);
            list.addAll(dao.findAll());
        }
    }

    @FXML
    public void afficher() {
        formation = formationTableView.getSelectionModel().getSelectedItem();
        if (formation != null) {
            Stage myDialog = formationDetails(Main.getPrimaryStage());
            myDialog.sizeToScene();
            myDialog.show();
            formation = null;
        }

    }

    @FXML
    public void actualiser() {
        list.removeAll(list);
        list.addAll(dao.findAll());
    }

    public void clear() {
        datefinDP.setValue(null);
        datedebutDP.setValue(null);
        descriptionTA.clear();

    }

    public boolean allFieldsFilled() {
        return (datefinDP.getValue() != null
                && datedebutDP.getValue() != null &&  !descriptionTA.getText().isEmpty());
    }

    public Stage formationDetails(Stage owner) {
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

        Label descriptionLbl = new Label("Description : ");
        gridpane.add(descriptionLbl, 0, 2);

        Label datedebutLbl = new Label("Date début : ");
        gridpane.add(datedebutLbl, 0, 3);

        Label datefinLbl = new Label("Date fin : ");
        gridpane.add(datefinLbl, 0, 4);

        Label descriptionFormationLbl = new Label(formation.getDescription());
        gridpane.add(descriptionFormationLbl, 1, 2);
        
        Label datedebutFormationLbl = new Label(formation.getDateDebut());
        gridpane.add(datedebutFormationLbl, 1, 3);
        
        Label datefinFormationLbl = new Label(formation.getDateFin());
        gridpane.add(datefinFormationLbl, 1, 4);
    
        root.getChildren().add(gridpane);
        return s;
    }

}
