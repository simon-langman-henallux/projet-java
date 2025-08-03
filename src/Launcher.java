import javax.swing.SwingUtilities;
import view.LauncherWindow;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LauncherWindow().setVisible(true);
        });
    }
}