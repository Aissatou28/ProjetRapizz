package dao;

import model.Vehicule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDAO {
    private final Connection connection;

    public VehiculeDAO(Connection connection) {
        this.connection = connection;
    }

    public Vehicule get(int idVehicule) throws SQLException {
        String query = "SELECT * FROM Vehicule WHERE id_vehicule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idVehicule);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vehicule(
                            rs.getInt("id_vehicule"),
                            rs.getString("type")
                    );
                }
            }
        }
        return null;
    }

    public List<Vehicule> getAll() throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM Vehicule";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                vehicules.add(new Vehicule(
                        rs.getInt("id_vehicule"),
                        rs.getString("type")
                ));
            }
        }
        return vehicules;
    }

    public void insert(Vehicule vehicule) throws SQLException {
        String query = "INSERT INTO Vehicule (type) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicule.getType());
            stmt.executeUpdate();
        }
    }

    public void update(Vehicule vehicule) throws SQLException {
        String query = "UPDATE Vehicule SET type = ? WHERE id_vehicule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicule.getType());
            stmt.setInt(2, vehicule.getIdVehicule());
            stmt.executeUpdate();
        }
    }

    public void delete(int idVehicule) throws SQLException {
        String query = "DELETE FROM Vehicule WHERE id_vehicule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idVehicule);
            stmt.executeUpdate();
        }
    }
}
