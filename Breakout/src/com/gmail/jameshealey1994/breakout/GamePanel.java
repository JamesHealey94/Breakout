package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Bat;
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
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'),
                "moveLeft");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),
                "moveLeft");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'),
                "moveRight");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),
                "moveRight");

        final DisplayManager displayManager = new DisplayManager(this);
        game = new Game(displayManager, this.getWidth(), this.getHeight());

        final Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left"); // TODO remove
                final Bat bat = game.getBat();

                synchronized (Lock.LOCK) {
                    for (int i = 0; i < 10; i++) {
                        bat.clear();
                        final int newPosX = bat.getX() - 1;
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
                        final int newPosX = bat.getX() + 1;
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
            game.getThread().join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Points: " + game.getPoints());
    }
}