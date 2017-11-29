/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Club;
import entities.Joueur;
import entities.Utilisateur;
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
public class JoueurDao implements iUserDao<Joueur> {

    private Connection connection;
    private PreparedStatement prepared;
    UtilisateurDao udao;

    interfaceDao<Club> cdao = new ClubDao();

    public JoueurDao() {
        connection = DataSource.getInstance().getConnection();
        udao = new UtilisateurDao();
    }

    @Override
    public boolean add(Joueur t) {
        if (udao.add(t)) {
            String req = "insert into joueur (id, nom, prenom,date_naissance,taille,poids,score,categorie,"
                    + "groupe_age,image,id_club,nationalite,logo_pays) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                prepared = connection.prepareStatement(req);
                prepared.setInt(1, udao.findByEmail(t.getEmail()).getId());
                prepared.setString(2, t.getNom());
                prepared.setString(3, t.getPrenom());
                prepared.setString(4, t.getDateNaissance());
                prepared.setDouble(5, t.getTaille());
                prepared.setDouble(6, t.getPoids());
                prepared.setDouble(7, t.getScore());
                prepared.setString(8, t.getCategorie());
                prepared.setString(9, t.getGroupeAge());
                prepared.setString(10, t.getImage());
                prepared.setInt(11, t.getClub().getId());
                prepared.setString(12, t.getNationalite());
                prepared.setString(13, t.getLogoPays());

                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(JoueurDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean update(Joueur t) {
        if (udao.update(t)) {
            String req = "UPDATE `joueur` SET `id_club`=?,`nom`=?,"
                    + "`prenom`=?,`date_naissance`=?,`taille`=?,"
                    + "`poids`=?,`score`=?,`categorie`=?,`groupe_age`=?,"
                    + "`image`=?,`nationalite`=? WHERE id=?";
            try {
                prepared = connection.prepareStatement(req);

                prepared.setInt(1, t.getClub().getId());
                prepared.setString(2, t.getNom());
                prepared.setString(3, t.getPrenom());
                prepared.setString(4, t.getDateNaissance());
                prepared.setDouble(5, t.getTaille());
                prepared.setDouble(6, t.getPoids());
                prepared.setDouble(7, t.getScore());
                prepared.setString(8, t.getCategorie());
                prepared.setString(9, t.getGroupeAge());
                prepared.setString(10, t.getImage());
                prepared.setString(11, t.getNationalite());
                prepared.setInt(12, t.getId());

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
        String requete = "delete from joueur where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            udao.removeById(id);
            System.out.println("joueur supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Joueur> findAll() {

        List<Joueur> listeJoueur = new ArrayList<>();
        String requete = "select * from joueur";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Joueur joueur = new Joueur();
                Utilisateur u = udao.findById(resultat.getInt(1));
                joueur.setUtilisateur(u);
                joueur.setId(resultat.getInt(1));
                joueur.setClub(cdao.findById(resultat.getInt(2)));
                joueur.setNom(resultat.getString(3));
                joueur.setPrenom(resultat.getString(4));
                joueur.setDateNaissance(resultat.getString(5));
                joueur.setTaille(resultat.getDouble(6));
                joueur.setPoids(resultat.getDouble(7));
                joueur.setScore(resultat.getDouble(8));
                joueur.setCategorie(resultat.getString(9));
                joueur.setGroupeAge(resultat.getString(10));
                joueur.setImage(resultat.getString(11));
                joueur.setNationalite(resultat.getString(12));
                joueur.setLogoPays(resultat.getString(13));
                listeJoueur.add(joueur);
            }
            return listeJoueur;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Joueurs " + ex.getMessage());
            return null;
        }

    }
    
    

    @Override
    public Joueur findById(int id) {

        Joueur joueur = null;
        String requete = "select * from joueur where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                Utilisateur u = udao.findById(id);
                joueur = new Joueur();
                joueur.setUtilisateur(u);
                joueur.setId(id);
                joueur.setClub(cdao.findById(resultat.getInt(2)));
                joueur.setNom(resultat.getString(3));
                joueur.setPrenom(resultat.getString(4));
                joueur.setDateNaissance(resultat.getString(5));
                joueur.setTaille(resultat.getDouble(6));
                joueur.setPoids(resultat.getDouble(7));
                joueur.setScore(resultat.getDouble(8));
                joueur.setCategorie(resultat.getString(9));
                joueur.setGroupeAge(resultat.getString(10));
                joueur.setImage(resultat.getString(11));
                joueur.setNationalite(resultat.getString(12));
                joueur.setLogoPays(resultat.getString(13));
            }
            return joueur;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    public Joueur login(String login, String password) {
        Utilisateur u = udao.login(login, password, "joueur");
        if (u != null) {
            String requete = "select * from joueur where id=?";
            try {
                prepared = connection.prepareStatement(requete);
                prepared.setInt(1, u.getId());
                ResultSet resultat = prepared.executeQuery();
                if (resultat.next()) {
                    Joueur joueur = new Joueur();
                    joueur.setUtilisateur(u);
                    joueur.setId(u.getId());
                    joueur.setClub(cdao.findById(resultat.getInt(2)));
                    joueur.setNom(resultat.getString(3));
                    joueur.setPrenom(resultat.getString(4));
                    joueur.setDateNaissance(resultat.getString(5));
                    joueur.setTaille(resultat.getDouble(6));
                    joueur.setPoids(resultat.getDouble(7));
                    joueur.setScore(resultat.getDouble(8));
                    joueur.setCategorie(resultat.getString(9));
                    joueur.setGroupeAge(resultat.getString(10));
                    joueur.setImage(resultat.getString(11));
                    joueur.setNationalite(resultat.getString(12));
                    joueur.setLogoPays(resultat.getString(13));
                    return joueur;
                }

            } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche  " + ex.getMessage());
            }
        }
        return null;
    }
}
