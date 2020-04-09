//package ass5;

import biuoop.GUI;
import biuoop.DrawSurface;

//import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2010-03-31 (the version of the package this class was first added to)
 *        This class is the general game class. it initializes all the game
 *        objects and then runs the animation loop. contains all the constants
 *        we need to run the game.
 */
public class GameLevel implements Animation {
    // the fields of the class
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    // part 1 - remove a block
    private Counter blocksCounter;
    // part 2 - remove a ball
    private Counter ballsCounter;
    // hit listener - block remover
    private HitListener blockRemover;
    // hit listener - ball remover
    private HitListener ballRemover;
    // part 3 - score
    private Counter scoreCounter;
    private HitListener scoreListener;
    // by the following sprite we will display the score on the screen.
    private ScoreIndicator scoreIndicator;
    // part 4 - lives
    private Counter lives;
    // for advanced mission
    private HitListener goodBlock;
    // for advanced mission
    //private HitListener BlockCreatesABall;
    // for Ass6 - PART 1
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private int paddleSize;
    private int height;
    private int width;
    private int edgeSize;
    private List<Block> blocks;
    private List<Velocity> velocities;
    private List<Point> centers;
    private Color ballColor;
    private Paddle paddle;
    private ArrayList<Ball> ball;

    /**
     * create a new game level based on the level information and the object needed.
     * to run the level.
     * @param l     the information about the level we are creating.
     * @param k     the keyboard sensor we will use.
     * @param r     the animation runner that will run the level.
     * @param g     the gui we wll show the level on.
     * @param live  the counter of the lives.
     * @param score the counter of the score.
     */
    public GameLevel(LevelInformation l, KeyboardSensor k, AnimationRunner r, GUI g, Counter live, Counter score) {
        this.level = l;
        this.keyboard = k;
        this.runner = r;
        this.gui = g;
        this.lives = live;
        this.scoreCounter = score;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
    }
    /**
     * get the number of lives remaining after/during the run of this level.
     * @return the number of lives.
     */
    public int getLivesValue() {
        return this.lives.getValue();
    }

    /**
     * add a collidable to this game using the game enviroment.
     * @param c the collidable we are adding.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a prite to this game using the sprite collection.
     * @param s the sprite we are adding to this game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * return the gui we are using in this game.
     * @return the gui of this game.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * sets gui for the game.
     * @param g gui of the game
     */
    public void setGUI(GUI g) {
        this.gui = g;
    }

    /**
     * return the game enviroment of this game.
     * @return the enviroment of this game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * getter for the blocks Counter.
     * @return blocksCounter counter of remaining blocks
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * getter for the balls Counter.
     * @return blocksCounter counter of remaining blocks
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * getter for the score counter.
     * @return the new number of score
     */
    public Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * getter for the lives counter.
     * @return the new number of live's score.
     */
    public Counter getLivesCounter() {
        return this.lives;
    }

    /**
     * setter for the lives Counter.
     * @param num number of lives to begin with.
     */
    public void setLivesCounter(int num) {
        this.lives.increase(num);
    }

    /**
     * get the height of our game screen.
     * @return the height of the game screen.
     */
    public int getHeight() {
        return height;
    }

    /**
     * get the width of out game screen.
     * @return the width of our game screen.
     */
    public int getWidth() {
        return width;
    }

    /**
     * get the size of the edges we had set for this game.
     * @return the edge size of this game.
     */
    public int getEdgeSize() {
        return this.edgeSize;
    }

