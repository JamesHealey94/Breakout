package com.gmail.jameshealey1994.breakout.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;

/**
 * Game GUI for a Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class GameGUI extends JFrame {

    /**
     * JPanel used to display the Game.
     */
    private final GamePanel gamePanel;

    /**
     * Constructor - Sets up JFrame.
     *
     * @param gameName      name of the game, used for the title
     * @param iconImage     icon image
     */
    GameGUI(String gameName, Image iconImage) {
        this.setTitle(gameName);
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(iconImage);
        this.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
    }

    /**
     * Starts a game in the Game Panel.
     */
    public void start() {
        final Thread thread = new Thread(gamePanel);
        thread.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
}