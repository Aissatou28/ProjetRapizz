package controller;

import dao.DBConnection;
import dao.LivreurDAO;
import model.Livreur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LivreurController {
    private final LivreurDAO livreurDAO;

    public LivreurController() throws SQLException {
        Connection connection = DBConnection.getConnection();
        this.livreurDAO = new LivreurDAO(connection);
    }

    // Ajouter un livreur
    public boolean addLivreur(String nom) {
        try {
            Livreur livreur = new Livreur(0, nom);
            livreurDAO.insert(livreur);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un livreur par ID
    public boolean deleteLivreur(int idLivreur) {
        try {
            livreurDAO.delete(idLivreur);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer un livreur par ID
    public Livreur getLivreurById(int id) {
        try {
            return livreurDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupérer tous les livreurs
    public List<Livreur> getAllLivreurs() {
        try {
            return livreurDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
