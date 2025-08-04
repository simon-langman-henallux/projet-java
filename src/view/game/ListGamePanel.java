package view.game;

import controller.GameController;
import exception.DataAccessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListGamePanel extends JPanel {
    private final GameController controller = new GameController();
    private final JTable table = new JTable();
    private final DefaultTableModel model = new DefaultTableModel();

    public ListGamePanel() {
        setLayout(new BorderLayout());

        String[] columns = {"Title", "Price", "Release Date", "Age", "Multiplayer", "Stock"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        JButton deleteButton = new JButton("Delete selected game");

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String title = table.getValueAt(selectedRow, 0).toString();
                try {
                    controller.removeGame(title);
                    controller.loadGames(table); // recharge la JTable
                } catch (DataAccessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        try {
            controller.loadGames(table);
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton updateButton = new JButton("Update selected game");

        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String title = table.getValueAt(selectedRow, 0).toString();
                try {
                    var game = controller.getService().getGameByTitle(title);
                    if (game != null) {
                        JFrame frame = new JFrame("Edit Game");
                        frame.setContentPane(new UpdateGame(game, controller, table));
                        frame.pack();
                        frame.setLocationRelativeTo(this);
                        frame.setVisible(true);
                    }
                } catch (DataAccessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }
}