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
        super(x, y);
        this.size = size;
        this.weight = weight;
        this.color = color;
        updatePos();
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Calculates a color based on the density
     *
     * @param size the radius of the ball
     * @param weight the weight of the ball
     * @return a color scaled to the density of the ball
     */
    static Color colorByDensity(int size, int weight) {
        double densityTop = 5;
        double density = (double) weight / Tools.square(size);
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
     *
     * @param x x of the point
     * @param y y of the point
     * @return true if point is inside or on ball
     */
    public boolean pointCollide(int x, int y) {
        return (Tools.square(x - this.xPos) + Tools.square(y - this.yPos) <= Tools.square(size));
    }

    /**
     * Bounce the ball off another ball if they've collided.
     *
     * @param obj the other ball to check
     */
    public void collideWithBall(Ball obj) {
        if (sqDist(obj.getX(), obj.getY()) <= Tools.square(size + obj.getSize())) {
            //find the angle of collision
            double collisionAngle = Math.atan2(obj.getY() - this.getY(), obj.getX() - this.getX()) - Math.PI / 2;
            //calculate each ball's angle of incidence from the angle of collision
            double thisIncidence = this.getDir() - collisionAngle;
            double objIncidence = obj.getDir() - collisionAngle;
            //calculate each ball's velocity components
            double thisComponent = this.getSpeed() * Math.sin(thisIncidence);
            double objComponent = obj.getSpeed() * Math.sin(objIncidence);
            double thisParallel = this.getSpeed() * Math.cos(thisIncidence);
            double objParallel = obj.getSpeed() * Math.cos(objIncidence);
            //calculate the momentum of each ball along the collision
            double thisMomentum = thisComponent * this.weight;
            double objMomentum = objComponent * obj.getWeight();
            //completely switch momentums; this is a perfectly elastic collision.
            double temp = objMomentum;
            objMomentum = thisMomentum;
            thisMomentum = temp;
            //convert back to velocity
            thisComponent = thisMomentum / this.weight;
            objComponent = objMomentum / obj.getWeight();
            //re-calculate the speed and direction
            double thisSpeed = Math.sqrt(Tools.square(thisComponent) + Tools.square(thisParallel));
            double thisDir = Math.atan2(thisComponent, thisParallel) + collisionAngle;
            double objSpeed = Math.sqrt(Tools.square(objComponent) + Tools.square(objParallel));
            double objDir = Math.atan2(objComponent, objParallel) + collisionAngle;
            System.out.println(thisDir + " " + objDir);
            //finally, reassign the speed of each.
            this.setSpeedDir(thisSpeed, thisDir);
            obj.setSpeedDir(objSpeed, objDir);
            this.move();
            obj.move();
        }
    }


    /**
     * Bounce the ball off a wall if they've collided.
     *
     * @param obj the wall to check
     * @return true if the ball has collided with the wall
     */
    public boolean collideWithWall(Wall obj) {
        double wx = obj.getX();
        double wy = obj.getY();
        int wid = obj.getWidth();
        int ht = obj.getHeight();

        //is a collision possible? if not, don't run the other tests.
        if (wx < getX() + size && getX() - size < wx + wid
                && wy < getY() + size && getY() - size < wy + ht) {

            //left and right sides
            if (wy < getY() && getY() < wy + ht) {
                if (wx < getX() + size && getX() - size < wx + wid) {
                    return true;
                }
            }
            //top and bottom sides
            if (wx < getX() && getX() < wx + wid) {
                if (wy < getY() + size && getY() - size < wy + ht) {
                    return true;
                }
            }
            //corners
            double[][] points = {{wx, wy}, {wx + wid, wy}, {wx, wy + ht}, {wx + wid, wy + ht}};
            for (double[] point : points) {
                if (sqDist(point[0], point[1]) < Tools.square(size)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Bounce off a wall.
     *
     * @param obj the wall to bounce off of
     */
    public void bounceOffWall(Wall obj) {
        //corner bounce or side bounce?

    }

    /**
     * Draw the ball to a window
     *
     * @param window the window to draw to
     */
    public void draw(Graphics window) {
        window.setColor(color);
        window.fillOval(xPos - size, yPos - size, 2 * size, 2 * size);
        window.setColor(Color.BLACK);
        window.drawLine(xPos, yPos, xPos + (int) (10 * getSpeed() * Math.cos(getDir())), yPos + (int) (10 * getSpeed() * Math.sin(getDir())));
    }

}
