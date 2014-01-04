package com.gmail.jameshealey1994.breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GUI for Breakout Game Main Menu.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class MainMenuGUI extends JFrame {

    /**
     * Creates a new instance of the Game GUI.
     *
     * @throws HeadlessException    thrown if user does not have a mouse or keyboard.
     */
    public MainMenuGUI() throws HeadlessException {
        this.setTitle("Breakout - Main Menu");
        this.setLocationRelativeTo(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        final JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        final JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGameGUI();
            }
        });

        playButton.setVisible(true);
        buttonPanel.add(playButton);
        buttonPanel.setVisible(true);
        this.add(buttonPanel);
        this.pack();
    }

    /**
     * Creates and shows the game gui.
     */
    private void createAndShowGameGUI() {
        final GameGUI gameGUI = new GameGUI();
        gameGUI.setLocationRelativeTo(this);
        gameGUI.setVisible(true);
        gameGUI.start();
    }
}