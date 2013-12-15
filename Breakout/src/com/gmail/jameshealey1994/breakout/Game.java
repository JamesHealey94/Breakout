package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.object.Bat;
import com.gmail.jameshealey1994.breakout.object.Ball;
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

    private Collection<Ball> balls;

    private Bat bat;

    private int livesRemaining = 3;

    private Thread thread;

    private int points = 0;

    public Game() {
        final Ball ball = new Ball(1, 1, 100, 20, 20, 10, 10, Color.BLUE);
        this.balls = new ArrayList<>();
        this.balls.add(ball);

        this.bat = new Bat(1, 1, 10, 100, 50, Color.BLACK);
    }

    public Game(Collection<Ball> balls, Bat bat) {
        this.balls = balls;
        this.bat = bat;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (hasLivesRemaining()) {
            //updatePositionsAndSpeeds();
            updateDisplay();
            livesRemaining--;
        }

        // TODO remove - just for testing
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
            updateDisplay();
        }
    }

    /**
     * To be overridden to however you would like the game to be displayed.
     */
    public void updateDisplay() { }

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
}