package model;

public class Ingredient {
    private int idIng;
    private String nom;

    public Ingredient(int idIng, String nom) {
        this.idIng = idIng;
        this.nom = nom;
    }

    public int getIdIng() { return idIng; }
    public void setIdIng(int idIng) { this.idIng = idIng; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public String toString() {
        return nom;
    }
}
