package view.order;

import controller.GameController;
import dataAccess.order.OrderLineDAO;
import exception.DataAccessException;
import model.Game;
import model.Order;
import model.OrderLine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddOrderLinePanel extends JPanel {

    private final Order order;
    private final GameController gameController;
    private final JComboBox<Game> cbGames;
    private final JTextField quantityField;

    public AddOrderLinePanel(Order order, GameController gameController) {
        this.order = order;
        this.gameController = gameController;

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Game:"));
        cbGames = new JComboBox<>();
        loadGames();
        add(cbGames);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add line");
        JButton exitBtn = new JButton("Exit");
        buttons.add(addBtn);
        buttons.add(exitBtn);
        add(buttons);

        addBtn.addActionListener(e -> addLine());
        exitBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
    }

    private void loadGames() {
        try {
            cbGames.removeAllItems();
            List<Game> games = gameController.getAllGames();
            for (Game g : games) if (g.getStock() > 0) cbGames.addItem(g);
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading games: " + e.getMessage());
        }
    }

    private void addLine() {
        try {
            Game game = (Game) cbGames.getSelectedItem();
            if (game == null) {
                JOptionPane.showMessageDialog(this, "Select a game.");
                return;
            }
            int q = Integer.parseInt(quantityField.getText());
            if (q <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be > 0.");
                return;
            }
            int stock = game.getStock();
            if (stock == 0) {
                JOptionPane.showMessageDialog(this, "Out of stock.");
                return;
            }
            if (q > stock) {
                int c = JOptionPane.showConfirmDialog(this, "Only " + stock + " in stock. Add all and set stock to 0?", "Low stock", JOptionPane.YES_NO_OPTION);
                if (c == JOptionPane.YES_OPTION) q = stock; else return;
            }

            OrderLineDAO dao = new OrderLineDAO();
            if (dao.exists(game.getTitle(), order.getReference())) {
                dao.updateQuantity(game.getTitle(), order.getReference(), q);
            } else {
                dao.insert(new OrderLine(game.getTitle(), order.getReference(), game.getPrice(), q));
            }

            new OrderLineDAO().insert(new OrderLine(game.getTitle(), order.getReference(), game.getPrice(), q));
            game.setStock(stock - q);
            gameController.editGame(game, game.getTitle());

            JOptionPane.showMessageDialog(this, "Line added.");
            quantityField.setText("");
            loadGames();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}