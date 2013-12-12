package com.gmail.jameshealey1994.breakout;

/**
 * Represents a Game of Breakout.
 * The game runs until the player is out of lives.
 * 
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Game implements Runnable {

    private Thread thread;
    
    private int points = 0;

    /**
     * Get the value of points
     *
     * @return the value of points
     */
    public int getPoints() {
        return points;
    }
    
    public void start() {
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run() {
        // TODO remove - just for testing
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
        }
    }
}