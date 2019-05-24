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

    public Balls(Ball[] balls) {
        this();
        for (Ball b : balls) {
            this.balls.add(b);
        }
    }

    public void add(Ball ball) {
        balls.add(ball);
    }

    public void remove(Ball ball) {
        balls.remove(ball);
    }

    public List<Ball> getBalls() {
        return balls;
    }
    
    public Ball get(int ind) {
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

    public void checkAllForCollisions(Walls walls) {
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) { //make sure we don't collide twice
                balls.get(i).collideWithBall(balls.get(j));
            }
            for (Wall w: walls.getWalls()) {
                balls.get(i).collideWithWall(w);
            }
        }
    }

    public void addGravity() {
        for (Ball b : balls) {
            b.setVY(b.getVY() + GRAV);
        }
    }

}
