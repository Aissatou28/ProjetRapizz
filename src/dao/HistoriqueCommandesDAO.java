package dao;

import model.HistoriqueCommande;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueCommandesDAO {
    private final Connection connection;

    public HistoriqueCommandesDAO(Connection connection) {
        this.connection = connection;
    }

    public HistoriqueCommande getById(int id) throws SQLException {
        String query = "SELECT * FROM Historique_Commandes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHistorique(rs);
                }
            }
        }
        return null;
    }

    public List<HistoriqueCommande> getAll() throws SQLException {
        List<HistoriqueCommande> historiques = new ArrayList<>();
        String query = "SELECT * FROM Historique_Commandes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                historiques.add(mapResultSetToHistorique(rs));
            }
        }
        return historiques;
    }

    public void insert(HistoriqueCommande historique) throws SQLException {
        String query = "INSERT INTO Historique_Commandes (id_commande, date_action, action) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, historique.getIdCommande());
            stmt.setTimestamp(2, Timestamp.valueOf(historique.getDateAction()));
            stmt.setString(3, historique.getAction());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    historique.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    private HistoriqueCommande mapResultSetToHistorique(ResultSet rs) throws SQLException {
        HistoriqueCommande historique = new HistoriqueCommande();
        historique.setId(rs.getInt("id"));
        historique.setIdCommande(rs.getInt("id_commande"));
        historique.setDateAction(rs.getTimestamp("date_action").toLocalDateTime());
        historique.setAction(rs.getString("action"));
        return historique;
    }
}
