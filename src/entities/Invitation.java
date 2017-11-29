/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Bouchriha
 */
public class Invitation {

    private Integer id;
    private String dateInvitation;
    private Medecin medecin;
    private Joueur joueur;

    public Invitation() {
    }

    public Invitation(Integer id) {
        this.id = id;
    }

    public Invitation(Integer id, String dateInvitation) {
        this.id = id;
        this.dateInvitation = dateInvitation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateInvitation() {
        return dateInvitation;
    }

    public void setDateInvitation(String dateInvitation) {
        this.dateInvitation = dateInvitation;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
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
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Invitation[ id=" + id + " ]";
    }
    
}
