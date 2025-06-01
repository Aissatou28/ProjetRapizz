package model;

public class Vehicule {
    private int idVehicule;
    private String type;

    public Vehicule(int idVehicule, String type) {
        this.idVehicule = idVehicule;
        this.type = type;
    }

    public int getIdVehicule() { return idVehicule; }
    public void setIdVehicule(int idVehicule) { this.idVehicule = idVehicule; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return type;
    }
}
