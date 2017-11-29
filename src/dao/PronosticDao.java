/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Fan;
import entities.Joueur;
import entities.Partie;
import entities.Pronostic;
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
public class PronosticDao implements interfaceDao<Pronostic> {

    private Connection connection;
    private PreparedStatement prepared;

    interfaceDao<Partie> mdao = new MatchDao();
    iUserDao<Fan> fdao = new FanDao();
    iUserDao<Joueur> jdao = new JoueurDao();

    public PronosticDao() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Pronostic t) {

        String req = "INSERT INTO `pronostic`(`id_gagnant1`, `id_match5`, `id_gagnant5`, `id_match6`,"
                + " `id_gagnant6`, `id_match7`, `id_gagnant7`, `id_match8`, `id_gagnant8`, `id_match9`,"
                + " `id_gagnant9`, `id_match1`, `id_match10`, `id_gagnant10`, `id_match11`, `id_gagnant11`,"
                + " `id_match12`, `id_gagnant12`, `id_fan`, `id_match2`, `id_gagnant2`, `id_match3`,"
                + " `id_gagnant3`, `id_match4`, `id_gagnant4`)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            prepared = connection.prepareStatement(req);

            prepared.setInt(1, t.getJoueur().getId());
            prepared.setInt(2, t.getPartie4().getId());
            prepared.setInt(3, t.getJoueur4().getId());
            prepared.setInt(4, t.getPartie5().getId());
            prepared.setInt(5, t.getJoueur5().getId());
            prepared.setInt(6, t.getPartie6().getId());
            prepared.setInt(7, t.getJoueur6().getId());
            prepared.setInt(8, t.getPartie7().getId());
            prepared.setInt(9, t.getJoueur7().getId());
            prepared.setInt(10, t.getPartie8().getId());
            prepared.setInt(11, t.getJoueur8().getId());
            prepared.setInt(12, t.getPartie().getId());
            prepared.setInt(13, t.getPartie9().getId());
            prepared.setInt(14, t.getJoueur9().getId());
            prepared.setInt(15, t.getPartie10().getId());
            prepared.setInt(16, t.getJoueur10().getId());
            prepared.setInt(17, t.getPartie11().getId());
            prepared.setInt(18, t.getJoueur11().getId());
            prepared.setInt(19, t.getFan().getId());
            prepared.setInt(20, t.getPartie1().getId());
            prepared.setInt(21, t.getJoueur1().getId());
            prepared.setInt(22, t.getPartie2().getId());
            prepared.setInt(23, t.getJoueur2().getId());
            prepared.setInt(24, t.getPartie3().getId());
            prepared.setInt(25, t.getJoueur3().getId());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PronosticDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Pronostic t) {

    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public List<Pronostic> findAll() {
        List<Pronostic> listePronostic = new ArrayList<>();
        String requete = "select * from pronostic";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Pronostic pronostic = new Pronostic();
                pronostic.setId(resultat.getInt(1));
                pronostic.setJoueur(jdao.findById(resultat.getInt(2)));
                pronostic.setPartie4(mdao.findById(resultat.getInt(3)));
                pronostic.setJoueur4(jdao.findById(resultat.getInt(4)));
                pronostic.setPartie5(mdao.findById(resultat.getInt(5)));
                pronostic.setJoueur5(jdao.findById(resultat.getInt(6)));
                pronostic.setPartie6(mdao.findById(resultat.getInt(7)));
                pronostic.setJoueur6(jdao.findById(resultat.getInt(8)));
                pronostic.setPartie7(mdao.findById(resultat.getInt(9)));
                pronostic.setJoueur7(jdao.findById(resultat.getInt(10)));
                pronostic.setPartie8(mdao.findById(resultat.getInt(11)));
                pronostic.setJoueur8(jdao.findById(resultat.getInt(12)));
                pronostic.setPartie(mdao.findById(resultat.getInt(13)));
                pronostic.setPartie9(mdao.findById(resultat.getInt(14)));
                pronostic.setJoueur9(jdao.findById(resultat.getInt(15)));
                pronostic.setPartie10(mdao.findById(resultat.getInt(16)));
                pronostic.setJoueur10(jdao.findById(resultat.getInt(17)));
                pronostic.setPartie11(mdao.findById(resultat.getInt(18)));
                pronostic.setJoueur11(jdao.findById(resultat.getInt(19)));
                pronostic.setFan(fdao.findById(resultat.getInt(20)));
                pronostic.setPartie1(mdao.findById(resultat.getInt(21)));
                pronostic.setJoueur1(jdao.findById(resultat.getInt(22)));
                pronostic.setPartie2(mdao.findById(resultat.getInt(23)));
                pronostic.setJoueur2(jdao.findById(resultat.getInt(24)));
                pronostic.setPartie3(mdao.findById(resultat.getInt(25)));
                pronostic.setJoueur3(jdao.findById(resultat.getInt(26)));
                listePronostic.add(pronostic);
            }
            return listePronostic;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des pronostics " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Pronostic findById(int id) {
        Pronostic pronostic = new Pronostic();
        String requete = "select * from pronostic where id=" + id;
        try {
            prepared = connection.prepareStatement(requete);
            ResultSet resultat = prepared.executeQuery();
            while (resultat.next()) {
                pronostic.setId(resultat.getInt(1));
                pronostic.setJoueur(jdao.findById(resultat.getInt(2)));
                pronostic.setPartie4(mdao.findById(resultat.getInt(3)));
                pronostic.setJoueur4(jdao.findById(resultat.getInt(4)));
                pronostic.setPartie5(mdao.findById(resultat.getInt(5)));
                pronostic.setJoueur5(jdao.findById(resultat.getInt(6)));
                pronostic.setPartie6(mdao.findById(resultat.getInt(7)));
                pronostic.setJoueur6(jdao.findById(resultat.getInt(8)));
                pronostic.setPartie7(mdao.findById(resultat.getInt(9)));
                pronostic.setJoueur7(jdao.findById(resultat.getInt(10)));
                pronostic.setPartie8(mdao.findById(resultat.getInt(11)));
                pronostic.setJoueur8(jdao.findById(resultat.getInt(12)));
                pronostic.setPartie(mdao.findById(resultat.getInt(13)));
                pronostic.setPartie9(mdao.findById(resultat.getInt(14)));
                pronostic.setJoueur9(jdao.findById(resultat.getInt(15)));
                pronostic.setPartie10(mdao.findById(resultat.getInt(16)));
                pronostic.setJoueur10(jdao.findById(resultat.getInt(17)));
                pronostic.setPartie11(mdao.findById(resultat.getInt(18)));
                pronostic.setJoueur11(jdao.findById(resultat.getInt(19)));
                pronostic.setFan(fdao.findById(resultat.getInt(20)));
                pronostic.setPartie1(mdao.findById(resultat.getInt(21)));
                pronostic.setJoueur1(jdao.findById(resultat.getInt(22)));
                pronostic.setPartie2(mdao.findById(resultat.getInt(23)));
                pronostic.setJoueur2(jdao.findById(resultat.getInt(24)));
                pronostic.setPartie3(mdao.findById(resultat.getInt(25)));
                pronostic.setJoueur3(jdao.findById(resultat.getInt(26)));
            }
            return pronostic;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}
