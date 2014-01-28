package com.gmail.jameshealey1994.breakout.gui;

import com.gmail.jameshealey1994.breakout.Game;
import com.gmail.jameshealey1994.breakout.Lock;
import com.gmail.jameshealey1994.breakout.object.Bat;
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
    private Game game/* = new Game(this)*/; // TODO figure out why balls and bat aren't shown if game is initialised here and not in run()

    @Override
    public void run() {
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'),
                "moveLeft");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),
                "moveLeft");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'),
                "moveRight");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),
                "moveRight");

        game = new Game(this);

        final Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left"); // TODO remove
                final Bat bat = game.getBat();

                synchronized (Lock.LOCK) {
                    for (int i = 0; i < 10; i++) {
                        bat.clear();
                        final double newPosX = bat.getX() - 1;
                        if (bat.isValidMove(newPosX)) {
                            bat.setX(newPosX);
                        }
                        bat.display();
                    }
                }
            }
        };

        final Action moveRight = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("right"); // TODO remove
                final Bat bat = game.getBat();

                synchronized (Lock.LOCK) {
                    for (int i = 0; i < 10; i++) {
                        bat.clear();
                        final double newPosX = bat.getX() + 1;
                        if (bat.isValidMove(newPosX)) {
                            bat.setX(newPosX);
                        }
                        bat.display();
                    }
                }
            }
        };

        this.getActionMap().put("moveLeft", moveLeft);
        this.getActionMap().put("moveRight", moveRight);

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