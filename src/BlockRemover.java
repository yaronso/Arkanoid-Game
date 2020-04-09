//package ass5;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 *        a BlockRemover is in charge of removing blocks from the game, as well
 *        as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * create a new block remover based on saving the game we are in and the counter
     * of the blocks in the game.
     * @param game we need this to know how to remove the block.
     * @param removedBlocks the number of blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * let the remover know that a block has been hit an remove it from the game.
     * @param beingHit the block that is being hit.
     * @param hitter the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // if the number of hit points reached to 0, we should remove the block.
        if (beingHit.getHitPoints() == 0) {
            // remove this block from the game.
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            // decrease the counter of the blocks by one
            this.remainingBlocks.decrease(1);
        }
    }

}
