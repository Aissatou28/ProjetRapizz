package view;

import controller.PizzaController;
import model.Pizza;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PizzaPanel extends JPanel {
    private PizzaController pizzaController;
    private DefaultListModel<Pizza> pizzaListModel;
    private JList<Pizza> pizzaJList;
    private JTextField nomField;
    private JTextField prixField;


    public PizzaPanel(PizzaController controller) {
        this.pizzaController = controller;
        setLayout(new BorderLayout());

        // Liste des pizzas
        pizzaListModel = new DefaultListModel<>();
        pizzaJList = new JList<>(pizzaListModel);
        refreshPizzaList();
        add(new JScrollPane(pizzaJList), BorderLayout.CENTER);

        // Formulaire d'ajout
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Nom :"));
        nomField = new JTextField();
        formPanel.add(nomField);

        formPanel.add(new JLabel("Prix :"));
        prixField = new JTextField();
        formPanel.add(prixField);

        JButton addButton = new JButton("Ajouter");
        formPanel.add(addButton);
        JButton deleteButton = new JButton("Supprimer");
        formPanel.add(deleteButton);

        add(formPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener(e -> {
            String nom = nomField.getText();
            double prix = Double.parseDouble(prixField.getText());
            pizzaController.addPizza(nom, prix);
            refreshPizzaList();
        });

        deleteButton.addActionListener(e -> {
            Pizza selected = pizzaJList.getSelectedValue();
            if (selected != null) {
                pizzaController.deletePizza(selected.getIdPizza());
                refreshPizzaList();
            }
        });
    }

    public PizzaPanel() {

    }

    private void refreshPizzaList() {
        pizzaListModel.clear();
        List<Pizza> pizzas = pizzaController.getAllPizzas();
        for (Pizza pizza : pizzas) {
            pizzaListModel.addElement(pizza);
        }
    }
}
