package dao;

import model.Livreur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreurDAO {
    private final Connection connection;

    public LivreurDAO(Connection connection) {
        this.connection = connection;
    }

    public Livreur getById(int idLivreur) throws SQLException {
        String query = "SELECT * FROM Livreur WHERE id_livreur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idLivreur);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livreur(
                            rs.getInt("id_livreur"),
                            rs.getString("nom")
                    );
                }
            }
        }
        return null;
    }

    public List<Livreur> getAll() throws SQLException {
        List<Livreur> livreurs = new ArrayList<>();
        String query = "SELECT * FROM Livreur";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                livreurs.add(new Livreur(
                        rs.getInt("id_livreur"),
                        rs.getString("nom")
                ));
            }
        }
        return livreurs;
    }

    public void insert(Livreur livreur) throws SQLException {
        String query = "INSERT INTO Livreur (nom) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livreur.getNom());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    livreur.setIdLivreur(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void delete(int idLivreur) throws SQLException {
        String query = "DELETE FROM Livreur WHERE id_livreur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idLivreur);
            stmt.executeUpdate();
        }
    }
}
