package BallPit;

/**
 *
 * @author yaod5171
 */
public interface Collideable {
    public boolean pointCollide(int x, int y);
    public boolean collideWith(Collideable obj);
}
