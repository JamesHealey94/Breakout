package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Bat;
import com.gmail.jameshealey1994.breakout.object.Block;
import com.gmail.jameshealey1994.breakout.object.GameObject;
import com.gmail.jameshealey1994.breakout.object.MovableGameObject;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to manage the position of GameObjects in a Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class PositionManager {

    /**
     * Set of GameObjects that can be bounced off of.
     */
    private final Set<GameObject> objects = new HashSet<>();

    /**
     * The X coordinate of the right wall.
     */
    private final int maxX;

    /**
     * The Y coordinate of the floor.
     */
    private final int maxY;

    /**
     * Constructor - Sets max X and Y.
     *
     * @param maxX      X coordinate of the right wall
     * @param maxY      Y coordinate of the floor
     */
    public PositionManager(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Move object, bouncing off other objects and walls if necessary.
     *
     * @param moving    the object moving
     */
    public void update(MovableGameObject moving) {
        for (GameObject obj : objects) {
            if (!(moving.equals(obj))) {
                final boolean bounceX = isTouchingX(moving, obj); // TODO test an object inside an object, should position be set as well as direction changed?
                final boolean bounceY = isTouchingY(moving, obj);

                if (bounceX && bounceY) { // TODO improve gameobject.???() and override
//                    moving.changeDirectionX();
//                    moving.changeDirectionY();
                    if (obj instanceof MovableGameObject) { // TODO change to a .beenHit() method thats overriden or an event perhaps.
                        moving.changeDirectionX();
                        //moving.changeDirectionY();
                        final MovableGameObject objMoving = (MovableGameObject) obj;
                        objMoving.changeDirectionX();
                        //objMoving.changeDirectionY(); TODO test further
                    }
                    if (obj instanceof Block) {
                        moving.changeDirectionX();
                        moving.changeDirectionY();
                        obj.clear();
                        this.removeGameObject(obj);
                        // TODO increase points
                    }
                    if (obj instanceof Bat) {
                        moving.changeDirectionY();
                        final int movingMiddleX = moving.getX() + (moving.getWidth() / 2);
                        final int batMiddleThirdLeftX = obj.getX() + (obj.getWidth() / 3);
                        final int batRightThirdLeftX = obj.getX() + (2 * (obj.getWidth() / 3));
                        if (movingMiddleX < batMiddleThirdLeftX) { // if ball hits left third of the bat
                            if (moving.getStepX() > 0) { // set direction to left
                                moving.changeDirectionX();
                            }
                        }
                        if (batRightThirdLeftX < movingMiddleX) { // if ball hits right third of the bat
                            if (moving.getStepX() < 0) { // set direction to right
                                moving.changeDirectionX();
                            }
                        }
                    }
                    break; // TODO check
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
            moving.changeDirectionY();
            //moving.setAlive(false); //TODO change once paddle is done
            // reduce game lives remaining by 1
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
                && ((moving.getX()/* + moving.getWidth()*/) <= (obj.getX() + obj.getWidth()));
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
                && ((moving.getY()/* + moving.getHeight()*/) <= (obj.getY() + obj.getHeight()));
    }

    /**
     * Returns if the moving object is touching the ceiling.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the ceiling
     */
    private boolean isTouchingCeiling(MovableGameObject moving) {
        //System.out.println("top            " + moving.getX() + " " + moving.getY());
        return moving.getY() <= 0;
    }

    /**
     * Returns if the moving object is touching the floor.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the floor
     */
    private boolean isTouchingFloor(MovableGameObject moving) {
        //System.out.println("bottom         " + moving.getX() + " " + moving.getY());
        return moving.getY() + moving.getHeight() >= maxY;
    }

    /**
     * Returns if the moving object is touching the left wall.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the left wall
     */
    private boolean isTouchingLeftWall(MovableGameObject moving) {
        //System.out.println("left wall      " + moving.getX() + " " + moving.getY());
        return moving.getX() <= 0;
    }

    /**
     * Returns if the moving object is touching the right wall.
     *
     * @param moving    moving object
     * @return          if the moving object is touching the right wall
     */
    private boolean isTouchingRightWall(MovableGameObject moving) {
        //System.out.println("right wall     " + moving.getX() + " " + moving.getY());
        return moving.getX() + moving.getWidth() >= maxX;
    }

    /**
     * Adds a GameObject to the objects Set.
     *
     * @param object    object to be added to the set
     * @return          if the object was successfully added
     */
    public boolean addGameObject(GameObject object) {
        return objects.add(object);
    }

    /**
     * Removes a GameObject from the objects Set.
     *
     * @param object    object to be removed from the set
     * @return          if the object was successfully removed
     */
    public boolean removeGameObject(GameObject object) {
        return objects.remove(object);
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