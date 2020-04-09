//package ass5;


import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
* @author Yaron Sofer <yaronsr0604@gmail.com>
* @version 1.6 (current version number of program)
* @since 2010-03-31 (the version of the package this class was first added to)
* used to save the collection of sprites we have in this game. notifies the
* sprites of different events that happen in the game.
*/
public class SpriteCollection {
  // the field of the class
  private ArrayList<Sprite> sprites;

  /**
  * create a collection by using a constructor for the sprites.
  */
  public SpriteCollection() {
      this.sprites = new ArrayList<Sprite>();
  }
  /**
   * adds a sprite to the game.
   * @param s the sprite we are adding to the game.
   */
  public void addSprite(Sprite s) {
      this.sprites.add(s);
  }
  /**
   * remove a sprite from this collection.
   * @param s the sprite we are removing
   */
  public void removeSprite(Sprite s) {
      this.sprites.remove(s);
  }
  /**
   * notifies all the sprites in out collection that time passed.
   * @param dt the time that had passed since the last call.
   */
  public void notifyAllTimePassed(double dt) {
      Sprite spr;
      List<Sprite> copy = new ArrayList<Sprite>(sprites);
      for (int i = 0; i < copy.size(); i++) {
          spr = copy.get(i);
          spr.timePassed(dt);
      }
  }

  /**
   * draws all of the sprites on to the screen.
   * @param d the draw surface we are drawing the sprites on.
   */
  public void drawAllOn(DrawSurface d) {
      Sprite spr;
      List<Sprite> copy = new ArrayList<>(sprites);
      for (int i = 0; i < copy.size(); i++) {
          spr = copy.get(i);
          spr.drawOn(d);
      }
  }
}
