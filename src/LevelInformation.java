//package ass5;

import java.awt.Color;
import java.util.List;

/**
 * @author Yaron's Laptop
 *  this interface contains the methods for the level.
 *  information object that are used to initialize levels.
 */
public interface LevelInformation {

    /**
     * the number of balls in the level.
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * the velocities of all the balls we are going to create.
     * @return a list containing the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the distance the paddle goes with each step it makes.
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * the width of the paddle.
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * the level name that will be displayed at the top of the screen.
     * @return a string of the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level. containes also the edges.
     * @return a list of all the blocks in the game. the 0 place is the bottom.
     * block.
     */
    List<Block> blocks();

    /**
     * the number of blocks in this level.
     * @return the number of block to remove in the level.
     */
    int numberOfBlocksToRemove();

    /**
     * the center points of all of the balls we are going to create.
     * @return a list containing the center points.
     */
    List<Point> initialBallCenters();

    /**
     * get what will be the color of the ball.
     * @return the color of the ball.
     */
    Color getBallColor();

    /**
     * return the width of the level.
     * @return the width of the level.
     */
    int width();

    /**
     * the height of the level.
     * @return the height of the level.
     */
    int height();

    /**
     * ths size of the edges of the level.
     * @return the size of the edges.
     */
    int edgeSize();

}
