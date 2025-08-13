package view.animation;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AnimatedLogoPanel extends JPanel {

    private int x = 100, y = 100;
    private int dx = 2, dy = 2;
    private final int logoSize = 200;
    private final Image logo;

    public AnimatedLogoPanel() {
        setBackground(Color.BLACK);
        setOpaque(false);
        logo = loadLogoImage();
        Timer timer = new Timer(10, e -> moveLogo());
        timer.start();
    }

    private Image loadLogoImage() {
        String path = new File("manette.jpg").getAbsolutePath();
        ImageIcon icon = new ImageIcon(path);
        return icon.getImage().getScaledInstance(logoSize, logoSize, Image.SCALE_SMOOTH);
    }

    private void moveLogo() {
        x += dx;
        y += dy;
        if (x < 0 || x + logoSize > getWidth()) dx = -dx;
        if (y < 0 || y + logoSize > getHeight()) dy = -dy;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logo, x, y, this);
    }
}