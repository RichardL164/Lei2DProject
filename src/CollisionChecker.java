import java.awt.Rectangle;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkCollision(Entity entity, Rectangle obstacle) {
        Rectangle hitbox = entity.solidArea;
        return hitbox.intersects(obstacle);
    }
}
