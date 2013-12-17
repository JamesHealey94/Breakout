package com.gmail.jameshealey1994.breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
        this.setBounds(400, 400, width, height); // TODO replace

        final JPanel gamePanel = new JPanel();
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setVisible(true);

        final JPanel buttonPanel = new JPanel(new GridLayout(2, 1));

        final JButton playButton = new JButton("Play"); // TODO Change to Menubar?
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Thread thread = new Thread() {
                    @Override
                    public void run() {
                        final DisplayManager displayManager = new DisplayManager(gamePanel);
                        final Game game = new Game(displayManager, width, height);
                        game.start();
                        try {
                            game.getThread().join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "Points: " + game.getPoints()); // TODO figure out how to get gamegui instead of null
                    }
                };
                thread.start();
            }
        });
        buttonPanel.add(playButton);
        playButton.setVisible(true);

        this.add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.setVisible(true);

        this.setVisible(true);
    }
}