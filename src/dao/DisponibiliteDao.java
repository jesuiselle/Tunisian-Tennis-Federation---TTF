/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Arbitre;
import entities.Disponibilite;
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
public class DisponibiliteDao implements interfaceDao<Disponibilite> {

    private Connection connection;
    private PreparedStatement prepared;
    
    iUserDao<Arbitre> adao = new ArbitreDao();

    public DisponibiliteDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Disponibilite t) {
        String req = "insert into disponibilite (date, time,etat,id_arbitre) values (?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDate());
            prepared.setString(2, t.getTime());
            prepared.setBoolean(3, t.getEtat());
            prepared.setInt(4, t.getArbitre().getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DisponibiliteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Disponibilite t) {
        String req = "UPDATE `disponibilite` SET `date`=?,`time`=?,`etat`=?,`id_arbitre`=? WHERE id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(1, t.getDate());
            prepared.setString(2, t.getTime());
            prepared.setBoolean(3, t.getEtat());
            prepared.setInt(4, t.getArbitre().getId());
            prepared.setInt(5, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DisponibiliteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from disponibilite where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("disponiblite supprime  supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Disponibilite> findAll() {
        List<Disponibilite> listedisp = new ArrayList<>();
        String requete = "select * from disponibilite";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Disponibilite disp = new Disponibilite();
                disp.setId(resultat.getInt(1));
                disp.setArbitre(adao.findById(resultat.getInt(2)));
                disp.setDate(resultat.getString(3));
                disp.setTime(resultat.getString(4));
                disp.setEtat(resultat.getBoolean(5));
                listedisp.add(disp);
            }
            return listedisp;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement diponibilite " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Disponibilite findById(int id) {
        Disponibilite disp = new Disponibilite();
        String requete = "select * from disponibilite where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                disp.setId(resultat.getInt(1));
                disp.setArbitre(adao.findById(resultat.getInt(2)));
                disp.setDate(resultat.getString(3));
                disp.setTime(resultat.getString(4));
                disp.setEtat(resultat.getBoolean(5));
            }
            return disp;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
