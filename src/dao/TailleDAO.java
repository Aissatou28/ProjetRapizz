package dao;

import model.Taille;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TailleDAO {
    private final Connection connection;

    public TailleDAO(Connection connection) {
        this.connection = connection;
    }

    public Taille get(String nomTaille) throws SQLException {
        String query = "SELECT * FROM Taille WHERE nom_taille = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomTaille);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Taille(
                            rs.getString("nom_taille"),
                            rs.getDouble("coef")
                    );
                }
            }
        }
        return null;
    }

    public List<Taille> getAll() throws SQLException {
        List<Taille> tailles = new ArrayList<>();
        String query = "SELECT * FROM Taille";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                tailles.add(new Taille(
                        rs.getString("nom_taille"),
                        rs.getDouble("coef")
                ));
            }
        }
        return tailles;
    }

    public void insert(Taille taille) throws SQLException {
        String query = "INSERT INTO Taille (nom_taille, coef) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, taille.getNomTaille());
            stmt.setDouble(2, taille.getCoefficient());
            stmt.executeUpdate();
        }
    }

    public void update(Taille taille) throws SQLException {
        String query = "UPDATE Taille SET coef = ? WHERE nom_taille = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, taille.getCoefficient());
            stmt.setString(2, taille.getNomTaille());
            stmt.executeUpdate();
        }
    }

    public void delete(String nomTaille) throws SQLException {
        String query = "DELETE FROM Taille WHERE nom_taille = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomTaille);
            stmt.executeUpdate();
        }
    }

    public double getCoefficient(String nomTaille) throws SQLException {
        String query = "SELECT coef FROM Taille WHERE nom_taille = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomTaille);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("coef");
                }
            }
        }
        throw new SQLException("Taille non trouvée : " + nomTaille);
    }

}
