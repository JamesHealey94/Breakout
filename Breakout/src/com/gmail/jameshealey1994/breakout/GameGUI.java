package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Bat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * GUI for Breakout Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class GameGUI extends JFrame {

    private final int width = 400;
    private final int height = 400;

    private Game game;

    private final JPanel gamePanel;

    GameGUI() {
        this.setTitle("Breakout");
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // TODO fix NPE in DisplayManager on close.
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        gamePanel = new JPanel(); // TODO should it be double buffered?
        gamePanel.setPreferredSize(new Dimension(width, height)); // TODO replace
        gamePanel.setVisible(true);
        this.add(gamePanel, BorderLayout.CENTER);
        this.pack();
    }

    public void start() {
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'),
                "moveLeft");
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("left"),
                "moveLeft");
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'),
                "moveRight");
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("right"),
                "moveRight");

        this.setVisible(true);
        final Thread thread = new Thread() {
            @Override
            public void run() {
                final DisplayManager displayManager = new DisplayManager(gamePanel);
                game = new Game(displayManager, width, height);

                Action moveLeft = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("left");
                        Bat bat = game.getBat();

                        synchronized (Lock.lock) {
                            for (int i = 0; i < 10; i++) {
                                bat.clear();
                                bat.setX(bat.getX() - 1);
                                bat.display();
                            }
                        }
                    }
                };

                Action moveRight = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("right");
                        Bat bat = game.getBat();

                        synchronized (Lock.lock) {
                            for (int i = 0; i < 10; i++) {
                                bat.clear();
                                bat.setX(bat.getX() + 1);
                                bat.display();
                            }
                        }
                    }
                };

                gamePanel.getActionMap().put("moveLeft", moveLeft);
                gamePanel.getActionMap().put("moveRight", moveRight);

                game.start();
                try {
                    game.getThread().join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Points: " + game.getPoints()); // TODO figure out how to get gamegui instead of null
            }
        };
        thread.start();
    }
}