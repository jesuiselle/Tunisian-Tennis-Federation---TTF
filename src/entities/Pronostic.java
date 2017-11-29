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
public class Pronostic {

    private Integer id;
    private Partie partie;
    private Joueur joueur;
    private Partie partie1;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueur3;
    private Joueur joueur4;
    private Joueur joueur5;
    private Joueur joueur6;
    private Partie partie2;
    private Partie partie3;
    private Partie partie4;
    private Joueur joueur7;
    private Partie partie5;
    private Joueur joueur8;
    private Partie partie6;
    private Joueur joueur9;
    private Partie partie7;
    private Partie partie8;
    private Joueur joueur10;
    private Partie partie9;
    private Partie partie10;
    private Partie partie11;
    private Fan fan;
    private Joueur joueur11;

    public Pronostic() {
    }

    public Pronostic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Partie getPartie1() {
        return partie1;
    }

    public void setPartie1(Partie partie1) {
        this.partie1 = partie1;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
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

    public Joueur getJoueur4() {
        return joueur4;
    }

    public void setJoueur4(Joueur joueur4) {
        this.joueur4 = joueur4;
    }

    public Joueur getJoueur5() {
        return joueur5;
    }

    public void setJoueur5(Joueur joueur5) {
        this.joueur5 = joueur5;
    }

    public Joueur getJoueur6() {
        return joueur6;
    }

    public void setJoueur6(Joueur joueur6) {
        this.joueur6 = joueur6;
    }

    public Partie getPartie2() {
        return partie2;
    }

    public void setPartie2(Partie partie2) {
        this.partie2 = partie2;
    }

    public Partie getPartie3() {
        return partie3;
    }

    public void setPartie3(Partie partie3) {
        this.partie3 = partie3;
    }

    public Partie getPartie4() {
        return partie4;
    }

    public void setPartie4(Partie partie4) {
        this.partie4 = partie4;
    }

    public Joueur getJoueur7() {
        return joueur7;
    }

    public void setJoueur7(Joueur joueur7) {
        this.joueur7 = joueur7;
    }

    public Partie getPartie5() {
        return partie5;
    }

    public void setPartie5(Partie partie5) {
        this.partie5 = partie5;
    }

    public Joueur getJoueur8() {
        return joueur8;
    }

    public void setJoueur8(Joueur joueur8) {
        this.joueur8 = joueur8;
    }

    public Partie getPartie6() {
        return partie6;
    }

    public void setPartie6(Partie partie6) {
        this.partie6 = partie6;
    }

    public Joueur getJoueur9() {
        return joueur9;
    }

    public void setJoueur9(Joueur joueur9) {
        this.joueur9 = joueur9;
    }

    public Partie getPartie7() {
        return partie7;
    }

    public void setPartie7(Partie partie7) {
        this.partie7 = partie7;
    }

    public Partie getPartie8() {
        return partie8;
    }

    public void setPartie8(Partie partie8) {
        this.partie8 = partie8;
    }

    public Joueur getJoueur10() {
        return joueur10;
    }

    public void setJoueur10(Joueur joueur10) {
        this.joueur10 = joueur10;
    }

    public Partie getPartie9() {
        return partie9;
    }

    public void setPartie9(Partie partie9) {
        this.partie9 = partie9;
    }

    public Partie getPartie10() {
        return partie10;
    }

    public void setPartie10(Partie partie10) {
        this.partie10 = partie10;
    }

    public Partie getPartie11() {
        return partie11;
    }

    public void setPartie11(Partie partie11) {
        this.partie11 = partie11;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public Joueur getJoueur11() {
        return joueur11;
    }

    public void setJoueur11(Joueur joueur11) {
        this.joueur11 = joueur11;
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
        if (!(object instanceof Pronostic)) {
            return false;
        }
        Pronostic other = (Pronostic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pronostic[ id=" + id + " ]";
    }
    
}
