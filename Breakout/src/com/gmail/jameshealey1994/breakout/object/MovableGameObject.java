package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.DisplayManager;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private Thread thread;

    /**
     * Constructs a new MovableGameObject using the passed values.
     */
    public MovableGameObject(int stepX, int stepY, int delay, int x, int y, int height, int width, Color color, DisplayManager displayManager) {
        super(x, y, height, width, color, displayManager);
        this.stepX = stepX;
        this.stepY = stepY;
        this.delay = delay;
    }

    /**
     * Moves the object a step in each direction.
     */
    public void move() {
        setX(getX() + getStepX());
        setY(getY() + getStepY());
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run() {
        while (thread.isAlive()) {
            synchronized (Lock.lock) {
                clear();
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(MovableGameObject.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //displayManager.clear(this);
            move(); // TODO check for collisions
            //displayManager.display(this);
            synchronized (Lock.lock) {
                display();
            }
        }
    }
    
    public Thread getThread() {
        return thread;
    }
    
    public void setThread(Thread thread) {
        this.thread = thread;
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
        setX(getX() * 1);
    }

    /**
     * Changes the Y direction.
     */
    public void changeDirectionY() {
        setY(getY() * 1);
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
}