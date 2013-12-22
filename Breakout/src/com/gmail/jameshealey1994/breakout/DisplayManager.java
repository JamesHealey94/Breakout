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

    /**
     * JComponent that GameObjects are displayed on.
     */
    private final JComponent component;

    /**
     * Constructor - Sets component.
     * 
     * @param component     JComponent that GameObjects are displayed on
     */
    public DisplayManager(JComponent component) {
        this.component = component;
    }

    /**
     * Displays a GameObject on to the Component.
     * 
     * @param obj       GameObject to display
     */
    public void display(GameObject obj) {
       final Graphics g = component.getGraphics();
       g.setColor(obj.getColor());
       g.fillRect(obj.getX(), /*component.getHeight() - */obj.getY(), obj.getWidth(), obj.getHeight());
//       g.setColor(Color.BLACK);
//       g.drawRect(obj.getX(), /*component.getHeight() - */obj.getY(), obj.getWidth(), obj.getHeight());
   }
   
   
    /**
     * Clears a GameObject from the Component.
     * 
     * @param obj       GameObject to clear
     */
    public void clear(GameObject obj) {
       final Graphics g = component.getGraphics();
       g.setColor(component.getBackground());
       g.fillRect(obj.getX(), /*component.getHeight() - */obj.getY(), obj.getWidth(), obj.getHeight());
   }
}