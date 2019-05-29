package BallPit;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author yaod5171
 */
public class Block extends MovingObject {

    private int size;
    private int weight;
    private Color color;
    
    public Block(double x, double y) {
        this(x, y, 5, 25, Ball.colorByDensity(5, 25));
    }

    public Block(double x, double y, int size, int weight) {
        this(x, y, size, weight, Ball.colorByDensity(size, weight));
    }

    public Block(double x, double y, Color color) {
        this(x, y, 5, 25, color);
    }

    public Block(double x, double y, int size, int weight, Color color) {
        super(x, y);
        this.size = size;
        this.weight = weight;
        this.color = color;
        updatePos();
    }

    @Override
    public void draw(Graphics window) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
