//package ass5;

import biuoop.DrawSurface;
import java.awt.Color;
//import ass2.GameEnvironment;
//import ass2.CollisionInfo;
//import ass2.Collidable;
//import ass2.Game;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2010-03-31 (the version of the package this class was first added to)
 *        a ball that has size, color, and a center point, velocity and the axis
 *        limits (xl, xr, yu, yd). in the following ass the ball has the ability
 *        to recongnize objects in the screen and move in the correct form of
 *        movement.
 */
public class Ball implements Sprite {
    // the fields of the class ball
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private int xl;
    private int xr;
    private int yu;
    private int yd;
    private GameEnvironment enviroment;

    /**
     * creates a ball based on its center point, its radios and color.
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(145, 6);
    }

    /**
     * creates a ball based on its center point, its radios and color.
     * @param x     the x value of the center point of the ball
     * @param y     the y value of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(145, 6);
    }
    /**
     * choose what will be the game enviroment of this game based on the game
     * we input.
     * @param g the game this ball is in.
     */
    public void setEnviroment(GameLevel g) {
        this.enviroment = g.getEnvironment();
    }
    /**

    /**
     * return the game enviorment of the ball.
     * @return the game enviorment of the ball.
     */
    public GameEnvironment getGame() {
        return this.enviroment;
    }

    /**
     * set the game enviorment to be a game enviorment created.
     * @param g the game enviorment we are setting.
     */
    public void setGame(GameEnvironment g) {
        this.enviroment = g;
    }

    /**
     * return the rounded value of the ball's center x.
     * @return the x value of the center of the ball.
     */
    public int getX() {
        double x = Math.round(this.center.getX());
        return (int) x;
    }

    /**
     * return the rounded value of the ball's center y.
     * @return the y value of the center of the ball.
     */
    public int getY() {
        double y = Math.round(this.center.getY());
        return (int) y;
    }

    /**
     * used to accesses the radius of the ball.
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * used to accesses the color of the ball.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * uses the balls parameters to draw it on the surface.
     * @param surface the surface the ball will be drawn on.
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.radius);
    }

    /**
     * get a velocity and put it as the velocity of the ball.
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * sets a new velocity for the ball based on the change in x and y received.
     * @param dx    the change in x.
     * @param dy    the change in y.
     * @param speed of the ball.
     */
    public void setVelocity(double dx, double dy, double speed) {
        this.velocity = new Velocity(dx, dy, speed);
    }

    /**
     * return the velocity of the ball.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * move the ball one step forward in the animation. check if the ball had a
     * collision with some object. we check collisions with the axis, we can treat
     * the corners in a proper manner and not lose the ball. Moreover, in this
     * mission the ball use an algorithm that checks colliadbles objects in the game
     * so the ball move in a correct manner. return the new point of the ball, his
     * new location on the screen.
     * @param dt -  the time that had passed since the last call
     */
    public void moveOneStep(double dt) {

        // create the trajectory of the ball which is a line from the center
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        // check if there is a hit point with the trajectory of the ball.
        CollisionInfo hitPoint = this.enviroment.getClosestCollision(trajectory);

        // if there is no hit point with any object move in the regular trajectory.
        if (hitPoint == null) {
            // the old version of moveOneStep - according the edges of the gui screen.
            // if the ball exit the screen from the right side.
            if ((this.center.getX() + this.radius + this.velocity.getDx() >= this.xr) && (this.velocity.getDx() >= 0)) {
                this.setVelocity((-this.velocity.getDx() * dt), this.velocity.getDy(), this.velocity.getSpeed());

            }
            //// if the ball exit the screen from the down side.
            if ((this.center.getY() + this.radius + this.velocity.getDy() >= this.yd) && (this.velocity.getDy() >= 0)) {
                this.setVelocity(this.velocity.getDx(), (-1 * this.velocity.getDy() * dt), this.velocity.getSpeed());
            }
            // if the ball exit the screen from the left side.
            if ((this.center.getX() - this.radius - this.velocity.getDx() <= this.xl) && (this.velocity.getDx() <= 0)) {
                this.setVelocity((-1 * this.velocity.getDx() * dt), this.velocity.getDy(), this.velocity.getSpeed());
            }
            // if the ball exit the screen from the upper side.
            if ((this.center.getY() - this.radius - this.velocity.getDy() <= this.yu) && (this.velocity.getDy() <= 0)) {
                this.setVelocity(this.velocity.getDx(), (-1 * this.velocity.getDy() * dt), this.velocity.getSpeed());
            }
            // apply the ball to the next point in the line trajectory.
            this.center = this.getVelocity().applyToPoint(this.center);
            // means there is a hit point
        } else {
            // move the ball slightly before the hitPoint by using the method
            // almostHitPoint.
            this.center = hitPoint.collisionObject().getCollisionRectangle().almostHitPoint(hitPoint);
            this.velocity = hitPoint.collisionObject().hit(this, hitPoint.collisionPoint(), this.velocity);

        }

    }

    /**
     * creates the limits of the ball in a title.
     * @param xLeft  the x value of the
     * @param xRight the x value of the
     * @param yUp    the y value of the
     * @param yDown  the y value of the
     */
    public void setLimit(int xLeft, int xRight, int yUp, int yDown) {
        this.xl = xLeft;
        this.xr = xRight;
        this.yu = yUp;
        this.yd = yDown;

    }

    /**
     * notify the ball that one step has passed. calls the move one step method.
     * @param dt - the time that has passed.
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * add the ball to the game.
     * @param g the game that the ball will be included.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        // this.setEnviroment(g);
    }

    /**
     * remove the ball to the game.
     * @param g the game that the ball will be removed from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}