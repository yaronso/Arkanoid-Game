//package ass5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//import biuoop.DrawSurface;

/**
 * the level information of the direct hit level.
 * @author Yaron Sofer
 */
public class DirectHit implements LevelInformation {

    /**
     * initialize the number of balls by one as it appear in the specific game.
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 1;
    }
    /**
     * return a list of velocities of each ball.
     * @return a list of velcoities.
     */
    public List<Velocity> initialBallVelocities() {
        // initialzie the arraylist of velocities
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(180, 2));
        return velocities;
    }
    /**
     * return the width of the level.
     * @return the width of the level.
     */
    public int width() {
        return 800;
    }
    /**
     * the height of the level.
     * @return the height of the level.
     */
    public int height() {
        return 600;
    }

    /**
     * ths size of the edges of the level.
     * @return the size of the edges.
     */
    public int edgeSize() {
        return 20;
    }

    /**
     * the distance the paddle goes with each step it makes.
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return 20 * 60;
    }

    /**
     * the width of the paddle.
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return 50;
    }

    /**
     * return a name by a string.
     * @return the game level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return the background of the level.
     */
    public Sprite getBackground() {
        return new BackgroundDH();

    }

    /*
     * return a list of block in the following level
     * @return a list of blocks objects
     *
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(355, 250), 50, 50), java.awt.Color.RED, 0);

        // add the block of the level to the list
        blocks.add(block);
        // return the list
        return blocks;*
    }*/
    /**
     * According the Block Factory
     * The Blocks that make up this level. containes also the edges.
     * @return a list of all the blocks in the game. the 0 place is the bottom block.
     */
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>(BlockFactory.createEnds(new Point(this.width(),
                this.height()), edgeSize()));
        list.add(new Block(new Rectangle(new Point((this.width() - (2 * this.edgeSize())) / 2 - 10,
                (this.height() - (2 * this.edgeSize())) / 2 - 10),
                 20, 20), Color.WHITE, 1));
        return list;
    }

    /**
     * we have only 1 block to remove.
     * @return the number of blocks we should remove
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    /**
     * get what will be the color of the ball.
     * @return the color of the ball.
     */
    public Color getBallColor() {
        return Color.white;
    }

    /**
     * return a list of center points of the gamelevel's balls.
     * @return a list of center points.
     */
    public List<Point> initialBallCenters() {
        // initialize the center list
        List<Point> centers = new ArrayList<Point>();
        centers.add(new Point((this.width() - 2 * this.edgeSize()) / 2, this.height() - 50));
        // return a list of center points
        return centers;
    }

}
