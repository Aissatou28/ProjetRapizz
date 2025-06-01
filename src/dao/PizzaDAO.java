package dao;

import model.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAO {
    private Connection connection;

    public PizzaDAO(Connection connection) {
        this.connection = connection;
    }

    // Récupère une pizza par son ID
    public Pizza getById(int id) throws SQLException {
        String query = "SELECT * FROM pizza WHERE id_pizza = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pizza(
                            rs.getInt("id_pizza"),
                            rs.getString("nom"),
                            rs.getDouble("prix_base")
                    );
                }
            }
        }
        return null;
    }

    // Récupère toutes les pizzas
    public List<Pizza> getAll() throws SQLException {
        List<Pizza> list = new ArrayList<>();
        String query = "SELECT * FROM pizza";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Pizza pizza = new Pizza(
                        rs.getInt("id_pizza"),
                        rs.getString("nom"),
                        rs.getDouble("prix_base")
                );
                list.add(pizza);
            }
        }
        return list;
    }

    // Insère une nouvelle pizza
    public void insert(Pizza pizza) throws SQLException {
        String query = "INSERT INTO pizza (nom, prix_base) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pizza.getNom());
            stmt.setDouble(2, pizza.getPrixBase());
            stmt.executeUpdate();
        }
    }

    // Supprime une pizza
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM pizza WHERE id_pizza = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public double getPrixBasePizza(int idPizza) throws SQLException {
        String query = "SELECT prix_base FROM Pizza WHERE id_pizza = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPizza);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("prix_base");
                }
            }
        }
        throw new SQLException("Pizza non trouvée !");
    }

}
