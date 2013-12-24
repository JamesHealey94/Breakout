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

    /**
     * Width of the Game Panel.
     */
    private final int width = 400;

    /**
     * Height of the Game Panel.
     */
    private final int height = 400;

    /**
     * Game using the Game Panel.
     */
    private Game game;

    /**
     * JPanel used to display the Game.
     */
    private final JPanel gamePanel;

    /**
     * Constructor - Sets up JFrame.
     */
    GameGUI() {
        this.setTitle("Breakout");
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // TODO fix NPE in DisplayManager on close.
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        gamePanel = new JPanel(); // TODO should it be double buffered?
        gamePanel.setPreferredSize(new Dimension(width, height)); // TODO replace
        this.add(gamePanel, BorderLayout.CENTER);
        //this.setResizable(false); TODO fix display issue when resizing.
        this.pack();
    }

    /**
     * Starts a Breakout game in the Game Panel.
     */
    public void start() {
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'),
                "moveLeft");
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),
                "moveLeft");
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'),
                "moveRight");
        gamePanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),
                "moveRight");

        this.setVisible(true);
        final Thread thread = new Thread() {
            @Override
            public void run() {
                final DisplayManager displayManager = new DisplayManager(gamePanel);
                game = new Game(displayManager, width, height);

                final Action moveLeft = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("left"); // TODO remove
                        final Bat bat = game.getBat();

                        synchronized (Lock.LOCK) {
                            for (int i = 0; i < 10; i++) {
                                bat.clear();
                                final int newPosX = bat.getX() - 1;
                                if (isValidMove(newPosX, bat)) {
                                    bat.setX(newPosX);
                                }
                                bat.display();
                            }
                        }
                    }

                    /**
                     * Returns if the bat's move will be valid.
                     * Invalid if the new position will make the bat go off the screen.
                     */
                    public boolean isValidMove(final int newPosX, final Bat bat) { // TODO improve - move to Bat class?
                        return (0 < newPosX) && (newPosX + bat.getWidth() < bat.getPositionManager().getMaxX());
                    }
                };

                final Action moveRight = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("right"); // TODO remove
                        final Bat bat = game.getBat();

                        synchronized (Lock.LOCK) {
                            for (int i = 0; i < 10; i++) {
                                bat.clear();
                                final int newPosX = bat.getX() + 1;
                                if (isValidMove(newPosX, bat)) {
                                    bat.setX(newPosX);
                                }
                                bat.display();
                            }
                        }
                    }

                    /**
                     * Returns if the bat's move will be valid.
                     * Invalid if the new position will make the bat go off the screen.
                     */
                    public boolean isValidMove(final int newPosX, final Bat bat) {
                        return (0 < newPosX) && (newPosX + bat.getWidth() < bat.getPositionManager().getMaxX());
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
                JOptionPane.showMessageDialog(gamePanel, "Points: " + game.getPoints());
            }
        };
        thread.start();
    }
}