//package ass5;

import biuoop.DrawSurface;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 *        this indicator shows us on the screen the number of lives we have left
 *        in the game.
 */
public class LivesIndicator implements Sprite {

    private Counter lives;
    private Block block;
    private GameLevel gameLevel;
    private  Counter livesCounter;

    /**
     * create a new lives indicator based on our block and the counter of the
     * scores.
     * @param block draw over it the curent score
     * @param l the number of scores
     */
    public LivesIndicator(Block block, Counter l) {
        this.block = block;
        this.lives = l;
    }

    /**
    * another constructor.
    * @param g - the level we recieve.
    * @param lives - the lives counter of the level.
    */
    public LivesIndicator(GameLevel g, Counter lives) {
        this.gameLevel = g;
        this.livesCounter = lives;
    }

    /**
     * draw the indicator on to the screen using the drawsurface.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        String s = "lives";
        d.setColor(java.awt.Color.BLACK);
        d.drawText(100, 20, "lives: " + Integer.toString(this.lives.getValue()), 15);

    }

    /**
     * notifiy the sprite that time has passed.
     * @param dt the time that had passed since the last call.
     */
    public void timePassed(double dt) {
    }

    /**
     * add this live indicator as a sptite to the game.
     * @param g - the game level.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
