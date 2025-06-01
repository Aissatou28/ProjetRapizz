package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteDAO {
    private final Connection connection;

    public CompteDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Récupère le solde du client via son ID.
     */
    public double getSoldeByClientId(int idClient) throws SQLException {
        String query = "SELECT solde FROM Compte WHERE id_client = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("solde");
                } else {
                    throw new SQLException("Compte introuvable pour le client ID : " + idClient);
                }
            }
        }
    }

    /**
     * Débite un montant du compte client.
     */
    public void debiterCompte(int idClient, double montant) throws SQLException {
        String query = "UPDATE Compte SET solde = solde - ? WHERE id_client = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, montant);
            stmt.setInt(2, idClient);
            stmt.executeUpdate();
        }
    }

    /**
     * Crédite un montant sur le compte client.
     */
    public void crediterCompte(int idClient, double montant) throws SQLException {
        String query = "UPDATE Compte SET solde = solde + ? WHERE id_client = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, montant);
            stmt.setInt(2, idClient);
            stmt.executeUpdate();
        }
    }
}

