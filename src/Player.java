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

    private double leftHorizontalVelocity = 0;
    private double rightHorizontalVelocity = 0;

    private final double momentum = 0.2;

    private final int groundLevel = 600;

    public Player(GamePanel gp, KeyHandler keyH, String front, boolean isFireboy) {
        this.gp = gp;
        this.keyH = keyH;
        this.isFireboy = isFireboy;
        setDefaultValues();
        solidArea = new Rectangle(0, 0, 50, 62);
        try {
            this.front = ImageIO.read(new File(front));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setDefaultValues() {
        setX(100);
        setY(groundLevel);
        setspeed(3);
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
        if (keyH.leftPressedFire && getxCoord() > 0) {
            if (leftHorizontalVelocity >= getspeed()) {
                setX(getxCoord() - getspeed());
            } else {
                leftHorizontalVelocity += momentum;
                setX(getxCoord() - (int) leftHorizontalVelocity);
            }
        }
        if (!keyH.leftPressedFire) {
            leftHorizontalVelocity = 0;
        }
        if (keyH.rightPressedFire && getxCoord() < gp.screenWidth) {
            if (rightHorizontalVelocity >= getspeed()) {
                setX(getxCoord() + getspeed());
            } else {
                rightHorizontalVelocity += momentum;
                setX(getxCoord() + (int) rightHorizontalVelocity);
            }
        }
        if (!keyH.rightPressedFire) {
            rightHorizontalVelocity = 0;
        }

    }

    public void updateWatergirl() {
        if (keyH.upPressedWater && !isJumping) {
            verticalVelocity = jumpStrength;
            isJumping = true;
        }
        if (keyH.leftPressedWater && getxCoord() > 0) {
            if (leftHorizontalVelocity >= getspeed()) {
                setX(getxCoord() - getspeed());
            } else {
                leftHorizontalVelocity += momentum;
                setX(getxCoord() - (int) leftHorizontalVelocity);
            }
        }
        if (!keyH.leftPressedWater) {
            leftHorizontalVelocity = 0;
        }
        if (keyH.rightPressedWater && getxCoord() < gp.screenWidth  ) {
            if (rightHorizontalVelocity >= getspeed()) {
                setX(getxCoord() + getspeed());
            } else {
                rightHorizontalVelocity += momentum;
                setX(getxCoord() + (int) rightHorizontalVelocity);
            }
        }
        if (!keyH.rightPressedWater) {
            rightHorizontalVelocity = 0;
        }
        collsionOn = false;
        gp.cChecker.checkTile(this);
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
