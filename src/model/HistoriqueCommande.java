package model;

import java.time.LocalDateTime;

public class HistoriqueCommande {
    private int id;
    private int idCommande;
    private LocalDateTime dateAction;
    private String action;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    public LocalDateTime getDateAction() { return dateAction; }
    public void setDateAction(LocalDateTime dateAction) { this.dateAction = dateAction; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}