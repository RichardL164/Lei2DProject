import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    private BufferedImage front;

    public Player(GamePanel gp, KeyHandler keyH, String front) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        try {
            this.front = ImageIO.read(new File(front));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void setDefaultValues() {
        setX(100);
        setY(100);
        setDistance(4);
    }

    public void updateFireboy() {
        if (keyH.upPressedFire) {
            setY(getyCoord() - getDistance());
        }
        if (keyH.leftPressedFire) {
            setX(getxCoord() - getDistance());
        }
        if (keyH.downPressedFire) {
            setY(getyCoord() + getDistance());
        }
        if (keyH.rightPressedFire) {
            setX(getxCoord() + getDistance());
        }
    }
    public void updateWatergirl() {
        if (keyH.upPressedWater) {
            setY(getyCoord() - getDistance());
        }
        if (keyH.leftPressedWater) {
            setX(getxCoord() - getDistance());
        }
        if (keyH.downPressedWater) {
            setY(getyCoord() + getDistance());
        }
        if (keyH.rightPressedWater) {
            setX(getxCoord() + getDistance());
        }
    }

    public void drawImage(Graphics g2) {
        g2.drawImage(front, getxCoord(), getyCoord(), null);
    }
}
