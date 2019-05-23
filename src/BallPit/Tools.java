package BallPit;

/**
 *
 * @author yaod5171
 */
public class Tools {

    public static void haltProgram() {
        try {
            while (true) {
                Thread.currentThread().sleep(10000);
            }
        } catch (Exception e) {
        }
    }

    public static double square(double a) {
        return a * a;
    }
    
}
