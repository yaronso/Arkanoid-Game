//package ass5;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * this is a sprite that is responsible to show the name of the level the game
 * is in to the screen.
 * @author Yaron Sofer
 */
public class LevelIndicator implements Sprite {
    private GameLevel game;
    private int font;
    private String name;
    private Color color;

    /**
     * create a new level indicator using a game level object.
     * @param g      we need this to know hot to place the indicator and get the
     * counter.
     * @param color - the color of the level
     */
    public LevelIndicator(GameLevel g, Color color) {
        this.game = g;
        // we want the font of the text to be a bit smaller than the edge size.
        this.font = g.getEdgeSize() - 5;
        this.name = this.game.getLevel().levelName();
        this.color = color;
    }

    /**
     * draw the name of the level to the screen.
     * @param d the drawsurface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText(this.game.getWidth() - 250, this.game.getEdgeSize(), "Level: " + this.name, this.font);
    }

    /**
     * notifiy the sprite that time has passed.
     * @param dt the time that had passed since the last call
     */
    public void timePassed(double dt) {
    }

    /**
     * add this indicator to the game.
     */
    public void addToGame() {
        this.game.addSprite(this);
    }
}
