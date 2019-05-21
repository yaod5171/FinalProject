package BallPit;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yaod5171
 */
public class Balls {

    private List<Ball> balls;
    private final static double GRAV = 0.05;

    public Balls() {
        balls = new ArrayList();
    }

    public Balls(Ball[] in) {
        this();
        for (Ball b : in) {
            balls.add(b);
        }
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void removeBall(Ball ball) {
        balls.remove(ball);
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public Ball getBall(int ind) {
        return balls.get(ind);
    }

    public void moveAll() {
        for (Ball b : balls) {
            b.move();
        }
    }

    public void drawAll(Graphics window) {
        for (Ball b : balls) {
            b.draw(window);
        }
    }

    public void checkAllForCollisions() {
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) { //make sure we don't collide twice
                Ball b = balls.get(i);
                Ball c = balls.get(j);
                if (b.collideWithBall(c) && !b.equals(c)) {
                    b.bounceOffBall(c);
                }
            }
        }
    }
    
    /**
     * A method to handle collision detection.
     * Taken from aaaaaaaaaaaa at https://gamedev.stackexchange.com/questions/20516/ball-collisions-sticking-together
     */
//    public void checkAllForCollisions() {
//        double xDist, yDist;
//        for (int i = 0; i < balls.size(); i++) {
//            Ball A = balls.get(i);
//            for (int j = i + 1; j < balls.size(); j++) {
//                Ball B = balls.get(j);
//                xDist = A.getX() - B.getX();
//                yDist = A.getY() - B.getY();
//                double distSquared = xDist * xDist + yDist * yDist;
//                //Check the squared distances instead of the the distances, same result, but avoids a square root.
//                if (distSquared <= (A.getSize() + B.getSize()) * (A.getSize() + B.getSize())) {
//                    double xVelocity = B.getVX() - A.getVX();
//                    double yVelocity = B.getVY() - A.getVY();
//                    double dotProduct = xDist * xVelocity + yDist * yVelocity;
//                    //Neat vector maths, used for checking if the objects moves towards one another.
//                    if (dotProduct > 0) {
//                        double collisionScale = dotProduct / distSquared;
//                        double xCollision = xDist * collisionScale;
//                        double yCollision = yDist * collisionScale;
//                    //The Collision vector is the speed difference projected on the Dist vector,
//                        //thus it is the component of the speed difference needed for the collision.
//                        double combinedMass = A.getWeight() + B.getWeight();
//                        double collisionWeightA = 2 * B.getWeight() / combinedMass;
//                        double collisionWeightB = 2 * A.getWeight() / combinedMass;
//                        A.setVX(A.getVX() + collisionWeightA * xCollision);
//                        A.setVY(A.getVY() + collisionWeightA * yCollision);
//                        B.setVX(B.getVX() - collisionWeightB * xCollision);
//                        B.setVX(B.getVX() - collisionWeightB * yCollision);
//                    }
//                }
//            }
//        }
//    }

    public void addGravity() {
        for (Ball b : balls) {
            b.setVY(b.getVY() + GRAV);
        }
    }

}
