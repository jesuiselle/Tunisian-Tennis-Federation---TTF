/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entities.Statistic;
import idao.interfaceDao;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StatisticDao implements interfaceDao<Statistic> {

    private Connection connection;
    private MatchDao mdao = new MatchDao();
    private JoueurDao jdao = new JoueurDao();

    public StatisticDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Statistic statistic) {
        try {
            String req = "INSERT INTO `statistic`( `id_match`, `id_joueur`, `aces`, `service_winners`, `double_faults`, `total_points`, `total_points_won`, `service_game`, `average_serve_speed`, `fastest_serve_speed`) VALUES (?,?,?,?,?,?,?,?,?,?)	";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, statistic.getPartie().getId());
            ps.setInt(2, statistic.getJoueur().getId());
            ps.setInt(3, statistic.getAces());
            ps.setInt(4, statistic.getServiceWinners());

            ps.setInt(5, statistic.getDoubleFaults());

            ps.setInt(6, statistic.getTotalPoints());
            ps.setInt(7, statistic.getTotalPointsWon());
            ps.setInt(8, statistic.getServiceGame());
            ps.setDouble(9, statistic.getAverageServeSpeed());
            ps.setDouble(10, statistic.getFastestServeSpeed());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Statistic statistic) {
        String requete = "UPDATE `statistic` SET `id_match`=?,`id_joueur`=?,`aces`=?,`service_winners`=?,`double_faults`=?,`total_points`=?,`total_points_won`=?,`service_game`=?,`average_serve_speed`=?,`fastest_serve_speed`=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, statistic.getPartie().getId());
            ps.setInt(2, statistic.getJoueur().getId());
            ps.setInt(3, statistic.getAces());
            ps.setInt(4, statistic.getServiceWinners());

            ps.setInt(5, statistic.getDoubleFaults());

            ps.setInt(6, statistic.getTotalPoints());
            ps.setInt(7, statistic.getTotalPointsWon());
            ps.setInt(8, statistic.getServiceGame());
            ps.setDouble(9, statistic.getAverageServeSpeed());
            ps.setDouble(10, statistic.getFastestServeSpeed());
            ps.setInt(11, statistic.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "DELETE FROM `statistic` WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("statistic supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Statistic> findAll() {
        List<Statistic> listeStatistic = new ArrayList<>();

        String requete = "select * from statistic";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Statistic statistic = new Statistic();
                statistic.setId(resultat.getInt(1));
                statistic.setPartie(mdao.findById(resultat.getInt(2)));//ta3tina l colonne theniya mel line loula ba3edm theniya
                statistic.setJoueur(jdao.findById(resultat.getInt(3)));
                statistic.setAces(resultat.getInt(4));
                statistic.setServiceWinners(resultat.getInt(5));
                statistic.setDoubleFaults(resultat.getInt(6));
                statistic.setTotalPoints(resultat.getInt(7));
                statistic.setTotalPointsWon(resultat.getInt(8));
                statistic.setServiceGame(resultat.getInt(9));
                statistic.setAverageServeSpeed(resultat.getDouble(10));
                statistic.setFastestServeSpeed(resultat.getDouble(11));

                listeStatistic.add(statistic);
            }
            return listeStatistic;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des statistics " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Statistic findById(int id) {
        Statistic statistic = new Statistic();
        String requete = "SELECT * FROM `statistic`  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                statistic.setId(resultat.getInt(1));
                statistic.setPartie(mdao.findById(resultat.getInt(2)));//ta3tina l colonne theniya mel line loula ba3edm theniya
                statistic.setJoueur(jdao.findById(resultat.getInt(3)));
                statistic.setAces(resultat.getInt(4));
                statistic.setServiceWinners(resultat.getInt(5));
                statistic.setDoubleFaults(resultat.getInt(6));
                statistic.setTotalPoints(resultat.getInt(7));
                statistic.setTotalPointsWon(resultat.getInt(8));
                statistic.setServiceGame(resultat.getInt(9));
                statistic.setAverageServeSpeed(resultat.getDouble(10));
                statistic.setFastestServeSpeed(resultat.getDouble(11));

            }
            return statistic;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche des statistics " + ex.getMessage());
            return null;
        }
    }

}
