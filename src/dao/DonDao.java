/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Club;
import entities.Don;
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
public class DonDao implements interfaceDao<Don> {

    private Connection connection;
    private PreparedStatement prepared;

    interfaceDao<Club> cdao = new ClubDao();

    public DonDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Don t) {
        String req = "insert into don (date_don, description,id_club) values (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDateDon());
            prepared.setString(2, t.getDescription());
            prepared.setInt(3, t.getClub().getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Don t) {
        String req = "UPDATE `don` SET `date_don`=?,`description`=?,`id_club`=? WHERE id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDateDon());
            prepared.setString(2, t.getDescription());
            prepared.setInt(3, t.getClub().getId());
            prepared.setInt(4, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from don where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("don supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Don> findAll() {
        List<Don> listeDon = new ArrayList<>();
        String requete = "select * from don";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Don don = new Don();
                don.setId(resultat.getInt(1));
                don.setClub(cdao.findById(resultat.getInt(2)));
                don.setDateDon(resultat.getString(3));
                don.setDescription(resultat.getString(4));
                listeDon.add(don);
            }
            return listeDon;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des don " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Don findById(int id) {
        Don don = new Don();
        String requete = "select * from don where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                don.setId(resultat.getInt(1));
                don.setClub(cdao.findById(resultat.getInt(2)));
                don.setDateDon(resultat.getString(3));
                don.setDescription(resultat.getString(4));
            }
            return don;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
