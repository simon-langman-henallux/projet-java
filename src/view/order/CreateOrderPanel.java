package view.order;

import controller.OrderController;
import controller.GameController;
import controller.PersonController;
import dataAccess.order.OrderLineDAO;
import exception.DataAccessException;
import model.Game;
import model.Order;
import model.OrderLine;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class CreateOrderPanel extends JPanel {

    private final JComboBox<Person> cbPersons;
    private final JComboBox<Game> cbGames;
    private final JTextField quantityField;
    private final OrderController orderController;
    private final GameController gameController;
    private final PersonController personController;

    public CreateOrderPanel(OrderController orderController, GameController gameController, PersonController personController) {
        this.orderController = orderController;
        this.gameController = gameController;
        this.personController = personController;

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Select Person:"));
        cbPersons = new JComboBox<>();
        loadPersons();
        add(cbPersons);

        add(new JLabel("Select Game:"));
        cbGames = new JComboBox<>();
        loadGames();
        add(cbGames);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        JPanel buttonPanel = new JPanel();
        JButton btnOrder = new JButton("Order");
        JButton exit = new JButton("Exit");
        buttonPanel.add(btnOrder);
        buttonPanel.add(exit);
        add(buttonPanel);

        btnOrder.addActionListener(e -> createOrder());
        exit.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
    }

    private void loadPersons() {
        try {
            List<Person> persons = personController.getAllPersons();
            for (Person p : persons) cbPersons.addItem(p);
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading persons: " + e.getMessage());
        }
    }

    private void loadGames() {
        try {
            cbGames.removeAllItems();
            List<Game> games = gameController.getAllGames();
            for (Game g : games) {
                if (g.getStock() > 0) cbGames.addItem(g);
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading games: " + e.getMessage());
        }
    }

    private void createOrder() {
        Person person = (Person) cbPersons.getSelectedItem();
        Game game = (Game) cbGames.getSelectedItem();

        try {
            int quantity = Integer.parseInt(quantityField.getText());

            if (person == null || game == null) {
                JOptionPane.showMessageDialog(this, "Select person and game.");
                return;
            }
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be > 0.");
                return;
            }

            int stock = game.getStock();
            if (stock == 0) {
                JOptionPane.showMessageDialog(this, "Out of stock.");
                return;
            }

            if (quantity > stock) {
                int choice = JOptionPane.showConfirmDialog(this,
                        "Only " + stock + " in stock. Order all and set stock to 0?",
                        "Low stock", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) quantity = stock; else return;
            }

            Order order = new Order();
            order.setReference("REF-" + System.currentTimeMillis());
            order.setDate(new Date());
            order.setPaymentMethod("Cash");
            order.setFinalized(true);
            order.setType("Order");
            order.setPerson(person.getId());
            orderController.add(order);

            OrderLineDAO dao = new OrderLineDAO();
            if (dao.exists(game.getTitle(), order.getReference())) {
                dao.updateQuantity(game.getTitle(), order.getReference(), quantity);
            } else {
                dao.insert(new OrderLine(game.getTitle(), order.getReference(), game.getPrice(), quantity));
            }

            game.setStock(stock - quantity);
            gameController.editGame(game, game.getTitle());

            JOptionPane.showMessageDialog(this, "Order created successfully.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity.");
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            return;
        }

        try {
            loadGames();
            quantityField.setText("");
        } catch (Exception ignored) {}
    }
}