package coliisionDetection;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import geometryPrimitives.Point;

/**
 * This class holds all velocity-related methods.
 * coliisionDetection.Velocity specifies the change in position on the `x` and the `y` axes.
 *
 */
public class Velocity {
    private double dx;
    private double dy;
    private static final double ANGLE = 360;

    // constructor
    /**
     * This constructor gets a 2 values, and sets them as the new velocity paddle.
     *
     * @param dx - the change on the x-axis.
     * @param dy - the change on the y-axis.
     */
    public Velocity(double dx, double dy) {
        setDx(dx);
        setDy(dy);
    }

    /**
     * This method allows data to be obtained in the configuration of speed and angle.
     * <p>
     * First the method converts the angle in to radians using the auxiliary method "toRadians".
     * Then the method calculates the desired progress of an object using the sin & cos math formulas.
     * And finally the method creates returns a new velocity.
     * </p>
     *
     * @param angle - the requested angle.
     * @param speed - the requested speed.
     * @return the velocity of a moving object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //angle %= ANGLE;
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = speed * Math.cos(angle) * (-1);
        return new Velocity(dx, dy);
    }

    /**
     * This method is a "get method".
     *
     * @return the dx value of a velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method gets a value and sets it as the dx value of a velocity.
     *
     * @param dx - the change on the x-axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * This method is a "get method".
     *
     * @return the dy value of a velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method gets a value and sets it as the dy value of a velocity.
     *
     * @param dy - the change on the y-axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * This method calculates the speed using the pythagoras formula.
     *
     * @return a speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * This method take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param point - the current point before the change in position
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + this.dx, point.getY() + this.dy);
    }
}
