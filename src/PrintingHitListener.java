//package ass5;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 * an hit listner that its job is to print out notices of the hits.
 **/
public class PrintingHitListener implements HitListener {

    /**
     * see that a hit accured and print out a sentence that notices about it.
     * @param beingHit the block that has been hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}