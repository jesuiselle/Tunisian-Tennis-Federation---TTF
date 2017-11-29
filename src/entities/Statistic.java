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
public class Statistic {

    private Integer id;
    private int aces;
    private int serviceWinners;
    private int doubleFaults;
    private int totalPoints;
    private int totalPointsWon;
    private int serviceGame;
    private double averageServeSpeed;
    private double fastestServeSpeed;
    private Partie partie;
    private Joueur joueur;

    public Statistic() {
    }

    public Statistic(Integer id) {
        this.id = id;
    }

    public Statistic(Integer id, int aces, int serviceWinners, int doubleFaults, int totalPoints, int totalPointsWon, int serviceGame, double averageServeSpeed, double fastestServeSpeed) {
        this.id = id;
        this.aces = aces;
        this.serviceWinners = serviceWinners;
        this.doubleFaults = doubleFaults;
        this.totalPoints = totalPoints;
        this.totalPointsWon = totalPointsWon;
        this.serviceGame = serviceGame;
        this.averageServeSpeed = averageServeSpeed;
        this.fastestServeSpeed = fastestServeSpeed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAces() {
        return aces;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public int getServiceWinners() {
        return serviceWinners;
    }

    public void setServiceWinners(int serviceWinners) {
        this.serviceWinners = serviceWinners;
    }

    public int getDoubleFaults() {
        return doubleFaults;
    }

    public void setDoubleFaults(int doubleFaults) {
        this.doubleFaults = doubleFaults;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalPointsWon() {
        return totalPointsWon;
    }

    public void setTotalPointsWon(int totalPointsWon) {
        this.totalPointsWon = totalPointsWon;
    }

    public int getServiceGame() {
        return serviceGame;
    }

    public void setServiceGame(int serviceGame) {
        this.serviceGame = serviceGame;
    }

    public double getAverageServeSpeed() {
        return averageServeSpeed;
    }

    public void setAverageServeSpeed(double averageServeSpeed) {
        this.averageServeSpeed = averageServeSpeed;
    }

    public double getFastestServeSpeed() {
        return fastestServeSpeed;
    }

    public void setFastestServeSpeed(double fastestServeSpeed) {
        this.fastestServeSpeed = fastestServeSpeed;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statistic)) {
            return false;
        }
        Statistic other = (Statistic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Statistic[ id=" + id + " ]";
    }
    
}
