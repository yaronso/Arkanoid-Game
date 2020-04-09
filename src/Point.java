//package ass5;

/**
* @author Yaron Sofer <yaronsr0604@gmail.com>
* @version 1.6 (current version number of program)
* @since 2010-03-31 (the version of the package this class was first added to)
*        the Point is consisted of 2 values, x value and y value. It has
*        methods that calculate simple things in the plain that the point is
*        on.
*/
public class Point {
  // the fields of the class point
  private double x;
  private double y;

  /**
   * creates a point based on the x value and y value supplied.
   *
   * @param x the x value of the point.
   * @param y the y value of the point.
   */
  // constructor
  public Point(double x, double y) {
      this.x = x;
      this.y = y;
  }

  /**
   * uses math to return the distance between two points.
   *
   * @param other - the point to calculate the distance to.
   * @return MultiplyX + MultiplyY - the distance.
   */
  // method - return the distance of this point to the other point
  public double distance(Point other) {
      double multiplyX = (this.x - other.getX()) * (this.x - other.getX());
      double multiplyY = (this.y - other.getY()) * (this.y - other.getY());
      return Math.sqrt(multiplyX + multiplyY);
  }

  /**
   * checks if two points are equal, it means the x and y are the same.
   *
   * @param other the point we are comparing with.
   * @return true - the points are equal. false - they are not.
   */
  public boolean equals(Point other) {
      // if the x's values and the y's values are equals the points are equal.
      return ((this.x == other.getX()) && (this.y == other.getY()));
  }

  /**
   * return the x value of the point.
   *
   * @return the x value of the current point.
   */
  public double getX() {
      return this.x;
  }

  /**
   * return the y value of the point.
   *
   * @return the y value of the current point.
   */
  public double getY() {
      return this.y;
  }

  /**
   * checks if the value (x or y) of a point is in between two other values.
   * @param m  the value we want to check if it is in between.
   * @param x1 one of the limits.
   * @param x2 one of the limits.
   * @return true - the point is between the two others, false - it is not.
   */
  public boolean isBetween(double m, double x1, double x2) {
      if ((m < x1 && m < x2) || (m > x1 && m > x2)) {
          return false;
      }
      //means its between.
      return true;
  }

 /** for the intersection.
  * set the x value of a point
  * @param x1 value of a point
  */
  public void setX(double x1) {
      this.x = x1;
  }

 /** for the intersection.
  * set the y value of a point
  * @param y1 value of a point
  */
  public void setY(double y1) {
      this.y = y1;
  }

}