/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Actualite;
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
public class ActualiteDao implements interfaceDao<Actualite> {

    private Connection connection;
    private PreparedStatement prepared;

    public ActualiteDao() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Actualite t) {

        String req = "insert into actualite (titre, contenu, date_publication,image) values (?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getTitre());
            prepared.setString(2, t.getContenu());
            prepared.setString(3, t.getDatePublication());
            prepared.setString(4, t.getImage());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Actualite t) {

        String req = "UPDATE `actualite` SET `titre`=?,`contenu`=?,`date_publication`=?,`image`=? WHERE id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getTitre());
            prepared.setString(2, t.getContenu());
            prepared.setString(3, t.getDatePublication());
            prepared.setString(4, t.getImage());
            prepared.setInt(5, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {

        String requete = "delete from actualite where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("actualite  supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Actualite> findAll() {
        List<Actualite> listeAct = new ArrayList<>();
        String requete = "select * from actualite";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Actualite act = new Actualite();
                act.setId(resultat.getInt(1));
                act.setTitre(resultat.getString(2));
                act.setContenu(resultat.getString(3));
                act.setDatePublication(resultat.getString(4));
                act.setImage(resultat.getString(5));
                listeAct.add(act);
            }
            return listeAct;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des actualite " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Actualite findById(int id) {
        Actualite act = new Actualite();
        String requete = "select * from actualite where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                act.setId(resultat.getInt(1));
                act.setTitre(resultat.getString(2));
                act.setContenu(resultat.getString(3));
                act.setDatePublication(resultat.getString(4));
                act.setImage(resultat.getString(5));

            }
            return act;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
