/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Evenement;
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
 * @author Aydi
 */
public class EvenementDao implements interfaceDao<Evenement> {

    private Connection connection;
    private PreparedStatement prepared;

    public EvenementDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Evenement t) {
        String req = "insert into evenement (nom, description,date_debut,date_fin,prix,gagnant,image) values (?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getNom());
            prepared.setString(2, t.getDescription());
            prepared.setString(3, t.getDateDebut());
            prepared.setString(4, t.getDateFin());
            prepared.setDouble(5, t.getPrix());
            prepared.setString(6, t.getGagnant());
            prepared.setString(7, t.getImage());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Evenement t) {

        String req = "UPDATE `evenement` SET `nom`=?,`description`=?,`date_debut`=?,`date_fin`=?,`prix`=?,`gagnant`=?,`image`=? WHERE id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getNom());
            prepared.setString(2, t.getDescription());
            prepared.setString(3, t.getDateDebut());
            prepared.setString(4, t.getDateFin());
            prepared.setDouble(5, t.getPrix());
            prepared.setString(6, t.getGagnant());
            prepared.setString(7, t.getImage());
            prepared.setInt(8, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from evenement where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("evenemt supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }
  
    @Override
    public List<Evenement> findAll() {
        List<Evenement> listEvenement = new ArrayList<>();
        String requete = "select * from evenement";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Evenement evenement = new Evenement();
                evenement.setId(resultat.getInt(1));
                evenement.setNom(resultat.getString(2));
                evenement.setDescription(resultat.getString(3));
                evenement.setDateDebut(resultat.getString(4));
                evenement.setDateFin(resultat.getString(5));
                evenement.setPrix(resultat.getDouble(6));
                evenement.setGagnant(resultat.getString(7));
                evenement.setImage(resultat.getString(8));
                listEvenement.add(evenement);
            }
            return listEvenement;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Evenement findById(int id) {
        Evenement evn = new Evenement();
        String requete = "select * from evenement where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                evn.setId(resultat.getInt(1));
                evn.setNom(resultat.getString(2));
                evn.setDescription(resultat.getString(3));
                evn.setDateDebut(resultat.getString(4));
                evn.setDateFin(resultat.getString(5));
                evn.setPrix(resultat.getDouble(6));
                evn.setGagnant(resultat.getString(7));
                evn.setImage(resultat.getString(8));
            }
            return evn;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
