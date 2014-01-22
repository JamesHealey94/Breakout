package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.Game;
import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Abstract class representing an object in the game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class GameObject {

    /**
     * The leftmost X coordinate of the GameObject.
     */
    private double x;

    /**
     * The highest y coordinate of the GameObject.
     */
    private double y;

    /**
     * The height of the GameObject.
     */
    private double height;

    /**
     * The width of the GameObject.
     */
    private double width;

    /**
     * The color of the GameObject.
     */
    private Color color;

    /**
     * Manages how the GameObject is displayed.
     */
    private final JComponent gamePanel;

    /**
     * Manages the position of the GameObject.
     */
    private final PositionManager positionManager;

    /**
     * Game the GameObject belongs to.
     */
    private final Game game;

    /**
     * Constructs a new GameObject using the passed values.
     *
     * @param x                 leftmost X coordinate of the GameObject
     * @param y                 highest y coordinate of the GameObject
     * @param height            height of the GameObject
     * @param width             width of the GameObject
     * @param color             color of the GameObject
     * @param game              game the object belongs to
     */
    public GameObject(double x, double y, double height, double width, Color color, Game game) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
        this.game = game;
        this.gamePanel = game.getGamePanel();
        this.positionManager = game.getPositionManager();
    }

    /**
     * Displays the GameObject to the object's gamePanel.
     */
    public void display() {
        gamePanel.repaint();
    }

    /**
     * Paints the GameObject onto the passed Graphics.
     *
     * @param g     Graphics to paint the GameObject on to
     */
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Clears the GameObject from display.
     */
    public void clear() {
        gamePanel.repaint(); // TODO change / improve
    }

    /**
     * Get the value of x.
     *
     * @return the value of x
     */
    public double getX() {
        return x;
    }

    /**
     * Set the value of x.
     *
     * @param x new value of x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the value of y.
     *
     * @return the value of y
     */
    public double getY() {
        return y;
    }

    /**
     * Set the value of y.
     *
     * @param y new value of y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the value of height.
     *
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value of height.
     *
     * @param height new value of height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the value of width.
     *
     * @return the value of width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the value of width.
     *
     * @param width new value of width
     */
    public void setWidth(double width) {
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

    /**
     * Returns the JComponent that the GameObject will be displayed on.
     *
     * @return  the JComponent that the GameObject will be displayed on.
     */
    public JComponent getGamePanel() {
        return gamePanel;
    }

    /**
     * Returns the GameObject's PositionManager.
     *
     * @return  the PositionManager of the GameObject
     */
    public PositionManager getPositionManager() {
        return positionManager;
    }

    /**
     * Called when the object has been hit by a MovableGameObject.
     *
     * @param moving    MovableGameObject that hit this object
     */
    abstract public void onHit(MovableGameObject moving);

    /**
     * Returns the middle X coordinate of the object.
     *
     * @return  the middle X coordinate of the object
     */
    public double getMiddleX() {
        return getX() + getWidth() / 2;
    }

    /**
     * Returns the Game the object.
     *
     * @return  the Game of the object
     */
    public Game getGame() {
        return game;
    }
}