package view.game;

import controller.GameController;
import exception.DataAccessException;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class UpdateGame extends JPanel {

    public UpdateGame(Game game, GameController controller, JTable tableToRefresh) {

        String originalTitle = game.getTitle();

        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField titleField = new JTextField(game.getTitle());
        JTextField priceField = new JTextField(String.valueOf(game.getPrice()));
        JTextField descriptionField = new JTextField(game.getDescription() == null ? "" : game.getDescription());
        JTextField durationField = new JTextField(String.valueOf(game.getDuration()));
        JTextField stockField = new JTextField(String.valueOf(game.getStock()));
        JTextField ageRestrictionField = new JTextField(String.valueOf(game.getAgeRestriction()));
        JCheckBox multiplayerCheck = new JCheckBox();
        multiplayerCheck.setSelected(game.isMultiplayer());
        JSpinner releaseDateSpinner = new JSpinner(new SpinnerDateModel());
        releaseDateSpinner.setValue(game.getReleaseDate());

        JComboBox<String> publisherBox = new JComboBox<>();
        JComboBox<String> genreBox = new JComboBox<>();
        JComboBox<String> platformBox = new JComboBox<>();

        try {
            List<String> publishers = controller.getAllPublisherNames();
            for (String name : publishers) {
                publisherBox.addItem(name);
            }
            publisherBox.setSelectedItem(game.getPublisher());
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading publishers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            List<String> genres = controller.getGenreNames();
            for (String name : genres) {
                genreBox.addItem(name);
            }
            genreBox.setSelectedItem(game.getGenre());
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error loading genres: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            List<String> platforms = controller.getPlatformNames();
            for (String name : platforms) {
                platformBox.addItem(name);
            }
            platformBox.setSelectedItem(game.getPlatform());
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

        JButton submit = new JButton("Update");
        form.add(new JLabel()); form.add(submit);

        add(form, BorderLayout.CENTER);

        submit.addActionListener(e -> {
            try {
                Game updated = new Game(
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
                controller.editGame(updated, originalTitle);
                controller.loadGames(tableToRefresh);
                JOptionPane.showMessageDialog(this, "Game updated.");
                SwingUtilities.getWindowAncestor(this).dispose();
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}