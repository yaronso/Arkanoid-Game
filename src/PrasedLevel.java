//package ass5;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Yaron's Laptop
 * this is an empty level information class. it is used.
 * to save an reuse the information about the level that was in the.
 * files.
 */
public class PrasedLevel implements LevelInformation {
    private String levelName;
    private List<Velocity> ballVelo;
    private Sprite backGround;
    private int paddleSpeed;
    private int paddleWidth;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private int blockNum;
    private String blocks;
    private String blockDef;

    /**
     * create a new parsed level.
     */
    public PrasedLevel() {
    }

    /**
     * set the block start x value.
     * @param x the x value we are setting.
     */
    public void setBlockStartX(int x) {
        this.blockStartX = x;
    }

    /**
     * set the block start y value.
     * @param y the y value we are setting.
     */
    public void setBlockStartY(int y) {
        this.blockStartY = y;
    }

    /**
     * set the height of each row in the level.
     * @param height the height of the rows.
     */
    public void setRowHeight(int height) {
        this.rowHeight = height;
    }

    /**
     * save the string of blocks.
     * @param b the string of blocks we are saving.
     */
    public void setBlocks(String b) {
        this.blocks = b;
    }

    /**
     * return the height of the level. set to default 600.
     * @return 600
     */
    public int height() {
        return 600;
    }

    /**
     * returh the width of the level. set to default 800.
     * @return 800
     */
    public int width() {
        return 800;
    }

    /**
     * return the size of the edges of the level. set to default 20.
     * @return 20
     */
    public int edgeSize() {
        return 20;
    }

    /**
     * the number of balls in the level.
     * @return the number of balls in the level.
     */
    public int numberOfBalls() {
        return this.ballVelo.size();
    }

    /**
     * the velocities of all the balls we are going to create.
     * @return a list containing the velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelo;
    }

    /**
     * get the list of ball velocities and save it.
     * @param v the list of ball velocities.
     */
    public void setBallVelo(List<Velocity> v) {
        this.ballVelo = v;
    }

    /**
     * the center points of all of the balls we are going to create.
     * @return a list containing the center points.
     */
    public Point initialBallCenter() {
        return new Point(300, 75);
    }

    /**
     * the distance the paddle goes with each step it makes.
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * set the speed of the paddle.
     * @param speed the speed of the paddle.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * the width of the paddle.
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * set the width of the paddle.
     * @param width the width of the paddle.
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * the level name that will be displayed at the top of the screen.
     * @return a string of the level name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * choose what will be the level name.
     * @param name the name that will be the level name.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * Returns a sprite with the background of the level.
     * @return the background of the level.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * get a sprite that will be the background of the level.
     * @param s the background we will draw.
     */
    public void setBackGround(Sprite s) {
        this.backGround = s;
    }

    /**
     * The Blocks that make up this level. containes also the edges.
     * @return a list of all the blocks in the game. the 0 place is the bottom.
     * block.
     */
    public List<Block> blocks() {
        // split the block string to seperate lines.
        String[] row = this.blocks.split("\\n");
        // create the ends of the screen.
        List<Block> list = new ArrayList<>(
                BlockFactory.createEnds(new Point(this.width(), this.height()), this.edgeSize()));
        BlocksFromSymbolsFactory factory = null;
        // load the block definition files.
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blockDef);
        factory = BlocksDefinitionReader.fromReader(new InputStreamReader(in));
        try { // close the input stream as we dont need it anymore.
            in.close();
        } catch (IOException e) {
            System.out.println("problem closing block definition files.");
        }
        String[] sentence;
        // the y value of each row.
        int rowY = this.blockStartY;
        // the space we have untill the next block.
        int space = 0;
        // go over the rows
        for (int i = 0; i < row.length; i++) {
            // go over each letter
            sentence = row[i].split("");
            for (int j = 0; j < sentence.length; j++) {
                // check if the letter is a block symbol.
                if (factory.isBlockSymbol(sentence[j])) {
                    // create a block based on the symbol and add it to the list.
                    list.add(factory.getBlock(sentence[j],
                            this.blockStartX + j * factory.getBlockWidte(sentence[j]) + space, rowY));
                    // check if the letter is a space symbol.
                } else if (factory.isSpaceSymbol(sentence[j])) {
                    // add to the space parameter the width of the space.
                    space = space + factory.getSpaceWidth(sentence[j]);
                }
            }
            // zero the space after each block row.
            space = 0;
            // if we created more blocks than only the edges.
            if (list.size() > 4) {
                // add the height of the last row to the height of the total.
                rowY = rowY + list.get(list.size() - 1).getHeight();
            }
        }
        return list;
    }

    /**
     * the number of blocks in this level.
     * @return the number of block to remove in the level.
     */
    public int numberOfBlocksToRemove() {
        return this.blockNum;
    }

    /**
     * decide what will be the number of blocks to remove.
     * @param num the number of blocks.
     */
    public void setBlockNum(int num) {
        this.blockNum = num;
    }

    /**
     * get what will be the color of the ball.
     * @return the color of the ball.
     */
    public Color getBallColor() {
        return Color.white;
    }

    /**
     * the center points of all of the balls we are going to create.
     * @return a list containing the center points.
     */
    public List<Point> initialBallCenters() {
        List<Point> list = new ArrayList<Point>();
        list.add(new Point((this.width() - 2 * this.edgeSize()) / 2, this.height() - 50));
        return list;
    }

    /**
     * choose what will be the block definition file path.
     * @param f the path to the block definitions.
     */
    public void setBlockDef(String f) {
        this.blockDef = f;
    }
}
