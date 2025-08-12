package view.person;

import controller.PersonController;
import controller.SearchController;
import exception.DataAccessException;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class AddPersonPanel extends JPanel {
    private final PersonController controller = new PersonController();

    public AddPersonPanel() {

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField nameField = new JTextField("Dupont");
        JTextField firstNameField = new JTextField("Jean");
        JTextField phoneField = new JTextField("0123456789");
        JSpinner birthDateSpinner = new JSpinner(new SpinnerDateModel());
        ((JSpinner.DateEditor) birthDateSpinner.getEditor()).getFormat().applyPattern("yyyy-MM-dd");
        birthDateSpinner.setValue(new Date());
        JTextField streetNameField = new JTextField("Rue de Paris");
        JTextField streetNumberField = new JTextField("10");
        JCheckBox isClientCheck = new JCheckBox();
        isClientCheck.setSelected(true);
        JCheckBox isSupplierCheck = new JCheckBox();
        JTextField boxNumberField = new JTextField();
        JTextField accountNumberField = new JTextField();

        JComboBox<String> cityCombo = new JComboBox<>();
        try {
            SearchController searchController = new SearchController();
            List<Object[]> cities = searchController.getCities();
            for (Object[] c : cities) {
                String zip = (String) c[0];
                String city = (String) c[1];
                String country = (String) c[2];
                cityCombo.addItem(zip + " - " + city + " - " + country);
            }
        } catch (DataAccessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        formPanel.add(new JLabel("Name : "));
        formPanel.add(nameField);
        formPanel.add(new JLabel("First Name : "));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Phone : "));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Birth Date : "));
        formPanel.add(birthDateSpinner);
        formPanel.add(new JLabel("Street Name : "));
        formPanel.add(streetNameField);
        formPanel.add(new JLabel("Street Number : "));
        formPanel.add(streetNumberField);
        formPanel.add(new JLabel("City : "));
        formPanel.add(cityCombo);
        formPanel.add(new JLabel("Is Client : "));
        formPanel.add(isClientCheck);
        formPanel.add(new JLabel("Is Supplier : "));
        formPanel.add(isSupplierCheck);
        formPanel.add(new JLabel("Box Number : "));
        formPanel.add(boxNumberField);
        formPanel.add(new JLabel("Account Number : "));
        formPanel.add(accountNumberField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton submit = new JButton("Create");
        JButton exit = new JButton("Exit");
        buttonPanel.add(submit);
        buttonPanel.add(exit);
        add(buttonPanel, BorderLayout.SOUTH);

        exit.addActionListener(e ->
                SwingUtilities.getWindowAncestor(this).dispose()
        );

        submit.addActionListener(e -> {
            try {
                String selected = (String) cityCombo.getSelectedItem();
                if (selected == null) return;
                String[] parts = selected.split(" - ");
                String zipCode = parts[0];
                String cityName = parts[1];
                String country = parts[2];

                Person p = new Person(
                        0,
                        nameField.getText(),
                        firstNameField.getText().isBlank() ? null : firstNameField.getText(),
                        phoneField.getText(),
                        (Date) birthDateSpinner.getValue(),
                        boxNumberField.getText().isBlank() ? null : Integer.parseInt(boxNumberField.getText()),
                        accountNumberField.getText().isBlank() ? null : accountNumberField.getText(),
                        streetNameField.getText(),
                        Integer.parseInt(streetNumberField.getText()),
                        isClientCheck.isSelected(),
                        isSupplierCheck.isSelected(),
                        Integer.parseInt(zipCode),
                        cityName,
                        country
                );
                controller.createPerson(p);
                JOptionPane.showMessageDialog(this, "Person added.");
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}