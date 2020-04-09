//package ass5;

import java.awt.Color;

//package ass2;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
//import ass2.Velocity;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2010-03-31 (the version of the package this class was first added to)
 *        the paddle is the player in the game. the ball bounces of it and it
 *        can move to the right or the left based on what the used presses.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private GameLevel game;
    // the distance the paddle moves in each step.
    private int steps;
    private int speed;

    /**
     * create a new paddle based on a block and the game we want the paddle to be
     * in.
     * @param b the block the paddle is based on.
     * @param k the keyboard sensor.
     * @param g - the game level.
     */
    public Paddle(Block b, KeyboardSensor k, GameLevel g) {
        this.block = b;
        this.keyboard = k;
        this.steps = g.getLevel().paddleSpeed();
    }

    /**
     * move the paddle one step to the left.
     * @param dt the time that passed between calls.
     */
    public void moveLeft(double dt) {
        // change the x value's of the block by -4 pixles
        this.block.setRectangle(new Rectangle(
                new Point(this.block.getRectangle().getUpperLeft().getX() - this.steps * dt,
                        this.block.getRectangle().getUpperLeft().getY()),
                this.block.getRectangle().getWidth(), this.block.getRectangle().getHeight()));
    }

    /**
     * move the paddle one step to the right.
     * @param dt the time that passed between calls.
     */
    public void moveRight(double dt) {
        // change the x value's of the block by +4 pixles
        this.block.setRectangle(new Rectangle(
                new Point(this.block.getRectangle().getUpperLeft().getX() + this.steps * dt,
                        this.block.getRectangle().getUpperLeft().getY()),
                this.block.getRectangle().getWidth(), this.block.getRectangle().getHeight()));
    }

    /**
     * notify the paddle that time passed. check for keyboard click and move the
     * paddle based on which key was clicked.
     * @param dt the time that passed between calls.
     */
    public void timePassed(double dt) {
        // check if the left key on the keyboard has been pressed.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // verify that the paddle is inside the gui from the left side.
            if (this.block.getRectangle().getUpperLeft().getX() > 33) {
                moveLeft(dt);
            }
            // check if the right key on the keyboard has been pressed.
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // verify that the paddle is inside the gui from the right side.
            if (this.block.getRectangle().getUpperLeft().getX() < 719) {
                moveRight(dt);
            }
        }
    }

    /**
     * draw the paddle on the draw surface inputed.
     * @param d the draw surface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        // this.block.drawOn(d);
        // print the paddle as
        // set the drawing color to be the color of the block.
        d.setColor(this.block.getColor());
        // draw the inside of the block.
        d.fillRectangle((int) this.block.getRectangle().getUpperLeft().getX(),
                (int) this.block.getRectangle().getUpperLeft().getY(), (int) this.block.getRectangle().getWidth(),
                (int) this.block.getRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.block.getRectangle().getUpperLeft().getX(),
                (int) this.block.getRectangle().getUpperLeft().getY(), (int) this.block.getRectangle().getWidth(),
                (int) this.block.getRectangle().getHeight());
        d.setColor(Color.BLACK);
    }

    /**
     * return the rectangle of the paddle.
     * @return the rectangle of the block of this paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * notifies the paddle that it has been hit in a certain collision point and
     * speed and returns the new speed of the colliding object.
     * @param hitter - the ball that hit the paddle.
     * @param collisionPoint  the point were the collision is happening.
     * @param currentVelocity the current velocity of the colliding object.
     * @return the new velocity of the coliding object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // each region end is width/i {1<i<5}
        // the new velocity of the ball after collide with the paddle
        Velocity newVel;
        // if the ball hits the top of the paddle
        if (collisionPoint.getX() == this.block.getRectangle().getUpperLeft().getX()) {
            newVel = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy(), currentVelocity.getSpeed());
            // another case the ball hit the top of the paddle
        } else if (collisionPoint
                .getX() == (this.block.getRectangle().getUpperLeft().getX() + this.block.getRectangle().getWidth())) {
            newVel = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy(), currentVelocity.getSpeed());
            // another case the ball hit the top of the paddle
        } else if (collisionPoint.getY() == this.block.getRectangle().getUpperLeft().getY()) {
            // the following variable reprersnt the region of the paddle.
            double rectanglePart = this.block.getRectangle().getWidth() / 5;

            // if the hit is in the first region of the rectangle
            if (collisionPoint.getX() <= 1 * rectanglePart + this.block.getRectangle().getUpperLeft().getX()) {
                newVel = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                // if the hit is in the 2nd part of the rectangle
            } else if (collisionPoint.getX() <= 2 * rectanglePart + this.block.getRectangle().getUpperLeft().getX()) {
                newVel = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                // if the hit is in the 3rd part of the rectangle
            } else if (collisionPoint.getX() <= 3 * rectanglePart + this.block.getRectangle().getUpperLeft().getX()) {
                newVel = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy(),
                        currentVelocity.getSpeed());
                // if the hit is in the 4th part of the rectangle
            } else if (collisionPoint.getX() <= 4 * rectanglePart + this.block.getRectangle().getUpperLeft().getX()) {
                newVel = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                // if the hit is in the 5th part of the rectangle
            } else if (collisionPoint.getX() <= 5 * rectanglePart + this.block.getRectangle().getUpperLeft().getX()) {
                newVel = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            } else {
                newVel = currentVelocity;
            }
        } else {
            newVel = currentVelocity; // error
        }
        return newVel;
    }

    /**
     * add the paddle to the game. add it to the collidable list and sprite list.
     * @param g the game we are adding the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removes the paddle from the game if needed.
     * @param g - the game level of the paddle.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
    * set the paddle's speed.
    * @param paddleSpeed - the speed of the paddle in the current game level.
    */
    public void setSpeed(int paddleSpeed) {
        this.speed = paddleSpeed;

    }

}