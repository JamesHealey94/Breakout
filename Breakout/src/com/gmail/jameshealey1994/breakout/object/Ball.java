package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;
import javax.swing.JComponent;

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

    public Ball(int stepX, int stepY, int delay, int x, int y, int height, int width, Color color, JComponent gamePanel, PositionManager positionManager) {
        super(stepX, stepY, delay, x, y, height, width, color, gamePanel, positionManager);
    }
}