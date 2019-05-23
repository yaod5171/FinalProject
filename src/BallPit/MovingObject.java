package BallPit;

/**
 *
 * @author yaod5171
 */
public abstract class MovingObject extends PhysicsObject implements Moveable {

    private double vx;
    private double vy;

    public MovingObject(double x, double y) {
        super(x, y);
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
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
    }

    /**
     * calculate and return the direction
     *
     * @return the direction in radians
     */
    public double getDir() {
        return Math.atan2(vy, vx);
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
     *
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

    /**
     * Move the object
     */
    public void move() {
        setX(getX() + vx);
        setY(getY() + vy);
        updatePos();
    }

    public double sqDist(double x, double y) {
        return Math.pow(getX() - x, 2) + Math.pow(getY() - y, 2);
    }

    public double dist(double x, double y) {
        return Math.sqrt(sqDist(x, y));
    }
    
}
