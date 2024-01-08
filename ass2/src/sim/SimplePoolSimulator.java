package sim;

/**
 * The SimplePoolSimulator class represents a simple pool simulation with basic ball movement
 * physics. It implements the PoolSimulator interface.
 */
public class SimplePoolSimulator implements PoolSimulator {

  private int width;
  private int height;
  private double radius;
  private double speed;
  private double dx;
  private double dy;
  private double x;
  private double y;
  private final double f = 0.1;
  private final double g = 9.8;
  private String status;
  private String type;
  private String xfac;

  /**
   * Constructs a SimplePoolSimulator with the specified width, height, and simulation type.
   *
   * @param width  The width of the pool table.
   * @param height The height of the pool table.
   * @param type   The type of the simulation, either "simple" or "friction".
   * @throws IllegalArgumentException If the width or height is negative, or the simulation type is
   *                                  invalid.
   */

  public SimplePoolSimulator(int width, int height, String type) throws IllegalArgumentException {
    if ((width < 0) || (height < 0)) {
      throw new IllegalArgumentException("invalid height or width");
    }
    if ((!type.equals("simple")) && (!type.equals("friction"))) {
      throw new IllegalArgumentException("invalid type");
    }
    this.status = "Ball not set up";
    this.type = type;

    this.width = width;
    this.height = height;
    this.radius = Double.NEGATIVE_INFINITY;
    this.x = Double.NEGATIVE_INFINITY;
    this.y = Double.NEGATIVE_INFINITY;
    this.speed = 0;
  }

  /**
   * Starts the pool simulation with the specified parameters.
   *
   * @param x      The initial X-coordinate of the ball.
   * @param y      The initial Y-coordinate of the ball.
   * @param radius The initial radius of the ball.
   * @param speed  The initial speed of the ball.
   * @param dx     The initial velocity in the X direction.
   * @param dy     The initial velocity in the Y direction.
   * @throws IllegalArgumentException If the radius is negative, speed is non-positive, or the ball
   *                                  is not inside the table.
   */

  public void start(int x, int y, int radius, int speed, double dx, double dy)
      throws IllegalArgumentException {
    if (radius < 0) {
      throw new IllegalArgumentException("radius cannot be negative");
    }
    if (speed <= 0) {
      throw new IllegalArgumentException("speed cannot be negative");
    }
    if (x - radius < 0 || x + radius > width || y - radius < 0 || y + radius > height) {
      throw new IllegalArgumentException("The ball is not inside the table.");
    }
    this.radius = radius;
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.status = "Simulation started";
    double mag = Math.sqrt(dx * dx + dy * dy);
    this.dx = dx / mag;
    this.dy = dy / mag;
  }

  /**
   * Advances the simulation by updating ball positions and velocities based on collision rules.
   * Contains two main cases and starts the implementation based on the type. Calls a helper private
   * method to solve the quadratic equation.
   */

