package view.search;

import controller.SearchController;
import exception.DataAccessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class SearchByAgePanel extends JPanel {

    private final SearchController controller = new SearchController();
    private final JComboBox<String> countryBox = new JComboBox<>();
    private final JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
    private final JTable table = new JTable();

    public SearchByAgePanel() {
        setLayout(new BorderLayout());

        JPanel criteria = new JPanel(new FlowLayout(FlowLayout.LEFT));
        criteria.add(new JLabel("country"));
        criteria.add(countryBox);
        criteria.add(new JLabel("age limit"));
        criteria.add(ageSpinner);
        JButton searchBtn = new JButton("Search");
        criteria.add(searchBtn);
        add(criteria, BorderLayout.NORTH);

        String[] cols = {"last name", "first name", "birth date", "age", "game title", "age restriction", "genre"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        table.setModel(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        try {
            for (String c : controller.getCountries()) {
                countryBox.addItem(c);
            }
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        searchBtn.addActionListener(e -> {
            String country = (String) countryBox.getSelectedItem();
            int ageLimit = (Integer) ageSpinner.getValue();
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            try {
                var rows = controller.searchGamesByAgeAndCountry(ageLimit, country);
                for (Object[] r : rows) {
                    String lastName = String.valueOf(r[0]);
                    String firstName = String.valueOf(r[1]);
                    Date bd = (Date) r[2];
                    int age = computeAge(bd);
                    String title = String.valueOf(r[3]);
                    Object ageRestr = r[4];
                    String genre = String.valueOf(r[5]);
                    m.addRow(new Object[]{lastName, firstName, bd, age, title, ageRestr, genre});
                }
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton exit = new JButton("Exit");
        exit.addActionListener(e ->
                SwingUtilities.getWindowAncestor(this).dispose()
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exit);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private int computeAge(Date birthDate) {
        if (birthDate == null) return 0;
        LocalDate bd;
        if (birthDate instanceof java.sql.Date) {
            bd = ((java.sql.Date) birthDate).toLocalDate();
        } else {
            bd = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return Period.between(bd, LocalDate.now()).getYears();
    }

}