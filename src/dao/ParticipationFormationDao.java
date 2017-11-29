/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Arbitre;
import entities.Formation;
import entities.ParticipationFormation;
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
public class ParticipationFormationDao implements interfaceDao<ParticipationFormation> {

    private Connection connection;
    private PreparedStatement prepared;
    
    iUserDao<Arbitre> adao = new ArbitreDao();
    interfaceDao<Formation> fdao = new FormationDao();

    public ParticipationFormationDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(ParticipationFormation t) {
        String req = "insert into participation_formation (etat,id_arbitre,id_formation) values (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);
            prepared.setBoolean(1, t.getEtat());
            prepared.setInt(2, t.getArbitre().getId());
            prepared.setInt(3, t.getFormation().getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationFormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(ParticipationFormation t) {
        String req = "UPDATE `participation_formation` SET `etat`=?,`id_arbitre`=?,`id_formation`=? where id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setBoolean(1, t.getEtat());
            prepared.setInt(2, t.getArbitre().getId());
            prepared.setInt(3, t.getFormation().getId());
            prepared.setInt(4, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationFormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from participation_formation where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("formation a ete  supprime  supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<ParticipationFormation> findAll() {
        List<ParticipationFormation> listeParFor = new ArrayList<>();
        String requete = "select * from participation_formation";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                ParticipationFormation pf = new ParticipationFormation();
                pf.setId(resultat.getInt(1));
                pf.setArbitre(adao.findById(resultat.getInt(2)));
                pf.setFormation(fdao.findById(resultat.getInt(3)));
                pf.setEtat(resultat.getBoolean(4));
                listeParFor.add(pf);
            }
            return listeParFor;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement liste de participation formation  " + ex.getMessage());
            return null;
        }

    }

    @Override
    public ParticipationFormation findById(int id) {
        ParticipationFormation pf = new ParticipationFormation();
        String requete = "select * from participation_formation where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                pf.setId(resultat.getInt(1));
                pf.setArbitre(adao.findById(resultat.getInt(2)));
                pf.setFormation(fdao.findById(resultat.getInt(3)));
                pf.setEtat(resultat.getBoolean(4));
            }
            return pf;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
