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
    private static Color colorByDensity(int size, int weight) {
        double densityTop = 5;
        double density = (double) weight / Math.pow(size, 2);
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
        return (Math.pow((x - this.xPos), 2) + Math.pow((y - this.yPos), 2) <= Math.pow(size, 2));
    }

    /**
     * Check if the ball has collided with another ball
     *
     * @param obj the other ball to check
     * @return true if the balls have collided
     */
    public boolean collideWithBall(Ball obj) {
        return (dist(obj.getX(), obj.getY()) <= size + obj.getSize());
    }

    /**
     * Bounce off a ball.
     *
     * @param obj the ball to bounce off of
     */
//    public void bounceOffBall(Ball obj) {
//        //find the angle of collision
//        double collisionAngle = Math.atan2(obj.getY() - this.getY(), obj.getX() - this.getX());
//        //calculate each ball's angle of incidence from the angle of collision
//        double thisIncidence = this.getDir() - collisionAngle;
//        double objIncidence = obj.getDir() - collisionAngle;
//        //calculate each ball's velocity components
//        double thisComponent = this.getSpeed() * Math.cos(thisIncidence);
//        double objComponent = obj.getSpeed() * Math.cos(objIncidence);
//        double thisParallel = this.getSpeed() * Math.sin(thisIncidence);
//        double objParallel = obj.getSpeed() * Math.sin(objIncidence);
//        //calculate the momentum of each ball along the collision
//        double thisMomentum = thisComponent * this.weight;
//        double objMomentum = objComponent * obj.getWeight();
//        //completely switch momentums; this is a perfectly elastic collision.
//        double temp = objMomentum;
//        objMomentum = thisMomentum;
//        thisMomentum = temp;
//        //convert back to velocity
//        thisComponent = thisMomentum / this.weight;
//        objComponent = objMomentum / obj.getWeight();
//        //re-calculate the speed and direction
//        double thisSpeed = Math.sqrt(Math.pow(thisComponent, 2) + Math.pow(thisParallel, 2));
//        double thisDir = Math.atan2(thisComponent, thisParallel) - collisionAngle + Math.PI / 2;
//        double objSpeed = Math.sqrt(Math.pow(objComponent, 2) + Math.pow(objParallel, 2));
//        double objDir = Math.atan2(objComponent, objParallel) - collisionAngle + Math.PI / 2;
//        System.out.println(thisDir + " " + objDir);
//        //finally, reassign the speed of each.
//        this.setSpeedDir(thisSpeed, thisDir);
//        obj.setSpeedDir(objSpeed, objDir);
//        this.move();
//        obj.move();
//    }
    /**
     * Bounce off a ball 
     * adapted from https://gamedevelopment.tutsplus.com/tutorials/when-worlds-collide-simulating-circle-circle-collisions--gamedev-769
     *
     * @param obj the ball to bounce off of
     */
    public void bounceOffBall(Ball obj) {
        double combinedWeight = this.getWeight() + obj.getWeight();
        double newVX1 = (this.getVX() * (this.getWeight() - obj.getWeight() + 2 * obj.getWeight() * obj.getVX())) / combinedWeight;
        double newVY1 = (this.getVY() * (this.getWeight() - obj.getWeight() + 2 * obj.getWeight() * obj.getVY())) / combinedWeight;
        double newVX2 = (obj.getVX() * (obj.getWeight() - this.getWeight() + 2 * this.getWeight() * this.getVX())) / combinedWeight;
        double newVY2 = (obj.getVX() * (obj.getWeight() - this.getWeight() + 2 * this.getWeight() * this.getVX())) / combinedWeight;
        this.setVX(newVX1);
        this.setVY(newVY1);
        obj.setVX(newVX2);
        obj.setVY(newVY2);
        this.move();
        obj.move();

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
