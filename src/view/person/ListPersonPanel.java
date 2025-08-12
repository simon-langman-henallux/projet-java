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
                "id", "name", "first name", "phone", "birth date", "street", "no", "zip", "city", "country", "is a client", "is a supplier"
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
                    if (controller.hasRelatedDocuments(id)) {
                        int confirm = JOptionPane.showConfirmDialog(this,
                                "This person has related documents. Do you want to delete them too?",
                                "Confirm delete",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm != JOptionPane.YES_OPTION) {
                            return;
                        }
                        controller.deletePersonWithDocuments(id);
                    } else {
                        controller.removePerson(id);
                    }
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
                    editFrame.setContentPane(new UpdatePersonPanel(existing, this::refreshTable));
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
            refreshTable();
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        try {
            model.setRowCount(0);
            var persons = controller.getAllPersons();
            for (var p : persons) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getName(),
                        p.getFirstName(),
                        p.getPhoneNumber(),
                        p.getBirthDate(),
                        p.getStreetName(),
                        p.getStreetNumber(),
                        p.getZipCodeCity(),
                        p.getNameCity(),
                        p.getCountry(),
                        p.isClient(),
                        p.isSupplier()
                });
            }
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error refresh", JOptionPane.ERROR_MESSAGE);
        }
    }


}