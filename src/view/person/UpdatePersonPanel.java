package view.person;

import controller.PersonController;
import exception.DataAccessException;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class UpdatePersonPanel extends JPanel {
    private final PersonController controller = new PersonController();

    public UpdatePersonPanel(Person existingPerson, Runnable onUpdateCallback) {
        setLayout(new BorderLayout());

        JTextField nameField = new JTextField(existingPerson.getName());
        JTextField firstNameField = new JTextField(existingPerson.getFirstName() != null ? existingPerson.getFirstName() : "");
        JTextField phoneField = new JTextField(existingPerson.getPhoneNumber());
        JSpinner birthDateSpinner = new JSpinner(new SpinnerDateModel(existingPerson.getBirthDate(), null, null, java.util.Calendar.DAY_OF_MONTH));
        JTextField streetNameField = new JTextField(existingPerson.getStreetName());
        JTextField streetNumberField = new JTextField(String.valueOf(existingPerson.getStreetNumber()));
        JTextField zipCodeField = new JTextField(String.valueOf(existingPerson.getZipCodeCity()));
        JTextField cityNameField = new JTextField(existingPerson.getNameCity());
        JTextField countryField = new JTextField(existingPerson.getCountry());
        JCheckBox isClientCheck = new JCheckBox("", existingPerson.isClient());
        JCheckBox isSupplierCheck = new JCheckBox("", existingPerson.isSupplier());
        JTextField boxNumberField = new JTextField(existingPerson.getBoxNumber());
        JTextField accountNumberField = new JTextField(existingPerson.getAccountNumber() != null ? existingPerson.getAccountNumber() : "");

        JPanel form = new JPanel(new GridLayout(0, 2));
        form.add(new JLabel("Name *")); form.add(nameField);
        form.add(new JLabel("First Name")); form.add(firstNameField);
        form.add(new JLabel("Phone *")); form.add(phoneField);
        form.add(new JLabel("Birth Date *")); form.add(birthDateSpinner);
        form.add(new JLabel("Street Name *")); form.add(streetNameField);
        form.add(new JLabel("Street Number *")); form.add(streetNumberField);
        form.add(new JLabel("Zip Code *")); form.add(zipCodeField);
        form.add(new JLabel("City Name *")); form.add(cityNameField);
        form.add(new JLabel("Country *")); form.add(countryField);
        form.add(new JLabel("Is Client *")); form.add(isClientCheck);
        form.add(new JLabel("Is Supplier *")); form.add(isSupplierCheck);
        form.add(new JLabel("Box Number")); form.add(boxNumberField);
        form.add(new JLabel("Account Number")); form.add(accountNumberField);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            try {
                Person updated = new Person(
                        existingPerson.getId(),
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
                controller.editPerson(updated);
                JOptionPane.showMessageDialog(this, "Person updated.");
                if (onUpdateCallback != null) onUpdateCallback.run();
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(form, BorderLayout.CENTER);
        add(updateBtn, BorderLayout.SOUTH);
    }
}