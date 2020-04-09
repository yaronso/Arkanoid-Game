//package ass5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the level information of the wide easy level.
 * @author Yaron Sofer
 */
public class WideEasy implements LevelInformation {

    /**
     * the number of balls in the game level.
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * return a list of velocities of each ball.
     * @return a list of velcoities
     */
    public List<Velocity> initialBallVelocities() {
        // initialzie the arraylist of velocities
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 1; i < 11; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(40, 2));
        }
        // return the list of velocities.
        return velocities;
    }

    /**
     * the center points of all of the balls we are going to create.
     * @return a list containing the center points.
     */
    public List<Point> initialBallCenters() {
        // initalize the list of the center points of each ball
        List<Point> list = new ArrayList<Point>();
        int diffX = 50;
        int diffY = 20;
        Point startLeft = new Point(this.width() / 2 - 50, 300);
        Point startRight = new Point(this.width() / 2 + 50, 300);
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            list.add(new Point((startRight.getX() + diffX * i), startRight.getY() + (diffY * i)));
            list.add(new Point((startLeft.getX() + diffX * i), startLeft.getY() + (diffY * i)));
        }
        // return a list of center points
        return list;
    }

    /**
     * get what will be the color of the ball.
     * @return the color of the ball.
     */
    public Color getBallColor() {
        return Color.white;
    }

    /**
     * the distance the paddle goes with each step it makes.
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return 600;
    }

    /**
     * the width of the paddle.
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        // return (this.width() - 2 * this.edgeSize()) - 100;
        return 600;
    }

    /**
     * return a name by a string.
     * @return the game level name
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return the background of the level.
     */
    public Sprite getBackground() {
        return new BackgorounWE();
    }

    /*
     * return a list of block in the following level.
     * @return a list of blocks objects
     *
    public List<Block> blocks() {
        // initialize the blocks array
        List<Block> blocks = new ArrayList<Block>();
        int width = this.width() - 300;
        int blockWidth = width / 12;
        Color[] colors = { Color.RED, Color.RED, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN,
                Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN };
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(60 + blockWidth * i, 200), blockWidth, 20), colors[i], 1));
        }
        // return the list of blocks
        return blocks;
    }*/
     /**
     * The Blocks that make up this level. containes also the edges.
     * @return a list of all the blocks in the game. the 0 place is the bottom block.
     */
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>(BlockFactory.createEnds(new Point(this.width(),
                this.height()), edgeSize()));
        list.addAll(BlockFactory.createBlockRowRandomColor(this.edgeSize(),
                this.height() / 2, this.numberOfBlocksToRemove(), 2, this.height(), this.edgeSize()));
        return list;
    }

    /**
     * return a number of blocks.
     * @return the number of blocks we should remove
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
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
}
