package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AnimatedLogoPanel extends JPanel implements Runnable {
    private final int width = 100;
    private final int height = 100;
    private int x = 100, y = 100;
    private int dx = 2, dy = 2;
    private final int logoSize = 120;
    private final Image logo;

    public AnimatedLogoPanel() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        logo = loadLogoImage();
        new Thread(this).start();
    }

    private Image loadLogoImage() {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/AlienCat.jpg")));
        return icon.getImage().getScaledInstance(logoSize, logoSize, Image.SCALE_SMOOTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logo, x, y, this);
    }

    @Override
    public void run() {
        while (true) {
            x += dx;
            y += dy;

            if (x < 0 || x + logoSize > getWidth()) dx = -dx;
            if (y < 0 || y + logoSize > getHeight()) dy = -dy;

            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }
    }
}