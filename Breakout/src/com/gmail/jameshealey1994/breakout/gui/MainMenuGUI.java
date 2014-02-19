package com.gmail.jameshealey1994.breakout.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main Menu GUI for Breakout Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class MainMenuGUI extends JFrame {

    /**
     * Name of the game.
     */
    private String gameName;

    /**
     * Creates a new instance of the Game GUI.
     *
     * @param gameName              the name of the game, used as part of title
     * @param iconImage             icon image
     * @throws HeadlessException    thrown if user does not have a mouse or keyboard.
     */
    public MainMenuGUI(String gameName, Image iconImage) throws HeadlessException {
        this.setTitle(gameName + " - Main Menu");
        this.setLocationRelativeTo(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(iconImage);
        this.setLayout(new BorderLayout());

        final JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.setVisible(true);
        this.add(buttonPanel);

        final JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGameGUI();
            }
        });
        buttonPanel.add(playButton);

        final JButton scoresButton = new JButton("Scores");
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowScoresGUI();
            }
        });
        buttonPanel.add(scoresButton);

        this.pack();
    }

    /**
     * Creates and shows the game GUI.
     */
    private void createAndShowGameGUI() {
        final GameGUI gameGUI = new GameGUI(gameName, null); // TODO create icon image
        gameGUI.setLocationRelativeTo(this);
        gameGUI.setVisible(true);
        gameGUI.start();
    }

    /**
     * Creates and shows the scores GUI.
     */
    private void createAndShowScoresGUI() {
        final ScoresGUI scoresGUI = new ScoresGUI(gameName, null); // TODO create icon image
        scoresGUI.setLocationRelativeTo(this);
        scoresGUI.setVisible(true);
    }
}