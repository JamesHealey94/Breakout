package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Ball;
import com.gmail.jameshealey1994.breakout.object.Bat;
import com.gmail.jameshealey1994.breakout.object.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.JComponent;

/**
 * Represents a Game of Breakout.
 * The game runs until the player is out of lives.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Game implements Runnable {

    /**
     * The PositionManager of the object.
     */
    private final PositionManager positionManager;

    /**
     * The current balls in the game.
     */
    private Collection<Ball> balls;

    /**
     * The current blocks in the game.
     */
    private Collection<Block> blocks;

    /**
     * The current Bat in the game.
     */
    private Bat bat;

    /**
     * The current lives remaining.
     */
    private int livesRemaining = 3;

    /**
     * The thread the game runs on.
     */
    private Thread thread;

    /**
     * The JComponent the Game is being played on.
     */
    private JComponent gamePanel;

    /**
     * The points the player of the game has scored.
     */
    private int points = 0;

    public Game(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.positionManager = new PositionManager(this, gamePanel.getWidth(), gamePanel.getHeight());

        this.balls = new ArrayList<>();
        this.balls.add(new Ball(1, 1, 10, 20, 70, 10, 10, Color.BLUE, gamePanel, positionManager));
        this.balls.add(new Ball(1, 1, 10, 200, 30, 10, 10, Color.GREEN, gamePanel, positionManager));

        this.blocks = new ArrayList<>();
        this.blocks.add(new Block(40, 30, 10, 30, Color.RED, gamePanel, positionManager)); // TODO do gameobjects need a position manager?
        this.blocks.add(new Block(70, 30, 10, 30, Color.CYAN, gamePanel, positionManager));
        this.blocks.add(new Block(10, 30, 10, 30, Color.ORANGE, gamePanel, positionManager));
        this.blocks.add(new Block(10, 50, 10, 30, Color.YELLOW, gamePanel, positionManager));
        this.blocks.add(new Block(40, 50, 10, 30, Color.MAGENTA, gamePanel, positionManager));
        this.blocks.add(new Block(70, 50, 10, 30, Color.PINK, gamePanel, positionManager));
        this.blocks.add(new Block(100, 30, 10, 30, Color.RED, gamePanel, positionManager));
        this.blocks.add(new Block(170, 30, 10, 30, Color.CYAN, gamePanel, positionManager));
        this.blocks.add(new Block(110, 70, 10, 30, Color.ORANGE, gamePanel, positionManager));
        this.blocks.add(new Block(110, 150, 10, 30, Color.YELLOW, gamePanel, positionManager));
        this.blocks.add(new Block(140, 150, 10, 30, Color.MAGENTA, gamePanel, positionManager));
        this.blocks.add(new Block(170, 50, 10, 30, Color.PINK, gamePanel, positionManager));
        this.blocks.add(new Block(370, 30, 10, 30, Color.CYAN, gamePanel, positionManager));
        this.blocks.add(new Block(210, 70, 10, 30, Color.ORANGE, gamePanel, positionManager));
        this.blocks.add(new Block(310, 150, 10, 40, Color.YELLOW, gamePanel, positionManager));
        this.blocks.add(new Block(240, 150, 10, 30, Color.MAGENTA, gamePanel, positionManager));
        this.blocks.add(new Block(370, 60, 20, 30, Color.PINK, gamePanel, positionManager));

        this.bat = new Bat((positionManager.getMaxX() - Bat.INITIAL_WIDTH) / 2, Bat.INITIAL_WIDTH, Color.BLACK, gamePanel, positionManager);
    }

    /**
     * Starts the game's thread.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (Block block : getBlocks()) {
            positionManager.addGameObject(block);
            //block.display();
        }

        positionManager.addGameObject(this.bat);
        bat.display();

        for (Ball ball : getBalls()) {
            ball.start();
        }
        while (hasLivesRemaining()) {  }
    }

    /**
     * Returns the thread the Game is on.
     *
     * @return      the thread the Game is on
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Get the value of livesRemaining.
     *
     * @return the value of livesRemaining
     */
    public int getLivesRemaining() {
        return livesRemaining;
    }

    /**
     * Set the value of livesRemaining.
     *
     * @param livesRemaining new value of livesRemaining
     */
    public void setLivesRemaining(int livesRemaining) {
        if (livesRemaining <= 0) {
            this.livesRemaining = 0;
        } else {
            this.livesRemaining = livesRemaining;
        }
    }

    /**
     * Get the value of points.
     *
     * @return the value of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Get the value of balls.
     *
     * @return the value of balls
     */
    public Collection<Ball> getBalls() {
        return Collections.unmodifiableCollection(balls);
    }

    /**
     * Set the value of balls.
     *
     * @param balls new value of balls
     */
    public void setBalls(Collection<Ball> balls) {
        this.balls = balls;
    }

    /**
     * Get the value of bat.
     *
     * @return the value of bat
     */
    public Bat getBat() {
        return bat;
    }

    /**
     * Set the value of bat.
     *
     * @param bat new value of bat
     */
    public void setBat(Bat bat) {
        this.bat = bat;
    }

    /**
     * Returns if lives remaining is more than 0.
     *
     * @return if livesRemaing > 0
     */
    public boolean hasLivesRemaining() {
        return livesRemaining > 0;
    }

    /**
     * Returns the object's blocks as an unmodifiable collection.
     *
     * @return  the object's blocks as an unmodifiable collection
     */
    public Collection<Block> getBlocks() {
        return Collections.unmodifiableCollection(blocks);
    }

    /**
     * Sets the object's blocks to a passed Collection of Blocks.
     *
     * @param blocks    new blocks value
     */
    public void setBlocks(Collection<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Returns the PositionManager of the object.
     *
     * @return  the PositionManager of the object
     */
    public PositionManager getPositionManager() {
        return positionManager;
    }

    /**
     * Creates and starts a new ball if the Game has lives remaining.
     */
    public void newBall() {
        if (this.hasLivesRemaining()) {
            final Ball newBall = new Ball(1, -1, 10, this.bat.getMiddleX(), positionManager.getMaxY() - Ball.INITIAL_Y, 10, 10, Color.CYAN, gamePanel, positionManager);
            this.balls.add(newBall);
            newBall.display();
            newBall.start();
        }
    }
}