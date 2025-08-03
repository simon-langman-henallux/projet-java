package view;

import javax.swing.*;
import java.awt.*;

public class LauncherWindow extends JFrame {
    private final CardLayout layout;
    private final JPanel container;

    public LauncherWindow() {
        setTitle("Game Store Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        container = new JPanel(layout);
        setContentPane(container);

        // Ajouter les panels disponibles
        container.add(new WelcomePanel(), "welcome");
        container.add(new AnimatedLogoPanel(), "logo");

        // Menu principal
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem welcomeItem = new JMenuItem("Welcome");
        welcomeItem.addActionListener(e -> layout.show(container, "welcome"));
        menu.add(welcomeItem);

        JMenuItem logoItem = new JMenuItem("Animated Logo");
        logoItem.addActionListener(e -> {
            layout.show(container, "logo");

            Timer timer = new Timer(5000, ev -> layout.show(container, "welcome"));
            timer.setRepeats(false);
            timer.start();
        });
        menu.add(logoItem);

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