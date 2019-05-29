package BallPit;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author yaod5171
 */
public class Wall extends PhysicsObject {

    private int width, height;

    public Wall(int x, int y, int width, int height) { //only define walls by int; no need for double
        super(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    public void draw(Graphics window) {
        window.setColor(Color.GRAY);
        window.fillRect(xPos, yPos, getWidth(), getHeight());
    }

}