  @Override
  public void advance() {

    int xmin = 0;
    int ymin = 0;
    int xmax = this.width;
    int ymax = this.height;
    if (type.equals("simple") && speed > 0) {

      double timeToHitRight = (xmax - this.radius - this.x) / (this.speed * Math.abs(this.dx));
      double timeToHitLeft = (this.x - xmin - this.radius) / (this.speed * Math.abs(this.dx));
      double timeToHitBottom = (this.y - ymin - this.radius) / (this.speed * Math.abs(this.dy));
      double timeToHitTop = (ymax - this.y - this.radius) / (this.speed * Math.abs(this.dy));
      double smallestTime = Double.MAX_VALUE;

      xfac = "";
      if ((this.dx > 0) && (this.dy > 0)) {
        smallestTime = Math.min(timeToHitTop, timeToHitRight);
        if (smallestTime == timeToHitTop) {
          this.status = "Ball hit top edge";
          this.xfac = "top";

        } else {
          this.status = "Ball hit right edge";
          this.xfac = "right";
        }
      }
      if ((this.dx > 0) && (this.dy < 0)) {
        smallestTime = Math.min(timeToHitRight, timeToHitBottom);
        if (smallestTime == timeToHitRight) {
          this.status = "Ball hit right edge";
          this.xfac = "right";
        } else {
          this.status = "Ball hit bottom edge";
          this.xfac = "bottom";
        }

      }
      if ((this.dx < 0) && (this.dy < 0)) {
        smallestTime = Math.min(timeToHitLeft, timeToHitBottom);
        if (smallestTime == timeToHitLeft) {
          this.status = "Ball hit left edge";
          this.xfac = "left";
        } else {
          this.status = "Ball hit bottom edge";
          this.xfac = "bottom";
        }
      }
      if ((this.dx < 0) && (this.dy > 0)) {
        smallestTime = Math.min(timeToHitLeft, timeToHitTop);
        if (smallestTime == timeToHitLeft) {
          this.status = "Ball hit left edge";
          this.xfac = "left";
        } else {
          this.status = "Ball hit top edge";
          this.xfac = "top";
        }
      }
      if ((this.dx == 0) && (this.dy > 0)) {
        smallestTime = timeToHitTop;
        this.status = "Ball hit top edge";
        this.xfac = "top";
      }
      if ((this.dx == 0) && (this.dy < 0)) {
        smallestTime = timeToHitBottom;
        this.status = "Ball hit bottom edge";
        this.xfac = "bottom";
      }
      if ((this.dx > 0) && (this.dy == 0)) {
        smallestTime = timeToHitRight;
        this.status = "Ball hit right edge";
        this.xfac = "right";
      }
      if ((this.dx < 0) && (this.dy == 0)) {
        smallestTime = timeToHitLeft;
        this.status = "Ball hit left edge";
        this.xfac = "left";
      }

      double xDisp = this.speed * this.dx * smallestTime;
      double yDisp = this.speed * this.dy * smallestTime;
      this.x += xDisp;
      this.y += yDisp;
      if (xfac.equals("top") || xfac.equals("bottom")) {
        this.dy = -1 * this.dy;
      }
      if (xfac.equals("left") || xfac.equals("right")) {
        this.dx = -1 * this.dx;
      }
      this.speed = this.speed - 5;
    }
    if (type.equals("friction") && this.speed > 0) {
      double timeRight = Math.abs(
          solve((f * g * dx) / 2, -1 * speed * dx, xmax - this.radius - this.x));
      double timeLeft = Math.abs(
          solve((f * g * dx) / 2, -1 * speed * dx, this.radius - this.x - xmin));
      double timeBottom = Math.abs(
          solve((f * g * dy) / 2, -1 * speed * dy, this.y - ymin - this.radius));
      double timeTop = Math.abs(
          solve((f * g * dy) / 2, -1 * speed * dy, ymax - this.radius - this.y));
      double smalltime = Double.MAX_VALUE;
      if (this.dx > 0 && this.dy > 0) {
        if (timeTop >= 0 && timeRight >= 0) {

          smalltime = Math.min(timeTop, timeRight);
          if (smalltime - timeTop < 0.001) {
            this.status = "Ball hit top edge";
            this.xfac = "top";
          } else {
            this.status = "Ball hit right edge";
            this.xfac = "right";
          }
        }
      }
      if ((this.dx > 0) && (this.dy < 0)) {
        if (timeRight >= 0 && timeBottom >= 0) {
          smalltime = Math.min(timeRight, timeBottom);
          if (smalltime - timeRight < 0.001) {
            this.status = "Ball hit right edge";
            this.xfac = "right";
          } else {
            this.status = "Ball hit bottom edge";
            this.xfac = "bottom";
          }
        }
      }
      if ((this.dx < 0) && (this.dy < 0)) {
        if (timeLeft >= 0 && timeBottom >= 0) {
          smalltime = Math.min(timeLeft, timeBottom);

          if (smalltime - timeLeft < 0.001) {
            this.status = "Ball hit left edge";
            this.xfac = "left";
          } else {
            this.status = "Ball hit bottom edge";
            this.xfac = "bottom";
          }
        }
      }
      if ((this.dx < 0) && (this.dy > 0)) {
        if (timeTop >= 0 && timeLeft >= 0) {
          smalltime = Math.min(timeLeft, timeTop);

          if (smalltime - timeLeft < 0.001) {
            this.status = "Ball hit left edge";
            this.xfac = "left";
          } else {
            this.status = "Ball hit top edge";
            this.xfac = "top";
          }
        }
      }

      double xDisp =
          this.speed * this.dx * smalltime - (((this.f * this.g) / 2) * this.dx * smalltime
              * smalltime);
      double yDisp =
          this.speed * this.dy * smalltime - (((this.f * this.g) / 2) * this.dy * smalltime
              * smalltime);
      this.x += xDisp;
      this.y += yDisp;
      if (xfac.equals("top") || xfac.equals("bottom")) {
        this.dy = -1 * this.dy;
      }
      if (xfac.equals("left") || xfac.equals("right")) {
        this.dx = -1 * this.dx;
      }
      this.speed = this.speed - (this.f * this.g * smalltime);
    }
    if (this.speed <= 0) {
      this.speed = 0;
      this.status = "Ball is stationary";
    }

  }

  private double solve(double a, double b, double c) {
    double d = b * b - 4 * a * c;
    if (d > 0) {
      double root1 = ((2 * c) / ((-1 * b) + Math.sqrt(d)));
      double root2 = ((2 * c) / ((-1 * b) - Math.sqrt(d)));
      //double mini = Math.min(root1,root2);
      return Math.min(root1, root2);
    } else if (d == 0) {
      double root = (2 * c) / (-1 * b);
      return root;
    } else {
      return -1; // No real roots (complex roots)
    }

  }

  /**
   * getter method to get width.
   *
   * @return width.
   */
  @Override
  public int getTableWidth() {
    return this.width;
  }

  /**
   * getter method to get height.
   *
   * @return height.
   */
  @Override
  public int getTableHeight() {
    return this.height;
  }

  /**
   * getter method to get x.
   *
   * @return x.
   */
  @Override
  public double getBallPositionX() {
    return this.x;
  }

  /**
   * getter method to get y.
   *
   * @return y.
   */
  @Override
  public double getBallPositionY() {
    return this.y;
  }

  /**
   * getter method to get radius.
   *
   * @return radius.
   */
  @Override
  public double getBallRadius() {
    return this.radius;
  }

  /**
   * getter method to get velocity vx.
   *
   * @return velocity.
   */
  @Override
  public double getBallVelocityX() {
    return this.speed * this.dx;
  }

  /**
   * getter method to get velocity vy.
   *
   * @return velocity.
   */
  @Override
  public double getBallVelocityY() {
    return this.speed * this.dy;
  }

  /**
   * getter method to return status of the ball.
   *
   * @return status.
   */
  @Override
  public String getStatus() {
    return "Status: " + this.status;
  }
}
