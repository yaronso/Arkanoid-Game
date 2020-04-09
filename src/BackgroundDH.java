//package ass5;

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Yaron sofer
 * this class is a sprite that is the background of the Direct Hit level.
 */
public class BackgroundDH implements Sprite {
    // the field
    private DirectHit li;

    /**
     * create a new background based on the level information.
     */
    public BackgroundDH() {
        this.li = new DirectHit();
    }

    /**
     * draw the background to the screen.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        Point center = new Point((li.width() - 2 * li.edgeSize()) / 2, (li.height() - 2 * li.edgeSize()) / 2);
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, li.width(), li.height());
        d.setColor(Color.blue);
        d.drawCircle((int) center.getX(), (int) center.getY(), 30);
        d.drawCircle((int) center.getX(), (int) center.getY(), 50);
        d.drawCircle((int) center.getX(), (int) center.getY(), 70);
        d.drawLine((int) center.getX(), (int) center.getY(), (int) center.getX() + 80, (int) center.getY());
        d.drawLine((int) center.getX(), (int) center.getY(), (int) center.getX(), (int) center.getY() + 80);
        d.drawLine((int) center.getX(), (int) center.getY(), (int) center.getX() - 80, (int) center.getY());
        d.drawLine((int) center.getX(), (int) center.getY(), (int) center.getX(), (int) center.getY() - 80);
    }

    /**
     * notify this sprite that time has passed.
     * @param dt the time has passed from the last call
     */
    public void timePassed(double dt) {
    }
}
