//package ass5;

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Yaron Sofer
 * this class is a sprite that is the background of the Wide Easy level.
 */
public class BackgorounWE implements Sprite {

    private LevelInformation level;

    /**
     * create a new back ground using the level information.
     */
    public BackgorounWE() {
        this.level = new WideEasy();
    }

    /**
     * draw the background to the screen using the functions of the drawsurface.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        // create the ractangle in the back of the screen.
        d.fillRectangle(0, 0, level.width(), level.height());
        // the center of the sun.
        Point sunCenter = new Point(100, 100);
        d.setColor(Color.yellow);
        // draw all of the lines that go out of the center of the sun.
        for (int i = 0; i < 40; i++) {
            d.drawLine((int) sunCenter.getX(), (int) sunCenter.getY(), level.edgeSize() + i * 15, level.width());
        }
        // create the sun
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), 60);
        d.setColor(Color.orange);
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), 45);
        d.setColor(Color.red);
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), 30);
    }

    /**
     * let this sprite know that time has passed.
     * @param dt the time has passed from the last call
     */
    public void timePassed(double dt) {
    }
}
