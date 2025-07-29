package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class LauncherWindow extends JFrame {
    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu gameMenu, personMenu, researchMenu, orderMenu;
    private JMenuItem addGameItem, viewGameItem;
    private JMenuItem addPersonItem, viewPersonItem;
    private JMenuItem ageResearchItem, gameResearchItem, byDateResearchItem;
    private JMenuItem newOrderItem;

    public LauncherWindow(){
        super("GMS (Games Management Software)");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        setContentPane(mainContainer);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        menuBar = new JMenuBar();

        gameMenu = new JMenu("Games");

        addGameItem = new JMenuItem("Add game");
        //Ajouter action vers le menu
        gameMenu.add(addGameItem);
        addGameItem.addActionListener(e -> {
            JFrame frame = new JFrame("Add Game");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);
            frame.add(new AddGamePanel());
            frame.setVisible(true);
        });

        viewGameItem = new JMenuItem("See all games");
        //Ajouter action vers le menu
        gameMenu.add(viewGameItem);

        personMenu = new JMenu("Person");

        addPersonItem = new JMenuItem("Add a person");
        //Ajouter action vers le menu
        personMenu.add(addPersonItem);

        viewPersonItem = new JMenuItem("List the persons");
        //Ajouter action vers le menu
        personMenu.add(viewPersonItem);

        researchMenu = new JMenu("Search");

        ageResearchItem = new JMenuItem("Search by age");
        //Ajouter action vers le menu
        researchMenu.add(ageResearchItem);

        gameResearchItem = new JMenuItem("Search by game name");
        //Ajouter action vers le menu
        researchMenu.add(gameResearchItem);

        byDateResearchItem = new JMenuItem("Search by date");
        //Ajouter action vers le menu
        researchMenu.add(byDateResearchItem);

        orderMenu = new JMenu("Order(s)");

        newOrderItem = new JMenuItem("Create new order");
        //Ajouter action vers le menu
        orderMenu.add(newOrderItem);

        menuBar.add(gameMenu);
        menuBar.add(personMenu);
        menuBar.add(researchMenu);
        menuBar.add(orderMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }


}