/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Formation;
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
public class FormationDao implements interfaceDao<Formation> {

    private Connection connection;
    private PreparedStatement prepared;

    public FormationDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Formation t) {
        String req = "insert into formation (date_debut, date_fin,description) values (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDateDebut());
            prepared.setString(2, t.getDateFin());
            prepared.setString(3, t.getDescription());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Formation t) {

        String req = "update formation set date_debut=?, date_fin=?, description=? where id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDateDebut());
            prepared.setString(2, t.getDateFin());
            prepared.setString(3, t.getDescription());
            prepared.setInt(4, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {

        String requete = "delete from formation where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("formation supprime  supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Formation> findAll() {
        List<Formation> listeFormation = new ArrayList<>();
        String requete = "select * from formation";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Formation formation = new Formation();
                formation.setId(resultat.getInt(1));
                formation.setDateDebut(resultat.getString(2));
                formation.setDateFin(resultat.getString(3));
                formation.setDescription(resultat.getString(4));
                listeFormation.add(formation);
            }
            return listeFormation;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des formations " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Formation findById(int id) {

        Formation formation = new Formation();
        String requete = "select * from formation where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                formation.setId(resultat.getInt(1));
                formation.setDateDebut(resultat.getString(2));
                formation.setDateFin(resultat.getString(3));
                formation.setDescription(resultat.getString(4));
            }
            return formation;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
