package com.gmail.jameshealey1994.breakout.gui;

import com.gmail.jameshealey1994.breakout.Game;
import com.gmail.jameshealey1994.breakout.object.GameObject;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * JPanel to display a Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class GamePanel extends JPanel implements Runnable {

    /**
     * Game using the Game Panel.
     */
    private Game game;

    @Override
    public void run() {
        final char[] leftKeyChars = {'a'};
        final String[] leftKeyStrings = {"LEFT"};

        final char[] rightKeyChars = {'d'};
        final String[] rightKeyStrings = {"RIGHT"};

        setupKeyActions("moveLeft", -1, leftKeyChars, leftKeyStrings);
        setupKeyActions("moveRight", 1, rightKeyChars, rightKeyStrings);
        String[] keyStrings = getReleasedKeyStrings(leftKeyStrings, rightKeyStrings, leftKeyChars, rightKeyChars);
        setupKeyActions("stopMoving", 0, new char[] {}, keyStrings);

        game = new Game(this);
        game.start();
        try {
            if (game.getThread().isAlive()) {
                game.getThread().join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Points: " + game.getPoints(), "Points", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Put key actions 
     * 
     * @param methodName    name of method to be put on the ActionMap
     * @param stepX         how far the object moves up every step
     * @param keysChars     chars to be put on the InputMap
     * @param keysStrings   strings to be put on the InputMap
     */
    private void setupKeyActions(final String methodName, final double stepX, final char[] keysChars, final String[] keysStrings) {
        final Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(methodName); // TODO remove
                game.getBat().setStepX(stepX);
            }
        };

        this.getActionMap().put(methodName, moveLeft);

        for (char key : keysChars) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    methodName);
        }

        for (String key : keysStrings) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    methodName);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game == null) {
            return; // TODO find a better solution
        }
        for (GameObject gameObject : game.getGameObjects()) {
            gameObject.paint(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }

    /**
     * Returns a String array of released keys.
     * 
     * @param leftKeyStrings    Strings representing keys, to be added
     * @param rightKeyStrings   Strings representing keys, to be added
     * @param leftKeyChars      chars representing keys, to be added
     * @param rightKeyChars     chars representing keys, to be added
     * @return                  String array of released keys
     */
    private String[] getReleasedKeyStrings(final String[] leftKeyStrings, final String[] rightKeyStrings, final char[] leftKeyChars, final char[] rightKeyChars) {
        List<String> keyStrings = new ArrayList<>();
        for (String string : leftKeyStrings) {
            keyStrings.add("released " + string);
        }
        for (String string : rightKeyStrings) {
            keyStrings.add("released " + string);
        }
        for (char key : leftKeyChars) {
            keyStrings.add("released " + String.valueOf(key).toUpperCase());
        }
        for (char key : rightKeyChars) {
            keyStrings.add("released " + String.valueOf(key).toUpperCase());
        }
        return keyStrings.toArray(new String[keyStrings.size()]);
    }
}