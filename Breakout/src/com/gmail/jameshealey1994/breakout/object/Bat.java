package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;
import javax.swing.JComponent;

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

    /**
     * The initial width of bats (normally).
     */
    public static final int INITIAL_WIDTH = 200;

    /**
     * Constructor - Creates a Bat with a GamePanel and PositionManager, and an
     * initial x coordinate, width, and color.
     *
     * @param x                 Initial leftmost x coordinate
     * @param width             Initial width
     * @param color             Initial color
     * @param gamePanel         Where the object is displayed
     * @param positionManager   Manages the positions of objects against the
     *                          walls and each other
     */
    public Bat(double x, double width, Color color, JComponent gamePanel, PositionManager positionManager) {
        super(x, positionManager.getMaxY() - BAT_Y, BAT_HEIGHT, width, color, gamePanel, positionManager);
    }

    /**
     * Returns if the bat's move will be valid.
     * Invalid if the new position will make the bat go off the screen.

     * @param newPosX       potential new X coordinate
     * @return              if the bat's move will be valid.
     */
    public boolean isValidMove(final double newPosX) {
        return (0 < newPosX) && (newPosX + this.getWidth() < this.getPositionManager().getMaxX());
    }

    @Override
    public void onHit(MovableGameObject moving) {
        // TODO perhaps increase speed (decrease delay) every time the ball hits the bat.
        final double thisX; // TODO better name?
        final double movingX; // TODO better name?

        if (moving.getMiddleX() < this.getMiddleX()) {
            // ball's middle landed in left half of bat
            thisX = this.getMiddleX() - this.getX();
            movingX = moving.getMiddleX() - this.getX();
            moving.setStepY(movingX / thisX);
            moving.setStepX(1 - moving.getStepY());
            moving.changeDirectionX();
        } else {
            // ball's middle landed in center of right half of bat
            thisX = this.getRightmostX() - this.getMiddleX();
            movingX = moving.getMiddleX() - this.getMiddleX();
            moving.setStepX(movingX / thisX);
            moving.setStepY(1 - moving.getStepX());
        }
        moving.changeDirectionY();
    }

    /**
     * Returns rightmost X coordinate of the Bat.
     *
     * @return  rightmost X coordinate of the Bat
     */
    public double getRightmostX() {
        return this.getX() + this.getWidth();
    }
}