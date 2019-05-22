package BallPit;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author yaod5171
 */
public class BallPit extends Canvas implements Runnable {

    private BufferedImage back;

    private Balls balls;

    private int size;
    private double density;

    public BallPit() {
        size = 15;
        density = 1.0;

        balls = new Balls();
        balls.addBall(new Ball(200, 200, 15, 225));
        balls.addBall(new Ball(100, 100));
        balls.addBall(new Ball(200, 100));
        makeBall(100, 200, 200, 100);
        makeBall(100, 300, 200, 200);
        makeBall(500, 100, 500, 500);
        //makeBall(500, 500, 500, 100);

        new Thread(this).start();
        setVisible(true);
    }

    /**
     * Make a new ball with a velocity specified by the user's vector
     *
     * @param x1 the x of the origin of the vector
     * @param y1 the y of the origin of the vector
     * @param x2 the x of the direction of the vector
     * @param y2 the y of the direction of the vector
     */
    public void makeBall(int x1, int y1, int x2, int y2) {
        final double SCALE = 0.025;

        Ball newBall = new Ball(x1, y1, size, (int) (MovingObject.square(size) * density));
        int dx = x2 - x1;
        int dy = y2 - y1;

        newBall.setVX(SCALE * dx);
        newBall.setVY(SCALE * dy);

        balls.addBall(newBall);
    }

    @Override
    public void update(Graphics window) {
        balls.addGravity();
        balls.moveAll();
        balls.checkAllForCollisions();

        paint(window);
    }

    @Override
    public void paint(Graphics window) {
        //this code taken from APlusCompSci
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();

        //end code from APlusCompSci
        //draw background each frame
        graphToBack.setColor(Color.WHITE);
        graphToBack.fillRect(0, 0, 800, 600);

        //draw the balls
        balls.drawAll(graphToBack);

        twoDGraph.drawImage(back, null, 0, 0);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(100);
                repaint();
            }
        } catch (Exception e) {
        }
    }

}
