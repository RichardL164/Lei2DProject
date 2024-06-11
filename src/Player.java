import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    private BufferedImage front;
    private boolean isFireboy;

    private boolean isJumping = false;
    private double verticalVelocity = 0;
    private final double gravity = 0.5;
    private final double jumpStrength = -12;

    private boolean blueWin = false;
    private boolean redWin = false;

    private double leftHorizontalVelocity = 0;
    private double rightHorizontalVelocity = 0;

    private final double momentum = 0.2;

    private final int groundLevel = 744;

    public Player(GamePanel gp, KeyHandler keyH, String front, boolean isFireboy) {
        this.gp = gp;
        this.keyH = keyH;
        this.isFireboy = isFireboy;
        setDefaultValues();
        solidArea = new Rectangle(getxCoord() + 3, getyCoord() + 3, 42, 68); // Initialize hitbox here
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
        // Initialize hitbox position
        if (solidArea != null) {
            solidArea.setLocation(getxCoord(), getyCoord());
        }
    }



    public void update() {
        if (isFireboy) {
            updateFireboy();
        } else {
            updateWatergirl();
        }
        applyGravity();
        // Update hitbox position
        updateHitbox();
        checkTileCollision();
        moveHorizontally();
        if (isTouchingGreen()) {
            setDefaultValues();
        }
        if (!isFireboy && isBlueWin()) {
            blueWin = true;
        }
        if (isFireboy && isRedWin()) {
            redWin = true;
        }
    }


    private void moveHorizontally() {
        // Move horizontally based on keyboard input
        if (isFireboy) {
            if (keyH.leftPressedFire && getxCoord() > 0 && !isCollidingLeft()) {
                setX(getxCoord() - getspeed());
            } else if (keyH.rightPressedFire && getxCoord() < gp.screenWidth - solidArea.width && !isCollidingRight()) {
                setX(getxCoord() + getspeed());
            }
        } else {
            if (keyH.leftPressedWater && getxCoord() > 0 && !isCollidingLeft()) {
                setX(getxCoord() - getspeed());
            } else if (keyH.rightPressedWater && getxCoord() < gp.screenWidth - solidArea.width && !isCollidingRight()) {
                setX(getxCoord() + getspeed());
            }
        }
    }

    private boolean isCollidingLeft() {
        // Check if the sprite is colliding with a wall to the left
        int leftTileIndex = (int) (getxCoord() / gp.tileSize);
        int row = (int) (getyCoord() / gp.tileSize);
        return gp.tileM.getTileType(leftTileIndex, row) == 1; // Assuming tile type 1 represents a wall
    }

    private boolean isCollidingRight() {
        // Check if the sprite is colliding with a wall to the right
        int rightTileIndex = (int) ((getxCoord() + solidArea.width) / gp.tileSize);
        int row = (int) (getyCoord() / gp.tileSize);
        return gp.tileM.getTileType(rightTileIndex, row) == 1; // Assuming tile type 1 represents a wall
    }

    private void checkTileCollision() {
        for (Rectangle hitbox : gp.tileM.getHitboxes()) {
            if (solidArea.intersects(hitbox)) {
                // Stop horizontal movement upon collision
                if (keyH.leftPressedFire && getxCoord() + solidArea.width > hitbox.x) {
//                    setX(hitbox.x + hitbox.width);
                    leftHorizontalVelocity = 0;
                } else if (keyH.rightPressedFire && getxCoord() < hitbox.x + hitbox.width) {
//                    setX(hitbox.x - solidArea.width);
                    rightHorizontalVelocity = 0;
                }

                // Stop vertical movement upon collision
                if (getyCoord() + solidArea.height > hitbox.y && getyCoord() < hitbox.y + hitbox.height) {
                    if (verticalVelocity > 0) {
                        // Falling
                        setY(hitbox.y - solidArea.height);
                        isJumping = false;
                        verticalVelocity = 0;
                    } else if (verticalVelocity < 0) {
                        // Jumping up
                        setY(hitbox.y + hitbox.height);
                        verticalVelocity = 0;
                    }
                }
            }
        }
    }


    public void updateFireboy() {
        if (keyH.upPressedFire && !isJumping) {
            verticalVelocity = jumpStrength;
            isJumping = true;
            fireDirection = "up";
        }
        if (keyH.leftPressedFire && getxCoord() > 0) {
            if (leftHorizontalVelocity >= getspeed()) {
                setX(getxCoord() - getspeed());
            } else {
                leftHorizontalVelocity += momentum;
                setX(getxCoord() - (int) leftHorizontalVelocity);
            }
            fireDirection = "left";
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
            fireDirection = "right";
        }
        if (!keyH.rightPressedFire) {
            rightHorizontalVelocity = 0;
        }
    }

    public void updateWatergirl() {
        if (keyH.upPressedWater && !isJumping) {
            verticalVelocity = jumpStrength;
            isJumping = true;
            waterDirection = "up";
        }
        if (keyH.leftPressedWater && getxCoord() > 0) {
            if (leftHorizontalVelocity >= getspeed()) {
                setX(getxCoord() - getspeed());
            } else {
                leftHorizontalVelocity += momentum;
                setX(getxCoord() - (int) leftHorizontalVelocity);
            }
            waterDirection = "left";
        }
        if (!keyH.leftPressedWater) {
            leftHorizontalVelocity = 0;
        }
        if (keyH.rightPressedWater && getxCoord() < gp.screenWidth) {
            if (rightHorizontalVelocity >= getspeed()) {
                setX(getxCoord() + getspeed());
            } else {
                rightHorizontalVelocity += momentum;
                setX(getxCoord() + (int) rightHorizontalVelocity);
            }
            waterDirection = "right";
        }
        if (!keyH.rightPressedWater) {
            rightHorizontalVelocity = 0;
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
        boolean standingOnTile = false;
        for (Rectangle hitbox : gp.tileM.getHitboxes()) {
            if (solidArea.intersects(hitbox) && getyCoord() + solidArea.height == hitbox.y) {
                standingOnTile = true;
                break;
            }
        }

        if (!standingOnTile) {
            // Apply gravity if not standing on a tile
            setY(getyCoord() + (int) verticalVelocity);
            verticalVelocity += gravity;
            if (getyCoord() >= groundLevel) {
                setY(groundLevel);
                isJumping = false;
                verticalVelocity = 0;
            }
        }

    }

    public boolean isTouchingGreen() {
        if (getxCoord() > 430 && getxCoord() < 526 && getyCoord() >= 744) {
            return true;
        } else if (getxCoord() > 624 && getxCoord() < 720 && getyCoord() >= 744) {
            return true;
        } else if (getxCoord() > 600 && getxCoord() < 680 && getyCoord() == 460) {
            return true;
        }
        return false;
    }

    public boolean isBlueWin() {
        if (getxCoord() >= 39 && getxCoord() <= 69 && getyCoord() == 76) {
            return true;
        }
        return false;
    }
    public boolean isRedWin() {
        if (getxCoord() >= 120 && getxCoord() <= 145 && getyCoord() == 76) {
            return true;
        }
        return false;
    }
    private void updateHitbox() {
        solidArea.setLocation(getxCoord() + 5, getyCoord() + 3);
    }

    public void drawImage(Graphics g) {
        g.drawImage(front, getxCoord(), getyCoord(), null);

        // Draw hitbox for debugging
        if (collisionOn) {
            g.setColor(Color.RED); // Change color to red if collision detected
        } else {
            g.setColor(Color.GREEN); // Default color
        }
        g.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }
}
