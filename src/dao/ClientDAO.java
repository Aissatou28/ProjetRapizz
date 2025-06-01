package dao;

import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    // Récupère un client par son ID
    public Client getById(int id) throws SQLException {
        String query = "SELECT * FROM Client WHERE id_client = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getInt("id_client"),
                            rs.getString("nom"),
                            rs.getString("adresse")
                    );
                }
            }
        }
        return null;
    }

    // Récupère tous les clients
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Client";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("adresse")
                ));
            }
        }
        return clients;
    }

    // méthode pour ajouter un client
    public void insert(Client client) throws SQLException {
        String query = "INSERT INTO Client (nom, adresse) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdresse());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setIdClient(generatedKeys.getInt(1));
                }
            }
        }
    }

    public boolean delete(int idClient) throws SQLException {
        String query = "DELETE FROM Client WHERE id_client = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idClient);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0; // retourne true si au moins une ligne a été supprimée
        }
    }

}
