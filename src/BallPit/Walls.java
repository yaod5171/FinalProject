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
public class Walls {

    private List<Wall> walls;

    public Walls() {
        walls = new ArrayList();
    }

    public Walls(Wall[] walls) {
        this();
        for (Wall w : walls) {
            this.walls.add(w);
        }
    }

    public Wall get(int ind) {
        return getWall(ind);
    }

    public Wall getWall(int ind) {
        return walls.get(ind);
    }

}
