import java.awt.*;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }

    public void setDefaultValues() {
        setX(100);
        setY(100); // Corrected from setX(100) to setY(100)
        setDistance(4);
    }

    public void update() {
        if (keyH.upPressed) {
            setY(getyCoord() - getDistance());
        }
        if (keyH.leftPressed) {
            setX(getxCoord() - getDistance());
        }
        if (keyH.downPressed) {
            setY(getyCoord() + getDistance());
        }
        if (keyH.rightPressed) {
            setX(getxCoord() + getDistance());
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE); // Changed to white for visibility
        g2.fillRect(getxCoord(), getyCoord(), gp.tileSize, gp.tileSize);
    }
}
