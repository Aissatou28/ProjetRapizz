package model;

import java.time.LocalDate;

public class StatsJournalieres {
    private LocalDate date;
    private int totalCommandes;
    private int pizzasGratuites;
    private double totalChiffreAffaires;

    public StatsJournalieres(LocalDate date, int totalCommandes, int pizzasGratuites, double totalChiffreAffaires) {
        this.date = date;
        this.totalCommandes = totalCommandes;
        this.pizzasGratuites = pizzasGratuites;
        this.totalChiffreAffaires = totalChiffreAffaires;
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getTotalCommandes() { return totalCommandes; }
    public void setTotalCommandes(int totalCommandes) { this.totalCommandes = totalCommandes; }

    public int getPizzasGratuites() { return pizzasGratuites; }
    public void setPizzasGratuites(int pizzasGratuites) { this.pizzasGratuites = pizzasGratuites; }

    public double getTotalChiffreAffaires() { return totalChiffreAffaires; }
    public void setTotalChiffreAffaires(double totalChiffreAffaires) { this.totalChiffreAffaires = totalChiffreAffaires; }

    @Override
    public String toString() {
        return date + " : " + totalCommandes + " commandes, " + pizzasGratuites + " gratuites, " + totalChiffreAffaires + " € CA";
    }
}
