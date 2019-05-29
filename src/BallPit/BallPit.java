package BallPit;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.lang.Character;

/**
 *
 * @author yaod5171
 */
public class BallPit extends Canvas implements Runnable, MouseListener, MouseMotionListener, KeyListener {

    private BufferedImage back;

    private Balls balls;
    private Walls walls;

    private int size;
    private double density;

    private int[] clickAndDragStart;
    private int[] mousePos;
    private boolean mouseHeld;

    private boolean intro;
    private boolean paused;

    public BallPit() {
        size = 15;
        density = 1.0;

        balls = new Balls();
        walls = new Walls();

        clickAndDragStart = new int[2];
        mousePos = new int[]{200, 200};
        mouseHeld = false;

        intro = true;
        paused = false;

        defaultWalls();

//        balls.addBall(new Ball(200, 220, 15, 225));
//        balls.addBall(new Ball(100, 100));
//        balls.addBall(new Ball(200, 100));
//        makeBall(100, 200, 200, 100);
//        makeBall(300, 300, 200, 200);
//        makeBall(500, 100, 500, 500);
//        makeBall(520, 500, 520, 100);
//        
//        for (int i = 100; i <= 400; i+=50) {
//            makeBall(i, 100, i, 100);
//        }
//        makeBall(500, 125, 400, 100);
//        balls.get(4).setWeight(Integer.MAX_VALUE); //note: balls of infinite mass behave poorly.
//        for (int i = 400; i < 700; i += 50) {
//            for (int j = 100; j < 300; j += 50) {
//                makeBall(i, j, i, j);
//            }
//        }
//        makeBall(100, 300, 110, 300);
        new Thread(this).start();
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        setVisible(true);
    }

    private void defaultWalls() {
        walls.add(new Wall(20, 20, 30, 480));
        walls.add(new Wall(20, 500, 750, 30));
        walls.add(new Wall(740, 20, 30, 480));
        walls.add(new Wall(20, 20, 750, 30));
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

        Ball newBall = new Ball(x1, y1, size, (int) (Tools.square(size) * density));
        int dx = x2 - x1;
        int dy = y2 - y1;

        newBall.setVX(SCALE * dx);
        newBall.setVY(SCALE * dy);

        balls.add(newBall);
    }

    @Override
    public void update(Graphics window) {
        if (!paused) {
            balls.addGravity();
            balls.moveAll();
            balls.checkAllForCollisions(walls);
        }
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

        //draw the objects
        balls.drawAll(graphToBack);
        walls.drawAll(graphToBack);

        //draw line from click and drag;
        if (mouseHeld && !paused) {
            graphToBack.drawLine(clickAndDragStart[0], clickAndDragStart[1],
                    mousePos[0], mousePos[1]);
        }

        //draw intro
        if (intro) {
            graphToBack.setColor(Color.RED);
            //graphToBack.setFont
            graphToBack.drawString("Ball Pit", 350, 100);
            graphToBack.drawString("by Derrick Yao", 330, 120);
            graphToBack.drawString("Left click and drag to create a ball.", 60, 480);
            graphToBack.drawString("Press SPACE to pause.", 600, 480);
        }

        //draw paused menu
        if (paused) {
            graphToBack.setColor(Color.RED);
            graphToBack.drawString("PAUSED", 350, 100);
            graphToBack.drawString("Press SPACE to unpause.", 310, 120);
            graphToBack.drawString("Press S to save", 60, 550);
            graphToBack.drawString("Press O to open a file", 325, 550);
            graphToBack.drawString("Press R to reset", 600, 550);
        }

        twoDGraph.drawImage(back, null, 0, 0);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickAndDragStart = new int[]{e.getX(), e.getY()};
        mousePos = new int[]{e.getX(), e.getY()};
        mouseHeld = true;
        intro = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos = new int[]{e.getX(), e.getY()};
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!paused) {
            makeBall(clickAndDragStart[0], clickAndDragStart[1], e.getX(), e.getY());
        }
        mouseHeld = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing. probably.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        intro = false;
        switch (Character.toUpperCase(e.getKeyChar())) {
            case ' ':
                paused = !paused;
                break;
            case 'S':
                break;
            case 'O':
                break;
            case 'R':
                if (paused) {
                    while (balls.getBalls().size() > 0) {
                        balls.remove(0);
                    }
                    while (walls.getWalls().size() > 0) {
                        walls.remove(0);
                    }
                    defaultWalls();
                    paused = false;
                    intro = true;
                }

        }
    }

}
