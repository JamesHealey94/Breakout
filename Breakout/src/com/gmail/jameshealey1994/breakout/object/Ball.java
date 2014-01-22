package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.Game;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Class representing a Movable Ball Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Ball extends MovableGameObject {

    /**
     * The height new Balls normally start at.
     */
    public static final int INITIAL_Y = Bat.BAT_Y + 20;

    /**
     * Constructor - Creates a Ball with starting position and initial color.
     *
     * @param stepX             Pixels the object moves on the X axis every step
     * @param stepY             Pixels the object moves on the Y axis every step
     * @param delay             Time in milliseconds between every step
     * @param x                 Initial leftmost x coordinate
     * @param y                 Initial highest Y coordinate
     * @param height            Initial height
     * @param width             Initial width
     * @param color             Initial color
     * @param game              Game the object belongs to
     */
    public Ball(double stepX, double stepY, int delay, double x, double y, double height, double width, Color color, Game game) {
        super(stepX, stepY, delay, x, y, height, width, color, game);
    }

    /**
     * Paints the GameObject onto the passed Graphics.
     *
     * @param g     Graphics to paint the GameObject on to
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillOval((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}