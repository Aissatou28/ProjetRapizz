package view;

import controller.CommandeController;
import model.Commande;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CommandePanel extends JPanel {
    private CommandeController commandeController;
    private DefaultListModel<Commande> commandeListModel;
    private JList<Commande> commandeJList;

    public CommandePanel(CommandeController controller) {
        this.commandeController = controller;
        setLayout(new BorderLayout());

        commandeListModel = new DefaultListModel<>();
        commandeJList = new JList<>(commandeListModel);
        refreshCommandeList();

        add(new JScrollPane(commandeJList), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Actualiser");
        add(refreshButton, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> refreshCommandeList());
    }

    public CommandePanel() {

    }

    private void refreshCommandeList() {
        commandeListModel.clear();
        List<Commande> commandes = commandeController.getAllCommandes();
        for (Commande commande : commandes) {
            commandeListModel.addElement(commande);
        }
    }
}

