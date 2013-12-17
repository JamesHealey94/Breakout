package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.DisplayManager;
import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;

/**
 * Class representing a Movable Ball Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Ball extends MovableGameObject {

    public Ball(int stepX, int stepY, int delay, int x, int y, int height, int width, Color color, DisplayManager displayManager, PositionManager positionManager) {
        super(stepX, stepY, delay, x, y, height, width, color, displayManager, positionManager);
    }
}