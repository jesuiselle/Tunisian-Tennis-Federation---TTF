/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Club;
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

/**
 *
 * @author Bouchriha
 */
public class ClubDao implements interfaceDao<Club> {

    private Connection connection;
    private PreparedStatement prepared;

    public ClubDao() {

        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Club t) {
        String req = "insert into club (nom, adresse,date_fondation,logo) values (?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getNom());
            prepared.setString(2, t.getAdresse());
            prepared.setString(3, t.getDateFondation());
            prepared.setString(4, t.getLogo());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Club t) {
        String req = "UPDATE `club` SET `nom`=?,`adresse`=?,`date_fondation`=?,`logo`=? WHERE id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getNom());
            prepared.setString(2, t.getAdresse());
            prepared.setString(3, t.getDateFondation());
            prepared.setString(4, t.getLogo());
            prepared.setInt(5, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {

        String requete = "delete from club where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("club supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Club> findAll() {
        List<Club> listeClub = new ArrayList<>();
        String requete = "select * from club";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Club club = new Club();
                club.setId(resultat.getInt(1));
                club.setNom(resultat.getString(2));
                club.setAdresse(resultat.getString(3));
                club.setDateFondation(resultat.getString(4));
                club.setLogo(resultat.getString(5));
                listeClub.add(club);
            }
            return listeClub;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des club " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Club findById(int id) {
        Club club = new Club();
        String requete = "select * from club where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                club.setId(resultat.getInt(1));
                club.setNom(resultat.getString(2));
                club.setAdresse(resultat.getString(3));
                club.setDateFondation(resultat.getString(4));
                club.setLogo(resultat.getString(5));
            }
            return club;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    }

}
