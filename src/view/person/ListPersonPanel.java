package view.person;

import controller.PersonController;
import exception.DataAccessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListPersonPanel extends JPanel {
    private final PersonController controller = new PersonController();
    private final JTable table = new JTable();
    private final DefaultTableModel model = new DefaultTableModel();

    public ListPersonPanel() {
        setLayout(new BorderLayout());

        // Colonnes
        String[] columns = {
                "ID", "Name", "First Name", "Phone", "Birth Date", "Street", "No", "Zip", "City", "Country", "Client", "Supplier"
        };
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        // Bouton de suppression
        JButton deleteBtn = new JButton("Delete selected person");

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                try {
                    controller.removePerson(id);
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Person deleted.");
                } catch (DataAccessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(deleteBtn, BorderLayout.SOUTH);

        // Chargement initial
        try {
            controller.loadPersons(table);
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
