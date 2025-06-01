package dao;

import model.PizzaIngredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaIngredientDAO {
    private final Connection connection;

    public PizzaIngredientDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PizzaIngredient> getByPizzaId(int idPizza) throws SQLException {
        List<PizzaIngredient> list = new ArrayList<>();
        String query = "SELECT * FROM Pizza_Ingredient WHERE id_pizza = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPizza);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PizzaIngredient pi = new PizzaIngredient();
                    pi.setIdPizza(rs.getInt("id_pizza"));
                    pi.setIdIng(rs.getInt("id_ing"));
                    list.add(pi);
                }
            }
        }
        return list;
    }

    public void insert(PizzaIngredient pi) throws SQLException {
        String query = "INSERT INTO Pizza_Ingredient (id_pizza, id_ing) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pi.getIdPizza());
            stmt.setInt(2, pi.getIdIng());
            stmt.executeUpdate();
        }
    }

    public void delete(PizzaIngredient pi) throws SQLException {
        String query = "DELETE FROM Pizza_Ingredient WHERE id_pizza = ? AND id_ing = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pi.getIdPizza());
            stmt.setInt(2, pi.getIdIng());
            stmt.executeUpdate();
        }
    }

    public List<PizzaIngredient> getAll() throws SQLException {
        List<PizzaIngredient> list = new ArrayList<>();
        String query = "SELECT * FROM Pizza_Ingredient";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PizzaIngredient pi = new PizzaIngredient();
                pi.setIdPizza(rs.getInt("id_pizza"));
                pi.setIdIng(rs.getInt("id_ing"));
                list.add(pi);
            }
        }
        return list;
    }
}
