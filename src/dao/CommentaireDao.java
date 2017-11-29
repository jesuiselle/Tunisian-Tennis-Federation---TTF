/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Actualite;
import entities.Commentaire;
import entities.Fan;
import idao.iUserDao;
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
public class CommentaireDao implements interfaceDao<Commentaire> {

    private Connection connection;
    private PreparedStatement prepared;

    iUserDao<Fan> fdao = new FanDao();
    interfaceDao<Actualite> adao = new ActualiteDao();

    public CommentaireDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Commentaire t) {
        String req = "insert into commentaire (contenu, date,id_utilisateur,id_actualite) values (?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getContenu());
            prepared.setString(2, t.getDate());
            prepared.setInt(3, t.getFan().getId());
            prepared.setInt(4, t.getActualite().getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Commentaire t) {
        String req = "UPDATE `commentaire` SET `contenu`=?,`date`=?,`id_utilisateur`=?,`id_actualite`=? WHERE id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getContenu());
            prepared.setString(2, t.getDate());
            prepared.setInt(3, t.getFan().getId());
            prepared.setInt(4, t.getActualite().getId());
            prepared.setInt(5, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from commentaire where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("commentaire supprime  supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Commentaire> findAll() {
        List<Commentaire> commentaires = new ArrayList<>();
        String requete = "select * from commentaire";

        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId(resultat.getInt(1));
                commentaire.setFan(fdao.findById(resultat.getInt(2)));
                commentaire.setActualite(adao.findById(resultat.getInt(3)));
                commentaire.setContenu(resultat.getString(4));
                commentaire.setDate(resultat.getString(5));
                commentaires.add(commentaire);
            }
            return commentaires;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Commentaire findById(int id) {

        Commentaire commentaire = new Commentaire();
        String requete = "select * from commentaire where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                commentaire.setId(resultat.getInt(1));
                commentaire.setFan(fdao.findById(resultat.getInt(2)));
                commentaire.setActualite(adao.findById(resultat.getInt(3)));
                commentaire.setContenu(resultat.getString(4));
                commentaire.setDate(resultat.getString(5));
            }
            return commentaire;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
