package view;

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

        // Configuration du tableau
        String[] columns = {"Title", "Price", "Release Date", "Age", "Multiplayer", "Stock"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        // Bouton de suppression
        JButton deleteBtn = new JButton("Delete selected game");

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String title = table.getValueAt(row, 0).toString();
                try {
                    controller.removeGame(title);
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Game deleted.");
                } catch (DataAccessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(deleteBtn, BorderLayout.SOUTH);

        // Charger les données
        try {
            controller.loadGames(table);
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}