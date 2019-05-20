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

    public void addGravity() {
        for (Ball b : balls) {
            b.setVY(b.getVY() + GRAV);
        }
    }

}
