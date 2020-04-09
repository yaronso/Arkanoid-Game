//package ass5;


import java.util.ArrayList;

/**
* @author Yaron Sofer <yaronsr0604@gmail.com>
* @version 1.6 (current version number of program)
* @since 2010-03-31 (the version of the package this class was first added to)
*        The rectangle is used in blocks to define the form of the block and
*        check the intersection with other geometric forms.
*/
public class Rectangle {
  // the fields of the class
  private Point upperLeft;
  private double width;
  private double height;
  private Line top;
  private Line bottom;
  private Line left;
  private Line right;

  /**
   * create a new rectangle based on our upper left point of it, its height and
   * its width.
   * @param upperLeft the upper left point of the rectangle.
   * @param width     the width of the rectangle.
   * @param height    the height of the rectangle.
   */
  public Rectangle(Point upperLeft, double width, double height) {
      this.upperLeft = upperLeft;
      this.width = width;
      this.height = height;
      // create a new line based on the top left point and the top right point.
      this.top = new Line(this.upperLeft.getX(), this.upperLeft.getY(), width + this.upperLeft.getX(),
              this.upperLeft.getY());
      // create a new line which is the bottom line of the rectangle.
      this.bottom = new Line(this.upperLeft.getX(), this.upperLeft.getY() + height, this.upperLeft.getX() + width,
              this.upperLeft.getY() + height);
      // create a new line which is the left line of the rectangle.
      this.left = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX(),
              this.upperLeft.getY() + height);
      // create a new line based on the top right point and the bottom right.
      this.right = new Line(this.upperLeft.getX() + width, this.upperLeft.getY(), this.upperLeft.getX() + width,
              this.upperLeft.getY() + height);
  }

  /**
   * checks if the line inputed intersects with one of the lines in our rectangle.
   * returns a list of all the intersection points. uses the methods in Line to do
   * this.
   * @param line the line we are checking intersections with.
   * @return a list of all the intersection points with the rectangle.
   */
  public java.util.List intersectionPoints(Line line) {
      // create a new array for saving the intersections points.
      java.util.List<Point> list = new ArrayList<Point>();
      // save the intersection point with the top line.
      Point intersection = line.intersectionWith(this.top);
      if (intersection != null) { // if there is an intersection point.
          list.add(intersection); // add to the list.
      }
      // save the intersection point with the bottom line.
      intersection = line.intersectionWith(this.bottom);
      if (intersection != null) { // if there is an intersection point.
          list.add(intersection); // add to the list.
      }
      // save the intersection point with the right line.
      intersection = line.intersectionWith(this.right);
      if (intersection != null) { // if there is an intersection point.
          list.add(intersection); // add to the list.
      }
      // save the intersection point with the left line.
      intersection = line.intersectionWith(this.left);
      if (intersection != null) { // if there is an intersection point.
          list.add(intersection); // add to the list.
      }
      // return a list of intersection points.
      return list;
  }

  /**
   * return the width of the rectangle.
   * @return the width of the rectangle.
   */
  public double getWidth() {
     return this.width;
  }

  /**
   * return the height of the rectangle.
   * @return the height of the rectangle.
   */
  public double getHeight() {
      return this.height;
  }

  /**
   * return the upper left point of this rectangle.
   * @return the upper left point.
   */
  public Point getUpperLeft() {
      return this.upperLeft;
  }

 /**
   * In the following method i care to move the ball to the "almost" hit point, in
   * a case of hit as it appears in moveOneStep, but just slightly before it.
   * @param c a collisionInfo variable which include the collision point and the
   *          collidable object.
   * @return the new point which is the next destination of the ball in his
   *         trajectory.
   */
  public Point almostHitPoint(CollisionInfo c) {
      double p1, p2;
      if (c.collisionPoint().getX() >= c.collisionObject().getCollisionRectangle().upperLeft.getX()
              && c.collisionPoint().getX() <= c.collisionObject().getCollisionRectangle().upperLeft.getX()
                      + c.collisionObject().getCollisionRectangle().getWidth()) {
          if (c.collisionPoint().getY() <= c.collisionObject().getCollisionRectangle().upperLeft.getY()) {
              // return new Point(c.collisionPoint().getX(), c.collisionPoint().getY() - 5);
              p1 = c.collisionPoint().getX();
              p2 = c.collisionPoint().getY() - 5;
              return new Point(c.collisionPoint().getX(), c.collisionPoint().getY() - 5);
          } else {
              // return new Point(c.collisionPoint().getX(), c.collisionPoint().getY() + 5);
              p1 = c.collisionPoint().getX();
              p2 = c.collisionPoint().getY() + 5;
              return new Point(c.collisionPoint().getX(), c.collisionPoint().getY() + 5);
          }
      }

      if (c.collisionPoint().getY() >= c.collisionObject().getCollisionRectangle().upperLeft.getY()
              && c.collisionPoint().getX() <= c.collisionObject().getCollisionRectangle().upperLeft.getX()
                      + c.collisionObject().getCollisionRectangle().getHeight()) {
          if (c.collisionPoint().getX() <= c.collisionObject().getCollisionRectangle().upperLeft.getX()) {
              // return new Point(c.collisionPoint().getX() - 5, c.collisionPoint().getY());
              p1 = c.collisionPoint().getX() - 5;
              p2 = c.collisionPoint().getY();
              return new Point(c.collisionPoint().getX() - 5, c.collisionPoint().getY());
          } else {
              // return new Point(c.collisionPoint().getX() + 5, c.collisionPoint().getY());
              p1 = c.collisionPoint().getX() + 5;
              p2 = c.collisionPoint().getY();
              return new Point(c.collisionPoint().getX() + 5, c.collisionPoint().getY());
          }
      }
      return null;
  }

}
