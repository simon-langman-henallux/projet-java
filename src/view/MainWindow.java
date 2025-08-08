package view;

import view.animation.AnimatedLogoPanel;
import view.animation.BackgroundPanel;
import view.game.AddGamePanel;
import view.game.ListGamePanel;
import view.person.AddPersonPanel;
import view.person.ListPersonPanel;
import view.search.SearchByAgePanel;
import view.search.SearchByDatePanel;
import view.search.SearchByGamePanel;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {

        setTitle("Game Store Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        JLabel welcome = new JLabel("Welcome to Game Store Manager");
        welcome.setBounds(10, 10, width, height);

        AnimatedLogoPanel animatedLogo = new AnimatedLogoPanel();
        animatedLogo.setBounds(0, 0, width, height);
        backgroundPanel.add(animatedLogo);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu gameManager = new JMenu("game manager");
        JMenuItem addGame = new JMenuItem("add game");
        JMenuItem listGame = new JMenuItem("list all games");
        menuBar.add(gameManager);
        gameManager.add(addGame);
        addGame.addActionListener(e -> openWindowWithPanel(new AddGamePanel(), "Add Game"));
        gameManager.add(listGame);
        listGame.addActionListener(e -> openWindowWithPanel(new ListGamePanel(), "List Games"));

        JMenu personManager = new JMenu("person manager");
        JMenuItem addPerson = new JMenuItem("add person");
        JMenuItem listPerson = new JMenuItem("list all persons");
        menuBar.add(personManager);
        personManager.add(addPerson);
        addPerson.addActionListener(e -> openWindowWithPanel(new AddPersonPanel(), "Add Person"));
        personManager.add(listPerson);
        listPerson.addActionListener(e -> openWindowWithPanel(new ListPersonPanel(), "List Persons"));

        JMenu searchManager = new JMenu("search manager");
        JMenuItem searchByAge = new JMenuItem("search by age");
        JMenuItem searchByDate = new JMenuItem("search by date");
        JMenuItem searchByGame = new JMenuItem("search by game");
        menuBar.add(searchManager);
        searchManager.add(searchByAge);
        searchByAge.addActionListener(e -> openWindowWithPanel(new SearchByAgePanel(), "Search by Age"));
        searchManager.add(searchByDate);
        searchByDate.addActionListener(e -> openWindowWithPanel(new SearchByDatePanel(), "Search by Date"));
        searchManager.add(searchByGame);
        searchByGame.addActionListener(e -> openWindowWithPanel(new SearchByGamePanel(), "Search by Game"));

        JMenuItem exit = new JMenuItem("exit");
        menuBar.add(exit);
        exit.addActionListener(e -> System.exit(0));

    }

    private void openWindowWithPanel(JPanel panel, String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}