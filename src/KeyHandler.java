import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    boolean upPressedFire;
    boolean leftPressedFire;
    boolean rightPressedFire;
    boolean upPressedWater;
    boolean leftPressedWater;
    boolean rightPressedWater;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressedWater = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressedWater = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressedWater = true;
        }
        if (code == KeyEvent.VK_UP) {
            upPressedFire = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressedFire = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressedFire = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressedWater = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressedWater = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressedWater = false;
        }
        if (code == KeyEvent.VK_UP) {
            upPressedFire = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressedFire = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressedFire = false;
        }
    }
}
