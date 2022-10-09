package geometryPrimitives;

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import java.util.List;

/**
 * This class holds all line-related methods.
 *
 */
public class Line {
    private Point start;
    private Point end;
    private static final double EPSILON = Math.pow(10, -10);

    // constructors
    /**
     * This constructor gets 2 points and sets a line.
     *
     * @param start
     * @param end
     */
    public Line(Point start, Point end) {
        setStart(start);
        setEnd(end);
    }

    /**
     * This constructor gets 2 X & Y values and sets a line.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public Line(double x1, double y1, double x2, double y2) {
        setStart(new Point(x1, y1));
        setEnd(new Point(x2, y2));
    }

    /**
     * This method calculates and returns a line's length.
     * <p>
     * the method use a distance method with the reference to point
     * </p>
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * This method calculates and returns a line's middle point.
     * <p>
     * the method use a mathematical formula as follows: middle = ((x1+x2)/2, (y1+y2)/2).
     * </p>
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * This method is a "get method".
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method gets a point and sets it as the start point of a line.
     *
     * @param start
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * This method is a "get method".
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method gets a point and sets it as the end point of a line.
     *
     * @param end
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * This method is a "get method".
     * <p>
     * the method use a mathematical formula as follows: slope = (y1+y2)/(x1+x2).
     * </p>
     *
     * @param line
     * @return the slope of a line.
     */
    public double getSlope(Line line) {
        return ((line.start.getY() - line.end.getY())
                / (line.start.getX() - line.end.getX()));
    }

    /**
     * This method is a "get method".
     * <p>
     * the method use a mathematical formula as follows: B = y - mx.
     * </p>
     *
     * @param line
     * @return the B value of an equation.
     */
    public double getB(Line line) {
        return (line.start().getY() - (getSlope(line) * line.start().getX()));
    }

    /**
     * This method calculates and returns a Y value.
     * <p>
     * the method use a mathematical formula as follows: Y = mx + b.
     * </p>
     *
     * @param m the slope of the equation.
     * @param b
     * @param x
     * @return the Y value of an equation.
     */
    public double calculateY(double m, double b, double x) {
        return (m * x) + b;
    }

