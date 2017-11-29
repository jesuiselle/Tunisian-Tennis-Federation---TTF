/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Fan;
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
public class FanDao implements iUserDao<Fan> {

    private Connection connection;
    private PreparedStatement prepared;
    private UtilisateurDao udao;

    public FanDao() {
        connection = DataSource.getInstance().getConnection();
        udao = new UtilisateurDao();
    }

    @Override
    public boolean add(Fan t) {
        if (!udao.add(t)) {
            return false;
        } else {
            String req = "insert into fan (id, nom, prenom) values (?,?,?)";
            try {
                prepared = connection.prepareStatement(req);

                prepared.setInt(1, udao.findByEmail(t.getEmail()).getId());
                prepared.setString(2, t.getNom());
                prepared.setString(3, t.getPrenom());
                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(FanDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    @Override
    public boolean update(Fan t) {

        if (!udao.update(t)) {
            return false;
        } else {
            String req = "UPDATE `fan` SET `nom`=?,`prenom`=? WHERE id=?";
            try {
                prepared = connection.prepareStatement(req);

                prepared.setString(1, t.getNom());
                prepared.setString(2, t.getPrenom());
                prepared.setInt(5, t.getId());

                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(FanDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from fan where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            udao.removeById(id);
            System.out.println("fan supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Fan> findAll() {
        List<Fan> listeFan = new ArrayList<>();
        String requete = "select * from fan";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Fan fan = new Fan();
                Utilisateur u = udao.findById(resultat.getInt(1));
                fan.setUtilisateur(u);
                fan.setId(resultat.getInt(1));
                fan.setNom(resultat.getString(2));
                fan.setPrenom(resultat.getString(3));
                fan.setEmail(u.getEmail());
                fan.setUsername(u.getUsername());
                listeFan.add(fan);
            }
            return listeFan;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des fans " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Fan findById(int id) {

        Fan fan = new Fan();
        String requete = "select * from fan where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                Utilisateur u = udao.findById(id);
                fan.setUtilisateur(u);
                fan.setId(id);
                fan.setNom(resultat.getString(2));
                fan.setPrenom(resultat.getString(3));
                fan.setEmail(u.getEmail());
                fan.setUsername(u.getUsername());
            }
            return fan;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    public Fan login(String login, String password) {
        Utilisateur u = udao.login(login, password, "fan");
        if (u != null) {
            String requete = "select * from fan where id=?";
            try {
                prepared = connection.prepareStatement(requete);
                prepared.setInt(1, u.getId());
                ResultSet resultat = prepared.executeQuery();
                if (resultat.next()) {
                    Fan fan = new Fan();
                    fan.setUtilisateur(u);
                    fan.setId(u.getId());
                    fan.setNom(resultat.getString(2));
                    fan.setPrenom(resultat.getString(3));
                    return fan;
                }

            } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche  " + ex.getMessage());
            }
        }
        return null;
    }
}
