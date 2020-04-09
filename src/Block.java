//package ass5;

//package ass2;

import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2010-03-31 (the version of the package this class was first added to)
 * the Block is out basic building block of the game. it is a sprite and
 * a collidable. it holds a rectangle, has hit points and color. in the
 * game the hitpoints of each block appears over the block (execpt of the
 * paddle).
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // the field of the class Block.
    private Rectangle rectangle;
    private int hitPoints;
    private Color color;
    // a list of Hit Listeners
    private List<HitListener> hitListeners;
    private Color border;
    private List<FillInfo> fills;
    private Image img;

    /**
     * create a new block that has hitpoints. use a rectangle, color and number of.
     * hit points we want it to have.
     * @param rectangle the rectangle that is the edges of the block.
     * @param r the color of the block.
     * @param hitPoints the hit points the block has to start with.
     */
    public Block(Rectangle rectangle, Color r, int hitPoints) {
        this.rectangle = rectangle;
        this.color = r;
        this.hitPoints = hitPoints;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * create a new block that has no hit points (edge, paddle, score).
     * @param rect the rectangular shape of this block.
     * @param r the color of the block.
     */
    public Block(Rectangle rect, Color r) {
        this.rectangle = rect;
        this.color = r;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * create a new block based on the block definer and the x and y starting positions
     * of the block.
     * @param definer the block definer of the block
     * @param xPos the x of the upper left point
     * @param yPos the y of the upper left point
     */
    public Block(BlockCreator definer, int xPos, int yPos) {
        // create the rectangle of the block.
        this.rectangle = new Rectangle(new Point(xPos, yPos), definer.getWidth(), definer.getHeight());
        this.fills = definer.getHitFill(); // get the hit points.
        if (definer.getStroke() != null) { // if we have a stroke save it.
            this.border = definer.getStroke();
        }
        if (definer.getImage() != null) { // if we have an image it will be the image of the block.
            try { // read the image into the block.
                InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(definer.getImage());
                this.img = ImageIO.read(in);
            } catch (IOException i) {
                System.out.println("error in reading image from block definer");
            }
        }
        if (definer.getFill() != null) { // if we have a fill for the block.
            this.color = definer.getFill();
        }
        this.hitPoints = definer.getHitPoints();
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * return the number of hit points the ball has.
     * @return return the hit points of the block.
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * set the hit points of the block to be another number.
     * @param hitPoints1 the new nmuber of hit points of the block.
     */
    public void setHitPoints(int hitPoints1) {
        this.hitPoints = hitPoints1;
    }

    /**
     * return the color of the block.
     * @return the color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * set the color of the block to be a color.
     * @param c the new color of the block.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * return the rectangle that his class holds.
     * @return the rectangle of this block.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * give this block a new rectangle.
     * @param d the new rectangle that will be the rectangle of the block.
     */
    public void setRectangle(Rectangle d) {
        this.rectangle = d;
    }

    /**
     * get the rectangle of the block, this time treat it as a collishion rectangle
     * and not just a regular one.
     * @return the rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * notify all of the hit listners that a hit has accured.
     * @param hitter the ball that hits this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            // the parameters are this block and the hitter ball.
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * uses the current velocity of the ball and the point of collosion with the.
     * block to calculate the balls new velocity after the impact.
     * @param hitter the ball that hitted the block.
     * @param collisionPoint the point were the collision is happening.
     * @param currentVelocity the current velocity of the object hitiing this block.
     * @return the new velocity of the object that hits the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // when a hit occured we notify the hitListeners.
        this.notifyHit(hitter);
        // check if we have more than 0 hit points.
        if (this.hitPoints > 0) {
            // if we do reduce 1 hit point for each hit.
            this.hitPoints--;
        }
        // check if the collision is on left side of the rectangle.
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()) {
            // change only the horizontal direction of the ball.
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy(), currentVelocity.getSpeed());
            // check if the colloshion is on the right side of the rectangle.
        } else if (collisionPoint.getX() == (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth())) {
            // change only the horizontal direction of the ball.
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy(), currentVelocity.getSpeed());
            // check if the collision is on the top of the block.
        } else if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()) {
            // change only the veertical direction of the ball.
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy(), currentVelocity.getSpeed());
            // check if the collision is on the bottom of the block.
        } else if (collisionPoint.getY() == (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())) {
            // change only the veertical direction of the ball.
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy(), currentVelocity.getSpeed());
        } else {
            return null; // error
        }
    }

    /**
     * use a drawsurface to draw the block to the screen. in addition i use the
     * function draw text in order to draw over the blocks the number of hit points
     * per each block.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        String s;
        // if there are no hit points
        if (this.hitPoints == 0) {
            s = "x";
            // in a case there are hit points
        } else {
            s = Integer.toString(this.hitPoints);
        }
        // if we have an image draw it as the block and don't use the color of the block.
        if (this.img != null) {
            d.drawImage((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), this.img);
            // when i have an image i draw the number of hit points in white
            d.setColor(Color.WHITE);
            // draw the number of hit points on the block
            d.drawText((int) (this.rectangle.getWidth()) / 2 + (int) this.rectangle.getUpperLeft().getX(),
                    (int) (this.rectangle.getHeight()) / 2 + (int) this.rectangle.getUpperLeft().getY() + 7, s, 15);
        } else if (this.color != null) {
                // set the drawing color to be the color of the block.
                d.setColor(this.color);
                // draw the inside of the block.
                d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(),
                        (int) this.rectangle.getHeight());
                d.setColor(Color.BLACK);
                // draw the number of hit points on the block
                d.drawText((int) (this.rectangle.getWidth()) / 2 + (int) this.rectangle.getUpperLeft().getX(),
                        (int) (this.rectangle.getHeight()) / 2 + (int) this.rectangle.getUpperLeft().getY() + 7, s, 15);
        } else {
            d.setColor(this.fills.get(0).getColor());
            d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
            d.setColor(Color.BLACK);
            // draw the number of hit points on the block
            d.drawText((int) (this.rectangle.getWidth()) / 2 + (int) this.rectangle.getUpperLeft().getX(),
                    (int) (this.rectangle.getHeight()) / 2 + (int) this.rectangle.getUpperLeft().getY() + 7, s, 15);
        }
        if (this.border != null) {
            // we draw all outer lines in black.
            d.setColor(this.border);
            // draw the outer lines of the block.
            d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        }
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

    /**
     * removes the block from the game when it is destroied.
     * @param game the game we are removing the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * add a hit listner to the blocks hit listener list.
     * @param hl the hit listner we are adding.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * remove a hit listner from the hit listener list.
     * @param hl the hit listner we are removing.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    /**
     * get the height of the block.
     * @return the height of the block.
     */
    public int getHeight() {
        return (int) this.rectangle.getHeight();
    }

    /**
     * return the list of fille infos of the block.
     * @return the fill info list.
     */
    public List<FillInfo> getFills() {
        return this.fills;
    }
}
