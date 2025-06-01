package view;

import controller.ClientController;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ClientPanel extends JPanel {
    private ClientController clientController;
    private JTextArea displayArea;

    public ClientPanel() {
        try {
            clientController = new ClientController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setLayout(new BorderLayout());

        JButton afficherBtn = new JButton("Afficher tous les clients");
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        afficherBtn.addActionListener(e -> afficherClients());

        add(afficherBtn, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    private void afficherClients() {
        try {
            List<Client> clients = clientController.getAllClients();
            StringBuilder sb = new StringBuilder();
            for (Client c : clients) {
                sb.append(c).append("\n");
            }
            displayArea.setText(sb.toString());
        } catch (Exception ex) {
            displayArea.setText("Erreur lors de la récupération des clients.");
            ex.printStackTrace();
        }
    }
}
