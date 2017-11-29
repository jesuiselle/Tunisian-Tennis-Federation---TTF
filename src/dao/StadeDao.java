/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Stade;
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
public class StadeDao implements interfaceDao<Stade> {

    private Connection connection;

    public StadeDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Stade stade) {
        try {
            String req = "INSERT INTO stade (nom, lieu, description, image) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, stade.getNom());
            ps.setString(2, stade.getLieu());
            ps.setString(3, stade.getDescription());
            ps.setString(4, stade.getImage());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Stade stade) {
        String requete = "UPDATE `stade` SET `nom`=?,`lieu`=?,`description`=?,`image`=? WHERE id=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, stade.getNom());
            ps.setString(2, stade.getLieu());
            ps.setString(3, stade.getDescription());
            ps.setString(4, stade.getImage());
            ps.setInt(5, stade.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "DELETE FROM `stade` WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Stade supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Stade> findAll() {
        List<Stade> listeStade = new ArrayList<>();

        String requete = "select * from stade";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Stade stade = new Stade();
                stade.setId(resultat.getInt(1));
                stade.setNom(resultat.getString(2));
                stade.setLieu(resultat.getString(3));
                stade.setDescription(resultat.getString(4));
                stade.setImage(resultat.getString(5));
                listeStade.add(stade);
            }
            return listeStade;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stades " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Stade findById(int id) {
        Stade stade = new Stade();
        String requete = "SELECT * FROM `stade`  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                stade.setId(resultat.getInt(1));
                stade.setNom(resultat.getString(2));
                stade.setLieu(resultat.getString(3));
                stade.setDescription(resultat.getString(4));
                stade.setImage(resultat.getString(5));

            }
            return stade;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du stade " + ex.getMessage());
            return null;
        }
    }

}
