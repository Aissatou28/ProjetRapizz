package model;

public class Livreur {
    private int idLivreur;
    private String nom;

    public Livreur(int idLivreur, String nom) {
        this.idLivreur = idLivreur;
        this.nom = nom;
    }

    public int getIdLivreur() { return idLivreur; }
    public void setIdLivreur(int idLivreur) { this.idLivreur = idLivreur; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public String toString() {
        return nom;
    }
}
