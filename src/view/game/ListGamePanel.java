package view.game;

import controller.GameController;
import exception.DataAccessException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListGamePanel extends JPanel {

    private final GameController controller = new GameController();
    private final JTable table = new JTable();

    public ListGamePanel() {

        setLayout(new BorderLayout());
        String[] columns = {"title", "price", "release date", "age minimum", "multiplayer", "duration", "stock", "publisher", "genre", "platform"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        JButton deleteButton = getDeleteButton();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        try {
            var games = controller.getAllGames();
            for (var g : games) {
                model.addRow(new Object[]{
                        g.getTitle(),
                        g.getPrice(),
                        g.getReleaseDate(),
                        g.getAgeRestriction(),
                        g.isMultiplayer(),
                        g.getDuration(),
                        g.getStock(),
                        g.getPublisher(),
                        g.getGenre(),
                        g.getPlatform()
                });
            }
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton updateButton = getUpdateButton();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private JButton getUpdateButton() {
        JButton updateButton = new JButton("Update selected game");

        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String title = table.getValueAt(selectedRow, 0).toString();
                try {
                    var game = controller.getService().getGameByTitle(title);
                    if (game != null) {
                        JFrame frame = new JFrame("Edit Game");
                        frame.setContentPane(new UpdateGamePanel(game, controller, table));
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
        return updateButton;
    }

    private JButton getDeleteButton() {
        JButton deleteButton = new JButton("Delete selected game");

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String title = table.getValueAt(selectedRow, 0).toString();
                try {
                    controller.removeGame(title);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);

                    var games = controller.getAllGames();
                    for (var g : games) {
                        model.addRow(new Object[]{
                                g.getTitle(),
                                g.getPrice(),
                                g.getReleaseDate(),
                                g.getAgeRestriction(),
                                g.isMultiplayer(),
                                g.getDuration(),
                                g.getStock(),
                                g.getPublisher(),
                                g.getGenre(),
                                g.getPlatform()
                        });
                    }

                } catch (DataAccessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        });
        return deleteButton;
    }

}