package controller;

import dao.ClientDAO;
import dao.DBConnection;
import model.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientController {
    private final ClientDAO clientDAO;

    public ClientController() throws SQLException {
        Connection connection = DBConnection.getConnection();
        this.clientDAO = new ClientDAO(connection);
    }

    // Ajouter un client
    public boolean addClient(String nom, String adresse) {
        try {
            Client client = new Client(0, nom, adresse); // id sera généré
            clientDAO.insert(client);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un client par son ID
    public boolean deleteClient(int idClient) {
        try {
            return clientDAO.delete(idClient);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtenir la liste de tous les clients
    public List<Client> getAllClients() {
        try {
            return clientDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Obtenir un client par ID
    public Client getClientById(int idClient) {
        try {
            return clientDAO.getById(idClient);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
