package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;
import javax.swing.JComponent;

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
     * @param gamePanel         Where the object is displayed
     * @param positionManager   Manages the positions of objects against the
     *                          walls and each other
     */
    public Block(double x, double y, double height, double width, Color color, JComponent gamePanel, PositionManager positionManager) {
        super(x, y, height, width, color, gamePanel, positionManager);
    }

    @Override
    public void onHit(MovableGameObject moving) {
        moving.changeDirectionX();
        moving.changeDirectionY();
        this.clear();
        this.getPositionManager().removeGameObject(this);
        // TODO increase points
    }
}