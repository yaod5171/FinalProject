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

    public MovingObject(double X, double Y) {
        x = X;
        y = Y;
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
}
