import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int colTiles = 16;
    final int rowTiles = 12;
    final int screenWidth = tileSize * colTiles;
    final int screenLength = tileSize * rowTiles;
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player fireboy = new Player(this, keyH, "src/fireboy.png", true);
    Player watergirl = new Player(this, keyH, "src/scaled watergirl.png", false);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenLength));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow(); // Request focus to capture key events
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timeInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / timeInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        fireboy.update();
        watergirl.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fireboy.drawImage(g);
        watergirl.drawImage(g);
    }
}