    /**
     * This method checks if two lines intersect.
     * <p>
     * the method uses "intersectionWith" method for to know if there has been an intersection
     * </p>
     *
     * @param other
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //same line
        if (this.equals(other)) {
            return true;
        }
        Point tmp = intersectionWith(other);
        return tmp != null;
    }

    /**
     * This method checks if two lines intersect and return the intersection point between them.
     * <p>
     * the method contains many edge cases, because there may be 2 lines that according to their line equation
     * should intersect, but since each line we get will be defined from a start and end points, actually the
     * definition of the lines prevents this intersection in some cases.
     * among the cases described appear: Lines without a slope, lines with a zero slope, parallel lines,
     * vertical lines, merging lines and more.
     * </p>
     *
     * @param other
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (Point.doubleEquals(this.start.getX(), this.end.getX())
                && Point.doubleEquals(other.start.getX(), this.start.getX())) {
            if (Point.doubleEquals(other.start.getY(), this.start.getY())) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (Point.doubleEquals(other.start.getY(), this.end.getY())) {
                return new Point(this.start.getX(), this.end.getY());
            }
            if (Point.doubleEquals(other.end.getY(), this.end.getY())) {
                return new Point(this.start.getX(), this.end.getY());
            }
            if (Point.doubleEquals(other.end.getY(), this.start.getY())) {
                return new Point(this.start.getX(), this.start.getY());
            }
        }
        //lines parallels.
        if (Point.doubleEquals(getSlope(this), getSlope(other))) {
            return parallelsIntersection(other);
        }
        //lines parallel but slope excists.
        if (Point.doubleEquals(getSlope(this), getSlope(other))) {
            //will never meet.
            if (this.start.getX() < other.start.getX() || this.start.getX() > other.start.getX()) {
                return null;
            }
        }
        //checking if slope excists.
        //both slope's doesn't excists.
        if (Point.doubleEquals(this.start.getX(), this.end.getX())
                && Point.doubleEquals(other.start.getX(), other.end.getX())) {
            //this and other intersect in one point.
            //case 1:
            if (Point.doubleEquals(this.end.getY(), other.start.getY()) && this.start.getY() != other.end.getY()) {
                if (other.start.getY() - EPSILON <= other.end.getY()
                        || other.end.getY() - EPSILON <= other.start.getY()) {
                    return new Point(this.end.getX(), this.end.getY());
                }
            } else if (Point.doubleEquals(this.end.getY(), other.end.getY()) //case 2
                    && this.start.getY() != other.start.getY()) {
                if (other.end.getY() - EPSILON <= other.start.getY()
                        || other.start.getY() - EPSILON <= other.end.getY()) {
                    return new Point(this.start.getX(), this.start.getY());
                }
            } else if (Point.doubleEquals(this.start.getY(), other.start.getY()) //case 3
                    && this.end.getY() != other.end.getY()) {
                if (other.start.getY() - EPSILON <= other.end.getY()
                        || other.end.getY() - EPSILON <= other.start.getY()) {
                    return new Point(this.end.getX(), this.end.getY());
                }
            } else if (Point.doubleEquals(this.start.getY(), other.end.getY()) //case 4
                    && this.end.getY() != other.start.getY()) {
                if (other.end.getY() - EPSILON <= other.start.getY()
                        || other.start.getY() - EPSILON <= other.end.getY()) {
                    return new Point(this.start.getX(), this.start.getY());
                }
            }
            return null;
        }
        //one slope doesn't excists and the other is zero.
        if (Point.doubleEquals(this.start.getX(), this.end.getX())
                && Point.doubleEquals(other.start.getY(), other.end.getY())) {
            //lines perpendicular and cross each other.
            if (this.start.getX() >= other.start.getX() - EPSILON
                    && this.start.getX() - EPSILON <= other.end.getX()
                    && this.start.getY() - EPSILON <= other.start.getY()
                    && this.end.getY() >= other.start.getY() - EPSILON) {
                return new Point(this.start.getX(), other.start.getY());
            }
            return null;
        }
        //one slope is zero and the other doesn't excists.
        if (Point.doubleEquals(this.start.getY(), this.end.getY())
                && Point.doubleEquals(other.start.getX(), other.end.getX())) {
            //lines perpendicular and cross each other.
            if (other.start.getX() >= this.start.getX() - EPSILON
                    && other.start.getX() - EPSILON <= this.end.getX()
                    && other.start.getY() - EPSILON <= this.start.getY()
                    && other.end.getY() >= this.start.getY() - EPSILON) {
                return new Point(other.start.getX(), this.start.getY());
            }
            return null;
        }
        //one slope doesn't excists and the other does.
        if (Point.doubleEquals(this.start.getX(), this.end.getX()) && other.start.getX() != other.end.getX()) {
            if (this.start.getX() >= other.start.getX() - EPSILON
                    && this.start.getX() - EPSILON <= other.end.getX()
                    || this.start.getX() - EPSILON <= other.start.getX()
                    && this.start.getX() >= other.end.getX() - EPSILON) {
                double py = calculateY(getSlope(other), getB(other), this.start.getX());
                Point intersectionPoint = new Point(this.start.getX(), py);
                if (intersectionPoint.distance(this.start) + intersectionPoint.distance(this.end) == this.length()) {
                    return intersectionPoint;
                }
            }
            return null;
        }
        //one slope excists and the other doesn't.
        if (this.start.getX() != this.end.getX() && Point.doubleEquals(other.start.getX(), other.end.getX())) {
            if (other.start.getX() >= this.start.getX() - EPSILON && other.start.getX() - EPSILON <= this.end.getX()
                    || other.start.getX() - EPSILON <= this.start.getX()
                    && other.start.getX() >= this.end.getX() - EPSILON) {
                double py = calculateY(getSlope(this), getB(this), other.start.getX());
                Point intersectionPoint = new Point(other.start.getX(), py);
                if (Point.doubleEquals(intersectionPoint.distance(other.start)
                        + intersectionPoint.distance(other.end), other.length())) {
                    return intersectionPoint;
                }
            }
            return null;
        }
        //lines parallels.
        if (Point.doubleEquals(getSlope(this), getSlope(other))) {
            //will never meet.
            if (getB(this) != getB(other)) {
                return null;
            }
            //might intersect in one point
            if (Point.doubleEquals(this.start.getX(), other.end.getX())
                    || Point.doubleEquals(this.start.getX(), other.start.getX())) {
                return new Point(this.start.getX(), this.start.getY());
            } else if (Point.doubleEquals(this.end.getX(), other.end.getX())
                    || Point.doubleEquals(this.end.getX(), other.start.getX())) {
                return new Point(this.end.getX(), this.end.getY());
            }
            return null;
        }
        //regular intersect between lines.
        double intersectX = (getB(other) - getB(this)) / (getSlope(this) - getSlope(other));
        double intersectY = intersectX * getSlope(this) + getB(this);
        if (this.start.getX() - EPSILON <= intersectX && intersectX - EPSILON <= this.end.getX()) {
            if ((other.start.getX() - EPSILON <= intersectX && intersectX - EPSILON <= other.end.getX())
                    || other.end.getX() - EPSILON <= intersectX && intersectX - EPSILON <= other.start.getX()) {
                return new Point(intersectX, intersectY);
            }
        } else if (this.end.getX() - EPSILON <= intersectX && intersectX - EPSILON <= this.start.getX()) {
            if ((other.start.getX() - EPSILON <= intersectX && intersectX - EPSILON <= other.end.getX())
                    || other.end.getX() - EPSILON <= intersectX && intersectX - EPSILON <= other.start.getX()) {
                return new Point(intersectX, intersectY);
            }
        }
        return null;
    }

    /**
     * This method checks if two lines are parallels and intersect, and return the intersection point between them.
     * <p>
     * this method is a auxiliary method to the intersectionWith method that checks specifically the parallels cases.
     * </p>
     *
     * @param other
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point parallelsIntersection(Line other) {
        if (Point.doubleEquals(getSlope(this), getSlope(other))) {
            //same line
            if (this.equals(other)) {
                //size of the lines is one point
                if (this.start.equals(this.end)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
                return null;
            }
            //this line has a slope that is not 0 "or" this line has a slope = 0
            if ((getSlope(this) != 0 && this.start.getX() != this.end.getX())
                    || Point.doubleEquals(getSlope(this), 0)) {
                //case 1:
                if (Point.doubleEquals(this.end.getX(), other.start.getX()) && this.start.getX() != other.end.getX()) {
                    if (other.start.getX() - EPSILON <= other.end.getX()
                            || other.end.getX() - EPSILON <= other.start.getX()) {
                        return new Point(this.end.getX(), this.end.getY());
                    }
                } else if (Point.doubleEquals(this.end.getX(), other.end.getX()) //case 2
                        && this.start.getX() != other.start.getX()) {
                    if (other.end.getX() - EPSILON <= other.start.getX()
                            || other.start.getX() - EPSILON <= other.end.getX()) {
                        return new Point(this.start.getX(), this.start.getY());
                    }
                } else if (Point.doubleEquals(this.start.getX(), other.start.getX()) //case 3
                        && this.end.getX() != other.end.getX()) {
                    if (other.start.getX() - EPSILON <= other.end.getX()
                            || other.end.getX() - EPSILON <= other.start.getX()) {
                        return new Point(this.end.getX(), this.end.getY());
                    }
                } else if (Point.doubleEquals(this.start.getX(), other.end.getX()) // case 4
                        && this.end.getX() != other.start.getX()) {
                    if (other.end.getX() - EPSILON <= other.start.getX()
                            || other.start.getX() - EPSILON <= other.end.getX()) {
                        return new Point(this.start.getX(), this.start.getY());
                    }
                }
                return null;
            }
        }
        return null;
    }

    /**
     * This method checks if two lines are equal.
     * <p>
     * the method use the "equals" method with reference to point.
     * </p>
     *
     * @param other a line in space.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * This method gets a rectangle and return the closest intersection (if there is such one)
     * with this line (otherwise returns null).
     * <p>
     * First, the method creates a dynamic array who is being filled up by the "intersectionPoints" method.
     * Then, if the array isn't empty, the method creates a temporary minimum point and initialize it
     * with the first point in the array.
     * Finally the method checks if there is a closer point from the current one in the array, and returns
     * the closest one.
     * </p>
     *
     * @param rect
     * @return the closest intersection point between a rectangle and a line or null if there isn't one.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intercectPointArray = rect.intersectionPoints(this);
        if (!intercectPointArray.isEmpty()) {
            Point tmpMinPoint = intercectPointArray.get(0);
            //checking if the temporary point is the closest.
            for (int i = 1; i < intercectPointArray.size(); i++) {
                if (this.start.distance(tmpMinPoint) > this.start.distance(intercectPointArray.get(i))) {
                    tmpMinPoint = intercectPointArray.get(i);
                }
            }
            return tmpMinPoint;
        }
        return null;
    }

    /**
     * This method gets a point and checks if its contained in a line.
     * <p>
     * The method is divided into two cases:
     * case 1 - the line is vertical.
     * case 2 - the line is horizontal.
     * In each case the method checks if the X/Y values of the point are equal to this line,
     * and if point is really on the line itself by using a distance mathematical calculation.
     * </p>
     *
     * @param p
     * @return true if the point is contained in the line, false otherwise.
     */
    public boolean contains(Point p) {
        double distance1 = this.start.distance(p);
        double distance2 = this.end.distance(p);

        //case 1: vertical line
        if (Point.doubleEquals(this.start.getX(), this.end.getX())) {
            if (Point.doubleEquals(this.start.getX(), p.getX())
                    && Point.doubleEquals(distance1 + distance2, this.length())) {
                return true;
            }
        } else if (Point.doubleEquals(this.start.getY(), this.end.getY())) { //case 2: horizontal line
            if (Point.doubleEquals(this.start.getY(), p.getY())
                    && Point.doubleEquals(distance1 + distance2, this.length())) {
                return true;
            }
        }
        return false;
    }
}
