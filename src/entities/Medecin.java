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
public class Medecin extends Utilisateur {

    private Integer id;
    private String nom;
    private String prenom;
    private String image;
    private List<CompteRenduTest> compteRenduTestList;
    private Utilisateur utilisateur;
    private List<Invitation> invitationList;

    public Medecin() {
    }

    public Medecin(Integer id) {
        this.id = id;
    }

    public Medecin(Integer id, String nom, String prenom, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public Medecin(String nom, String prenom, String image) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CompteRenduTest> getCompteRenduTestList() {
        return compteRenduTestList;
    }

    public void setCompteRenduTestList(List<CompteRenduTest> compteRenduTestList) {
        this.compteRenduTestList = compteRenduTestList;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
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
