package com.gmail.jameshealey1994.breakout.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Scores Menu GUI for Breakout Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ScoresGUI extends JFrame {

    /**
     * JPanel used to display the scores.
     */
    private final JPanel scoresPanel;

    /**
     * Constructor - Sets up JFrame.
     */
    public ScoresGUI() {
        this.setTitle("Breakout - Scores");
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        scoresPanel = new GamePanel();
        this.add(scoresPanel, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
}