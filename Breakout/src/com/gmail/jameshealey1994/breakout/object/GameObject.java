package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.DisplayManager;
import java.awt.Color;

/**
 * Abstract class representing an object in the game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class GameObject {
//isAlive = true TODO needed?
//die() { isAlive = false; this = null; }

    /**
     * The leftmost X coordinate of the GameObject.
     */
    private int x;

    /**
     * The highest y coordinate of the GameObject.
     */
    private int y;

    /**
     * The height of the GameObject.
     */
    private int height;

    /**
     * The width of the GameObject.
     */
    private int width;

    /**
     * The color of the GameObject.
     */
    private Color color;

    private final DisplayManager displayManager;

    /**
     * Constructs a new GameObject using the passed values.
     *
     * @param x                 leftmost X coordinate of the GameObject
     * @param y                 highest y coordinate of the GameObject
     * @param height            height of the GameObject
     * @param width             width of the GameObject
     * @param color             color of the GameObject
     * @param displayManager    displayManager of the GameObject
     */
    public GameObject(int x, int y, int height, int width, Color color, DisplayManager displayManager) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
        this.displayManager = displayManager;
    }

    public void clear() {
        displayManager.clear(this);
    }

    public void display() {
        displayManager.display(this);
    }

    /**
     * Get the value of x.
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Set the value of x.
     *
     * @param x new value of x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the value of y.
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the value of y.
     *
     * @param y new value of y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the value of height.
     *
     * @return the value of height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the value of height.
     *
     * @param height new value of height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the value of width.
     *
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the value of width.
     *
     * @param width new value of width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get the value of color.
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the value of color.
     *
     * @param color new value of color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}