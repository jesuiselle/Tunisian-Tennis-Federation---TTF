/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Utilisateur;
import idao.iUserDao;
import utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author Bouchriha
 */
public class UtilisateurDao implements iUserDao<Utilisateur> {

    private Connection connection;
    private PreparedStatement prepared;

    public UtilisateurDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public boolean add(Utilisateur t) {
        if ((Utils.isLoginUsed(t.getUsername())) || (Utils.isEmailUsed(t.getEmail()))) {
            return false;
        }
        String req = "INSERT INTO `utilisateur`(`username`, `username_canonical`, `email`, `email_canonical`,"
                + " `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`,"
                + " `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`,"
                + " `credentials_expire_at`, `type`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getUsername());
            prepared.setString(2, t.getUsername());
            prepared.setString(3, t.getEmail());
            prepared.setString(4, t.getEmail());
            prepared.setBoolean(5, true);
            prepared.setString(6, Utils.hashPassword(t.getPassword()).getKey());
            prepared.setString(7, Utils.hashPassword(t.getPassword()).getValue());
            prepared.setDate(8, null);
            prepared.setBoolean(9, t.getLocked());
            prepared.setBoolean(10, t.getExpired());
            prepared.setDate(11, null);
            prepared.setString(12, t.getConfirmationToken());
            prepared.setDate(13, null);
            prepared.setString(14, t.getRoles());
            prepared.setBoolean(15, t.getCredentialsExpired());
            prepared.setDate(16, null);
            prepared.setString(17, t.getType());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean update(Utilisateur t) {
        Utilisateur u = findById(t.getId());
        if (((!u.getUsername().equals(t.getUsername())) && (Utils.isLoginUsed(t.getUsername())))
                || ((!u.getEmail().equals(t.getEmail())) && (Utils.isEmailUsed(t.getEmail())))) {
            return false;
        }
        String req = "update utilisateur set password =?, username=?, `username_canonical`=?,"
                + " email=?, `email_canonical`=? where id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getPassword());
            prepared.setString(2, t.getUsername());
            prepared.setString(3, t.getUsername());
            prepared.setString(4, t.getEmail());
            prepared.setString(5, t.getEmail());
            prepared.setInt(6, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from utilisateur where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("utilisateur supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> listeUtilisateur = new ArrayList<>();
        String requete = "select * from utilisateur";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(resultat.getInt(1));
                utilisateur.setUsername(resultat.getString(2));
                utilisateur.setUsernameCanonical(resultat.getString(3));
                utilisateur.setEmail(resultat.getString(4));
                utilisateur.setEmailCanonical(resultat.getString(5));
                utilisateur.setEnabled(resultat.getBoolean(6));
                utilisateur.setSalt(resultat.getString(7));
                utilisateur.setPassword(resultat.getString(8));
                utilisateur.setLastLogin(resultat.getTimestamp(9));
                utilisateur.setLocked(resultat.getBoolean(10));
                utilisateur.setExpired(resultat.getBoolean(11));
                utilisateur.setExpiresAt(resultat.getTimestamp(12));
                utilisateur.setConfirmationToken(resultat.getString(13));
                utilisateur.setPasswordRequestedAt(resultat.getTimestamp(14));
                utilisateur.setRoles(resultat.getString(15));
                utilisateur.setCredentialsExpired(resultat.getBoolean(16));
                utilisateur.setCredentialsExpireAt(resultat.getTimestamp(17));
                utilisateur.setType(resultat.getString(18));
                listeUtilisateur.add(utilisateur);
            }
            return listeUtilisateur;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des utilisateurs " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Utilisateur findById(int id) {
        Utilisateur utilisateur = new Utilisateur();
        String requete = "select * from utilisateur where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                utilisateur.setId(resultat.getInt(1));
                utilisateur.setUsername(resultat.getString(2));
                utilisateur.setUsernameCanonical(resultat.getString(3));
                utilisateur.setEmail(resultat.getString(4));
                utilisateur.setEmailCanonical(resultat.getString(5));
                utilisateur.setEnabled(resultat.getBoolean(6));
                utilisateur.setSalt(resultat.getString(7));
                utilisateur.setPassword(resultat.getString(8));
                utilisateur.setLastLogin(resultat.getTimestamp(9));
                utilisateur.setLocked(resultat.getBoolean(10));
                utilisateur.setExpired(resultat.getBoolean(11));
                utilisateur.setExpiresAt(resultat.getTimestamp(12));
                utilisateur.setConfirmationToken(resultat.getString(13));
                utilisateur.setPasswordRequestedAt(resultat.getTimestamp(14));
                utilisateur.setRoles(resultat.getString(15));
                utilisateur.setCredentialsExpired(resultat.getBoolean(16));
                utilisateur.setCredentialsExpireAt(resultat.getTimestamp(17));
                utilisateur.setType(resultat.getString(18));
            }
            return utilisateur;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    public Utilisateur login(String email, String password, String type) {
        String requete = "select * from utilisateur where email=? and type=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setString(1, email);
            prepared.setString(2, type);
            ResultSet resultat = prepared.executeQuery();
            if (resultat.next()) {
                if (Utils.checkPassword(password, resultat.getString(8))) {
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setId(resultat.getInt(1));
                    utilisateur.setUsername(resultat.getString(2));
                    utilisateur.setUsernameCanonical(resultat.getString(3));
                    utilisateur.setEmail(resultat.getString(4));
                    utilisateur.setEmailCanonical(resultat.getString(5));
                    utilisateur.setEnabled(resultat.getBoolean(6));
                    utilisateur.setSalt(resultat.getString(7));
                    utilisateur.setPassword(resultat.getString(8));
                    utilisateur.setLastLogin(resultat.getTimestamp(9));
                    utilisateur.setLocked(resultat.getBoolean(10));
                    utilisateur.setExpired(resultat.getBoolean(11));
                    utilisateur.setExpiresAt(resultat.getTimestamp(12));
                    utilisateur.setConfirmationToken(resultat.getString(13));
                    utilisateur.setPasswordRequestedAt(resultat.getTimestamp(14));
                    utilisateur.setRoles(resultat.getString(15));
                    utilisateur.setCredentialsExpired(resultat.getBoolean(16));
                    utilisateur.setCredentialsExpireAt(resultat.getTimestamp(17));
                    utilisateur.setType(resultat.getString(18));
                    return utilisateur;
                }
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
        }
        return null;
    }

    public boolean searchByLogin(String username) {
        String requete = "select * from utilisateur where username=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setString(1, username);
            ResultSet resultat = prepared.executeQuery();
            if (resultat.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
        }
        return false;
    }

    public Utilisateur findByEmail(String email) {
        Utilisateur utilisateur = null;
        String requete = "select * from utilisateur where email=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setString(1, email);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setId(resultat.getInt(1));
                utilisateur.setUsername(resultat.getString(2));
                utilisateur.setUsernameCanonical(resultat.getString(3));
                utilisateur.setEmail(resultat.getString(4));
                utilisateur.setEmailCanonical(resultat.getString(5));
                utilisateur.setEnabled(resultat.getBoolean(6));
                utilisateur.setSalt(resultat.getString(7));
                utilisateur.setPassword(resultat.getString(8));
                utilisateur.setLastLogin(resultat.getTimestamp(9));
                utilisateur.setLocked(resultat.getBoolean(10));
                utilisateur.setExpired(resultat.getBoolean(11));
                utilisateur.setExpiresAt(resultat.getTimestamp(12));
                utilisateur.setConfirmationToken(resultat.getString(13));
                utilisateur.setPasswordRequestedAt(resultat.getTimestamp(14));
                utilisateur.setRoles(resultat.getString(15));
                utilisateur.setCredentialsExpired(resultat.getBoolean(16));
                utilisateur.setCredentialsExpireAt(resultat.getTimestamp(17));
                utilisateur.setType(resultat.getString(18));
            }
            return utilisateur;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    }

}
