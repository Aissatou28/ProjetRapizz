package controller;

import dao.DBConnection;
import dao.VehiculeDAO;
import model.Vehicule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VehiculeController {
    private final VehiculeDAO vehiculeDAO;

    public VehiculeController() throws SQLException {
        Connection connection = DBConnection.getConnection();
        this.vehiculeDAO = new VehiculeDAO(connection);
    }

    // Ajouter un véhicule
    public boolean addVehicule(String type) {
        try {
            Vehicule vehicule = new Vehicule(0, type);
            vehiculeDAO.insert(vehicule);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Modifier un véhicule
    public boolean updateVehicule(int id, String nouveauType) {
        try {
            Vehicule vehicule = new Vehicule(id, nouveauType);
            vehiculeDAO.update(vehicule);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un véhicule
    public boolean deleteVehicule(int id) {
        try {
            vehiculeDAO.delete(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer un véhicule par ID
    public Vehicule getVehiculeById(int id) {
        try {
            return vehiculeDAO.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupérer tous les véhicules
    public List<Vehicule> getAllVehicules() {
        try {
            return vehiculeDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

