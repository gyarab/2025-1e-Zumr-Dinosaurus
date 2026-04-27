import javax.swing.JFrame;
import java.awt.*;

public class DinoGameActivation extends JFrame {
    static void main(String[] args) {
        JFrame frame = new JFrame("Java Dino Runner");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        {

        }
    }
}


