package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu gameMenu, personMenu, researchMenu, orderMenu;
    private JMenuItem addGameItem, viewGameItem;
    private JMenuItem addPersonItem, viewPersonItem;
    private JMenuItem ageResearchItem, gameResearchItem, byDateResearchItem;
    private JMenuItem newOrderItem;

    public MainWindow() {
        super("Gestion Magasin de Jeu Vidéo");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        setContentPane(mainContainer);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();

        // Menu Jeux
        gameMenu = new JMenu("Jeux");
        addGameItem = new JMenuItem("Ajouter un jeu");
        viewGameItem = new JMenuItem("Voir tous les jeux");
        gameMenu.add(addGameItem);
        gameMenu.add(viewGameItem);

        // Menu Personnes
        personMenu = new JMenu("Personnes");
        addPersonItem = new JMenuItem("Ajouter une personne");
        viewPersonItem = new JMenuItem("Voir toutes les personnes");
        personMenu.add(addPersonItem);
        personMenu.add(viewPersonItem);

        // Ajout des actions pour le menu Personnes
        addPersonItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Ajout de personne non implémenté"));
        viewPersonItem.addActionListener(e -> new PersonCRUDWindow());

        // Menu Recherches
        researchMenu = new JMenu("Recherches");
        ageResearchItem = new JMenuItem("Recherche par âge");
        gameResearchItem = new JMenuItem("Recherche par jeu");
        byDateResearchItem = new JMenuItem("Recherche par date");
        researchMenu.add(ageResearchItem);
        researchMenu.add(gameResearchItem);
        researchMenu.add(byDateResearchItem);

        // Menu Commandes
        orderMenu = new JMenu("Commandes");
        newOrderItem = new JMenuItem("Créer une nouvelle commande");
        orderMenu.add(newOrderItem);

        // Ajout des menus à la barre de menus
        menuBar.add(gameMenu);
        menuBar.add(personMenu);
        menuBar.add(researchMenu);
        menuBar.add(orderMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }
}