import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    boolean upPressedFire;
    boolean leftPressedFire;
    boolean downPressedFire;
    boolean rightPressedFire;
    boolean upPressedWater;
    boolean leftPressedWater;
    boolean downPressedWater;
    boolean rightPressedWater;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressedFire = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressedFire = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressedFire = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressedFire = true;
        }
        if (code == KeyEvent.VK_UP) {
            upPressedWater = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressedWater = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressedWater = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressedWater = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressedFire = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressedFire = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressedFire = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressedFire = false;
        }
        if (code == KeyEvent.VK_UP) {
            upPressedWater = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressedWater = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressedWater = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressedWater = false;
        }
    }
}
