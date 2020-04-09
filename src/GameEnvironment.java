//package ass5;

//package ass2;

import java.util.ArrayList;
import java.util.List;

/**
* @author Yaron Sofer <yaronsr0604@gmail.com>
* @version 1.6 (current version number of program)
* @since 2010-03-31 (the version of the package this class was first added to)
* This class holds all of the collidables in out game and helps manage the
* movement of the ball in our game.
*/
public class GameEnvironment {
  // creating collidables list as a field of the class
  private java.util.List<Collidable> objects;

  /**
   * create the list of the colliadbles objects which will be in the game
   * enviorment.
   */
  public GameEnvironment() {
      this.objects = new ArrayList<Collidable>();
  }

  /**
   * add a colidable to out list of collaidiables in the game enviorment.
   * @param c the collidable we are adding.
   */
  public void addCollidable(Collidable c) {
      // adding the new object to the collidables list.
      this.objects.add(c);
  }
  /**
   * used to remove a collidable object from the list of collidables.
   * @param c the collidable we are removing.
   */
  public void removeCollidable(Collidable c) {
      this.objects.remove(c);
  }
  /**
   * get the information about the next closest collision in the way of the
   * ball. use the line between the current and next position of the ball
   * and checks if the line is intersecting with on of out collidables.
   * if so, we return the info about the closest collision.
   * @param trajectory the line beteen the balls current position and its
   * next position.
   * @return the info about the collision.
   */
  public CollisionInfo getClosestCollision(Line trajectory) {
      // create a list to hold all of our intersections.
      List<Point> intersections = new ArrayList<Point>();
      // create a list to hold all of our collidiables that have intersection.
      List<Collidable> colids = new ArrayList<Collidable>();
      List<Collidable> copy = new ArrayList<Collidable>(this.objects);
      // used to iterate over the three lists.
      Point intersection;
      Collidable colid;
      // go over the objects we have saved in the game enviroment.
      for (int i = 0; i < copy.size(); i++) {
          // save the next collidiable on our list.
          colid = (Collidable) copy.get(i);
          // get the intersection of the trajectory with the colidable.
          intersection = trajectory.closestIntersectionToStartOfLine(colid.getCollisionRectangle());
          /*
           * if we have an intersection and if the intersection isn't the old center of
           * the ball.
           */
          if (intersection != null && !intersection.equals(trajectory.start())) {
              // add the intersection to our list.
              intersections.add(intersection);
              // add the colidiable to our list.
              colids.add(colid);
          }
      }
      // we have no intersection.
      if (intersections.size() == 0) {
          // no intersections.
          return null;
          // we have only 1 intersection so we don't need to find the closest.
      } else if (intersections.size() == 1) {
          // return the info of the collision.
          return new CollisionInfo(intersections.get(0), colids.get(0));
      } else { // we have multiple intersections.
          // randomly set the first point to be the closest.
          Point closest = (Point) intersections.get(0);
          // set the place of the closest point in the two arrays.
          int closestPlace = 0;
          // save the next point we will compare.
          Point next;
          // used to save the closest place.
          int i = 0;
          // go over all the intersections we had saved.
          for (i = 0; i < intersections.size(); i++) {
              next = (Point) intersections.get(i); // save the next element.
              /*
               * if the next point is closer to the center than the last closest point we
               * found.
               */
              if (closest.distance(trajectory.start()) > next.distance(trajectory.start())) {
                  closest = next; // the next is closer than the closest.
                  closestPlace = i; // save the position of the closest place.
              }
          }
          return new CollisionInfo(closest, colids.get(closestPlace));
      }
  }
}
