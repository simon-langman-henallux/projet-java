package view.person;

import controller.PersonController;
import exception.DataAccessException;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AddPersonPanel extends JPanel {
    private final PersonController controller = new PersonController();

    public AddPersonPanel() {
        setLayout(new BorderLayout());

        // Champs
        JTextField nameField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField phoneField = new JTextField();
        JSpinner birthDateSpinner = new JSpinner(new SpinnerDateModel());
        JTextField streetNameField = new JTextField();
        JTextField streetNumberField = new JTextField();
        JTextField zipCodeField = new JTextField();
        JTextField cityNameField = new JTextField();
        JTextField countryField = new JTextField();
        JCheckBox isClientCheck = new JCheckBox();
        JCheckBox isSupplierCheck = new JCheckBox();
        JTextField boxNumberField = new JTextField();
        JTextField accountNumberField = new JTextField();

        // Ajout des labels et champs
        add(new JLabel("Name *"));
        add(nameField);
        add(new JLabel("First Name"));
        add(firstNameField);
        add(new JLabel("Phone *"));
        add(phoneField);
        add(new JLabel("Birth Date *"));
        add(birthDateSpinner);
        add(new JLabel("Street Name *"));
        add(streetNameField);
        add(new JLabel("Street Number *"));
        add(streetNumberField);
        add(new JLabel("Zip Code *"));
        add(zipCodeField);
        add(new JLabel("City Name *"));
        add(cityNameField);
        add(new JLabel("Country *"));
        add(countryField);
        add(new JLabel("Is Client *"));
        add(isClientCheck);
        add(new JLabel("Is Supplier *"));
        add(isSupplierCheck);
        add(new JLabel("Box Number"));
        add(boxNumberField);
        add(new JLabel("Account Number"));
        add(accountNumberField);

        JButton submit = new JButton("Create");
        add(submit);

        submit.addActionListener(e -> {
            try {
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
                        Integer.parseInt(zipCodeField.getText()),
                        cityNameField.getText(),
                        countryField.getText()
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