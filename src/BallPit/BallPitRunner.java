package BallPit;

/**
 *
 * @author yaod5171
 */
import javax.swing.JFrame;
import java.awt.Component;

public class BallPitRunner extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public BallPitRunner() {
        super("BallPit");
        setSize(WIDTH, HEIGHT);

        BallPit pit = new BallPit();
        ((Component) pit).setFocusable(true);

        getContentPane().add(pit);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        BallPitRunner run = new BallPitRunner();
    }
}
