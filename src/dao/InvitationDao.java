/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.CompteRenduTest;
import entities.Invitation;
import entities.Joueur;
import entities.Medecin;
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
import utils.Utils;

/**
 *
 * @author Bouchriha
 */
public class InvitationDao implements interfaceDao<Invitation> {

    private Connection connection;
    private PreparedStatement prepared;

    iUserDao<Medecin> mdao = new MedecinDao();
    iUserDao<Joueur> jdao = new JoueurDao();

    public InvitationDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Invitation t) {
        String req = "insert into invitation (date_invitation,id_joueur,id_medecin) values (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDateInvitation());
            prepared.setInt(2, t.getJoueur().getId());
            prepared.setInt(3, t.getMedecin().getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InvitationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Invitation t) {
        String req = "UPDATE `invitation` SET `date_invitation`=?,`id_joueur`=?,`id_medecin`=? WHERE id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDateInvitation());
            prepared.setInt(2, t.getJoueur().getId());
            prepared.setInt(3, t.getMedecin().getId());
            prepared.setInt(4, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InvitationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from invitation where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("invitation supprime  supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Invitation> findAll() {
        List<Invitation> listeInvitation = new ArrayList<>();
        String requete = "select * from invitation";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Invitation invit = new Invitation();
                invit.setId(resultat.getInt(1));
                invit.setJoueur(jdao.findById(resultat.getInt(2)));
                invit.setMedecin(mdao.findById(resultat.getInt(3)));
                invit.setDateInvitation(resultat.getString(4));
                listeInvitation.add(invit);
            }
            return listeInvitation;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des invitations " + ex.getMessage());
            return null;
        }

    }
    
    public List<CompteRenduTest> findAllMedecinCRTA() {

         List<CompteRenduTest> listecrt = new ArrayList<>();
        String requete = "select * from compte_rendu_test where etat=0";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CompteRenduTest crt = new CompteRenduTest();
                crt.setJoueur(jdao.findById(resultat.getInt(4)));
                crt.setDescription(resultat.getString(5));
                crt.setResultat(resultat.getBoolean(6));
                crt.setEtat(resultat.getBoolean(7));

                listecrt.add(crt);
            }
            return listecrt;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }
    
    
         public List<Invitation> findAllRecentInvitation(){
        
             List<Invitation> listeInvitation = new ArrayList<>();
             String requete = "select * from invitation where id_joueur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, Utils.joueur.getId());
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Invitation recentinvitationtest = new Invitation();
                recentinvitationtest.setMedecin(mdao.findById(resultat.getInt(3)));
                recentinvitationtest.setDateInvitation(resultat.getString(4));

                listeInvitation.add(recentinvitationtest);
            }
            return listeInvitation;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }
    
        public List<Invitation> findAllInvitation(){
        List<Invitation> listeInvitation = new ArrayList<>();
        String requete = "select * from invitation";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Invitation recentinvitationtest = new Invitation();
                recentinvitationtest.setJoueur(jdao.findById(resultat.getInt(2)));
                recentinvitationtest.setDateInvitation(resultat.getString(4));

                listeInvitation.add(recentinvitationtest);
            }
            return listeInvitation;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Invitation findById(int id) {
        Invitation invit = new Invitation();
        String requete = "select * from invitation where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                invit.setId(resultat.getInt(1));
                invit.setJoueur(jdao.findById(resultat.getInt(2)));
                invit.setMedecin(mdao.findById(resultat.getInt(3)));
                invit.setDateInvitation(resultat.getString(4));
            }
            return invit;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
