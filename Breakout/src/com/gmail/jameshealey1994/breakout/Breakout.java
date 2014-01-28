package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.gui.MainMenuGUI;
import javax.swing.SwingUtilities;

/**
 * Breakout clone.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Breakout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * Creates and shows the main menu.
     * Runs on the Event Dispatch Thread to prevent possible deadlock.
     *
     * @see http://docs.oracle.com/javase/tutorial/uiswing/painting/step1.html
     */
    private static void createAndShowGUI() {
        final MainMenuGUI gui = new MainMenuGUI();
        gui.setVisible(true);
    }
}