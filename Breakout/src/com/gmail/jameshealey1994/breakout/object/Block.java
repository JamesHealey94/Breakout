package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.Game;
import java.awt.Color;

/**
 * Class representing a Block Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Block extends GameObject {

    /**
     * Constructor - Creates a Bat with a GamePanel and PositionManager, and an
     * initial x coordinate, width, and color.
     *
     * @param x                 Initial leftmost X coordinate
     * @param y                 Initial highest Y coordinate
     * @param height            Initial height
     * @param width             Initial width
     * @param color             Initial color
     * @param game              Game the object belongs to
     */
    public Block(double x, double y, double height, double width, Color color, Game game) {
        super(x, y, height, width, color, game);
    }

    @Override
    public void onHit(MovableGameObject moving) {
        moving.changeDirectionX();
        moving.changeDirectionY();
        this.clear();
        this.getGame().getBlocks().remove(this);
        this.getGame().setPoints(this.getGame().getPoints() + 1); // TODO change points given based on different factors
    }
}