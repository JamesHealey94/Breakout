package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.DisplayManager;
import java.awt.Color;

/**
 * Class representing a Movable Bat Game Object.
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

    public Bat(int stepX, int stepY, int delay, int x, int width, Color color, DisplayManager displayManager) {
        super(stepX, stepY, delay, x, BAT_Y, BAT_HEIGHT, width, color, displayManager);
    }
}