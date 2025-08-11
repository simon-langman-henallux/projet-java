package view.search;

import controller.SearchController;
import exception.DataAccessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class SearchByDatePanel extends JPanel {

    private final SearchController controller = new SearchController();
    private final JComboBox<String> personBox = new JComboBox<>();
    private final JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
    private final JTable table = new JTable();

    public SearchByDatePanel() {
        setLayout(new BorderLayout());

        JPanel criteria = new JPanel(new FlowLayout(FlowLayout.LEFT));
        criteria.add(new JLabel("Client"));
        criteria.add(personBox);
        criteria.add(new JLabel("Before date"));
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        criteria.add(dateSpinner);
        JButton searchBtn = new JButton("Search");
        criteria.add(searchBtn);
        add(criteria, BorderLayout.NORTH);

        String[] cols = {"Title", "Date", "Quantity", "Unit Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        table.setModel(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        try {
            List<Object[]> persons = controller.getPersons();
            for (Object[] p : persons) {
                personBox.addItem(p[0] + " - " + p[1] + " " + p[2]);
            }
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        searchBtn.addActionListener(e -> {
            String selected = (String) personBox.getSelectedItem();
            if (selected == null) return;
            int personId = Integer.parseInt(selected.split(" - ")[0]);
            Date toDate = (Date) dateSpinner.getValue();
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            try {
                List<Object[]> rows = controller.searchGamesBoughtBefore(personId, toDate);
                for (Object[] r : rows) {
                    m.addRow(new Object[]{r[0], r[1], r[2], r[3]});
                }
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}