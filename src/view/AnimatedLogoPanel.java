package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AnimatedLogoPanel extends JPanel implements Runnable {
    private int x = 100, y = 100;
    private int dx = 2, dy = 2;
    private final int logoSize = 200;
    private final Image logo;

    public AnimatedLogoPanel() {
        int width = 100;
        int height = 100;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        logo = loadLogoImage();
        new Thread(this).start();
    }

    private Image loadLogoImage() {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/manette.jpg")));
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