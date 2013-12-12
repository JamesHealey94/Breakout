package com.gmail.jameshealey1994.breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GUI for Breakout Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
class GameGUI extends JFrame {

    /**
     * Creates a new instance of the Game GUI.
     *
     * @throws HeadlessException    thrown if user does not have a mouse or keyboard.
     */
    public GameGUI() throws HeadlessException {
        this.setTitle("Breakout");
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        final JPanel gamePanel = new JPanel();
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setVisible(true);

        final JButton playButton = new JButton("Play"); // TODO Change to Menubar?
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Game game = new Game();
                game.start();
                
                // TODO figure out how to get message to wait till game is finished, but GUI should be ok to move when waiting
                JOptionPane.showMessageDialog(null, "Points: " + game.getPoints()); // TODO figure out how to get "this" instead of null
            }
        });
        this.add(playButton, BorderLayout.NORTH);
        playButton.setVisible(true);

        this.setVisible(true);
    }
}