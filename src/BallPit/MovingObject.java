package BallPit;

/**
 *
 * @author yaod5171
 */
public class MovingObject implements Moveable {

    private double x;
    private double y;
    private double vx;
    private double vy;
    protected int xPos; //this is basically the output, so :P
    protected int yPos;

    public MovingObject(double x, double y) {
        this(x, y, 0, 0);
    }

    public MovingObject(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the vx
     */
    public double getVX() {
        return vx;
    }

    /**
     * @return the vy
     */
    public double getVY() {
        return vy;
    }

    /**
     * calculate and return the speed
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
    }
    
    /**
     * calculate and return the direction
     * @return the direction in radians
     */
    public double getDir() {
        return Math.atan(vy/vx);
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @param vx the vx to set
     */
    public void setVX(double vx) {
        this.vx = vx;
    }

    /**
     * @param vy the vy to set
     */
    public void setVY(double vy) {
        this.vy = vy;
    }
    
    /**
     * Set the speed/dir
     * @param speed the speed to set
     * @param dir the dir to set
     */
    public void setSpeedDir(double speed, double dir) {
        vx = speed * Math.cos(dir);
        vy = speed * Math.sin(dir);
    }

    /**
     * Update xPos and yPos
     */
    public void updatePos() {
        xPos = (int) x;
        yPos = (int) y;
    }

    /**
     * Move the object
     */
    public void move() {
        x += vx;
        y += vy;
        updatePos();
    }

    public double dist(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
}
