package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.GameObject;
import com.gmail.jameshealey1994.breakout.object.MovableGameObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class PositionManager {
    private Set<GameObject> objects = new HashSet<>();

    public boolean addGameObject(GameObject object) {
        return objects.add(object);
    }

    public boolean removeGameObject(GameObject object) {
        return objects.remove(object);
    }
    
    private int maxX;
    
    private int maxY;

    public PositionManager(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    // Bounce ball off other objects.
    public void update(MovableGameObject moving) {
        for (GameObject obj : objects) {
            if (!(moving.equals(obj))) {
                final boolean xBounce = (((moving.getX() + moving.getWidth()) >= obj.getX())
                        && ((moving.getX() + moving.getWidth()) <= (obj.getX() + obj.getWidth())));
                final boolean yBounce = ((((moving.getY() + moving.getHeight())) >= obj.getY())
                        && ((moving.getY() - moving.getHeight()) <= (obj.getY() - obj.getHeight()))); // TODO should it be + or -?
                
                if (xBounce && yBounce) {
                    if (xBounce) {
                        System.out.println("x collision");
                        moving.changeDirectionX();
                        if (obj instanceof MovableGameObject) { // TODO change to a .beenHit() method thats overriden or an event perhaps.
                            MovableGameObject objMoving = (MovableGameObject) obj;
                            objMoving.changeDirectionX();
                        }
                    }
                    if (yBounce) {
                        System.out.println("y collision");
                        moving.changeDirectionY();
                        if (obj instanceof MovableGameObject) { // TODO change to a .beenHit() method thats overriden or an event perhaps.
                            MovableGameObject objMoving = (MovableGameObject) obj;
                            objMoving.changeDirectionY();
                        }
                    }
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

    private boolean isTouchingCeiling(MovableGameObject moving) {
        System.out.println("top            " + moving.getX() + " " + moving.getY());
        return moving.getY() <= 0;
    }

    private boolean isTouchingFloor(MovableGameObject moving) {
        System.out.println("bottom         " + moving.getX() + " " + moving.getY());
        return moving.getY() >= maxY;
    }

    private boolean isTouchingLeftWall(MovableGameObject moving) {
        System.out.println("left wall      " + moving.getX() + " " + moving.getY());
        return moving.getX() <= 0;
    }

    private boolean isTouchingRightWall(MovableGameObject moving) {
        System.out.println("right wall     " + moving.getX() + " " + moving.getY());
        return moving.getX() + moving.getWidth() >= maxX;
    }
}