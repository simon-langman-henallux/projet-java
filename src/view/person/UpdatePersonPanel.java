package view.person;

import controller.PersonController;
import controller.SearchController;
import exception.DataAccessException;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

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
        JCheckBox isClientCheck = new JCheckBox("", existingPerson.isClient());
        JCheckBox isSupplierCheck = new JCheckBox("", existingPerson.isSupplier());
        JTextField boxNumberField = new JTextField(existingPerson.getBoxNumber() != null ? existingPerson.getBoxNumber().toString() : "");
        JTextField accountNumberField = new JTextField(existingPerson.getAccountNumber() != null ? existingPerson.getAccountNumber() : "");

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

        JPanel form = new JPanel(new GridLayout(0, 2));
        form.add(new JLabel("Name : ")); form.add(nameField);
        form.add(new JLabel("First Name : ")); form.add(firstNameField);
        form.add(new JLabel("Phone : ")); form.add(phoneField);
        form.add(new JLabel("Birth Date : ")); form.add(birthDateSpinner);
        form.add(new JLabel("Street Name  :")); form.add(streetNameField);
        form.add(new JLabel("Street Number : ")); form.add(streetNumberField);
        form.add(new JLabel("City : ")); form.add(cityCombo);
        form.add(new JLabel("Is Client : ")); form.add(isClientCheck);
        form.add(new JLabel("Is Supplier : ")); form.add(isSupplierCheck);
        form.add(new JLabel("Box Number : ")); form.add(boxNumberField);
        form.add(new JLabel("Account Number : ")); form.add(accountNumberField);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            try {
                String selected = (String) cityCombo.getSelectedItem();
                assert selected != null;
                String[] parts = selected.split(" - ");
                int zipCode = Integer.parseInt(parts[0]);
                String cityName = parts[1];
                String country = parts[2];

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
                        zipCode,
                        cityName,
                        country
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

        JButton exit =  new JButton("Exit");

        exit.addActionListener(e ->
                SwingUtilities.getWindowAncestor(this).dispose()
        );

        add(form, BorderLayout.CENTER);
        add(updateBtn, BorderLayout.SOUTH);
        add(exit , BorderLayout.SOUTH);



    }
}