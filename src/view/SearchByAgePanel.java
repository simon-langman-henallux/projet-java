package view;

import controller.SearchController;
import dataAccess.CountryDAO;
import exception.DataAccessException;
import model.Country;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchByAgePanel extends JPanel {
    private final SearchController controller = new SearchController();
    private final JComboBox<String> countryCombo = new JComboBox<>();
    private final JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 120, 1));
    private final JTable resultTable = new JTable();

    public SearchByAgePanel() {
        setLayout(new BorderLayout());

        // Critères
        JPanel top = new JPanel();
        top.add(new JLabel("Country:"));
        top.add(countryCombo);
        top.add(new JLabel("Min Age:"));
        top.add(ageSpinner);
        JButton searchBtn = new JButton("Search");
        top.add(searchBtn);
        add(top, BorderLayout.NORTH);

        // Table
        String[] columns = {"Name", "First Name", "Birth Date", "Game Title", "Age Restriction", "Genre"};
        resultTable.setModel(new DefaultTableModel(columns, 0));
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // Charger pays
        try {
            CountryDAO dao = new CountryDAO();
            List<Country> countries = dao.getAllCountries();
            for (Country c : countries) {
                countryCombo.addItem(c.getName());
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Failed to load countries.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Recherche
        searchBtn.addActionListener(e -> {
            String country = (String) countryCombo.getSelectedItem();
            int age = (Integer) ageSpinner.getValue();
            if (country != null) {
                try {
                    List<Object[]> results = controller.searchByAge(age, country);
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