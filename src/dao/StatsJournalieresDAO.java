package dao;

import model.StatsJournalieres;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatsJournalieresDAO {
    private final Connection connection;

    public StatsJournalieresDAO(Connection connection) {
        this.connection = connection;
    }

    public StatsJournalieres getByDate(LocalDate date) throws SQLException {
        String query = "SELECT * FROM Stats_Journalieres WHERE date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new StatsJournalieres(
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("total_commandes"),
                            rs.getInt("pizzas_gratuites"),
                            rs.getDouble("total_chiffre_affaires")
                    );
                }
            }
        }
        return null;
    }

    public List<StatsJournalieres> getAll() throws SQLException {
        List<StatsJournalieres> stats = new ArrayList<>();
        String query = "SELECT * FROM Stats_Journalieres ORDER BY date DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                stats.add(new StatsJournalieres(
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("total_commandes"),
                        rs.getInt("pizzas_gratuites"),
                        rs.getDouble("total_chiffre_affaires")
                ));
            }
        }
        return stats;
    }
}

