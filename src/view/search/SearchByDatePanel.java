package view.search;

import controller.SearchController;
import dataAccess.PersonDAO;
import exception.DataAccessException;
import model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class SearchByDatePanel extends JPanel {
    private final SearchController controller = new SearchController();
    private final JComboBox<Person> personCombo = new JComboBox<>();
    private final JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
    private final JTable resultTable = new JTable();

    public SearchByDatePanel() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Client:"));
        top.add(personCombo);
        top.add(new JLabel("Before date:"));
        top.add(dateSpinner);
        JButton searchBtn = new JButton("Search");
        top.add(searchBtn);
        add(top, BorderLayout.NORTH);

        String[] columns = {"Title", "Date", "Quantity", "Unit Price"};
        resultTable.setModel(new DefaultTableModel(columns, 0));
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        try {
            PersonDAO personDAO = new PersonDAO();
            List<Person> people = personDAO.getAllPerson();
            for (Person p : people) {
                if (p.isClient()) {
                    personCombo.addItem(p);
                }
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Failed to load clients.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        searchBtn.addActionListener(e -> {
            Person p = (Person) personCombo.getSelectedItem();
            Date to = (Date) dateSpinner.getValue();
            if (p != null && to != null) {
                try {
                    List<Object[]> results = controller.searchByDate(p.getId(), to);
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

        JButton exit = new JButton("exit");
        exit.addActionListener(e ->
                SwingUtilities.getWindowAncestor(this).dispose()
        );
        add(new JLabel());
        add(exit);

    }
}