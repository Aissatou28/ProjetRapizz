package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;

    public MainFrame() {
        setTitle("RaPizz - Gestion des commandes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // centrer la fenêtre

        tabbedPane = new JTabbedPane();

        // Ajout des panels par onglet
        tabbedPane.add("Clients", new ClientPanel());
        tabbedPane.add("Pizzas", new PizzaPanel());
        tabbedPane.add("Commandes", new CommandePanel());
        // tu peux en ajouter d’autres ici plus tard

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
