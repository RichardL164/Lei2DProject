import java.awt.*;

public class Entity {
    private int xCoord; //worldX
    private int yCoord; //worldY
    private int speed;
    public String fireDirection;
    public String waterDirection;

    public Rectangle solidArea;
    public boolean collisionOn = false; // Updated from collsionOn to collisionOn

    public int getspeed() {
        return speed;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setX(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setY(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setspeed(int speed) {
        this.speed = speed;
    }
}
