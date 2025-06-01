package model;

public class Client {
    private int idClient;
    private String nom;
    private String adresse;

    public Client(int idClient, String nom, String adresse) {
        this.idClient = idClient;
        this.nom = nom;
        this.adresse = adresse;
    }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    @Override
    public String toString() {
        return nom + " (" + adresse + ")";
    }
}