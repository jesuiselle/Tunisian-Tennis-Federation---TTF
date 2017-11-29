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
public class Club {

    private Integer id;
    private String nom;
    private String adresse;
    private String dateFondation;
    private String logo;
    private List<Don> donList;
    private List<Joueur> joueurList;

    public Club() {
    }

    public Club(Integer id) {
        this.id = id;
    }

    public Club(Integer id, String nom, String adresse, String dateFondation) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.dateFondation = dateFondation;
    }

    public Club(String nom, String adresse, String dateFondation, String logo) {
        this.nom = nom;
        this.adresse = adresse;
        this.dateFondation = dateFondation;
        this.logo = logo;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateFondation() {
        return dateFondation;
    }

    public void setDateFondation(String dateFondation) {
        this.dateFondation = dateFondation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Don> getDonList() {
        return donList;
    }

    public void setDonList(List<Don> donList) {
        this.donList = donList;
    }

    public List<Joueur> getJoueurList() {
        return joueurList;
    }

    public void setJoueurList(List<Joueur> joueurList) {
        this.joueurList = joueurList;
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
        if (!(object instanceof Club)) {
            return false;
        }
        Club other = (Club) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
