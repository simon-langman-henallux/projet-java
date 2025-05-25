package UserInterface;

import DataAccess.PersonCRUD;
import Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonCRUDWindow extends JFrame {
    private DefaultListModel<Person> personListModel;
    private JList<Person> personList;
    private JButton addButton, updateButton, deleteButton, refreshButton;

    public PersonCRUDWindow() {
        super("Gestion des Personnes");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        personListModel = new DefaultListModel<>();
        personList = new JList<>(personListModel);
        personList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addButton = new JButton("Ajouter");
        updateButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");
        refreshButton = new JButton("Actualiser");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(personList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshList();

        addButton.addActionListener(e -> openPersonForm(null));
        updateButton.addActionListener(e -> {
            Person selected = personList.getSelectedValue();
            if (selected != null) openPersonForm(selected);
            else JOptionPane.showMessageDialog(this, "Sélectionnez une personne");
        });
        deleteButton.addActionListener(e -> deletePerson());
        refreshButton.addActionListener(e -> refreshList());

        setVisible(true);
    }

    private void refreshList() {
        try {
            personListModel.clear();
            ArrayList<Person> persons = PersonCRUD.listPersons();
            for (Person p : persons) personListModel.addElement(p);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur chargement personnes : " + e.getMessage());
        }
    }

    private void openPersonForm(Person person) {
        PersonForm form = new PersonForm(this, person);
        form.setVisible(true);
        if (form.isSaved()) refreshList();
    }

    private void deletePerson() {
        Person selected = personList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une personne");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Supprimer " + selected.getName() + " ?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                PersonCRUD.deletePerson(selected.getName());
                refreshList();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur suppression : " + e.getMessage());
            }
        }
    }

    private class PersonForm extends JDialog {
        private JTextField nameField, firstNameField, phoneField, birthDateField, boxNumberField;
        private JTextField accountNumberField, streetNameField, zipCodeField, cityNameField, countryField;
        private JCheckBox clientCheck, supplierCheck;
        private JButton saveButton, cancelButton;
        private boolean saved = false;
        private Person person;

        public PersonForm(Frame owner, Person person) {
            super(owner, true);
            this.person = person;
            setTitle(person == null ? "Ajouter Personne" : "Modifier Personne");
            setSize(400, 450);
            setLayout(new GridLayout(13, 2));

            add(new JLabel("Nom"));
            nameField = new JTextField();
            add(nameField);

            add(new JLabel("Prénom"));
            firstNameField = new JTextField();
            add(firstNameField);

            add(new JLabel("Téléphone"));
            phoneField = new JTextField();
            add(phoneField);

            add(new JLabel("Date de naissance (yyyy-mm-dd)"));
            birthDateField = new JTextField();
            add(birthDateField);

            add(new JLabel("Boîte (numéro)"));
            boxNumberField = new JTextField();
            add(boxNumberField);

            add(new JLabel("Numéro compte"));
            accountNumberField = new JTextField();
            add(accountNumberField);

            add(new JLabel("Nom rue"));
            streetNameField = new JTextField();
            add(streetNameField);

            add(new JLabel("Client"));
            clientCheck = new JCheckBox();
            add(clientCheck);

            add(new JLabel("Fournisseur"));
            supplierCheck = new JCheckBox();
            add(supplierCheck);

            add(new JLabel("Code postal"));
            zipCodeField = new JTextField();
            add(zipCodeField);

            add(new JLabel("Nom ville"));
            cityNameField = new JTextField();
            add(cityNameField);

            add(new JLabel("Pays"));
            countryField = new JTextField();
            add(countryField);

            saveButton = new JButton("Enregistrer");
            cancelButton = new JButton("Annuler");
            add(saveButton);
            add(cancelButton);

            if (person != null) {
                nameField.setText(person.getName());
                firstNameField.setText(person.getFirstName());
                phoneField.setText(person.getPhoneNumber());
                birthDateField.setText(person.getBirthDate().toString());
                boxNumberField.setText(person.getBoxNumber() == 0 ? "" : String.valueOf(person.getBoxNumber()));
                accountNumberField.setText(person.getAccountNumber());
                streetNameField.setText(person.getStreetName());
                clientCheck.setSelected(person.isClient());
                supplierCheck.setSelected(person.isSupplier());
                zipCodeField.setText(String.valueOf(person.getZipCodeCity()));
                cityNameField.setText(person.getNameCity());
                countryField.setText(person.getCountry());
                nameField.setEnabled(false);
            }

            saveButton.addActionListener(e -> savePerson());
            cancelButton.addActionListener(e -> dispose());
        }

        private void savePerson() {
            try {
                if (person == null) person = new Person();

                person.setName(nameField.getText());
                person.setFirstName(firstNameField.getText());
                person.setPhoneNumber(phoneField.getText());
                person.setBirthDate(java.sql.Date.valueOf(birthDateField.getText()));

                String boxStr = boxNumberField.getText().trim();
                if (boxStr.isEmpty()) person.setBoxNumber(0);
                else person.setBoxNumber(person.getBoxNumber() + Integer.parseInt(boxStr));

                person.setAccountNumber(accountNumberField.getText());
                person.setStreetName(streetNameField.getText());
                person.setClient(clientCheck.isSelected());
                person.setSupplier(supplierCheck.isSelected());
                person.setZipCodeCity(Integer.parseInt(zipCodeField.getText()));
                person.setNameCity(cityNameField.getText());
                person.setCountry(countryField.getText());

                if (nameField.isEnabled()) {
                    PersonCRUD.insertPerson(person);
                } else {
                    PersonCRUD.updatePerson(person);
                }
                saved = true;
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        }

        public boolean isSaved() {
            return saved;
        }
    }
}