/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Message;
import idao.interfaceDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author L
 */
public class MessageDao implements interfaceDao<Message> {

    private Connection connection;
    private PreparedStatement prepared;


    public MessageDao() {
        connection = DataSource.getInstance().getConnection();

    }
    
    
    @Override
    public void add(Message t){
        
        String req = "INSERT INTO `message`(`nom`, `email`, `message`) VALUES (?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setString(2, t.getNom());
            prepared.setString(3, t.getEmail());
            prepared.setString(4, t.getMessage());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> findAll() {
       List<Message> listeMessage = new ArrayList<>();
        String requete = "select * from message";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Message message = new Message();
                message.setId(resultat.getInt(1));
                message.setNom(resultat.getString(2));
                message.setEmail(resultat.getString(3));
                message.setMessage(resultat.getString(4));
                listeMessage.add(message);
            }
            return listeMessage;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des formations " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Message findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
