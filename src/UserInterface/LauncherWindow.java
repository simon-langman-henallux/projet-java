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
        super("Gestion Magasin de Jeu Vidéo");
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

        gameMenu = new JMenu("Jeux");

        addGameItem = new JMenuItem("Ajouter un jeu");
        //Ajouter action vers le menu
        gameMenu.add(addGameItem);

        viewGameItem = new JMenuItem("Voir tous les jeux");
        //Ajouter action vers le menu
        gameMenu.add(viewGameItem);

        personMenu = new JMenu("Personnes");

        addPersonItem = new JMenuItem("Ajouter une personne");
        //Ajouter action vers le menu
        personMenu.add(addPersonItem);

        viewPersonItem = new JMenuItem("Voir toutes les personnes");
        //Ajouter action vers le menu
        personMenu.add(viewPersonItem);

        researchMenu = new JMenu("Recherches");

        ageResearchItem = new JMenuItem("Recherche par âge");
        //Ajouter action vers le menu
        researchMenu.add(ageResearchItem);

        gameResearchItem = new JMenuItem("Recherche par jeu");
        //Ajouter action vers le menu
        researchMenu.add(gameResearchItem);

        byDateResearchItem = new JMenuItem("Recherche par date");
        //Ajouter action vers le menu
        researchMenu.add(byDateResearchItem);

        orderMenu = new JMenu("Commandes");

        newOrderItem = new JMenuItem("Créer une nouvelle commande");
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