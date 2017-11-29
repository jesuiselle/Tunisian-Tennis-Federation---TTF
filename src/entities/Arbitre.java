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
public class Arbitre extends Utilisateur {

    
    private Integer id;
    private String nom;
    private String prenom;
    private String categorie;
    private String dateNaissance;
    private String image;
    private List<Disponibilite> disponibiliteList;
    private List<CompteRenduMatch> compteRenduMatchList;
    private List<Partie> partieList;
    private List<ParticipationFormation> participationFormationList;
    private Utilisateur utilisateur;

    public Arbitre() {
    }

    public Arbitre(Integer id) {
        this.id = id;
    }

    public Arbitre(Integer id, String nom, String prenom, String categorie, String dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.categorie = categorie;
        this.dateNaissance = dateNaissance;
    }

    public Arbitre(String nom, String prenom, String categorie, String dateNaissance, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.categorie = categorie;
        this.dateNaissance = dateNaissance;
        this.image = image;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Disponibilite> getDisponibiliteList() {
        return disponibiliteList;
    }

    public void setDisponibiliteList(List<Disponibilite> disponibiliteList) {
        this.disponibiliteList = disponibiliteList;
    }

    public List<CompteRenduMatch> getCompteRenduMatchList() {
        return compteRenduMatchList;
    }

    public void setCompteRenduMatchList(List<CompteRenduMatch> compteRenduMatchList) {
        this.compteRenduMatchList = compteRenduMatchList;
    }

    public List<Partie> getPartieList() {
        return partieList;
    }

    public void setPartieList(List<Partie> partieList) {
        this.partieList = partieList;
    }

    public List<ParticipationFormation> getParticipationFormationList() {
        return participationFormationList;
    }

    public void setParticipationFormationList(List<ParticipationFormation> participationFormationList) {
        this.participationFormationList = participationFormationList;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
        if (!(object instanceof Arbitre)) {
            return false;
        }
        Arbitre other = (Arbitre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
    
}
