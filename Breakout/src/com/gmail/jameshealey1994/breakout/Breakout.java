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
     * The name of the Game.
     */
    private static final String GAME_NAME = "Breakout";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI(GAME_NAME);
            }
        });
    }

    /**
     * Creates and shows the main menu.
     * Runs on the Event Dispatch Thread to prevent possible deadlock.
     *
     * @param gameName  the name of the game
     * @see http://docs.oracle.com/javase/tutorial/uiswing/painting/step1.html
     */
    private static void createAndShowGUI(String gameName) {
        final MainMenuGUI gui = new MainMenuGUI(gameName, null); // TODO create icon image
        gui.setVisible(true);
    }
}