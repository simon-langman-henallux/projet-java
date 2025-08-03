package view;

import javax.swing.*;
import java.awt.*;

public class LauncherWindow extends JFrame {
    private final CardLayout layout;
    private final JPanel container;

    public LauncherWindow() {
        setTitle("Game Store Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Gestion des panneaux (card layout)
        layout = new CardLayout();
        container = new JPanel(layout);
        setContentPane(container);

        // Ajouter les panels disponibles
        container.add(new WelcomePanel(), "welcome");

        // Menu principal
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem welcomeItem = new JMenuItem("Welcome");
        welcomeItem.addActionListener(e -> layout.show(container, "welcome"));
        menu.add(welcomeItem);

        // Affichage initial
        layout.show(container, "welcome");
    }

    public void addPanel(JPanel panel, String name) {
        container.add(panel, name);
    }

    public void showPanel(String name) {
        layout.show(container, name);
    }
}