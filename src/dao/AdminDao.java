/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utils.DataSource;
import entities.Administrateur;
import entities.Utilisateur;
import idao.iUserDao;
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
public class AdminDao implements iUserDao<Administrateur> {

    private Connection connection;
    private PreparedStatement prepared;
    private UtilisateurDao udao;

    public AdminDao() {
        connection = DataSource.getInstance().getConnection();
        udao = new UtilisateurDao();
    }

    @Override
    public boolean add(Administrateur t) {
        if (!udao.add(t)) {
            return false;
        }
        else{
            String req = "insert into administrateur (id, nom, prenom) values (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setInt(1, udao.findByEmail(t.getEmail()).getId());
            prepared.setString(2, t.getNom());
            prepared.setString(3, t.getPrenom());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
        }
        
    }

    @Override
    public boolean update(Administrateur t) {
        return udao.update(t);
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from administrateur where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            udao.removeById(id);
            System.out.println("administrateur supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Administrateur> findAll() {
        List<Administrateur> listeAdmin = new ArrayList<>();
        String requete = "select * from administrateur";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Administrateur admin = new Administrateur() ;
                admin.setUtilisateur(udao.findById(resultat.getInt(1)));
                admin.setNom(resultat.getString(2));
                admin.setPrenom(resultat.getString(3));
                listeAdmin.add(admin);
            }
            return listeAdmin;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des admin " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Administrateur findById(int id) {
        Administrateur admin = new Administrateur() ;
        admin.setUtilisateur(udao.findById(id));
        String requete = "select * from administrateur where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                admin.setNom(resultat.getString(2));
                admin.setPrenom(resultat.getString(3));
            }
            return admin;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    }

    public Administrateur login(String login, String password) {
        Utilisateur u = udao.login(login, password, "administrateur");
        Administrateur admin = null;
        if (u != null) {
            String requete = "select * from administrateur where id=?";
            try {
                prepared = connection.prepareStatement(requete);
                prepared.setInt(1, u.getId());
                ResultSet resultat = prepared.executeQuery();
                if (resultat.next()) {
                    admin = new Administrateur();
                    admin.setUtilisateur(u);
                    admin.setId(u.getId());
                    admin.setNom(resultat.getString(2));
                    admin.setPrenom(resultat.getString(3));
                }

            } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche  " + ex.getMessage());
            }
        }
        System.out.println(admin);
        return admin;
    }
}
