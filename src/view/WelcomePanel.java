package view;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome to the Game Store Manager", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.CENTER);
    }

}