package BallPit;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author yaod5171
 */
public class Ball extends MovingObject /*implements Collideable*/ {

    private int size;
    private int weight;
    private Color color;
    
    public Ball(double x, double y) {
        this(x, y, 5, 25, colorByDensity(5, 25));
    }

    public Ball(double x, double y, int size, int weight) {
        this(x, y, size, weight, colorByDensity(size, weight));
    }


    public Ball(double x, double y, Color color) {
        this(x, y, 5, 25, color);
    }

    public Ball(double x, double y, int size, int weight, Color color) {
        super(x, y, 0, 0);
        this.size = size;
        this.weight = weight;
        this.color = color;
        updatePos();
    }

    /**
     * Calculates a color based on the density
     *
     * @param size the radius of the ball
     * @param weight the weight of the ball
     * @return a color scaled to the density of the ball
     */
    private static Color colorByDensity(int size, int weight) {
        double densityTop = 5;
        double density = (double) weight / Math.pow(size, 2);
        System.out.println(density);
        float hue;
        if (density < densityTop) {
            hue = (float) (density / densityTop);
            hue = 1 - hue;/*
            hue += 0.2;
            hue = (float)Math.min(hue, 0.8);*/
        } else {
            hue = 0;
        }

        return Color.getHSBColor(hue, 1, 1);
    }
    
    /**
     * Check if a point has collided with the ball
     * @param x x of the point
     * @param y y of the point
     * @return true if point is inside or on ball
     */
    public boolean pointCollide(int x, int y) {
        return (Math.pow((x - this.xPos), 2) + Math.pow((y - this.yPos), 2) <= Math.pow(size, 2));
    }

    /**
     * Check if the ball has collided with another ball
     * @param obj the other ball to check
     * @return true if the balls have collided
     */
    public boolean collideWithBall(Ball obj) {
        return (dist(obj.getX(), obj.getY()) <= 2 * size);
    }
    
    /**
     * Draw the ball to a window
     * @param window the window to draw to
     */
    public void draw(Graphics window) {
        window.setColor(color);
        window.fillOval(xPos, yPos, 2*size, 2*size);
    }

}
