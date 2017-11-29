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
public class Formation {

    private Integer id;
    private String dateDebut;
    private String dateFin;
    private String description;
    private List<ParticipationFormation> participationFormationList;

    public Formation() {
    }

    public Formation(Integer id) {
        this.id = id;
    }

    public Formation(Integer id, String dateDebut, String dateFin, String description) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ParticipationFormation> getParticipationFormationList() {
        return participationFormationList;
    }

    public void setParticipationFormationList(List<ParticipationFormation> participationFormationList) {
        this.participationFormationList = participationFormationList;
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
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Formation[ id=" + id + " ]";
    }
    
}
