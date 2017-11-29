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
public class Joueur extends Utilisateur {

    private Integer id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private double taille;
    private double poids;
    private double score;
    private String categorie;
    private String groupeAge;
    private String image;
    private String nationalite;
    private String logoPays;
    private Club club;
    private Utilisateur utilisateur;
    private List<CompteRenduTest> compteRenduTestList;
    private List<Statistic> statisticList;
    private List<Partie> partieList;
    private List<Invitation> invitationList;

    public Joueur() {
    }

    public Joueur(Integer id) {
        this.id = id;
    }

    public Joueur(Integer id, String nom, String prenom, String dateNaissance, double taille, double poids, double score, String categorie, String groupeAge, String nationalite, String logoPays) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.taille = taille;
        this.poids = poids;
        this.score = score;
        this.categorie = categorie;
        this.groupeAge = groupeAge;
        this.nationalite = nationalite;
        this.logoPays = logoPays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getGroupeAge() {
        return groupeAge;
    }

    public void setGroupeAge(String groupeAge) {
        this.groupeAge = groupeAge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getLogoPays() {
        return logoPays;
    }

    public void setLogoPays(String logoPays) {
        this.logoPays = logoPays;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<CompteRenduTest> getCompteRenduTestList() {
        return compteRenduTestList;
    }

    public void setCompteRenduTestList(List<CompteRenduTest> compteRenduTestList) {
        this.compteRenduTestList = compteRenduTestList;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public List<Partie> getPartieList() {
        return partieList;
    }

    public void setPartieList(List<Partie> partieList) {
        this.partieList = partieList;
    }

    public List<Invitation> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<Invitation> invitationList) {
        this.invitationList = invitationList;
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
        if (!(object instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    public Object stream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
