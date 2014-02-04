package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.Game;
import java.awt.Color;

/**
 * Class representing a Movable Bat Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Bat extends MovableGameObject {

    /**
     * The Y coordinate of the top of all the bats.
     */
    public static final int BAT_Y = 30;

    /**
     * The height of all the bats.
     */
    public static final int BAT_HEIGHT = 15;

    /**
     * The initial width of bats (normally).
     */
    public static final int INITIAL_WIDTH = 150;

    /**
     * Constructor - Creates a Bat with a GamePanel and PositionManager, and an
     * initial x coordinate, width, and color.
     *
     * @param x                 Initial leftmost x coordinate
     * @param width             Initial width
     * @param color             Initial color
     * @param game              Game the object belongs to
     */
    public Bat(double x, double width, Color color, Game game) {
        super(0, 0, 2, x, game.getPositionManager().getMaxY() - BAT_Y, BAT_HEIGHT, width, color, game);
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

    @Override
    public void setStepY(double stepY) {
        super.setStepY(0);
    }
}