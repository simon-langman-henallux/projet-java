package UserInterface;

import DataAccess.GameDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddGamePanel extends JPanel {
    private JTextField title, description, price, duration, releaseDate, ageRestriction;
    private JCheckBox isMultiplayer;
    private JComboBox<String> genre, platform, publisher;

    public AddGamePanel() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        formPanel.add(new JLabel("Title :"), gbc);
        gbc.gridx = 1;
        title = new JTextField("Doom : Eternal", 20);
        formPanel.add(title, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        description = new JTextField("so much gore in this game...not for people less than 86.4 year old", 20);
        formPanel.add(description, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Price :"), gbc);
        gbc.gridx = 1;
        price = new JTextField("45.0", 20);
        formPanel.add(price, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Duration :"), gbc);
        gbc.gridx = 1;
        duration = new JTextField("120", 20);
        formPanel.add(duration, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Release Date (YYYY-MM-DD) :"), gbc);
        gbc.gridx = 1;
        releaseDate = new JTextField("2025-12-31", 20);
        formPanel.add(releaseDate, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Age Restriction :"), gbc);
        gbc.gridx = 1;
        ageRestriction = new JTextField("18", 20);
        formPanel.add(ageRestriction, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Multiplayer :"), gbc);
        gbc.gridx = 1;
        isMultiplayer = new JCheckBox();
        formPanel.add(isMultiplayer, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Publisher :"), gbc);
        gbc.gridx = 1;
        publisher = new JComboBox<>(new String[]{"Ubisoft", "EA", "Activision", "Nintendo", "Sony"});
        formPanel.add(publisher, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Genre :"), gbc);
        gbc.gridx = 1;
        genre = new JComboBox<>(new String[]{"Action", "Adventure", "RPG", "Strategy", "Sport"});
        formPanel.add(genre, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Platform:"), gbc);
        gbc.gridx = 1;
        platform = new JComboBox<>(new String[]{"PC", "PS5", "Xbox", "Nintendo", "Mobile"});
        formPanel.add(platform, gbc);

        JButton addButton = new JButton("Add Game");
        addButton.addActionListener(new AddGameListener());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class AddGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Game game = new Game(
                        title.getText(),
                        Double.parseDouble(price.getText()),
                        java.sql.Date.valueOf(releaseDate.getText()),
                        description.getText(),
                        Integer.parseInt(ageRestriction.getText()),
                        isMultiplayer.isSelected(),
                        Double.parseDouble(duration.getText()),
                        (String) publisher.getSelectedItem(),
                        (String) genre.getSelectedItem(),
                        (String) platform.getSelectedItem()
                );

                GameDAO.insertGame(game);
                JOptionPane.showMessageDialog(AddGamePanel.this, "Game added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(AddGamePanel.this, "SQL Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AddGamePanel.this, "Check number fields format!");
            }
        }
    }

}