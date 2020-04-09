//package ass5;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * the animation that shows the end screen of our game.
 * @author Yaron Sofer
 */
public class EndScreen implements Animation {
    /* has to stop */
    private boolean stop;
    /* game score counter */
    private Counter score;
    /* game score counter */
    private Counter lives;

    /**
     * constructor for end screen animation.
     * @param score of game
     * @param lives in the game
     */
    public EndScreen(Counter score, Counter lives) {
        this.score = score;
        this.lives = lives;
    }

    /**
     * draw to the screen on frame of the animation.
     * @param d the draw surface we are drawing the animation on.
     * @param dt the time that passed between calls.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        /* if the game is lost */
        if (this.lives.getValue() == 0) {
            d.setColor(new Color(62, 68, 60));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.WHITE);
            d.drawText(150, d.getHeight() / 2, "Game Over!", 40);
        } else {
            /* if the game is won */
            d.setColor(new Color(24, 188, 177));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.WHITE);
            d.drawText(150, d.getHeight() / 2, "You Win!", 40);
        }
        /* show score */
        d.drawText(250, d.getHeight() / 2 + 80, "Your Score Is: " + this.score.getValue(), 30);
    }

    /**
     * check if the animation needs to stop of not.
     * @return true - we need to stop, false - we don't need to stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
