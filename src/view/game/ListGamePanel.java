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

        JButton deleteButton = getDeleteButton(model);
        JButton updateButton = getUpdateButton();
        JButton exit = new JButton("Exit");

        exit.addActionListener(e ->
                SwingUtilities.getWindowAncestor(this).dispose()
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(exit);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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

    private JButton getDeleteButton(DefaultTableModel model) {
        JButton deleteButton = new JButton("Delete selected game");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String title = table.getValueAt(selectedRow, 0).toString();
                try {
                    if (controller.hasRelatedDocumentLines(title)) {
                        int confirm = JOptionPane.showConfirmDialog(this,
                                "This game has related document lines. Do you want to delete them too?",
                                "Confirm delete",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm != JOptionPane.YES_OPTION) {
                            return;
                        }
                        controller.deleteWithDocumentLines(title);
                    } else {
                        controller.removeGame(title);
                    }

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
                    JOptionPane.showMessageDialog(this, "Game deleted.");
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