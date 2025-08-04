package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackgroundPanel extends JPanel {
    private final Image image;

    public BackgroundPanel() {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background.jpg"))).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}