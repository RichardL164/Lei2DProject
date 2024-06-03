import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    private BufferedImage front;
    private boolean isFireboy;
    private boolean isJumping = false;
    private double verticalVelocity = 0;
    private final double gravity = 0.5;
    private final double jumpStrength = -10;
    private final int groundLevel = 300;

    public Player(GamePanel gp, KeyHandler keyH, String front, boolean isFireboy) {
        this.gp = gp;
        this.keyH = keyH;
        this.isFireboy = isFireboy;
        setDefaultValues();
        try {
            this.front = ImageIO.read(new File(front));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setDefaultValues() {
        setX(100);
        setY(groundLevel); // Start at ground level
        setDistance(4);
    }

    public void update() {
        if (isFireboy) {
            updateFireboy();
        } else {
            updateWatergirl();
        }
        applyGravity();
    }

    public void updateFireboy() {
        if (keyH.upPressedFire && !isJumping) {
            verticalVelocity = jumpStrength;
            isJumping = true;
        }
        if (keyH.leftPressedFire) {
            setX(getxCoord() - getDistance());
        }
        if (keyH.rightPressedFire) {
            setX(getxCoord() + getDistance());
        }
    }

    public void updateWatergirl() {
        if (keyH.upPressedWater && !isJumping) {
            verticalVelocity = jumpStrength;
            isJumping = true;
        }
        if (keyH.leftPressedWater) {
            setX(getxCoord() - getDistance());
        }
        if (keyH.rightPressedWater) {
            setX(getxCoord() + getDistance());
        }
    }

    public void applyGravity() {
        if (isJumping) {
            setY(getyCoord() + (int) verticalVelocity);
            verticalVelocity += gravity;
            if (getyCoord() >= groundLevel) {
                setY(groundLevel);
                isJumping = false;
                verticalVelocity = 0;
            }
        }
    }

    public void drawImage(Graphics g) {
        g.drawImage(front, getxCoord(), getyCoord(), null);
    }
}
