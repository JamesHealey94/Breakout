package com.gmail.jameshealey1994.breakout.gui;

import com.gmail.jameshealey1994.breakout.Game;
import com.gmail.jameshealey1994.breakout.object.GameObject;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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
        final char[] leftKeysChars = {'a'};
        final String[] leftKeysStrings = {"LEFT"};

        final char[] rightKeysChars = {'d'};
        final String[] rightKeysStrings = {"RIGHT"};

        setupLeftKeyPressActions(leftKeysChars, leftKeysStrings);
        setupRightKeyPressActions(rightKeysChars, rightKeysStrings);
        setupKeyReleaseActions(leftKeysChars, leftKeysStrings, rightKeysChars, rightKeysStrings);

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

    private void setupLeftKeyPressActions(final char[] leftKeysChars, final String[] leftKeysStrings) {
        final String leftMethodName = "moveLeft";

        final Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left"); // TODO remove
                game.getBat().setStepX(-1);
            }
        };

        this.getActionMap().put(leftMethodName, moveLeft);

        for (char key : leftKeysChars) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    leftMethodName);
        }

        for (String key : leftKeysStrings) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    leftMethodName);
        }
    }

    private void setupRightKeyPressActions(final char[] rightKeysChars, final String[] rightKeysStrings) {
        final String rightMethodName = "moveRight";

        final Action moveRight = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("right"); // TODO remove
                game.getBat().setStepX(1);
            }
        };

        this.getActionMap().put(rightMethodName, moveRight);

        for (char key : rightKeysChars) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    rightMethodName);
        }

        for (String key : rightKeysStrings) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    rightMethodName);
        }
    }

    private void setupKeyReleaseActions(final char[] leftKeysChars, final String[] leftKeysStrings, final char[] rightKeysChars, final String[] rightKeysStrings) {
        final String stopMovingMethodName = "stopMoving";

        final Action stopMoving = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stop"); // TODO remove
                game.getBat().setStepX(0);
            }
        };

        this.getActionMap().put(stopMovingMethodName, stopMoving);

        for (char key : leftKeysChars) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + String.valueOf(key).toUpperCase()),
                    stopMovingMethodName);
        }

        for (String key : leftKeysStrings) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + key),
                    stopMovingMethodName);
        }

        for (char key : rightKeysChars) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + String.valueOf(key).toUpperCase()),
                    stopMovingMethodName);
        }

        for (String key : rightKeysStrings) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + key),
                    stopMovingMethodName);
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
}