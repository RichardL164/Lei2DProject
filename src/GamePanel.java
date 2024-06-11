import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int colTiles = 26;
    public final int rowTiles = 18;
    public final int screenWidth = tileSize * colTiles;
    public final int screenLength = tileSize * rowTiles;
    int FPS = 60;


    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    CollisionChecker cChecker = new CollisionChecker(this);
    Player fireboy = new Player(this, keyH, "src/fireboy.png", true);
    Player watergirl = new Player(this, keyH, "src/watergirl.png", false);

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

        // Check for collisions (example with a tile hitbox)
        Rectangle tileHitbox = new Rectangle(350, 50, 20, 100); // Example obstacle
        fireboy.collisionOn = cChecker.checkCollision(fireboy, tileHitbox);
        watergirl.collisionOn = cChecker.checkCollision(watergirl, tileHitbox);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tileM.draw(g);
        g.setFont(new Font("Courier New", Font.BOLD, 40));
        if (fireboy.isRedWin()){
            g.setColor(Color.RED);
            g.drawString("FIREBOY WINS", 300, 100);
        }
        if (watergirl.isBlueWin()){
            g.setColor(Color.blue);
            g.drawString("WATERGIRL WINS", 300, 150);
        }


//        g.drawImage(tileM.tile[8].image, 350, 50, 20, 100, null); // wall
        g.drawImage(tileM.tile[10].image, 50, 75, 75, 75, null); // blue door
        g.drawImage(tileM.tile[11].image, 125, 75, 75, 75, null); //red door
        g.drawImage(tileM.tile[4].image, 480, 815, 96, 48, null); // red lava
        g.drawImage(tileM.tile[4].image, 672, 815, 96, 48, null); // green lava
        g.drawImage(tileM.tile[4].image, 624, 527, 96, 48, null); // blue lava
//        g.drawImage(tileM.tile[3].image, 240, 135, 48, 30, null); // floor button
//        g.drawImage(tileM.tile[3].image, 816, 180, 48, 30, null); // floor button
        fireboy.drawImage(g);
        watergirl.drawImage(g);

        // Draw example tile hitbox for visualization
//        g.setColor(Color.RED);
//        g.drawRect(350, 50, 20, 100);
    }
}
