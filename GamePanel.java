import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    // Nastavení okna
    final int WIDTH = 800;
    final int HEIGHT = 300;
    Timer timer;

    // Hráč (Dino)
    int dinoY = 250;
    int dinoYSpeed = 20;
    final int DINO_X = 50;
    final int DINO_SIZE = 40;

    // Překážka (Kaktus)
    int cactusX = 800;
    final int CACTUS_Y = 250;
    final int CACTUS_WIDTH = 20;
    final int CACTUS_HEIGHT = 40;

    private int score = 0;
    private boolean isGameOver = false;

    private Image dinoImage;
    private Image cactusImage;


    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(this);

        // Načtení obrázků
        dinoImage = new ImageIcon(getClass().getResource("/dino.png")).getImage();
        cactusImage = new ImageIcon(getClass().getResource("/cactus.png")).getImage();
        // Herní smyčka (cca 60 FPS)
        timer = new Timer(16, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vykreslení země
        g.setColor(Color.GRAY);
        g.drawLine(0, 290, WIDTH, 290);

        // Vykreslení Dinosaura
        g.drawImage(dinoImage, DINO_X, dinoY, DINO_SIZE, DINO_SIZE, null);


        // Vykreslení kaktusu

        g.drawImage(cactusImage, cactusX, CACTUS_Y, CACTUS_WIDTH, CACTUS_HEIGHT, null);

        // Skóre
        g.setColor(Color.BLACK);
        g.drawString("Skóre: " + score, 10, 20);

        if (isGameOver) {
            g.drawString("GAME OVER! Stiskni R pro restart.", WIDTH / 2 - 100, HEIGHT / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            // Gravitace a skok
            dinoY += dinoYSpeed;
            if (dinoY < 250) {
                dinoYSpeed += 1; // gravitace
            } else {
                dinoY = 250;
                dinoYSpeed = 0;
            }

            // Pohyb překážky
            cactusX -= 7;
            if (cactusX < -20) {
                cactusX = 800;
                score++;
            }

            // Detekce kolize
            Rectangle dinoBounds = new Rectangle(DINO_X, dinoY, DINO_SIZE, DINO_SIZE);
            Rectangle cactusBounds = new Rectangle(cactusX, CACTUS_Y, CACTUS_WIDTH, CACTUS_HEIGHT);

            if (dinoBounds.intersects(cactusBounds)) {
                isGameOver = true;
                timer.stop();
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Skok mezerníkem
        if (e.getKeyCode() == KeyEvent.VK_SPACE && dinoY == 250) {
            dinoYSpeed = -15;
        }
        // Restart hry
        if (e.getKeyCode() == KeyEvent.VK_R && isGameOver) {
            restartGame();
        }
    }

    private void restartGame() {
        dinoY = 250;
        cactusX = 800;
        score = 0;
        isGameOver = false;
        timer.start();
    }



    // Povinné metody pro KeyListener
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}


