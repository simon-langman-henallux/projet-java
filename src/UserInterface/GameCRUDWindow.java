package UserInterface;

import DataAccess.GameCRUD;
import Model.Game;
import Model.Genre;
import Model.Platform;
import Model.Publisher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GameCRUDWindow extends JFrame {
    private DefaultListModel<Game> gameListModel;
    private JList<Game> gameList;
    private JButton addButton, updateButton, deleteButton, refreshButton;

    public GameCRUDWindow() {
        super("Gestion des Jeux");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gameListModel = new DefaultListModel<>();
        gameList = new JList<>(gameListModel);
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addButton = new JButton("Ajouter");
        updateButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");
        refreshButton = new JButton("Actualiser");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(gameList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshList();

        addButton.addActionListener(e -> openGameForm(null));
        updateButton.addActionListener(e -> {
            Game selected = gameList.getSelectedValue();
            if (selected != null) openGameForm(selected);
            else JOptionPane.showMessageDialog(this, "Sélectionnez un jeu");
        });
        deleteButton.addActionListener(e -> deleteGame());
        refreshButton.addActionListener(e -> refreshList());

        setVisible(true);
    }

    private void refreshList() {
        try {
            gameListModel.clear();
            ArrayList<Game> games = GameCRUD.listAllGames();
            for (Game g : games) gameListModel.addElement(g);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur chargement jeux : " + e.getMessage());
        }
    }

    private void openGameForm(Game game) {
        GameForm form = new GameForm(this, game);
        form.setVisible(true);
        if (form.isSaved()) refreshList();
    }

    private void deleteGame() {
        Game selected = gameList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un jeu");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Supprimer " + selected.getTitle() + " ?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                GameCRUD.deleteGame(selected);
                refreshList();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur suppression : " + e.getMessage());
            }
        }
    }

    private class GameForm extends JDialog {
        private JTextField titleField, priceField, releaseDateField, descriptionField, ageRestrictionField, durationField;
        private JCheckBox multiplayerCheck;
        private JTextField publisherField, genreField, platformField;
        private JButton saveButton, cancelButton;
        private boolean saved = false;
        private Game game;

        public GameForm(Frame owner, Game game) {
            super(owner, true);
            this.game = game;
            setTitle(game == null ? "Ajouter Jeu" : "Modifier Jeu");
            setSize(400, 400);
            setLayout(new GridLayout(11, 2));

            add(new JLabel("Titre"));
            titleField = new JTextField();
            add(titleField);

            add(new JLabel("Prix"));
            priceField = new JTextField();
            add(priceField);

            add(new JLabel("Date sortie (yyyy-mm-dd)"));
            releaseDateField = new JTextField();
            add(releaseDateField);

            add(new JLabel("Description"));
            descriptionField = new JTextField();
            add(descriptionField);

            add(new JLabel("Restriction âge"));
            ageRestrictionField = new JTextField();
            add(ageRestrictionField);

            add(new JLabel("Multijoueur"));
            multiplayerCheck = new JCheckBox();
            add(multiplayerCheck);

            add(new JLabel("Durée"));
            durationField = new JTextField();
            add(durationField);

            add(new JLabel("Éditeur"));
            publisherField = new JTextField();
            add(publisherField);

            add(new JLabel("Genre"));
            genreField = new JTextField();
            add(genreField);

            add(new JLabel("Plateforme"));
            platformField = new JTextField();
            add(platformField);

            saveButton = new JButton("Enregistrer");
            cancelButton = new JButton("Annuler");
            add(saveButton);
            add(cancelButton);

            if (game != null) {
                titleField.setText(game.getTitle());
                priceField.setText(String.valueOf(game.getPrice()));
                releaseDateField.setText(game.getReleaseDate().toString());
                descriptionField.setText(game.getDescription());
                ageRestrictionField.setText(String.valueOf(game.getAgeRestriction()));
                multiplayerCheck.setSelected(game.isMultiplayer());
                durationField.setText(String.valueOf(game.getDuration()));
                publisherField.setText(game.getPublisher().getName());
                genreField.setText(game.getGenre().getName());
                platformField.setText(game.getPlatform().getName());
                titleField.setEnabled(false);
            }

            saveButton.addActionListener(e -> saveGame());
            cancelButton.addActionListener(e -> dispose());
        }

        private void saveGame() {
            try {
                if (game == null) game = new Game();

                game.setTitle(titleField.getText());
                game.setPrice(Double.parseDouble(priceField.getText()));
                game.setReleaseDate(java.sql.Date.valueOf(releaseDateField.getText()));
                game.setDescription(descriptionField.getText());
                game.setAgeRestriction(Integer.parseInt(ageRestrictionField.getText()));
                game.setMultiplayer(multiplayerCheck.isSelected());
                game.setDuration(Double.parseDouble(durationField.getText()));
                game.setPublisher(new Publisher(publisherField.getText()));
                game.setGenre(new Genre(genreField.getText(), ""));
                game.setPlatform(new Platform(platformField.getText()));

                if (titleField.isEnabled()) {
                    GameCRUD.insertGame(game);
                } else {
                    GameCRUD.updateGame(game);
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