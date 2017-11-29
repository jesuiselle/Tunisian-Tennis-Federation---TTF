/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Responsable;
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
public class ResponsableDao implements iUserDao<Responsable> {

    private Connection connection;
    private PreparedStatement prepared;
    private UtilisateurDao udao;

    public ResponsableDao() {
        connection = DataSource.getInstance().getConnection();
        udao = new UtilisateurDao();
    }

    @Override
    public boolean add(Responsable t) {
        if (!udao.add(t)) {
            return false;
        } else {
            String req = "insert into responsable (id, nom, prenom) values (?,?,?)";
            try {
                prepared = connection.prepareStatement(req);

                prepared.setInt(1, udao.findByEmail(t.getEmail()).getId());
                prepared.setString(2, t.getNom());
                prepared.setString(3, t.getPrenom());
                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ResponsableDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    @Override
    public boolean update(Responsable t) {
        return udao.update(t);
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from responsable where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            udao.removeById(id);
            System.out.println("responsable supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Responsable> findAll() {
        List<Responsable> listeResponsable = new ArrayList<>();
        String requete = "select * from responsable";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Responsable responsable = new Responsable();
                Utilisateur u = udao.findById(resultat.getInt(1));
                responsable.setUtilisateur(u);
                responsable.setId(resultat.getInt(1));
                responsable.setNom(resultat.getString(2));
                responsable.setPrenom(resultat.getString(3));
                responsable.setUsername(u.getUsername());
                responsable.setEmail(u.getEmail());
                listeResponsable.add(responsable);
            }
            return listeResponsable;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des responsables " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Responsable findById(int id) {
        Responsable responsable = new Responsable();
        String requete = "select * from responsable where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                Utilisateur u = udao.findById(id);
                responsable.setUtilisateur(u);
                responsable.setId(id);
                responsable.setNom(resultat.getString(2));
                responsable.setPrenom(resultat.getString(3));
                responsable.setUsername(u.getUsername());
                responsable.setEmail(u.getEmail());
            }
            return responsable;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    public Responsable login(String login, String password) {
        Utilisateur u = udao.login(login, password, "responsable");
        if (u != null) {
            String requete = "select * from responsable where id=?";
            try {
                prepared = connection.prepareStatement(requete);
                prepared.setInt(1, u.getId());
                ResultSet resultat = prepared.executeQuery();
                if (resultat.next()) {
                    Responsable responsable = new Responsable();
                    responsable.setUtilisateur(u);
                    responsable.setId(u.getId());
                    responsable.setNom(resultat.getString(2));
                    responsable.setPrenom(resultat.getString(3));
                    return responsable;
                }

            } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche  " + ex.getMessage());
            }
        }
        return null;
    }
}
