package BallPit;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yaod5171
 */
public class Blocks {
    
    private List<Block> blocks;
    private final static double GRAV = 0.05;

    public Blocks() {
        blocks = new ArrayList();
    }

    public Blocks(Block[] in) {
        this();
        for (Block b : in) {
            blocks.add(b);
        }
    }
    
    public Block get(int ind) {
        return blocks.get(ind);
    }

    public void add(Block block) {
        blocks.add(block);
    }

    public void remove(Ball ball) {
        blocks.remove(ball);
    }

    public void remove(int ind) {
        blocks.remove(ind);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void drawAll(Graphics window) {
        for (Block b : blocks) {
            b.draw(window);
        }
    }

    public void moveAll() {
        for (Block b : blocks) {
            b.move();
        }
    }
}
