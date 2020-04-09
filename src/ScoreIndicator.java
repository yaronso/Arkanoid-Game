//package ass5;

//import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 *        this class is and indicator that shows the score of out player onto
 *        the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Block block;
    private GameLevel gameLevel;
    private Counter scoreCounter;

    /**
     * create a new score indicator based on our block and the counter of the
     * scores.
     * @param block draw over it the curent score
     * @param s the number of scores
     */
    public ScoreIndicator(Block block, Counter s) {
        this.block = block;
        this.score = s;
    }

    /**
    * create a new score indicator based on the game level and the
    * score counter.
    * @param g - the current game level.
    * @param scoreCounter - the scorres counters.
    */
    public ScoreIndicator(GameLevel g, Counter scoreCounter) {
        this.gameLevel = g;
        this.scoreCounter = scoreCounter;
    }

    /**
     * draw the indicator to the screen.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        String s = "score";
        d.setColor(java.awt.Color.BLACK);
        d.drawText(350, 20, "score: " + Integer.toString(this.score.getValue()), 20);

    }

    /**
     * notifiy the sprite that time has passed.
     * @param dt the time that had passed since the last call
     */
    public void timePassed(double dt) {
    }

    /**
     * add this score indicator to the game.
     * @param g - the current game level.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
