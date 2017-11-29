/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medecin;
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
public class MedecinDao implements iUserDao<Medecin> {

    private Connection connection;
    private PreparedStatement prepared;
    private UtilisateurDao udao;

    public MedecinDao() {
        connection = DataSource.getInstance().getConnection();
        udao = new UtilisateurDao();
    }

    @Override
    public boolean add(Medecin t) {
        if (!udao.add(t)) {
            return false;
        } else {
            String req = "insert into medecin (id, nom, prenom,image) values (?,?,?,?)";
            try {
                prepared = connection.prepareStatement(req);

                prepared.setInt(1, udao.findByEmail(t.getEmail()).getId());
                prepared.setString(2, t.getNom());
                prepared.setString(3, t.getPrenom());
                prepared.setString(4, t.getImage());
                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    @Override
    public boolean update(Medecin t) {
        if (!udao.update(t)) {
            return false;
        } else {
            String req = "UPDATE `medecin` SET `nom`=?,`prenom`=?,`image`=? where id=?";
            try {
                prepared = connection.prepareStatement(req);

                prepared.setString(1, t.getNom());
                prepared.setString(2, t.getPrenom());
                prepared.setString(3, t.getImage());
                prepared.setInt(4, t.getId());

                prepared.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from medecin where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            udao.removeById(id);
            System.out.println("medecin supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Medecin> findAll() {
        List<Medecin> listeMedecin = new ArrayList<>();
        String requete = "select * from medecin";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Medecin medecin = new Medecin();
                Utilisateur u = udao.findById(resultat.getInt(1));
                medecin.setUtilisateur(u);
                medecin.setId(resultat.getInt(1));
                medecin.setNom(resultat.getString(2));
                medecin.setPrenom(resultat.getString(3));
                medecin.setImage(resultat.getString(4));
                medecin.setUsername(u.getUsername());
                medecin.setEmail(u.getEmail());
                listeMedecin.add(medecin);
            }
            return listeMedecin;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des medecins " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Medecin findById(int id) {
        Medecin medecin = new Medecin();
        String requete = "select * from medecin where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                Utilisateur u = udao.findById(id);
                medecin.setUtilisateur(u);
                medecin.setId(id);
                medecin.setNom(resultat.getString(2));
                medecin.setPrenom(resultat.getString(3));
                medecin.setImage(resultat.getString(4));
                medecin.setUsername(u.getUsername());
                medecin.setEmail(u.getEmail());
            }
            return medecin;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    public Medecin login(String login, String password) {
        Utilisateur u = udao.login(login, password, "medecin");
        if (u != null) {
            String requete = "select * from medecin where id=?";
            try {
                prepared = connection.prepareStatement(requete);
                prepared.setInt(1, u.getId());
                ResultSet resultat = prepared.executeQuery();
                if (resultat.next()) {
                    Medecin medecin = new Medecin();
                    medecin.setUtilisateur(u);
                    medecin.setId(u.getId());
                    medecin.setNom(resultat.getString(2));
                    medecin.setPrenom(resultat.getString(3));
                    medecin.setImage(resultat.getString(4));
                    medecin.setUsername(u.getUsername());
                    medecin.setEmail(u.getEmail());
                    return medecin;
                }

            } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche  " + ex.getMessage());
            }
        }
        return null;
    }
}
