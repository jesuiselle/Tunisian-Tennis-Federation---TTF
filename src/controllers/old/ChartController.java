/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.old;

import dao.MatchDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Aydi
 */
public class ChartController {

    @FXML
    PieChart piechart;
    
    MatchDao pdao = new MatchDao();
    
    public void initialize() {
        int total = pdao.findAll().size();
        int par = 0; //pdao.findByIdJoueur(1).size();
        ObservableList<PieChart.Data> list
                = FXCollections.observableArrayList(
                        
                        new PieChart.Data("Total", total - par),
                        new PieChart.Data("Participation", par)
                );
        piechart.setData(list);
    }

}
