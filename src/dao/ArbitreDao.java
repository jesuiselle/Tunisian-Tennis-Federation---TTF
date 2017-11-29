/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Arbitre;
import entities.Utilisateur;
import idao.iUserDao;
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
public class ArbitreDao implements iUserDao<Arbitre> {

    private Connection connection;
    private PreparedStatement prepared;
    private UtilisateurDao udao;

    public ArbitreDao() {
        connection = DataSource.getInstance().getConnection();
        udao = new UtilisateurDao();
    }

    @Override
    public boolean add(Arbitre t) {
        if (udao.add(t)) {
            String req = "insert into arbitre(id, nom, prenom,categorie,date_naissance,image) values (?,?,?,?,?,?)";
            try {
                prepared = connection.prepareStatement(req);
                prepared.setInt(1, udao.findByEmail(t.getEmail()).getId());
                prepared.setString(2, t.getNom());
                prepared.setString(3, t.getPrenom());
                prepared.setString(4, t.getCategorie());
                prepared.setString(5, t.getDateNaissance());
                prepared.setString(6, t.getImage());
                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ArbitreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {

            return false;
        }
    }

    @Override
    public boolean update(Arbitre t) {
        if (udao.update(t)) {
            String req = "update arbitre set `nom`=?,`prenom`=?,"
                    + "`categorie`=?,`date_naissance`=?, `image`=? where id=?";
            try {
                prepared = connection.prepareStatement(req);

                prepared.setString(1, t.getNom());
                prepared.setString(2, t.getPrenom());
                prepared.setString(3, t.getCategorie());
                prepared.setString(4, t.getDateNaissance());
                prepared.setString(5, t.getImage());
                prepared.setInt(6, t.getId());

                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ArbitreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {

            return true;
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from arbitre where id=?";
        try {
            prepared = connection.prepareStatement(requete);

            prepared.setInt(1, id);
            prepared.executeUpdate();
            udao.removeById(id);
            System.out.println("arbitre supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Arbitre> findAll() {

        List<Arbitre> listeArbitre = new ArrayList<>();
        String requete = "select * from arbitre";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Arbitre arb = new Arbitre();
                arb.setUtilisateur(udao.findById(resultat.getInt(1)));
                arb.setId(resultat.getInt(1));
                arb.setNom(resultat.getString(2));
                arb.setPrenom(resultat.getString(3));
                arb.setCategorie(resultat.getString(4));
                arb.setDateNaissance(resultat.getString(5));
                arb.setImage(resultat.getString(6));
                listeArbitre.add(arb);
            }
            return listeArbitre;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des arbitres " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Arbitre findById(int id) {

        Arbitre arbitre = new Arbitre();
        String requete = "select * from arbitre where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                arbitre.setUtilisateur(udao.findById(id));
                arbitre.setId(id);
                arbitre.setNom(resultat.getString(2));
                arbitre.setPrenom(resultat.getString(3));
                arbitre.setCategorie(resultat.getString(4));
                arbitre.setDateNaissance(resultat.getString(5));
                arbitre.setImage(resultat.getString(6));
            }
            return arbitre;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    public Arbitre login(String login, String password) {
        Utilisateur u = udao.login(login, password, "arbitre");
        if (u != null) {
            String requete = "select * from arbitre where id=?";
            try {
                prepared = connection.prepareStatement(requete);
                prepared.setInt(1, u.getId());
                ResultSet resultat = prepared.executeQuery();
                if (resultat.next()) {
                    Arbitre arbitre = new Arbitre();
                    arbitre.setUtilisateur(u);
                    arbitre.setId(u.getId());
                    arbitre.setNom(resultat.getString(2));
                    arbitre.setPrenom(resultat.getString(3));
                    arbitre.setCategorie(resultat.getString(4));
                    arbitre.setDateNaissance(resultat.getString(5));
                    return arbitre;
                }

            } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche  " + ex.getMessage());
            }
        }
        return null;
    }
}
