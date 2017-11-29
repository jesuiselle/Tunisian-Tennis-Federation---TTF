/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author Bouchriha
 */
public class Partie {

    private Integer id;
    private String description;
    private String genre;
    private String type;
    private String score;
    private String setUn;
    private String setDeux;
    private String setTrois;
    private String setQuatre;
    private String setCinq;
    private String dateMatch;
    private String lien;
    private List<CompteRenduMatch> compteRenduMatchList;
    private List<Statistic> statisticList;
    private Joueur joueur;
    private Joueur joueur1;
    private Stade stade;
    private Evenement evenement;
    private Joueur joueur2;
    private Joueur joueur3;
    private Arbitre arbitre;
    private List<Ticket> ticketList;

    public Partie() {
    }

    public Partie(Integer id) {
        this.id = id;
    }

    public Partie(Integer id, String description, String genre, String type, String score, String setUn, String setDeux, String dateMatch, String lien) {
        this.id = id;
        this.description = description;
        this.genre = genre;
        this.type = type;
        this.score = score;
        this.setUn = setUn;
        this.setDeux = setDeux;
        this.dateMatch = dateMatch;
        this.lien = lien;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSetUn() {
        return setUn;
    }

    public void setSetUn(String setUn) {
        this.setUn = setUn;
    }

    public String getSetDeux() {
        return setDeux;
    }

    public void setSetDeux(String setDeux) {
        this.setDeux = setDeux;
    }

    public String getSetTrois() {
        return setTrois;
    }

    public void setSetTrois(String setTrois) {
        this.setTrois = setTrois;
    }

    public String getSetQuatre() {
        return setQuatre;
    }

    public void setSetQuatre(String setQuatre) {
        this.setQuatre = setQuatre;
    }

    public String getSetCinq() {
        return setCinq;
    }

    public void setSetCinq(String setCinq) {
        this.setCinq = setCinq;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public List<CompteRenduMatch> getCompteRenduMatchList() {
        return compteRenduMatchList;
    }

    public void setCompteRenduMatchList(List<CompteRenduMatch> compteRenduMatchList) {
        this.compteRenduMatchList = compteRenduMatchList;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    public Joueur getJoueur3() {
        return joueur3;
    }

    public void setJoueur3(Joueur joueur3) {
        this.joueur3 = joueur3;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partie)) {
            return false;
        }
        Partie other = (Partie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " ";
    }
    
}
