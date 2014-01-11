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
    public static final int INITIAL_WIDTH = 90;

    public Bat(int x, int width, Color color, JComponent gamePanel, PositionManager positionManager) {
        super(x, positionManager.getMaxY() - BAT_Y, BAT_HEIGHT, width, color, gamePanel, positionManager);
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

    @Override
    public void onHit(MovableGameObject moving) {
        // TODO perhaps increase speed (decrease delay) every time the ball hits the bat.
        moving.changeDirectionY();
        final int movingMiddleX = moving.getX() + (moving.getWidth() / 2); // TODO improve
        final int batMiddleThirdLeftX = this.getX() + (this.getWidth() / 3);
        final int batRightThirdLeftX = this.getX() + (2 * (this.getWidth() / 3));
        if (movingMiddleX < batMiddleThirdLeftX) { // if ball hits left third of the bat...
            if (moving.getStepX() > 0) { // ...set direction to left
                moving.changeDirectionX();
            }
        }
        if (batRightThirdLeftX < movingMiddleX) { // if ball hits right third of the bat...
            if (moving.getStepX() < 0) { // ...set direction to right
                moving.changeDirectionX();
            }
        }
    }

    /**
     * Returns the middle X coordinate of the Bat.
     *
     * @return  the middle X coordinate of the Bat
     */
    public int getMiddleX() {
        return getX() + getWidth() / 2;
    }
}