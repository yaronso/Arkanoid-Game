//package ass5;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 *        a hit listner that is added to the bottom edge of the screen to notice
 *        that it was hit an remove the ball from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * create a new ball remover based on saving the number of balls and the level.
     * @param game         the game we are in.
     * @param removedBalls the counter of the number of balls.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * let the listner know that a hit event accured.
     * @param beingHit the block that is being hit.
     * @param hitter   the ball that is hitting the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // remove the specific ball from the game
        hitter.removeFromGame(this.game);
        // decrease the number of balls in the game
        this.remainingBalls.decrease(1);

    }
}
