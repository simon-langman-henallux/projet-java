package view;

import javax.swing.*;
import java.awt.*;

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

        BackgroundPanel background = new BackgroundPanel();
        background.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        AnimatedLogoPanel animatedLogo = new AnimatedLogoPanel();
        animatedLogo.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(animatedLogo, JLayeredPane.PALETTE_LAYER);

        layout = new CardLayout();
        container = new JPanel(layout);
        container.setOpaque(false);
        container.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(container, JLayeredPane.MODAL_LAYER);

        container.add(new AddGamePanel(), "addGame");
        container.add(new ListGamePanel(), "listGame");
        container.add(new AddPersonPanel(), "addPerson");
        container.add(new ListPersonPanel(), "listPerson");
        container.add(new SearchByGamePanel(), "searchGame");
        container.add(new SearchByAgePanel(), "searchAge");
        container.add(new SearchByDatePanel(), "searchDate");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem listGames = new JMenuItem("List Games");
        listGames.addActionListener(e -> layout.show(container, "listGame"));
        menu.add(listGames);

        JMenuItem addGame = new JMenuItem("Add Game");
        addGame.addActionListener(e -> layout.show(container, "addGame"));
        menu.add(addGame);

        JMenuItem listPersons = new JMenuItem("List Persons");
        listPersons.addActionListener(e -> layout.show(container, "listPerson"));
        menu.add(listPersons);

        JMenuItem addPerson = new JMenuItem("Add Person");
        addPerson.addActionListener(e -> layout.show(container, "addPerson"));
        menu.add(addPerson);

        JMenu search = new JMenu("Search");
        menu.add(search);

        JMenuItem searchGame = new JMenuItem("By Game");
        searchGame.addActionListener(e -> layout.show(container, "searchGame"));
        search.add(searchGame);

        JMenuItem searchAge = new JMenuItem("By Age & Country");
        searchAge.addActionListener(e -> layout.show(container, "searchAge"));
        search.add(searchAge);

        JMenuItem searchDate = new JMenuItem("By Date");
        searchDate.addActionListener(e -> layout.show(container, "searchDate"));
        search.add(searchDate);

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(e -> System.exit(0));
        menu.add(quit);

        layout.show(container, "listGame");
    }
}