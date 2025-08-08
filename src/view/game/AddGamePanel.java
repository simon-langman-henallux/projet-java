package view.game;

import controller.GameController;
import exception.DataAccessException;
import model.Game;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Date;

public class AddGamePanel extends JPanel {

    private final GameController controller = new GameController();

    public AddGamePanel() {

        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField titleField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField ageRestrictionField = new JTextField();
        JCheckBox multiplayerCheck = new JCheckBox();
        JSpinner releaseDateSpinner = new JSpinner(new SpinnerDateModel());

        JComboBox<String> publisherBox = new JComboBox<>();
        JComboBox<String> genreBox = new JComboBox<>();
        JComboBox<String> platformBox = new JComboBox<>();

        try {
            List<String> publishers = controller.getAllPublisherNames();
            for (String name : publishers) {
                publisherBox.addItem(name);
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading publishers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            List<String> genres = controller.getGenreNames();
            for (String name : genres) {
                genreBox.addItem(name);
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading genres: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            List<String> platforms = controller.getPlatformNames();
            for (String name : platforms) {
                platformBox.addItem(name);
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading platforms: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        form.add(new JLabel("Title : ")); form.add(titleField);
        form.add(new JLabel("Price : ")); form.add(priceField);
        form.add(new JLabel("Release Date : ")); form.add(releaseDateSpinner);
        form.add(new JLabel("Age Restriction : ")); form.add(ageRestrictionField);
        form.add(new JLabel("Multiplayer : ")); form.add(multiplayerCheck);
        form.add(new JLabel("Stock : ")); form.add(stockField);
        form.add(new JLabel("Description : ")); form.add(descriptionField);
        form.add(new JLabel("Duration : ")); form.add(durationField);
        form.add(new JLabel("Publisher : ")); form.add(publisherBox);
        form.add(new JLabel("Platform : ")); form.add(platformBox);
        form.add(new JLabel("Genre : ")); form.add(genreBox);

        titleField.setText("DOOM");
        priceField.setText("20");
        releaseDateSpinner.setModel(new SpinnerDateModel());
        ageRestrictionField.setText("20");
        multiplayerCheck.setSelected(true);
        stockField.setText("25");
        descriptionField.setText("so cool !!!");
        durationField.setText("100");
        publisherBox.setSelectedIndex(0);
        platformBox.setSelectedIndex(0);
        genreBox.setSelectedIndex(0);

        JButton submit = new JButton("Create");
        form.add(new JLabel()); form.add(submit);

        add(form, BorderLayout.CENTER);

        submit.addActionListener(e -> {
            try {
                Game game = new Game(
                        titleField.getText(),
                        Double.parseDouble(priceField.getText()),
                        (Date) releaseDateSpinner.getValue(),
                        descriptionField.getText().isBlank() ? null : descriptionField.getText(),
                        Integer.parseInt(ageRestrictionField.getText()),
                        multiplayerCheck.isSelected(),
                        durationField.getText().isBlank() ? 0 : Double.parseDouble(durationField.getText()),
                        Integer.parseInt(stockField.getText()),
                        publisherBox.getSelectedItem().toString(),
                        genreBox.getSelectedItem().toString(),
                        platformBox.getSelectedItem().toString()
                );
                controller.createGame(game);
                JOptionPane.showMessageDialog(this, "Game added.");
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}