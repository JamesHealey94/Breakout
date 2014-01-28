package com.gmail.jameshealey1994.breakout;

import com.gmail.jameshealey1994.breakout.gui.GamePanel;
import com.gmail.jameshealey1994.breakout.object.Ball;
import com.gmail.jameshealey1994.breakout.object.Bat;
import com.gmail.jameshealey1994.breakout.object.Block;
import com.gmail.jameshealey1994.breakout.object.GameObject;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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

    /**
     * Constructor - Creates a Game on a GamePanel, sets the PositionManager,
     * and adds Balls, Blocks and a Bat.
     *
     * @param gamePanel     GamePanel the Game is displayed on
     */
    public Game(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.positionManager = new PositionManager(this, gamePanel.getWidth(), gamePanel.getHeight());

        this.balls = new ArrayList<>();
        this.balls.add(new Ball(0.2, 0.8, 3, 100, 270, 15, 15, Color.BLUE, this));
        this.balls.add(new Ball(0.5, 0.5, 3, 200, 230, 15, 15, Color.GREEN, this));

        this.blocks = new ArrayList<>();

        this.blocks.add(new Block(100, 30, 20, 40, Color.ORANGE, this));
        this.blocks.add(new Block(140, 30, 20, 40, Color.RED, this));
        this.blocks.add(new Block(180, 30, 20, 40, Color.GREEN, this));
        this.blocks.add(new Block(220, 30, 20, 40, Color.CYAN, this));
        this.blocks.add(new Block(260, 30, 20, 40, Color.RED, this));
        this.blocks.add(new Block(300, 30, 20, 40, Color.PINK, this));

        this.blocks.add(new Block(110, 50, 20, 40, Color.YELLOW, this));
        this.blocks.add(new Block(150, 50, 20, 40, Color.MAGENTA, this));
        this.blocks.add(new Block(190, 50, 20, 40, Color.RED, this));
        this.blocks.add(new Block(230, 50, 20, 40, Color.PINK, this));
        this.blocks.add(new Block(270, 50, 20, 40, Color.BLUE, this));
        this.blocks.add(new Block(310, 50, 20, 40, Color.PINK, this));

        this.blocks.add(new Block(120, 70, 20, 40, Color.PINK, this));
        this.blocks.add(new Block(160, 70, 20, 40, Color.ORANGE, this));
        this.blocks.add(new Block(200, 70, 20, 40, Color.MAGENTA, this));
        this.blocks.add(new Block(240, 70, 20, 40, Color.ORANGE, this));

        this.blocks.add(new Block(130, 90, 20, 40, Color.YELLOW, this));
        this.blocks.add(new Block(170, 90, 20, 40, Color.MAGENTA, this));
        this.blocks.add(new Block(210, 90, 20, 40, Color.YELLOW, this));
        this.blocks.add(new Block(250, 90, 20, 40, Color.MAGENTA, this));

        this.blocks.add(new Block(140, 110, 20, 40, Color.RED, this));
        this.blocks.add(new Block(180, 110, 20, 40, Color.LIGHT_GRAY, this));
        this.blocks.add(new Block(220, 110, 20, 40, Color.MAGENTA, this));


        this.bat = new Bat((positionManager.getMaxX() - Bat.INITIAL_WIDTH) / 2, Bat.INITIAL_WIDTH, Color.BLACK, this);
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
        for (Ball ball : getBalls()) {
            ball.start();
        }
        while (hasLivesRemaining() || !this.getBalls().isEmpty()) {
            System.out.println("livesRemaining " + livesRemaining + "     this.getBalls().size()" + this.getBalls().size()); // TODO remove once working fully
        }

        System.out.println("livesRemaining " + livesRemaining + "     this.getBalls().size()" + this.getBalls().size() + " THREAD FINISHED"); // TODO remove once working fully
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

    /**
     * Returns the object's blocks as an unmodifiable collection.
     *
     * @return  the object's blocks as an unmodifiable collection
     */
    public Collection<Block> getBlocks() {
        return blocks;
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
            final Ball newBall = new Ball(0.8, -0.2, 3, this.bat.getMiddleX(), positionManager.getMaxY() - Ball.INITIAL_Y, 15, 15, Color.CYAN, this);
            this.balls.add(newBall);
            newBall.display();
            newBall.start();
        }
    }

    /**
     * Returns the JComponent the game is displayed on.
     *
     * @return  the JComponent the game is displayed on
     */
    public JComponent getGamePanel() {
        return gamePanel;
    }

    /**
     * Sets the points of the game.
     *
     * @param points    new points value
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Returns all the GameObjects in the game.
     *
     * @return      the GameObjects stored
     */
    public Set<GameObject> getGameObjects() {
        final Set<GameObject> objects = new HashSet<>();
        objects.addAll(blocks);
        objects.addAll(balls);
        objects.add(bat);
        return Collections.unmodifiableSet(objects);
    }

//    TODO perhaps remove
//    /**
//     * Adds a GameObject to the objects Set.
//     *
//     * @param object    object to be added to the set
//     * @return          if the object was successfully added
//     */
//    public boolean addGameObject(GameObject object) {
//        return objects.add(object);
//    }
//
//    /**
//     * Removes a GameObject from the objects Set.
//     *
//     * @param object    object to be removed from the set
//     * @return          if the object was successfully removed
//     */
//    public boolean removeGameObject(GameObject object) {
//        return objects.remove(object);
//    }
}