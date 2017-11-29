/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Arbitre;
import entities.CompteRenduMatch;
import entities.Partie;
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
public class CompteRenduMatchDao implements interfaceDao<CompteRenduMatch> {

    private Connection connection;
    private PreparedStatement prepared;
    
    iUserDao<Arbitre> adao = new ArbitreDao();
    interfaceDao<Partie> mdao = new MatchDao();

    public CompteRenduMatchDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(CompteRenduMatch t) {
        String req = "insert into compte_rendu_match (description, id_match,id_arbitre) values (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDescription());
            prepared.setInt(2, t.getPartie().getId());
            prepared.setInt(3, t.getArbitre().getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CompteRenduMatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(CompteRenduMatch t) {

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from compte_rendu_match where id=?";
        try {
            
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("compte rendu match supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<CompteRenduMatch> findAll() {
        List<CompteRenduMatch> listecrm = new ArrayList<>();
        String requete = "select * from compte_rendu_match where id_arbitre=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, Utils.arbitre.getId());
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                CompteRenduMatch crm = new CompteRenduMatch();
                crm.setId(resultat.getInt(1));
                crm.setPartie(mdao.findById(resultat.getInt(2)));
                crm.setDescription(resultat.getString(4));
                
                listecrm.add(crm);
            }
            return listecrm;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des compte rendu match " + ex.getMessage());
            return null;
        }
    }
    
    public List<CompteRenduMatch> findAllByArbitre() {

        List<CompteRenduMatch> listecrm = new ArrayList<>();
        String requete = "select * from compte_rendu_match where id_arbitre=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, Utils.arbitre.getId());
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                CompteRenduMatch crm = new CompteRenduMatch();
                crm.setPartie(mdao.findById(resultat.getInt(2)));
                crm.setDescription(resultat.getString(4));                
                listecrm.add(crm);
            }
            return listecrm;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }

    @Override
    public CompteRenduMatch findById(int id) {
        CompteRenduMatch crm = new CompteRenduMatch();
        String requete = "select * from compte_rendu_match where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                crm.setId(resultat.getInt(1));
                crm.setPartie(mdao.findById(resultat.getInt(2)));
                crm.setArbitre(adao.findById(resultat.getInt(3)));
                crm.setDescription(resultat.getString(4));
            }
            return crm;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    }

}
