/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Arbitre;
import entities.Evenement;
import entities.Joueur;
import entities.Partie;
import entities.Stade;
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
 * @author 
 */
public class MatchDao implements interfaceDao<Partie> {

    private Connection connection;
    private PreparedStatement prepared;
    
    interfaceDao<Stade> sdao = new StadeDao();
    interfaceDao<Evenement> edao = new EvenementDao();
    iUserDao<Arbitre> adao = new ArbitreDao();
    iUserDao<Joueur> jdao = new JoueurDao();
    

    public MatchDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Partie t) {
        String req = "INSERT INTO `partie`(`id_stade`, `id_evenement`, `id_arbitre`, `id_joueur_trois`,"
                + " `id_joueur_quatre`, `id_joueur_un`, `id_joueur_deux`, `description`, `genre`, `type`, `score`,"
                + " `set_un`, `set_deux`, `set_trois`, `set_quatre`, `set_cinq`, `date_match`, `lien`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);
            prepared.setInt(1, t.getStade().getId());
            prepared.setInt(2, t.getEvenement().getId());
            prepared.setInt(3, t.getArbitre().getId());
            if(t.getJoueur2() != null){
                prepared.setInt(4, t.getJoueur2().getId());
            }else{
                prepared.setObject(4, null);
            }
            if(t.getJoueur3() != null){
                prepared.setInt(5, t.getJoueur3().getId());
            }else{
                prepared.setObject(5, null);
            }
            prepared.setInt(6, t.getJoueur().getId());
            prepared.setInt(7, t.getJoueur1().getId());
            prepared.setString(8, t.getDescription());
            prepared.setString(9, t.getGenre());
            prepared.setString(10, t.getType());
            prepared.setString(11, t.getScore());
            prepared.setString(12, t.getSetUn());
            prepared.setString(13, t.getSetDeux());
            prepared.setString(14, t.getSetTrois());
            prepared.setString(15, t.getSetQuatre());
            prepared.setString(16, t.getSetCinq());
            prepared.setString(17, t.getDateMatch());
            prepared.setString(18, t.getLien());
            
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Partie t) {
        String req = "UPDATE `partie` SET `id_stade`=?,`id_evenement`=?,`id_arbitre`=?,`id_joueur_trois`=?,"
                + "`id_joueur_quatre`=?,`id_joueur_un`=?,`id_joueur_deux`=?,`description`=?,`genre`=?,"
                + "`type`=?,`score`=?,`set_un`=?,`set_deux`=?,`set_trois`=?,`set_quatre`=?,`set_cinq`=?,"
                + "`date_match`=?,`lien`=? where id=?";
        try {
            prepared = connection.prepareStatement(req);
            prepared.setInt(1, t.getStade().getId());
            prepared.setInt(2, t.getEvenement().getId());
            prepared.setInt(3, t.getArbitre().getId());
            if(t.getJoueur2() != null){
                prepared.setInt(4, t.getJoueur2().getId());
            }else{
                prepared.setObject(4, null);
            }
            if(t.getJoueur3() != null){
                prepared.setInt(5, t.getJoueur3().getId());
            }else{
                prepared.setObject(5, null);
            }
            prepared.setInt(6, t.getJoueur().getId());
            prepared.setInt(7, t.getJoueur1().getId());
            prepared.setString(8, t.getDescription());
            prepared.setString(9, t.getGenre());
            prepared.setString(10, t.getType());
            prepared.setString(11, t.getScore());
            prepared.setString(12, t.getSetUn());
            prepared.setString(13, t.getSetDeux());
            prepared.setString(14, t.getSetTrois());
            prepared.setString(15, t.getSetQuatre());
            prepared.setString(16, t.getSetCinq());
            prepared.setString(17, t.getDateMatch());
            prepared.setString(18, t.getLien());
            prepared.setInt(19, t.getId());

            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeById(int id) {
        String requete = "delete from partie where id=?";
        try {
            prepared = connection.prepareStatement(requete);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            System.out.println("match supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    @Override
    public List<Partie> findAll() {
        List<Partie> listeMatch = new ArrayList<>();
        String requete = "select * from partie";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Partie partie = new Partie();
                partie.setId(resultat.getInt(1));
                partie.setStade(sdao.findById(resultat.getInt(2)));
                partie.setEvenement(edao.findById(resultat.getInt(3)));
                partie.setArbitre(adao.findById(resultat.getInt(4)));
                partie.setJoueur2(jdao.findById(resultat.getInt(5)));
                partie.setJoueur3(jdao.findById(resultat.getInt(6)));
                partie.setJoueur(jdao.findById(resultat.getInt(7)));
                partie.setJoueur1(jdao.findById(resultat.getInt(8)));
                partie.setDescription(resultat.getString(9));
                partie.setGenre(resultat.getString(10));
                partie.setType(resultat.getString(11));
                partie.setScore(resultat.getString(12));
                partie.setSetUn(resultat.getString(13));
                partie.setSetDeux(resultat.getString(14));
                partie.setSetTrois(resultat.getString(15));
                partie.setSetQuatre(resultat.getString(16));
                partie.setSetCinq(resultat.getString(17));
                partie.setDateMatch(resultat.getString(18));
                partie.setLien(resultat.getString(19));
                listeMatch.add(partie);
            }
            return listeMatch;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des matches" + ex.getMessage());
            return null;
        }

    }

    @Override
    public Partie findById(int id) {
        Partie partie = new Partie();
        String requete = "select * from partie where id="+id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                
                partie.setId(resultat.getInt(1));
                partie.setStade(sdao.findById(resultat.getInt(2)));
                partie.setEvenement(edao.findById(resultat.getInt(3)));
                partie.setArbitre(adao.findById(resultat.getInt(4)));
                partie.setJoueur2(jdao.findById(resultat.getInt(5)));
                partie.setJoueur3(jdao.findById(resultat.getInt(6)));
                partie.setJoueur(jdao.findById(resultat.getInt(7)));
                partie.setJoueur1(jdao.findById(resultat.getInt(8)));
                partie.setDescription(resultat.getString(9));
                partie.setGenre(resultat.getString(10));
                partie.setType(resultat.getString(11));
                partie.setScore(resultat.getString(12));
                partie.setSetUn(resultat.getString(13));
                partie.setSetDeux(resultat.getString(14));
                partie.setSetTrois(resultat.getString(15));
                partie.setSetQuatre(resultat.getString(16));
                partie.setSetCinq(resultat.getString(17));
                partie.setDateMatch(resultat.getString(18));
                partie.setLien(resultat.getString(19));
            }
            return partie;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche match " + ex.getMessage());
            return null;
        }

    }
    
    public List<Partie> findByIdJoueur(int id) {
        List<Partie> listeMatch = new ArrayList<>();
        String requete = "select * from partie where id_joueur_un=" + id + 
                " or id_joueur_deux=" + id;
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Partie partie = new Partie();
                partie.setId(resultat.getInt(1));
                partie.setStade(sdao.findById(resultat.getInt(2)));
                partie.setEvenement(edao.findById(resultat.getInt(3)));
                partie.setArbitre(adao.findById(resultat.getInt(4)));
                partie.setJoueur2(jdao.findById(resultat.getInt(5)));
                partie.setJoueur3(jdao.findById(resultat.getInt(6)));
                partie.setJoueur(jdao.findById(resultat.getInt(7)));
                partie.setJoueur1(jdao.findById(resultat.getInt(8)));
                partie.setDescription(resultat.getString(9));
                partie.setGenre(resultat.getString(10));
                partie.setType(resultat.getString(11));
                partie.setScore(resultat.getString(12));
                partie.setSetUn(resultat.getString(13));
                partie.setSetDeux(resultat.getString(14));
                partie.setSetTrois(resultat.getString(15));
                partie.setSetQuatre(resultat.getString(16));
                partie.setSetCinq(resultat.getString(17));
                partie.setDateMatch(resultat.getString(18));
                partie.setLien(resultat.getString(19));
                listeMatch.add(partie);
            }
            return listeMatch;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des matches" + ex.getMessage());
            return null;
        }

    }

}
