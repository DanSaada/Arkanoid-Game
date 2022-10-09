package geometryPrimitives;

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

/**
 * This class holds all point-related methods.
 *
 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = Math.pow(10, -10);

    //constructor
    /**
     * This constructor gets a X & Y values, and sets a point.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * This method calculates and returns the distance between 2 points.
     * <p>
     * the method use a mathematical formula as follows: sqrt[(x1-x2)^2 + (y1-y2)^2].
     * </p>
     *
     * @param other a point in space.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(((getX() - other.getX()) * (getX() - other.getX()))
                + ((getY() - other.getY()) * (getY() - other.getY())));
    }

    /**
     * This method checks if two points are equal.
     * <p>
     * the method check that the deviation is not greater than EPSILON.
     * </p>
     *
     * @param other a point in space.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return ((Math.abs(this.x - other.getX()) < EPSILON) && (Math.abs(this.y - other.getY()) < EPSILON));
    }

    /**
     * This method is a "get method".
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method gets a value and sets it as the x value of a point.
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This method is a "get method".
     *
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method gets a value and sets it as the y value of a point.
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This method gets 2 values and checks if they are equal for a particular epsilon.
     *
     * @param a
     * @param b
     * @return true if the a == b in the limit calculation of EPSILON, otherwise false.
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

}
