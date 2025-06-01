package model;

public class Compte {
    private int idClient;
    private double solde;

    public Compte(int idClient, double solde) {
        this.idClient = idClient;
        this.solde = solde;
    }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
}