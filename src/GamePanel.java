import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int colTiles = 16;
    final int rowTiles = 12;
    final int screenWidth = tileSize * colTiles;
    final int screenLength = tileSize * rowTiles;
    Thread gameThread;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenLength));
        this.setBackground(Color.BLACK); // ADD BACKGROUND
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
    }
    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();

        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
