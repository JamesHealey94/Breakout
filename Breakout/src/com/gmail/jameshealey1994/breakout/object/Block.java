package com.gmail.jameshealey1994.breakout.object;

import com.gmail.jameshealey1994.breakout.PositionManager;
import java.awt.Color;
import javax.swing.JComponent;

/**
 * Class representing a Block Game Object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Block extends GameObject {

    public Block(int x, int y, int height, int width, Color color, JComponent gamePanel, PositionManager positionManager) {
        super(x, y, height, width, color, gamePanel, positionManager);
    }

    @Override
    public void onHit(MovableGameObject moving) {
        moving.changeDirectionX();
        moving.changeDirectionY();
        this.clear();
        this.getPositionManager().removeGameObject(this);
        // TODO increase points
    }
}