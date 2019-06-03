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

    public Wall(String data) {
        super(Double.parseDouble(data.split(",")[1]),
                Double.parseDouble(data.split(",")[2]));
        width = Integer.parseInt(data.split(",")[3]);
        height = Integer.parseInt(data.split(",")[4]);
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

    /**
     * Draw the wall to a window
     *
     * @param window the window to draw to
     */
    public void draw(Graphics window) {
        window.setColor(Color.GRAY);
        window.fillRect(xPos, yPos, getWidth(), getHeight());
    }

    /**
     * Return a string, used to save the wall.
     * 
     * @return the wall data
     */
    public String toString() {
        String out = "W,";
        out += (getX() + ",");
        out += (getY() + ",");
        out += (width + ",");
        out += (height);
        return out;
    }

}
