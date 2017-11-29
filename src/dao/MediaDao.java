/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Media;

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

public class MediaDao implements interfaceDao<Media> {

    private Connection connection;

    public MediaDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Media media) {
        try {
            String req = "INSERT INTO `media`( `genre`, `titre`, `source`) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, media.getGenre());
            ps.setString(2, media.getTitre());
            ps.setString(3, media.getSource());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MediaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Media media) {
        String requete = "UPDATE `media` SET `genre`=?,`titre`=?,`source`=? WHERE id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, media.getGenre());
            ps.setString(2, media.getTitre());
            ps.setString(3, media.getSource());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "DELETE FROM `media` WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Media supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Media> findAll() {
        List<Media> listeMedia = new ArrayList<>();

        String requete = "select * from media";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Media media = new Media();
                media.setId(resultat.getInt(1));
                media.setGenre(resultat.getString(2));
                media.setTitre(resultat.getString(3));
                media.setSource(resultat.getString(4));

                listeMedia.add(media);
            }
            return listeMedia;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des medias " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Media findById(int id) {
        Media media = new Media();
        String requete = "SELECT * FROM `media`  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                media.setId(resultat.getInt(1));
                media.setGenre(resultat.getString(2));
                media.setTitre(resultat.getString(3));
                media.setSource(resultat.getString(4));

            }
            return media;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du media " + ex.getMessage());
            return null;
        }
    }
    
    
    public List<Media> findAllByGenre() {
        List<Media> listeMedia = new ArrayList<>();

        String requete;
        requete = "select * from media where genre='Image'";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Media media = new Media();
                //media.setId(resultat.getInt(1));
                //media.setGenre(resultat.getString(2));
                //media.setTitre(resultat.getString(3));
                media.setSource(resultat.getString(4));

                listeMedia.add(media);
            }
            return listeMedia;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des medias " + ex.getMessage());
            return null;
        }
    }
    
    public List<Media> findAllByGenreV() {
        List<Media> listeMedia = new ArrayList<>();

        String requete;
        requete = "select * from media where genre='Video'";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Media media = new Media();
                //media.setId(resultat.getInt(1));
                //media.setGenre(resultat.getString(2));
                //media.setTitre(resultat.getString(3));
                media.setSource(resultat.getString(4));

                listeMedia.add(media);
            }
            return listeMedia;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des medias " + ex.getMessage());
            return null;
        }
    }

    }



