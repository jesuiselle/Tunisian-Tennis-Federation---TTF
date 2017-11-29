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
public class Responsable extends Utilisateur {

    private Integer id;
    private String nom;
    private String prenom;
    private List<CompteRenduTest> compteRenduTestList;
    private Utilisateur utilisateur;

    public Responsable() {
    }

    public Responsable(Integer id) {
        this.id = id;
    }

    public Responsable(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Responsable(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsable)) {
            return false;
        }
        Responsable other = (Responsable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Responsable[ id=" + id + " ]";
    }
    
}
