package controller;

import dao.CommandeDAO;
import dao.ClientDAO;
import dao.CompteDAO;
import dao.TailleDAO;
import dao.DBConnection;
import model.Commande;
import dao.PizzaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CommandeController {
    private final Connection connection;
    private final CommandeDAO commandeDAO;
    private final CompteDAO compteDAO;
    private final TailleDAO tailleDAO;
    private final PizzaDAO pizzaDAO;

    public CommandeController() throws SQLException {
        this.connection = DBConnection.getConnection();
        this.commandeDAO = new CommandeDAO(connection);
        this.compteDAO = new CompteDAO(connection);
        this.tailleDAO = new TailleDAO(connection);
        this.pizzaDAO = new PizzaDAO(connection);
    }

    public boolean creerCommande(int idClient, int idPizza, String taille, int idLivreur, int idVehicule) {
        try {
            // 1. Vérifier le solde du client
            double solde = compteDAO.getSoldeByClientId(idClient);

            // 2. Calculer le prix en fonction de la taille
            double prixBase = pizzaDAO.getPrixBasePizza(idPizza);
            double coefficient = tailleDAO.getCoefficient(taille);
            double prixFinal = prixBase * coefficient;

            // 3. Vérifier si le client a droit à une pizza gratuite
            boolean gratuite = commandeDAO.checkPizzaGratuite(idClient);

            // 4. Vérifier solde si la pizza n’est pas gratuite
            if (!gratuite && solde < prixFinal) {
                System.out.println("Solde insuffisant.");
                return false;
            }

            // 5. Débiter le compte si ce n'est pas gratuit
            if (!gratuite) {
                compteDAO.debiterCompte(idClient, prixFinal);
            } else {
                prixFinal = 0.0;
            }

            // 6. Créer l’objet commande
            Commande commande = new Commande(
                    0, // ID généré automatiquement
                    idClient,
                    idPizza,
                    taille,
                    idLivreur,
                    idVehicule,
                    LocalDateTime.now(),
                    null,
                    gratuite,
                    prixFinal
            );

            // 7. Insérer la commande
            commandeDAO.insertCommande(commande);
            System.out.println("Commande créée avec succès.");
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la commande : " + e.getMessage());
            return false;
        }
    }

    public List<Commande> getAllCommandes() {
        try {
            return commandeDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}

