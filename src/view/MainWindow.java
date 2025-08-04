package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainWindow extends JFrame {
    private final CardLayout layout;
    private final JPanel container;

    public MainWindow() {
        setTitle("Game Store Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        layeredPane.setLayout(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        AnimatedLogoPanel animatedLogo = new AnimatedLogoPanel();
        animatedLogo.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(animatedLogo, JLayeredPane.PALETTE_LAYER);

        layout = new CardLayout();
        container = new JPanel(layout);
        container.setOpaque(false);
        container.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(container, JLayeredPane.MODAL_LAYER);

        container.add(new AddGamePanel(), "add game");
        container.add(new ListGamePanel(), "list all game");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem welcome= new JMenuItem("Welcome");
        welcome.addActionListener(e -> layout.show(container, "welcome"));
        menu.add(welcome);

        JMenuItem addGame = new JMenuItem("Add Game");
        addGame.addActionListener(e -> layout.show(container, "add game"));
        menu.add(addGame);

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(e -> System.exit(0));
        menu.add(quit);

        layout.show(container, "welcome");
    }

    public static class BackgroundPanel extends JPanel {
        private final Image background;

        public BackgroundPanel() {
            background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background.jpg"))).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

}