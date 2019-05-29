package BallPit;

import java.awt.Graphics;

/**
 *
 * @author yaod5171
 */
public abstract class PhysicsObject {

    private double x;
    private double y;
    protected int xPos;
    protected int yPos;

    public PhysicsObject() {
        x = y = 100;
    }

    public PhysicsObject(double x, double y) {
        this.x = x;
        this.y = y;
        updatePos();
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    public void updatePos() {
        xPos = (int) getX();
        yPos = (int) getY();
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    public abstract void draw(Graphics window);

}
