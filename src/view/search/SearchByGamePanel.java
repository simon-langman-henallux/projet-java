package view.search;

import controller.SearchController;
import dataAccess.GameDAO;
import exception.DataAccessException;
import model.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchByGamePanel extends JPanel {
    private final SearchController controller = new SearchController();
    private final JComboBox<String> gameCombo = new JComboBox<>();
    private final JTable resultTable = new JTable();

    public SearchByGamePanel() {
        setLayout(new BorderLayout());

        // Panel haut avec combo + bouton
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select a game:"));
        topPanel.add(gameCombo);
        JButton searchBtn = new JButton("Search");
        topPanel.add(searchBtn);
        add(topPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {
                "First Name", "Last Name", "Street", "Zip Code", "City", "Country", "Currency"
        };
        resultTable.setModel(new DefaultTableModel(columns, 0));
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // Charger les titres de jeux
        try {
            GameDAO gameDAO = new GameDAO();
            List<Game> games = gameDAO.getAllGame();
            for (Game g : games) {
                gameCombo.addItem(g.getTitle());
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Failed to load games.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Action bouton
        searchBtn.addActionListener(e -> {
            String title = (String) gameCombo.getSelectedItem();
            if (title != null) {
                try {
                    List<Object[]> results = controller.searchByGame(title, resultTable);
                    DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
                    model.setRowCount(0);
                    for (Object[] row : results) {
                        model.addRow(row);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
