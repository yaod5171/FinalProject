package BallPit;

/**
 *
 * @author yaod5171
 */
public class MovingObject implements Moveable {

    private int x;
    private int y;
    private int vx;
    private int vy;

    public MovingObject(int X, int Y) {
        x = X;
        y = Y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the vx
     */
    public int getVX() {
        return vx;
    }

    /**
     * @return the vy
     */
    public int getVY() {
        return vy;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param vx the vx to set
     */
    public void setVX(int vx) {
        this.vx = vx;
    }

    /**
     * @param vy the vy to set
     */
    public void setVY(int vy) {
        this.vy = vy;
    }
}
