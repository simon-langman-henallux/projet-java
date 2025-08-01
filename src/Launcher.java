import javax.swing.SwingUtilities;
import View.LauncherWindow;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LauncherWindow().setVisible(true);
        });
    }
}