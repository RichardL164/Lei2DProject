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

    private double horizontalVelocity = 0;
    private final double momentum = 0.2;

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
        setY(groundLevel);
        setspeed(4);
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
            if (horizontalVelocity >= getspeed()) {
                setX(getxCoord() - getspeed());
            } else {
                horizontalVelocity += momentum;
                setX(getxCoord() - (int) horizontalVelocity);
            }
        }
        if (!keyH.leftPressedFire) {
            horizontalVelocity = 0;
        }
        if (keyH.rightPressedFire) {
            setX(getxCoord() + getspeed());
        }
//        if (keyH.rightPressedFire) {
//            if (horizontalVelocity >= getspeed()) {
//                setX(getxCoord() + getspeed());
//            } else {
//                horizontalVelocity += momentum;
//                setX(getxCoord() + (int) horizontalVelocity);
//            }
//        }
//        if (!keyH.rightPressedFire) {
//            horizontalVelocity = 0;
//        }

    }

    public void updateWatergirl() {
        if (keyH.upPressedWater && !isJumping) {
            verticalVelocity = jumpStrength;
            isJumping = true;
        }
        if (keyH.leftPressedWater) {
            if (horizontalVelocity >= getspeed()) {
                setX(getxCoord() - getspeed());
            } else {
                horizontalVelocity += momentum;
                setX(getxCoord() - (int) horizontalVelocity);
            }
        }
        if (!keyH.leftPressedWater) {
            horizontalVelocity = 0;
        }
//        if (keyH.rightPressedWater) {
//            setX(getxCoord() + getspeed());
//        }
        if (keyH.rightPressedWater) {
            if (horizontalVelocity >= getspeed()) {
                setX(getxCoord() + getspeed());
            } else {
                horizontalVelocity += momentum;
                setX(getxCoord() + (int) horizontalVelocity);
            }
        }
        if (!keyH.rightPressedWater) {
            horizontalVelocity = 0;
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
