package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.GameObject;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Class responsible for how objects are displayed.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DisplayManager {

    private final JComponent component;

    public DisplayManager(JComponent component) {
        this.component = component;
    }

   public void display(GameObject obj) {
       final Graphics g = component.getGraphics();
       g.setColor(obj.getColor());
       g.fillRect(obj.getX(), /*component.getHeight() - */obj.getY(), obj.getWidth(), obj.getHeight());
//       g.setColor(Color.BLACK);
//       g.drawRect(obj.getX(), /*component.getHeight() - */obj.getY(), obj.getWidth(), obj.getHeight());
   }
   
   public void clear(GameObject obj) {
       final Graphics g = component.getGraphics();
       g.setColor(component.getBackground());
       g.fillRect(obj.getX(), /*component.getHeight() - */obj.getY(), obj.getWidth(), obj.getHeight());
   }
}