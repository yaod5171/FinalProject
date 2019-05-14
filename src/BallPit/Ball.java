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

    private Ball(double x, double y) {
        this(x, y, 5, 25, colorByDensity(5, 25));
    }

    private Ball(double x, double y, int size, int weight) {
        this(x, y, size, weight, colorByDensity(size, weight));
    }

    private Ball(double x, double y, Color color) {
        this(x, y, 5, 25, color);
    }

    public Ball(double x, double y, int size, int weight, Color color) {
        super(x, y, 0, 0);
        this.size = size;
        this.weight = weight;
        this.color = color;
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
        double density = (double) weight / (size ^ 2);
        float hue;
        if (density < densityTop) {
            hue = (float) (density / densityTop);
            hue = 1 - hue;
            hue += 0.2;
            hue = Math.min(hue, 1);
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

}
