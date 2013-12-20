package com.gmail.jameshealey1994.breakout;

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
    private final Set<GameObject> objects = new HashSet<>();

    public boolean addGameObject(GameObject object) {
        return objects.add(object);
    }

    public boolean removeGameObject(GameObject object) {
        return objects.remove(object);
    }

    private final int maxX;

    private final int maxY;

    public PositionManager(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    // Bounce ball off other objects.
    public void update(MovableGameObject moving) { // TODO bat is a MovableGameObject, this would need to be changed.
        for (GameObject obj : objects) {
            if (!(moving.equals(obj))) {
                final boolean bounceX = isTouchingX(moving, obj); // TODO test an object inside an object, should position be set as well as direction changed?
                final boolean bounceY = isTouchingY(moving, obj);

                // TODO solve logic error
                if (bounceX && bounceY) {
                    if (bounceX) {
                        //System.out.println("x collision");
                        moving.changeDirectionX();
                        if (obj instanceof MovableGameObject) { // TODO change to a .beenHit() method thats overriden or an event perhaps.
                            MovableGameObject objMoving = (MovableGameObject) obj;
                            objMoving.changeDirectionX();
                        }
                    }
                    if (bounceY) {
                        //System.out.println("y collision");
                        moving.changeDirectionY();
                        if (obj instanceof MovableGameObject) { // TODO change to a .beenHit() method thats overriden or an event perhaps.
                            MovableGameObject objMoving = (MovableGameObject) obj;
                            objMoving.changeDirectionY();
                        }
                    }
                    if (obj instanceof Block) {
                        obj.clear();
                        this.removeGameObject(obj);
                        // TODO increase points
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

    private boolean isTouchingX(MovableGameObject moving, GameObject obj) {
        return ((moving.getX() + moving.getWidth()) >= obj.getX())
                && ((moving.getX()/* + moving.getWidth()*/) <= (obj.getX() + obj.getWidth()));
    }

    private boolean isTouchingY(MovableGameObject moving, GameObject obj) {
        return ((moving.getY() + moving.getHeight()) >= obj.getY())
                && ((moving.getY()/* + moving.getHeight()*/) <= (obj.getY() + obj.getHeight()));
    }

    private boolean isTouchingCeiling(MovableGameObject moving) {
        //System.out.println("top            " + moving.getX() + " " + moving.getY());
        return moving.getY() <= 0;
    }

    private boolean isTouchingFloor(MovableGameObject moving) {
        //System.out.println("bottom         " + moving.getX() + " " + moving.getY());
        return moving.getY() + moving.getHeight() >= maxY;
    }

    private boolean isTouchingLeftWall(MovableGameObject moving) {
        //System.out.println("left wall      " + moving.getX() + " " + moving.getY());
        return moving.getX() <= 0;
    }

    private boolean isTouchingRightWall(MovableGameObject moving) {
        //System.out.println("right wall     " + moving.getX() + " " + moving.getY());
        return moving.getX() + moving.getWidth() >= maxX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}