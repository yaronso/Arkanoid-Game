//package ass5;

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Yaron Sofer
 * this class is a sprite that is the background of the Final Four level.
 */
public class BackgroundFF implements Sprite {

    private LevelInformation level;

    /**
     * create a new instance with the final four level information.
     */
    public BackgroundFF() {
        this.level = new FinalFour();
    }

    /**
     * draw the back ground on the draw surface.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        // draw the back of the screen.
        d.fillRectangle(0, 0, level.width(), level.height());
        d.setColor(Color.WHITE);
        // draw the lines of the rain
        for (int i = 1; i < 13; i++) {
            d.drawLine(40 + 5 * i, 470, 20 + 5 * i, this.level.height());
        }
        // draw the second set of the lines of the rain
        for (int i = 1; i < 25; i++) {
            d.drawLine(280 + 5 * i, 400, 260 + 5 * i, this.level.height());
        }
        // the the circles of the clouds.
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(50, 480, 20);
        d.fillCircle(80, 480, 30);
        d.setColor(Color.gray);
        d.fillCircle(65, 450, 25);
        d.fillCircle(95, 465, 20);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(300, 435, 35);
        d.fillCircle(330, 460, 25);
        d.setColor(Color.gray);
        d.fillCircle(315, 410, 30);
        d.fillCircle(345, 420, 40);
        d.setColor(Color.darkGray);
        d.fillCircle(395, 400, 20);
        d.fillCircle(365, 395, 25);
        d.setColor(Color.WHITE);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the time has passed from the last call
     */
    public void timePassed(double dt) {
    }

}
