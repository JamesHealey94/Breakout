package com.gmail.jameshealey1994.breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * GUI for Breakout Game.
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
     */
    GameGUI() {
        this.setTitle("Breakout");
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // TODO fix NPE in DisplayManager on close.
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setPreferredSize(new Dimension(640, 480));
        //this.setResizable(false); // TODO uncomment after display work is finished
        this.pack();
    }

    /**
     * Starts a Breakout game in the Game Panel.
     */
    public void start() {
        final Thread thread = new Thread(gamePanel);
        thread.start();
    }
}