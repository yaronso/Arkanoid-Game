//package ass5;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * this class is a sprite that is the background of the Green 3 level.
 */
public class BackGroundG3 implements Sprite {
    private LevelInformation level;

    /**
     * create a new backgournd using the level information.
     */
    public BackGroundG3() {
        this.level = new Green3();
    }

    /**
     * draw this backdround on the drawsurface.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        // draw the back of the screen.
        d.fillRectangle(0, 0, this.level.width(), this.level.height());
        d.setColor(Color.BLACK);
        // draw the buildings and the light.
        d.fillRectangle(this.level.edgeSize() * 2, this.level.height() - (200 + this.level.edgeSize()), 100, 200);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(this.level.edgeSize() + 60, this.level.height() - (250 + this.level.edgeSize()), 25, 50);
        d.setColor(Color.GRAY);
        d.fillRectangle(this.level.edgeSize() + 67, this.level.height() - (400 + this.level.edgeSize()), 10, 150);
        d.setColor(Color.orange);
        d.fillCircle(this.level.edgeSize() + 71, this.level.height() - (400 + this.level.edgeSize()), 15);
        d.setColor(Color.red);
        d.fillCircle(this.level.edgeSize() + 71, this.level.height() - (400 + this.level.edgeSize()), 10);
        d.setColor(Color.white);
        d.fillCircle(this.level.edgeSize() + 71, this.level.height() - (400 + this.level.edgeSize()), 5);
        d.setColor(Color.white);
        // draw the windows in the building.
        for (int j = 190; j > 0; j = j - 25) {
            for (int i = 0; i < 30; i = i + 5) {
                d.fillRectangle(this.level.edgeSize() * 2 + 10 + (3 * i),
                        this.level.height() - (j + this.level.edgeSize()), 5, 10);
            }
        }
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the time has passed from the last call
     */
    public void timePassed(double dt) {

    }
}
