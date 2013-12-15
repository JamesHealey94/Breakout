package com.gmail.jameshealey1994.breakout.object;

import java.awt.Color;

/**
 * Abstract class representing a GameObject that can move.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class MovableGameObject extends GameObject {

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
     * Constructs a new MovableGameObject using the passed values.
     */
    public MovableGameObject(int stepX, int stepY, int delay, int x, int y, int height, int width, Color color) {
        super(x, y, height, width, color);
        this.stepX = stepX;
        this.stepY = stepY;
        this.delay = delay;
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