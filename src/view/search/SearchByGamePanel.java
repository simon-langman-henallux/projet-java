package view.search;

import controller.SearchController;
import exception.DataAccessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchByGamePanel extends JPanel {

    private final SearchController controller = new SearchController();
    private final JComboBox<String> gameBox = new JComboBox<>();
    private final JTable table = new JTable();

    public SearchByGamePanel() {

        setLayout(new BorderLayout());

        JButton exit = new JButton("Exit");
        exit.addActionListener(e ->
                SwingUtilities.getWindowAncestor(this).dispose()
        );

        JPanel criteria = new JPanel(new FlowLayout(FlowLayout.LEFT));
        criteria.add(new JLabel("Game"));
        criteria.add(gameBox);
        JButton searchBtn = new JButton("Search");


        criteria.add(searchBtn);
        add(criteria, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exit);
        add(buttonPanel, BorderLayout.SOUTH);

        String[] cols = {"First Name", "Last Name", "Street", "Zip Code", "City", "Country", "Currency"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        table.setModel(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        try {
            List<Object[]> games = controller.getGames();
            for (Object[] g : games) {
                gameBox.addItem((String) g[0]);
            }
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        searchBtn.addActionListener(e -> {
            String title = (String) gameBox.getSelectedItem();
            if (title == null) return;
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            try {
                List<Object[]> rows = controller.searchByGameTitle(title);
                for (Object[] r : rows) {
                    m.addRow(new Object[]{r[0], r[1], r[2], r[3], r[4], r[5], r[6]});
                }
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}