    /**
     * return the sprite collection of this game.
     * @return the sprite collection.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * initialize a new game. set all the game parameters that we need. then create
     * the general members we need for the game (gui, sprites, collidables). then
     * create the blocks, paddle and ball. add them to the game.
     */
    public void initialize() {
        // before GameFlow:
        // set the gui of the game.
        // this.setGUI(new GUI("Check", 800, 600));
        // before the gameflow - debug
        // this.keyboard = this.gui.getKeyboardSensor();
        // this.runner = new AnimationRunner(this.gui, new Sleeper());
        // call a method that initializes the counters:
        // this.initializeCounters();
        // call a method that creates HitListeners for the blocks removing.
        this.initializeHitLisetenrs();
        // initialize the hit listener print.
        PrintingHitListener print = new PrintingHitListener();
        // set the size of the screen and edges.
        this.width = this.level.width();
        this.height = this.level.height();
        this.edgeSize = this.level.edgeSize();
        this.paddleSize = this.level.paddleWidth();
        Sprite background = this.level.getBackground();
        this.sprites.addSprite(background);
        // get the call of the ball from the specific game level
        this.ballColor = level.getBallColor();
        // get the list of block for the game.
        this.blocks = new ArrayList<Block>(this.level.blocks());
        // count the blocks of the level
        this.blocksCounter.increase(this.level.numberOfBlocksToRemove());
        // destroy the block
        // blocks.get(0).addHitListener(blockRemover);
        // add the ball remover to the bottom end of the screen.
        //blocks.get(0).addHitListener(new BallRemover(this, this.ballsCounter));
        // add all the block to the game from each specific game level.
        for (Block b : this.blocks) {
            b.addToGame(this);
            b.addHitListener(scoreListener);
            b.addHitListener(blockRemover);
        }
        // save the velocities and centers of the balls.
        this.velocities = level.initialBallVelocities();
        this.centers = level.initialBallCenters();
        // scoreIndicator.
        Rectangle scoreIndicatorFrame = new Rectangle(new Point(0, 0), 800, 800);
        // create a block which consist of the previous rectangle for the
        // scoreIndicator.
        Block scoreIndicatorBlock = new Block(scoreIndicatorFrame, java.awt.Color.GRAY, 0);
        // create the scoreIndicator.
        ScoreIndicator scoreIndicator1 = new ScoreIndicator(scoreIndicatorBlock, scoreCounter);
        scoreIndicator1.addToGame(this);
        this.sprites.addSprite(scoreIndicator1);
        // create a block for the score representation on the screen.
        BlockLimit scoreRepresent = new BlockLimit(
                new Block(new Rectangle(new Point(0, 0), 800, 30), java.awt.Color.gray, 0));
        scoreRepresent.addToGame(this);
        // LevelIndicator
        LevelIndicator levelIndicator = new LevelIndicator(this, java.awt.Color.BLACK);
        levelIndicator.addToGame();
        this.sprites.addSprite(levelIndicator);
        // LivesIndicator:
        Rectangle livesIndicatorFrame = new Rectangle(new Point(10, 10), 800, 700);
        // create a block which consist of the previous rectangle for the
        // livesIndicator.
        Block livesIndicatorBlock = new Block(livesIndicatorFrame, java.awt.Color.GRAY, 0);
        // create the livesIndicator.
        LivesIndicator livesIndicator = new LivesIndicator(livesIndicatorBlock, lives);
        livesIndicator.addToGame(this);
        this.sprites.addSprite(livesIndicator);
        // limits:
        BlockLimit b2 = new BlockLimit(new Block(new Rectangle(new Point(0, 0), 30, 600), java.awt.Color.gray, 0));
        BlockLimit b3 = new BlockLimit(new Block(new Rectangle(new Point(770, 0), 30, 600), java.awt.Color.gray, 0));
        b2.addToGame(this);
        b3.addToGame(this);
        // death region:
        Block deathRegion = new Block(new Rectangle(new Point(0, 590), 800, 600), java.awt.Color.BLACK, 0);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * the following method initializes the HitListeners of the game.
     */
    private void initializeHitLisetenrs() {
        // create a HitListener for the block removing.
        this.blockRemover = new BlockRemover(this, getBlocksCounter());
        // create a HitListener for the balls removing.
        this.ballRemover = new BallRemover(this, getBallsCounter());
        // create a HitListener for the score tracking.
        this.scoreListener = new ScoreTrackingListener(getScoreCounter());
        // create a HitListener for the raising up live score block.
        // this.goodBlock = new GoodBlock(this, getLivesCounter());
        // create a HitListener for creating a new ball by hitting a specific block
        // this.BlockCreatesABall = new BlockCreatesABall(this, getBallsCounter(),
        // this.environment);
    }

    /**
     * the following method initalizes the counters of the game.
     **/
    private void initializeCounters() {
        // this.blocksCounter = new Counter();
        // this.ballsCounter = new Counter();
        // this.scoreCounter = new Counter();
        // this.lives = new Counter();
    }

    /**
     * in the following method we create the balls, paddle. moreover, we run the.
     * animation loop, set the color of the screen, draw all the sprites over it,
     * and run the game one time.
     **/
    public void playOneTurn() {
        /*
         * balls initialization: // add the first ball to the game. Ball ball1 = new
         * Ball(60, 450, 10, java.awt.Color.RED); Velocity v1 =
         * Velocity.fromAngleAndSpeed(30, 9); ball1.setVelocity(v1); ball1.setLimit(0,
         * 800, 0, 600); ball1.addToGame(this); this.getBallsCounter().increase(1);
         * // add the second ball to the game. Ball ball2 = new Ball(300, 60, 3,
         * Color.black); Velocity v2 = Velocity.fromAngleAndSpeed(30, 9);
         * ball2.setVelocity(v2); ball2.setLimit(0, 800, 0, 600); ball2.addToGame(this);
         * this.getBallsCounter().increase(1); ball1.setGame(environment);
         * ball2.setGame(environment);
         */

        this.spawnBalls();

        this.spawnPaddle();

        /*
         * paddle initialization: - debug //create the paddle of the game Point.
         * paddlePoint = new Point(200, 500); Rectangle paddleRect = new.
         * Rectangle(paddlePoint, 200, 50); Block paddleBlock = new Block(paddleRect,
         * Color.RED); Paddle paddle = new Paddle(paddleBlock, this.keyboard, this);
         * paddle.addToGame(this);
         */

        // countDown before the turn starts
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // run this animation
        this.runner.run(this);

        this.removeBallsAndPaddle();

        // debug
        // ball1.removeFromGame(this);
        // ball2.removeFromGame(this);
        // paddle.removeFromGame(this);
    }

    /**
     * remove the ball and paddle from the game to make sure they do not go from.
     * level to or live to live.
     */
    public void removeBallsAndPaddle() {
        // remove the balls and paddle at the end of the turn.
        this.paddle.removeFromGame(this);
        // remove the balls from the game
        for (Ball b : this.ball) {
            b.removeFromGame(this);
        }
    }

    /**
     * create the paddle of our game level.
     */
    public void spawnPaddle() {
        this.paddle = new Paddle(
                new Block(
                        new Rectangle(new Point(this.centers.get(0).getX() - (this.paddleSize / 2),
                                this.height - this.edgeSize * 2), this.level.paddleWidth(), this.edgeSize),
                        Color.orange),
                this.keyboard, this);
        this.paddle.setSpeed(this.level.paddleSpeed());
        this.paddle.addToGame(this);
        this.sprites.addSprite(paddle);

    }

    /**
     * create the balls of the game level.
     */
    private void spawnBalls() {
        /* create a list of balls
        this.ball = new ArrayList<Ball>();
        this.velocities = this.level.initialBallVelocities();
        this.centers = this.level.initialBallCenters();
        for (int j = 0; j < this.level.numberOfBalls(); j++) {
            Point p = centers.get(j);
            Ball ball1 = new Ball((int) p.getX(), (int) p.getY(), 5, this.level.getBallColor());
            // Ball ball = this.balls123().get(j);
            Velocity initializeVelocity = velocities.get(j);
            ball1.setVelocity(initializeVelocity);
            ball1.setLimit(0, 800, 0, 600);
            ball1.addToGame(this);
            ball1.setGame(environment);
            this.ballsCounter.increase(1);
        }*/
        // create our ball based on a list of pointa on our screen.
        this.ball = new ArrayList<Ball>();
        for (int i = 0; i < this.velocities.size(); i++) {
            this.ball.add(new Ball(this.centers.get(0), 3, this.ballColor));
            // set the velocity using our velocity list.
            this.ball.get(i).setVelocity(this.velocities.get(i));
            this.ball.get(i).setLimit(0, 800, 0, 600);
            this.ball.get(i).addToGame(this);
            this.ball.get(i).setGame(environment);
        }
        // count the balls.
        this.ballsCounter.increase(this.velocities.size());

    }

    /**
     * in the following method we run the game by using a while loop inside the
     * while loop we call the function playOneTurn which run one round of the game
     * until the number of lives counter is equal to zero. when the number of lives
     * is equal to zero we close the screen and end the game.
     **/
    public void run() {
        int numOfLives = 4;
        this.setLivesCounter(numOfLives);
        this.playOneTurn();
        while (getLivesCounter().getValue() > 0) {
            // this.lives.decrease(1);
            initialize();
            // calling a single turn
            this.playOneTurn();
        }
        // close the GUI window
        this.getGui().close();
    }

    /**
     * return the keyboard sensor we are using in this level.
     * @return the keyboard sensor we are using in this level.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * remove a collidable from the game.
     * @param c the collidable we are removing.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite from the game.
     * @param s the sprite we are removing from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * the logic used to run one frame of this animation.
     * @param d the draw surface we are drawing the animation on.
     * @param dt the time that passed from the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {

        /* set the color of the screen
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);*/

        // pause animation condition
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        // notify all the sprites in the game that the time was passed
        this.sprites.notifyAllTimePassed(dt);
        // draw all the sprites on the screen
        this.sprites.drawAllOn(d);

        d.setColor(Color.black);
        // draw the score on the screen.
        d.drawText(370, 20, "score: " + Integer.toString(this.scoreCounter.getValue()), 15);
        // draw the lives on the screen.
        d.drawText(100, 20, "lives: " + Integer.toString(this.lives.getValue()), 15);

        // we continue because it's just a one turn
        this.running = false;

    }

    @Override
    /**
     * a template method that determined when we should stop the game according the.
     * number of the blocks and the .
     * @return true - we should stop running. false - go on.
     */
    public boolean shouldStop() {
        // add 100 points to the score if the player won.
        if (this.getBlocksCounter().getValue() == 0) {
            this.getScoreCounter().increase(100);
            return true;
        }
        // if there are no more balls in the game
        if (this.getBallsCounter().getValue() == 0) {
            this.lives.decrease(1);
            return true;
        }

        // false means we should continue
        return false;

    }

    /**
     * return the level information of this level.
     * @return the level information of this level.
     */
    public LevelInformation getLevel() {
        return this.level;
    }

    /**
     * return the amount of block remaining.
     * @return the number of block remaining.
     */
    public int blocksRemaining() {
        return this.blocksCounter.getValue();
    }

    /**
     * @return the number of lives in the game level
     */
    public int getLives() {
        return this.lives.getValue();
    }

}
