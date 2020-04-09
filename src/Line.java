//package ass5;

//import java.util.ArrayList;
import java.util.ListIterator;


/**
* @author Yaron Sofer <yaronsr0604@gmail.com>
* @version 1.6 (current version number of program)
* @since 2010-03-31 (the version of the package this class was first added to)
*        This class is a line segment, it has a start and end point, a slope,
*        and an equation. The class contains methods to find special points on
*        the line, intersection with other lines and general properties of the
*        line.
*/
public class Line {
  // the fields of the class line.
  // the starting point of the segment.
  private Point start;
  // the end point of the segment.
  private Point end;
  // the incline of the segment.
  private double slope;
  // the free coefficient in the equation of the line.
  private double freeCoefficient;

  /**
   * constructs a line based on its starting point and ending point.
   * @param start - the starting point of the line.
   * @param end   - the end point of the line.
   */
  public Line(Point start, Point end) {
      this.start = start;
      this.end = end;
      // sets the slope of the line and its equation.
      this.setSlopeEquation();
  }

  /**
   * constructs a line based on the x and y values of the start and end points.
   * @param x1 the x of the starting point.
   * @param y1 the y of the staring point.
   * @param x2 the x of the end point.
   * @param y2 the y of the end point.
   */
  public Line(double x1, double y1, double x2, double y2) {
      // calling the constructor which creates points in the class Point.
      this.start = new Point(x1, y1);
      this.end = new Point(x2, y2);
      // sets the slope of the line and its equation.
      this.setSlopeEquation();
  }

  /**
   * finds the slope of the line segments and the equation of the line. saves them
   * as properties of the line.
   */
  public void setSlopeEquation() {
      if ((this.start.getX() - this.end.getX()) == 0) { // it is a line of form x = c
          // double supports infinity.
          this.slope = Double.POSITIVE_INFINITY;
          // the free coefficient is the X.
          this.freeCoefficient = this.start().getX();
      } else if (this.start.getY() - this.end().getY() == 0) {
          this.slope = 0;
          // the free coefficient is the Y.
          this.freeCoefficient = this.start().getY();
          // when the slope is not 0.
      } else {
          this.slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
          this.freeCoefficient = (-1 * this.start.getX() * this.slope) + this.start.getY();
      }
  }

  /**
   * gets the length of the line.
   * @return returns the length of the line.
   */
  public double length() {
      double lengthX = (start.getX() - end.getX()) * (start.getX() - end.getX());
      double lengthY = (start.getY() - end.getY()) * (start.getY() - end.getY());
      return Math.sqrt(lengthX + lengthY);
  }

  /**
   * find the middle point of the line segment.
   * @return the middle point of the line.
   */
  public Point middle() {
      double mX = (start.getX() + end.getX()) / 2;
      double mY = (start.getY() + end.getY()) / 2;
      // calling the constructor which creates points in the class Point.
      return new Point(mX, mY);
  }

  /**
   * get the slope of the line.
   * @return the slope/incline of the line.
   */
  public double getSlope() {
      return this.slope;
  }

  /**
   * get the free coefficient of the equation.
   *
   * @return the free coefficient of the line.
   */
  public double getFreeCoefficient() {
      return this.freeCoefficient;
  }

  /**
   * returns the starting point of the line.
   *
   * @return returns the starting point of the line.
   */
  public Point start() {
      return this.start;
  }

  /**
   * returns the ending point of the line.
   *
   * @return returns the end point of the line/
   */
  public Point end() {
      return this.end;
  }

  /**
   * checks if the two lines intersect by getting the intersection point and then
   * checking if the intersection point is on both line segments. in addition, i
   * check if the lines are vertical.
   * @param other the line we are checking intersection with.
   * @return true - the lines intersect, false - they do not.
   */
  public Point intersectionWith(Line other) {
      // if the lines have the same slop they are not intersecting.
      if (this.slope == other.getSlope()) {
          return null;
      }
      double xIntersectionPoint;
      double yIntersectionPoint;
      Point intersection;
      // checking if the line is vertical.
      if (this.start.getX() == this.end.getX()) {
          // same value of x
          xIntersectionPoint = this.start().getX();
          // change the y value.
          yIntersectionPoint = other.slope * (xIntersectionPoint - other.start().getX()) + other.start().getY();
          intersection = new Point(xIntersectionPoint, yIntersectionPoint);
      } else if (other.start.getX() == other.end.getX()) {
          xIntersectionPoint = other.start().getX();
          yIntersectionPoint = this.slope * (xIntersectionPoint - this.start().getX()) + this.start().getY();
          intersection = new Point(xIntersectionPoint, yIntersectionPoint);
      } else {
          // get the intersection point.
          intersection = this.intersectionPoint(other);

      }

      // used to check if the intersection point is on the two line segments.
      boolean first = false, second = false;
      // if the point is on the first line segment.
      if ((intersection.isBetween(intersection.getX(), this.start.getX(), this.end.getX()))
              && (intersection.isBetween(intersection.getY(), this.start.getY(), this.end.getY()))) {
          first = true;
      } // if the point is on the second line segment.
      if ((intersection.isBetween(intersection.getX(), other.start().getX(), other.end().getX()))
              && (intersection.isBetween(intersection.getY(), other.start().getY(), other.end().getY()))) {
          second = true;
      }
      if (first && second) {
          return intersection;

      }
      return null;
  }

  /**
   * returns the intersection point of the two line by using and solving the
   * equation system of the equations of the two lines.
   * @param other the other line we are getting the intersection point with.
   * @return the intersection point of the two lines.
   */
  public Point intersectionPoint(Line other) {
      double valueX;
      double valueY;
      // create the intersection point.
      valueX = (other.getFreeCoefficient() - this.freeCoefficient) / (this.slope - other.getSlope());
      valueY = slope * valueX + freeCoefficient;
      return new Point(java.lang.Math.round(valueX), java.lang.Math.round(valueY));
  }

  /**
   * checks if the lines intersect. if they do intersect it returns the
   * intersection point.
   * @param other the line we are checking intersection with.
   * @return the intersection point if there is one and null if there is no
   *  intersection point.
   */
  public boolean isIntersecting(Line other) {
      // if the lines are not intersecting.
      if (intersectionWith(other) == null) {
          return false;
      }
      // if the lines are intersection return the intersection point.
      return true;
  }

  /**
   * find the closest intersection of the line and the rectangle that is the
   * closest one to the start of the line.
   * @param rect the rectangle we are checking intersection with.
   * @return the closest intersection
   */
  public Point closestIntersectionToStartOfLine(Rectangle rect) {
      // recieve the list of the intersection points with the rectangle's lines.
      java.util.List list = rect.intersectionPoints(this);
      if (list.size() == 0) {
          return null;
      }
      // using ListIterator to iterate over the points list.
      ListIterator<Point> itr = list.listIterator();
      Point closest = (Point) list.get(0);
      // iterate over the list by a loop
      for (int i = 1; i < list.size(); i++) {
          Point next = (Point) list.get(i);
          // if the point in the 'i' index is closer return it.
          if (closest.distance(this.start) > next.distance(this.start)) {
              // casting the closest point.
              closest = (Point) list.get(i);
              closest = next;
          }
      }
      // return the closest point
      return closest;
  }

}
