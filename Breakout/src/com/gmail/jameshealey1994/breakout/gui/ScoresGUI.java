package com.gmail.jameshealey1994.breakout.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Scores Menu GUI for a Game.
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
     *
     * @param gameName      name of the game, used as part of title
     * @param iconImage     icon image
     */
    public ScoresGUI(String gameName, Image iconImage) {
        this.setTitle(gameName + " - Scores");
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(iconImage); // TODO create Icon
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