package view;

import controller.GameController;
import exception.DataAccessException;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AddGamePanel extends JPanel {
    private final GameController controller = new GameController();

    public AddGamePanel() {
        setLayout(new BorderLayout());

        JTextField titleField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField publisherField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField platformField = new JTextField();

        JSpinner releaseDateSpinner = new JSpinner(new SpinnerDateModel());
        JCheckBox multiplayerCheck = new JCheckBox();
        JTextField ageRestrictionField = new JTextField();

        add(new JLabel("Title *"));
        add(titleField);
        add(new JLabel("Price *"));
        add(priceField);
        add(new JLabel("Release Date *"));
        add(releaseDateSpinner);
        add(new JLabel("Age Restriction *"));
        add(ageRestrictionField);
        add(new JLabel("Multiplayer *"));
        add(multiplayerCheck);
        add(new JLabel("Stock *"));
        add(stockField);
        add(new JLabel("Description"));
        add(descriptionField);
        add(new JLabel("Duration"));
        add(durationField);
        add(new JLabel("Publisher *"));
        add(publisherField);
        add(new JLabel("Platform *"));
        add(platformField);
        add(new JLabel("Genre *"));
        add(genreField);

        JButton submit = new JButton("Create");
        add(submit);

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
                        publisherField.getText(),
                        genreField.getText(),
                        platformField.getText()
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