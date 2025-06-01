package dao;

import model.Commande;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CommandeDAO {
    private final Connection connection;

    public CommandeDAO(Connection connection) {
        this.connection = connection;
    }

    public Commande getById(int id) throws SQLException {
        String query = "SELECT * FROM Commande WHERE id_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCommande(rs);
                }
            }
        }
        return null;
    }

    public List<Commande> getAll() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String query = "SELECT * FROM Commande";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                commandes.add(mapResultSetToCommande(rs));
            }
        }
        return commandes;
    }

    public void insert(Commande commande) throws SQLException {
        String query = "INSERT INTO Commande (id_client, id_pizza, taille, id_livreur, id_vehicule, date_commande, date_livraison, gratuite, prix_facturé) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, commande.getIdClient());
            stmt.setInt(2, commande.getIdPizza());
            stmt.setString(3, commande.getTaille());
            stmt.setInt(4, commande.getIdLivreur());
            stmt.setInt(5, commande.getIdVehicule());
            stmt.setTimestamp(6, Timestamp.valueOf(commande.getDateCommande()));
            stmt.setTimestamp(7, commande.getDateLivraison() != null ? Timestamp.valueOf(commande.getDateLivraison()) : null);
            stmt.setBoolean(8, commande.isGratuite());
            stmt.setDouble(9, commande.getPrixFacture());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    commande.setIdCommande(generatedKeys.getInt(1));
                }
            }
        }
    }

    private Commande mapResultSetToCommande(ResultSet rs) throws SQLException {
        return new Commande(
                rs.getInt("id_commande"),
                rs.getInt("id_client"),
                rs.getInt("id_pizza"),
                rs.getString("taille"),
                rs.getInt("id_livreur"),
                rs.getInt("id_vehicule"),
                rs.getTimestamp("date_commande").toLocalDateTime(),
                rs.getTimestamp("date_livraison") != null ? rs.getTimestamp("date_livraison").toLocalDateTime() : null,
                rs.getBoolean("gratuite"),
                rs.getDouble("prix_facturé")
        );
    }

    public boolean checkPizzaGratuite(int idClient) throws SQLException {
        String call = "{ CALL calcul_pizza_gratuite(?, ?) }";
        try (CallableStatement stmt = connection.prepareCall(call)) {
            stmt.setInt(1, idClient);
            stmt.registerOutParameter(2, java.sql.Types.BOOLEAN);
            stmt.execute();
            return stmt.getBoolean(2);
        }
    }

    public void insertCommande(Commande commande) throws SQLException {
        String query = "INSERT INTO Commande (id_client, id_pizza, taille, id_livreur, id_vehicule, date_commande, date_livraison, gratuite, prix_facturé) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, commande.getIdClient());
            stmt.setInt(2, commande.getIdPizza());
            stmt.setString(3, commande.getTaille());
            stmt.setInt(4, commande.getIdLivreur());
            stmt.setInt(5, commande.getIdVehicule());
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(commande.getDateCommande()));
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(commande.getDateLivraison()));
            stmt.setBoolean(8, commande.isGratuite());
            stmt.setDouble(9, commande.getPrixFacture());
            stmt.executeUpdate();
        }
    }



}
