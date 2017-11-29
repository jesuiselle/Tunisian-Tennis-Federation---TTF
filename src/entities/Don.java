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
public class Don {

    private Integer id;
    private String dateDon;
    private String description;
    private Club club;

    public Don() {
    }

    public Don(Integer id) {
        this.id = id;
    }

    public Don(Integer id, String dateDon, String description) {
        this.id = id;
        this.dateDon = dateDon;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateDon() {
        return dateDon;
    }

    public void setDateDon(String dateDon) {
        this.dateDon = dateDon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
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
        if (!(object instanceof Don)) {
            return false;
        }
        Don other = (Don) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Don[ id=" + id + " ]";
    }
    
}
