/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.CompteRenduTest;
import entities.Joueur;
import entities.Medecin;
import entities.Responsable;
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
 * @author Aydi
 */
public class CompteRenduTestDao implements interfaceDao<CompteRenduTest> {

    private Connection connection;
    private PreparedStatement prepared;

    iUserDao<Joueur> jdao = new JoueurDao();
    iUserDao<Responsable> rdao = new ResponsableDao();
    iUserDao<Medecin> mdao = new MedecinDao();

    public CompteRenduTestDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(CompteRenduTest t) {
        String req
                = "insert into compte_rendu_test (description, resultat,id_joueur,id_medecin,etat) values (?,?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDescription());
            prepared.setBoolean(2, t.getResultat());
            prepared.setInt(3, t.getJoueur().getId());
            prepared.setInt(4, t.getMedecin().getId());
            prepared.setBoolean(5, t.getEtat());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CompteRenduTestDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(CompteRenduTest t) {

        String req = "update compte_rendu_test set description =? where id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDescription());
            prepared.setInt(2, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CompteRenduTestDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from compte_rendu_test where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("compte rendu test supprime  supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<CompteRenduTest> findAll() {
        List<CompteRenduTest> listecrt = new ArrayList<>();
        String requete = "select * from compte_rendu_test";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CompteRenduTest crt = new CompteRenduTest();
                crt.setId(resultat.getInt(1));
                crt.setMedecin(mdao.findById(resultat.getInt(2)));
                crt.setResponsable(rdao.findById(resultat.getInt(3)));
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

    @Override
    public CompteRenduTest findById(int id) {
        CompteRenduTest crt = new CompteRenduTest();
        String requete = "select * from compte_rendu_test where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                crt.setId(resultat.getInt(1));
                crt.setMedecin(mdao.findById(resultat.getInt(2)));
                crt.setResponsable(rdao.findById(resultat.getInt(3)));
                crt.setJoueur(jdao.findById(resultat.getInt(4)));
                crt.setDescription(resultat.getString(5));
                crt.setResultat(resultat.getBoolean(6));
                crt.setEtat(resultat.getBoolean(7));

            }
            return crt;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    public List<CompteRenduTest> findAllForPub() {

        List<CompteRenduTest> listecrt = new ArrayList<>();
        String requete = "select * from compte_rendu_test where etat=0";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CompteRenduTest crt = new CompteRenduTest();
                crt.setId(resultat.getInt(1));
                crt.setMedecin(mdao.findById(resultat.getInt(2)));
                crt.setResponsable(rdao.findById(resultat.getInt(3)));
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
    
    public List<CompteRenduTest> findAllResultatTest() {

        List<CompteRenduTest> listecrt = new ArrayList<>();
        String requete = "select * from compte_rendu_test where id_joueur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, Utils.joueur.getId());
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                CompteRenduTest crt = new CompteRenduTest();
                crt.setMedecin(mdao.findById(resultat.getInt(2)));
                crt.setDescription(resultat.getString(5));
                crt.setResultat(resultat.getBoolean(6));
                
                listecrt.add(crt);
            }
            return listecrt;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }
    
    public List<CompteRenduTest> findAllMedecinCRTA() {

         List<CompteRenduTest> listecrt = new ArrayList<>();
        String requete = "select * from compte_rendu_test";
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
    
     public void updateEtat(CompteRenduTest t) {

        String req = "update compte_rendu_test set etat=1, id_responsable=? where id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setInt(1, t.getResponsable().getId());
            prepared.setInt(2, t.getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CompteRenduTestDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
