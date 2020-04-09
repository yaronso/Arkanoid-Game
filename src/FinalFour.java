//package ass5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the level information of the Final Four level.
 * @author Yaron Sofer
 */
public class FinalFour implements LevelInformation {

    /**
     * the number of balls in the game level.
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * return a list of velocities of each ball.
     * @return a list of velcoities.
     */
    public List<Velocity> initialBallVelocities() {
        // initalize a list of velocities
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 1; i < this.numberOfBalls() + 1; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(40, 8));
        }
        // return the list of velocties
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
        Point startLeft = new Point(this.width() / 2 - 50, 500);
        Point startRight = new Point(this.width() / 2 + 50, 500);
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            list.add(new Point((startRight.getX() + diffX * i), startRight.getY() + (diffY * i)));
            list.add(new Point((startLeft.getX() + diffX * i), startLeft.getY() + (diffY * i)));
        }
        // return a list of center points
        return list;
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
        return 75;
    }

    /**
     * return a name by a string.
     * @return the game level name.
     */
    public String levelName() {
        return "Final Four";
    }
    /**
     * Returns a sprite with the background of the level.
     * @return the background of the level.
     */
    public Sprite getBackground() {
        return new BackgroundFF();
    }

    /*
     * return a list of block in the following level.
     * @return a list of blocks objects.
     *
    public List<Block> blocks() {
        // initialize a list of blocks
        List<Block> blocks = new ArrayList<Block>();
        for (int i = 1; i < 8; i++) {
            for (int j = i; j < 15; j++) {

                Color colorBlock = null;
                int hitPoints = 3;

                if (i == 1) {
                    colorBlock = java.awt.Color.GRAY;
                }

                if (i == 2) {
                    colorBlock = java.awt.Color.RED;
                }

                if (i == 3) {
                    colorBlock = java.awt.Color.YELLOW;
                }

                if (i == 4) {
                    colorBlock = java.awt.Color.GREEN;
                }

                if (i == 5) {
                    colorBlock = java.awt.Color.WHITE;
                }

                if (i == 6) {
                    colorBlock = java.awt.Color.PINK;
                }

                if (i == 7) {
                    colorBlock = java.awt.Color.CYAN;
                }

                Block block = new Block(new Rectangle(new Point(200 + 50 * j, 100 + 50 * i), 50, 50), colorBlock,
                        hitPoints);
                blocks.add(block);
            }
        }

        // return a list of blocks
        return blocks;
    }*/
    /**
     * return a number of blocks.
     * @return the number of blocks we should remove
     */
    public int numberOfBlocksToRemove() {
        return 105;
    }
    /**
     * The Blocks that make up this level. containes also the edges.
     * @return a list of all the blocks in the game. the 0 place is the bottom block.
     */
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>(BlockFactory.createEnds(new Point(this.width(),
                this.height()), edgeSize()));
        int blockWidth = (this.width() - 2 * this.edgeSize()) / 20;
        int j = 1;
        for (int i = 7; i > 0; i--) {
            list.addAll(BlockFactory.createBlockRowRandomColor(this.edgeSize(), 100
                            + (j * this.edgeSize()), 20,
                    1, blockWidth, this.edgeSize()));
            j++;
        }
        return list;
    }
    /**

    /**
     * get what will be the color of the ball.
     * @return the color of the ball.
     */
    public Color getBallColor() {
        return Color.WHITE;
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
