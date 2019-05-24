/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BallPit;

import java.awt.Graphics;
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
        return walls.get(ind);
    }

    public void add(Wall wall) {
        walls.add(wall);
    }

    public void remove(Wall wall) {
        walls.remove(wall);
    }

    public void remove(int ind) {
        walls.remove(ind);
    }
    
    public List<Wall> getWalls() {
        return walls;
    }
    
    public void drawAll(Graphics window) {
        for (Wall w : walls) {
            w.draw(window);
        }
    }


}
