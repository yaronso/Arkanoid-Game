//package ass5;

//package ass2;

/**
* @author Yaron Sofer <yaronsr0604@gmail.com>
* @version 1.6 (current version number of program)
* @since 2010-03-31 (the version of the package this class was first added to)
*        Velocity is used to define the movements of objects. has change on x
*        axis and change on y axis.
*/
public class Velocity {
  // the fields of the class velocity
  private double dx;
  private double dy;
  private double speed;

  /**
   * creates a velocity based on change in the axis.
   *
   * @param dx change on x axis.
   * @param dy change on y axis.
   * @param speed the speed of the ball
   */
  // constructor
  public Velocity(double dx, double dy, double speed) {
      this.dx = dx;
      this.dy = dy;
      this.speed = speed;
  }

  /**
   * define the ratio between speed and size. gets a size of a ball and returns
   * the speed it should have.
   *
   * @param size the size of the ball we are calculation the speed for.
   * @return the speed of the object.
   */
  public static int speedRatio(int size) {
      // all balls over size 50 have the same slow speed.
      if (size >= 50) {
          return 1;
      } else { // the ball is smaller than size 50.
          return 4 - (int) (size / 15); // the formula we will use. 4 is top.
      }
  }

  /**
   * return the speed of the ball for other uses.
   * @return the speed of the ball.
   */
  public double getSpeed() {
      return speed;
  }

  /**
   * return the value of the change in x.
   *
   * @return the value of the change in x.
   */
  public double getDx() {
      return this.dx;
  }

  /**
   * return the value of the change in y.
   *
   * @return the value of the change in y.
   */
  public double getDy() {
      return this.dy;
  }

  /**
   * change the value of the change in x.
   *
   * @param change the new value of the change in x.
   */
  public void changeDx(double change) {
      this.dx = change;
  }

  /**
   * change the value of the change in y.
   * @param change the new value of the change in y.
   */
  public void changeDy(double change) {
      this.dy = change;
  }

  /**
   * use the received point and the current properties of velocity to return a new
   * point with the change of velocity.
   * @param p the point we are changing.
   * @return the new point after change.
   */
  // Take a point with position (x,y) and return a new point
  // with position (x + dx, y + dy).
  public Point applyToPoint(Point p) {
      return new Point(p.getX() + this.dx, p.getY() + this.dy);
  }

  /**
   * create a new velocity based on and angle and speed we want out of the object.
   * @param angle the angle we want.
   * @param speed the speed we want.
   * @return the new velocity we have created.
   */
  public static Velocity fromAngleAndSpeed(double angle, double speed) {
      // use the trigonometric function to make the conversion.
      double dx = speed * Math.sin(Math.toRadians(angle));
      double dy = (-1) * speed * Math.cos(Math.toRadians(angle));
      return new Velocity(dx, dy, speed);
  }

  /**
   * check if two velocities are equal.
   * @param other the other velocity we are comparing to.
   * @return if the Velocities are equal or not.
   */
  public boolean equal(Velocity other) {
      return ((this.dx == other.getDx()) && (this.getDy() == other.getDy()));
  }
}
