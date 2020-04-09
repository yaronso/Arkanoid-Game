//package ass5;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 *        the following class is for blocks which appear at the corners of the
 *        gui screen.
 */
public class BlockLimit implements Sprite, Collidable {
    private Block block;

    /**
     * create a new block limit based on the block we get.
     * @param block the block we get as the limit of the screen.
     */
    public BlockLimit(Block block) {
        this.block = block;
    }

    /**
     * return the rectangle of the block.
     * @return the rectangle of the block of this paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * (a regular hit method of a regular block) uses the current velocity of the
     * ball and the point of collosion with the block to calculate the balls new
     * velocity after the impact.
     * @param hitter the ball that hitted the block.
     * @param collisionPoint the point were the collision is happening.
     * @param currentVelocity the current velocity of the object hitiing this block.
     * @return the new velocity of the object that hits the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // check if the collision is on left side of the rectangle.
        if (collisionPoint.getX() == this.block.getRectangle().getUpperLeft().getX()) {
            // change only the horizontal direction of the ball.
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy(), currentVelocity.getSpeed());
            // check if the colloshion is on the right side of the rectangle.
        } else if (collisionPoint
                .getX() == (this.block.getRectangle().getUpperLeft().getX() + this.block.getRectangle().getWidth())) {
            // change only the horizontal direction of the ball.
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy(), currentVelocity.getSpeed());
            // check if the collision is on the top of the block.
        } else if (collisionPoint.getY() == this.block.getRectangle().getUpperLeft().getY()) {
            // change only the veertical direction of the ball.
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy(), currentVelocity.getSpeed());
            // check if the collision is on the bottom of the block.
        } else if (collisionPoint
                .getY() == (this.block.getRectangle().getUpperLeft().getY() + this.block.getRectangle().getHeight())) {
            // change only the veertical direction of the ball.
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy(), currentVelocity.getSpeed());
        } else {
            return null; // error
        }
    }

    /**
     * use a drawsurface to draw the block to the screen.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        // set the color of the block.
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
     * The block does nothing when the time passes.
     * @param dt the time that passed since the last call.
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * we add this block to the game enviroment and the sprite collection.
     * @param g the game we are adding this block to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
