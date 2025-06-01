package model;

public class Pizza {
    private int idPizza;
    private String nom;
    private double prixBase;

    public Pizza(int idPizza, String nom, double prixBase) {
        this.idPizza = idPizza;
        this.nom = nom;
        this.prixBase = prixBase;
    }

    public int getIdPizza() { return idPizza; }
    public void setIdPizza(int idPizza) { this.idPizza = idPizza; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrixBase() { return prixBase; }
    public void setPrixBase(double prixBase) { this.prixBase = prixBase; }

    @Override
    public String toString() {
        return nom + " (" + prixBase + " €)";
    }
}
