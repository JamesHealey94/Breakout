package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Bat;
import com.gmail.jameshealey1994.breakout.object.Ball;
import com.gmail.jameshealey1994.breakout.object.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a Game of Breakout.
 * The game runs until the player is out of lives.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Game implements Runnable {
    
    private final PositionManager positionManager;

    private Collection<Ball> balls;
    
    private Collection<Block> blocks;

    private Bat bat; // TODO several bats once special effects are added.

    private int livesRemaining = 3;

    private Thread thread;

    private int points = 0;
   
    public Game(DisplayManager displayManager, int maxX, int maxY) {
        this.positionManager = new PositionManager(maxX, maxY);
        
        this.balls = new ArrayList<>();
        this.balls.add(new Ball(1, 1, 10, 20, 70, 10, 10, Color.BLUE, displayManager, positionManager));
        this.balls.add(new Ball(1, 1, 10, 200, 30, 10, 10, Color.GREEN, displayManager, positionManager));

        this.blocks = new ArrayList<>();
        this.blocks.add(new Block(40, 30, 10, 30, Color.RED, displayManager));
        this.blocks.add(new Block(70, 30, 10, 30, Color.CYAN, displayManager));
        this.blocks.add(new Block(10, 30, 10, 30, Color.ORANGE, displayManager));
        this.blocks.add(new Block(10, 50, 10, 30, Color.YELLOW, displayManager));
        this.blocks.add(new Block(40, 50, 10, 30, Color.MAGENTA, displayManager));
        this.blocks.add(new Block(70, 50, 10, 30, Color.PINK, displayManager));
        this.blocks.add(new Block(100, 30, 10, 30, Color.RED, displayManager));
        this.blocks.add(new Block(170, 30, 10, 30, Color.CYAN, displayManager));
        this.blocks.add(new Block(110, 70, 10, 30, Color.ORANGE, displayManager));
        this.blocks.add(new Block(110, 150, 10, 30, Color.YELLOW, displayManager));
        this.blocks.add(new Block(140, 150, 10, 30, Color.MAGENTA, displayManager));
        this.blocks.add(new Block(170, 50, 10, 30, Color.PINK, displayManager));
        this.blocks.add(new Block(370, 30, 10, 30, Color.CYAN, displayManager));
        this.blocks.add(new Block(210, 70, 10, 30, Color.ORANGE, displayManager));
        this.blocks.add(new Block(310, 150, 10, 40, Color.YELLOW, displayManager));
        this.blocks.add(new Block(240, 150, 10, 30, Color.MAGENTA, displayManager));
        this.blocks.add(new Block(370, 60, 20, 30, Color.PINK, displayManager));
                
        final int initialBatWidth = 40;
        this.bat = new Bat((positionManager.getMaxX() - initialBatWidth) / 2, initialBatWidth, Color.BLACK, displayManager, positionManager);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (Block block : getBlocks()) {
            positionManager.addGameObject(block);
            block.display();
        }
        
        positionManager.addGameObject(this.bat);
        bat.display();
        
        for (Ball ball : getBalls()) {
            ball.start();
        }
        while (hasLivesRemaining()) { }
    }

    public Thread getThread() { // TODO needed?
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
        if (livesRemaining >= 0) {
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
        return balls;
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

    public Collection<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Collection<Block> blocks) {
        this.blocks = blocks;
    }
}