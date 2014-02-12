package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Ball;
import com.gmail.jameshealey1994.breakout.object.GameObject;
import com.gmail.jameshealey1994.breakout.object.MovableGameObject;

/**
 * Class to manage the position of GameObjects in a Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class PositionManager {

    /**
     * The X coordinate of the right wall.
     */
    private final int maxX;

    /**
     * The Y coordinate of the floor.
     */
    private final int maxY;

    /**
     * The Game being played.
     */
    private final Game game;

    /**
     * Constructor - Sets max X and Y.
     *
     * @param game      Game being played
     * @param maxX      X coordinate of the right wall
     * @param maxY      Y coordinate of the floor
     */
    public PositionManager(Game game, int maxX, int maxY) {
        this.game = game;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Updates positions of MovableGameObject passed and any GamesObjects
     * in the PositionManager that the MovableGameObject collides with.
     *
     * @param moving    object moving and possibly colliding with walls or other
     *                  objects
     */
    public synchronized void update(MovableGameObject moving) {
        for (GameObject obj : game.getGameObjects()) {
            if (!(moving.equals(obj))) {
                final boolean isTouchingX = isTouchingX(moving, obj);
                final boolean isTouchingY = isTouchingY(moving, obj);

                if (isTouchingX && isTouchingY) {
                    obj.onHit(moving);
                    break;
                }
            }
        }

        // bounce off sides
        if (isTouchingLeftWall(moving) || isTouchingRightWall(moving)) {
            moving.changeDirectionX();
        }

        if (isTouchingCeiling(moving)) {
            moving.changeDirectionY();
        }

        if (isTouchingFloor(moving)) {
            moving.setAlive(false);
            if (moving instanceof Ball) {
                game.setLivesRemaining(game.getLivesRemaining() - 1);
                game.removeBall((Ball) moving);
                game.newBall();
            }
        }

        moving.step();
    }

    /**
     * Returns if the moving object and the passed object are in the same X coordinates.
     *
     * @param moving    moving object
     * @param obj       object
     * @return          if the moving object and the passed object are in the same X coordinates
     */
    private boolean isTouchingX(MovableGameObject moving, GameObject obj) {
        return ((moving.getX() + moving.getWidth()) >= obj.getX())
                && ((moving.getX()) <= (obj.getX() + obj.getWidth()));
    }

    /**
     * Returns if the moving object and the passed object are in the same Y coordinates.
     *
     * @param moving    moving object
     * @param obj       object
     * @return          if the moving object and the passed object are in the same Y coordinates
     */
    private boolean isTouchingY(MovableGameObject moving, GameObject obj) {
        return ((moving.getY() + moving.getHeight()) >= obj.getY())
                && ((moving.getY()) <= (obj.getY() + obj.getHeight()));
    }

    /**
     * Returns if the moving object is touching the ceiling.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the ceiling
     */
    private boolean isTouchingCeiling(MovableGameObject moving) {
        return moving.getY() <= 0;
    }

    /**
     * Returns if the moving object is touching the floor.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the floor
     */
    private boolean isTouchingFloor(MovableGameObject moving) {
        return moving.getY() >= maxY;
    }

    /**
     * Returns if the moving object is touching the left wall.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the left wall
     */
    private boolean isTouchingLeftWall(MovableGameObject moving) {
        return moving.getX() <= 0;
    }

    /**
     * Returns if the moving object is touching the right wall.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the right wall
     */
    private boolean isTouchingRightWall(MovableGameObject moving) {
        return moving.getX() + moving.getWidth() >= maxX;
    }

    /**
     * Returns the current value of maxX.
     *
     * @return      the current value of maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * Returns the current value of maxY.
     *
     * @return      the current value of maxY
     */
    public int getMaxY() {
        return maxY;
    }
}