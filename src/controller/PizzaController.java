package controller;

import dao.DBConnection;
import dao.PizzaDAO;
import model.Pizza;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PizzaController {
    private final PizzaDAO pizzaDAO;

    public PizzaController() throws SQLException {
        Connection connection = DBConnection.getConnection();
        this.pizzaDAO = new PizzaDAO(connection);
    }

    // Ajouter une pizza
    public boolean addPizza(String nom, double prixBase) {
        try {
            Pizza pizza = new Pizza(0, nom, prixBase);
            pizzaDAO.insert(pizza);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer une pizza par son ID
    public boolean deletePizza(int idPizza) {
        try {
            pizzaDAO.delete(idPizza);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer toutes les pizzas
    public List<Pizza> getAllPizzas() {
        try {
            return pizzaDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupérer une pizza par ID
    public Pizza getPizzaById(int id) {
        try {
            return pizzaDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Obtenir le prix de base d'une pizza
    public double getPrixBase(int idPizza) {
        try {
            return pizzaDAO.getPrixBasePizza(idPizza);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // valeur d'erreur
        }
    }
}

