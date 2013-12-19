package com.gmail.jameshealey1994.breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GUI for Breakout Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class GameGUI extends JFrame {

    private int width = 400;
    private int height = 400;

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
        this.setVisible(true);
        final Thread thread = new Thread() {
            @Override
            public void run() {
                final DisplayManager displayManager = new DisplayManager(gamePanel);
                final Game game = new Game(displayManager, width, height);
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