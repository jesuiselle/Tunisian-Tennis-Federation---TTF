/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Fan;
import entities.Partie;
import entities.Ticket;
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
public class TicketDao implements interfaceDao<Ticket> {

    private Connection connection;
    private PreparedStatement prepared;
    
    interfaceDao<Partie> mdao = new MatchDao();
    iUserDao<Fan> fdao = new FanDao();

    public TicketDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Ticket t) {
        String req = "insert into ticket (etat, id_match,id_fan) values (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setBoolean(1, t.getEtat());
            prepared.setInt(2, t.getPartie().getId());
            prepared.setInt(3, t.getFan().getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TicketDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Ticket t) {

        String req = "update ticket set etat =? where id=?";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setBoolean(1, t.getEtat());
            prepared.setInt(2, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TicketDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeById(int id) {
        String requete = "delete from ticket where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("ticket a ete supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> listeTicket = new ArrayList<>();
        String requete = "select * from ticket";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultat.getInt(1));
                ticket.setPartie(mdao.findById(resultat.getInt(2)));
                ticket.setFan(fdao.findById(resultat.getInt(3)));
                ticket.setEtat(resultat.getBoolean(4));
                listeTicket.add(ticket);
            }
            return listeTicket;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des tickets " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Ticket findById(int id) {

        Ticket ticket = new Ticket();
        String requete = "select * from ticket where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                ticket.setId(resultat.getInt(1));
                ticket.setPartie(mdao.findById(resultat.getInt(2)));
                ticket.setFan(fdao.findById(resultat.getInt(3)));
                ticket.setEtat(resultat.getBoolean(4));
            }
            return ticket;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
