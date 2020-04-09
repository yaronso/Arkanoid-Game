//package ass5;

//package ass2;

/**
* @author Yaron Sofer <yaronsr0604@gmail.com>
* @version 1.6 (current version number of program)
* @since 2010-03-31 (the version of the package this class was first added to)
* used to hold the information about collisions. holds the collision point and
* what was the collision with.
*/
public class CollisionInfo {
  // the fields
  private Point collisionPoint;
  private Collidable collisionObject;

  /**
   * creates a new collision info.
   * @param p the collision point.
   * @param c the object we collided with.
   */
  public CollisionInfo(Point p, Collidable c) {
      this.collisionPoint = p;
      this.collisionObject = c;
  }

  /**
   * return the collision point.
   * @return the collision point.
   */
  public Point collisionPoint() {
      return collisionPoint;
  }

  /**
   * return the object we had collided with.
   * @return the object we had collided with.
   */
  public Collidable collisionObject() {
      return collisionObject;
  }
}
