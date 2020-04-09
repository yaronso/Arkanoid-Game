//package ass5;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 * a hit listener that sees that blocks are removed and adds numbers to
 * the score counter.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * create a score tracking listner and get the counter of the score.
     * @param scoreCounter the counter of the score we will add score to.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * let the score tracker know about the hit and add update the score
     * accordingly.
     * @param beingHit the block that has been hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // increase by 5 points after hitting a block
        if (beingHit.getHitPoints() > 0) {
            this.currentScore.increase(5);
            // if the block was destroyed increase by additional 10 points.
        } else if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
}
