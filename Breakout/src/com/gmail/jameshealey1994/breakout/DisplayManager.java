package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Bat;
import com.gmail.jameshealey1994.breakout.object.Ball;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Collection;

/**
 * Class containing a static method to display an object.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DisplayManager {

   public static void updateBalls(Component component, Collection<Ball> balls) {
       final Graphics g = component.getGraphics();
       for (Ball ball : balls) {
            g.drawOval(ball.getX(), ball.getY(), ball.getHeight(), ball.getWidth());
       }
   }

   public static void updateBat(Component component, Bat bat) {
       final Graphics g = component.getGraphics();
       g.drawRect(bat.getX(), component.getHeight() - bat.getY(), bat.getWidth(), bat.getHeight());
   }
}