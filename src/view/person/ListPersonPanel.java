package view.person;

import controller.PersonController;
import exception.DataAccessException;
import model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListPersonPanel extends JPanel {
    private final PersonController controller = new PersonController();
    private final JTable table = new JTable();
    private final DefaultTableModel model = new DefaultTableModel();

    public ListPersonPanel() {
        setLayout(new BorderLayout());

        String[] columns = {
                "ID", "Name", "First Name", "Phone", "Birth Date", "Street", "No", "Zip", "City", "Country", "Client", "Supplier"
        };
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        JButton deleteBtn = new JButton("Delete selected person");
        JButton editBtn = new JButton("Edit selected person");

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

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                try {
                    Person existing = controller.getService().getPersonById(id);
                    JFrame editFrame = new JFrame("Edit Person");
                    editFrame.setContentPane(new UpdatePersonPanel(existing, () -> {
                        try {
                            controller.loadPersons(table);
                            editFrame.dispose();
                        } catch (DataAccessException ex) {
                            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }));
                    editFrame.pack();
                    editFrame.setLocationRelativeTo(this);
                    editFrame.setVisible(true);
                } catch (DataAccessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteBtn);
        buttonPanel.add(editBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        try {
            controller.loadPersons(table);
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}