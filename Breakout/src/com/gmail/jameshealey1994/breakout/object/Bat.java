package com.gmail.jameshealey1994.breakout.object;

import java.awt.Color;

/**
 * Class representing a Movable Ball Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Bat extends MovableGameObject {

    /**
     * The Y coordinate of the top of all the bats.
     */
    public static final int BAT_Y = 20;

    /**
     * The height of all the bats.
     */
    public static final int BAT_HEIGHT = 10;

    public Bat(int stepX, int stepY, int delay, int x, int width, Color color) {
        super(stepX, stepY, delay, x, BAT_Y, BAT_HEIGHT, width, color);
    }
}