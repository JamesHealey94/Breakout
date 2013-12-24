package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.DisplayManager;
import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;

/**
 * Class representing a Block Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Block extends GameObject {

    public Block(int x, int y, int height, int width, Color color, DisplayManager displayManager, PositionManager positionManager) {
        super(x, y, height, width, color, displayManager, positionManager);
    }
}