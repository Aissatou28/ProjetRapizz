package model;

import java.time.LocalDateTime;

public class Commande {
    private int idCommande;
    private int idClient;
    private int idPizza;
    private String taille;
    private int idLivreur;
    private int idVehicule;
    private LocalDateTime dateCommande;
    private LocalDateTime dateLivraison;
    private boolean gratuite;
    private double prixFacture;

    public Commande(int idCommande, int idClient, int idPizza, String taille, int idLivreur,
                    int idVehicule, LocalDateTime dateCommande, LocalDateTime dateLivraison,
                    boolean gratuite, double prixFacture) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.idPizza = idPizza;
        this.taille = taille;
        this.idLivreur = idLivreur;
        this.idVehicule = idVehicule;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.gratuite = gratuite;
        this.prixFacture = prixFacture;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(int idLivreur) {
        this.idLivreur = idLivreur;
    }

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public boolean isGratuite() {
        return gratuite;
    }

    public void setGratuite(boolean gratuite) {
        this.gratuite = gratuite;
    }

    public double getPrixFacture() {
        return prixFacture;
    }

    public void setPrixFacture(double prixFacture) {
        this.prixFacture = prixFacture;
    }

    @Override
    public String toString() {
        return "Commande #" + idCommande + " | Client: " + idClient +
                " | Pizza: " + idPizza + " (" + taille + ")" +
                " | Prix: " + prixFacture + (gratuite ? " (Gratuite)" : "");
    }
}