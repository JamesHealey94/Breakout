package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.DisplayManager;
import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;

/**
 * Class representing a Movable Bat Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Bat extends GameObject {

    /**
     * The Y coordinate of the top of all the bats.
     */
    public static final int BAT_Y = 20;

    /**
     * The height of all the bats.
     */
    public static final int BAT_HEIGHT = 10;

    public Bat(int x, int width, Color color, DisplayManager displayManager, PositionManager positionManager) {
        super(x, positionManager.getMaxY() - BAT_Y, BAT_HEIGHT, width, color, displayManager, positionManager);
    }

    /**
     * Returns if the bat's move will be valid.
     * Invalid if the new position will make the bat go off the screen.

     * @param newPosX       potential new X coordinate
     * @return              if the bat's move will be valid.
     */
    public boolean isValidMove(final int newPosX) {
        return (0 < newPosX) && (newPosX + this.getWidth() < this.getPositionManager().getMaxX());
    }
}