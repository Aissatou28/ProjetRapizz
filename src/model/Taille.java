package model;

public class Taille {
    private String nomTaille;
    private double coefficient;

    public Taille(String nomTaille, double coefficient) {
        this.nomTaille = nomTaille;
        this.coefficient = coefficient;
    }

    public String getNomTaille() { return nomTaille; }
    public void setNomTaille(String nomTaille) { this.nomTaille = nomTaille; }

    public double getCoefficient() { return coefficient; }
    public void setCoefficient(double coefficient) { this.coefficient = coefficient; }

    @Override
    public String toString() {
        return nomTaille;
    }
}
