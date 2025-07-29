package UserInterface;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel(){

        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Système de gestion d'un Magasin de Jeu Vidéo");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        add(welcomeLabel, BorderLayout.NORTH);
    }
}
