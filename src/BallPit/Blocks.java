/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BallPit;

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
}
