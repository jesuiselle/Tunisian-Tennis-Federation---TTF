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
public class Stade {

    private Integer id;
    private String nom;
    private String lieu;
    private String description;
    private String image;
    private List<Partie> partieList;

    public Stade() {
    }

    public Stade(Integer id) {
        this.id = id;
    }

    public Stade(Integer id, String nom, String lieu, String description) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
    }

    public Stade(String nom, String lieu, String description, String image) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Partie> getPartieList() {
        return partieList;
    }

    public void setPartieList(List<Partie> partieList) {
        this.partieList = partieList;
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
        if (!(object instanceof Stade)) {
            return false;
        }
        Stade other = (Stade) object;
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
