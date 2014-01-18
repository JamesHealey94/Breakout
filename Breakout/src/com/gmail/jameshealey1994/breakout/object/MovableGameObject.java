package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.Lock;
import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 * Abstract class representing a GameObject that can move.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class MovableGameObject extends GameObject implements Runnable {

    /**
     * How far the object moves right every step.
     */
    private int stepX;

    /**
     * How far the object moves up every step.
     */
    private int stepY;

    /**
     * Delay in milliseconds between steps.
     */
    private int delay;

    /**
     * The thread that the MovableGameObject will run on when started.
     */
    private Thread thread;

    /**
     * If the MovableGameObject is alive and should continue to run.
     */
    private boolean alive;

    /**
     * Constructs a new MovableGameObject using the passed values.
     *
     * @param stepX             Pixels the object moves on the X axis every step
     * @param stepY             Pixels the object moves on the Y axis every step
     * @param delay             Time in milliseconds between every step
     * @param x                 Initial leftmost X coordinate
     * @param y                 Initial leftmost Y coordinate
     * @param height            Height in pixels
     * @param width             Width in pixels
     * @param color             Colour of the object
     * @param gamePanel         GamePanel the object will be displayed on
     * @param positionManager   PositionManager used to manage object positions
     */
    public MovableGameObject(int stepX, int stepY, int delay, int x, int y, int height, int width, Color color, JComponent gamePanel, PositionManager positionManager) {
        super(x, y, height, width, color, gamePanel, positionManager);
        this.stepX = stepX;
        this.stepY = stepY;
        this.delay = delay;
    }

    /**
     * Moves an object 1 step, changing direction and other events if needed.
     * e.g. hitting a wall or object would cause a change in direction.
     */
    public void move() {
        getPositionManager().update(this);
    }

    /**
     * Moves the object a step in each direction.
     */
    public void step() {
        setX(getX() + getStepX());
        setY(getY() + getStepY());
    }

    /**
     * Starts the thread of the MovableGameObject.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
        alive = true;
    }

    @Override
    public void run() {
        getPositionManager().addGameObject(this); // TODO change?
        while (isAlive()) {
            synchronized (Lock.LOCK) {
                clear();
                move();
                display();
            }
            sleep();
        }
        //System.out.println("dead"); // TODO remove
        getPositionManager().removeGameObject(this); // TODO change?
    }

    /**
     * Sleeps for the current delay in milliseconds.
     */
    private void sleep() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovableGameObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the value of alive.
     *
     * @return the value of alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Set the value of alive.
     *
     * @param alive new value of alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets the thread of the MovableGameObject.
     *
     * @return      the thread of the MovableGameObject
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Get the value of stepX.
     *
     * @return the value of stepX
     */
    public int getStepX() {
        return stepX;
    }

    /**
     * Set the value of stepX.
     *
     * @param stepX new value of stepX
     */
    public void setStepX(int stepX) {
        this.stepX = stepX;
    }

    /**
     * Get the value of stepY.
     *
     * @return the value of stepY
     */
    public int getStepY() {
        return stepY;
    }

    /**
     * Set the value of stepY.
     *
     * @param stepY new value of stepY
     */
    public void setStepY(int stepY) {
        this.stepY = stepY;
    }

    /**
     * Changes the X direction.
     */
    public void changeDirectionX() {
        setStepX(getStepX() * -1);
    }

    /**
     * Changes the Y direction.
     */
    public void changeDirectionY() {
        setStepY(getStepY() * -1);
    }

    /**
     * Get the value of delay.
     *
     * @return the value of delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Set the value of delay.
     *
     * @param delay new value of delay
     */
    public void setDelay(int delay) {
        if (delay >= 0) {
            this.delay = delay;
        }
    }

    @Override
    public void onHit(MovableGameObject moving) {
        moving.changeDirectionX();
        this.changeDirectionX();
    }
}