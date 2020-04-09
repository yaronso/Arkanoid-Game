//package ass5;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 * this is the interface of all the objects that can "hear" other objects
 * hits.
 */
public interface HitListener {
    /**
     * notifies the hit listener that an object has been hit.
     * @param beingHit the block that has been hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
