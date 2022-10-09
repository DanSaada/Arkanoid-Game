package geometryPrimitives;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all rectangle-related methods.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Line topRec;
    private Line bottomRec;
    private Line leftRec;
    private Line rightRec;
    private Line[] recPerimeter;

    //constructor

    /**
     * This constructor create a new rectangle with location and width/heigh.
     * @param upperLeft
     * @param width
     * @param height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        setWidth(width);
        setHeight(height);
        setUpperLeft(upperLeft);
        setSides();
        setRecArray();
    }

    private void setSides() {
        setTopRec(upperLeft, width);
        setBottomRec(upperLeft, width, height);
        setLeftRec(upperLeft, height);
        setRightRec(upperLeft, width, height);
    }

    /**
     * This method gets a line and returns a list of possible intersection point with that line.
     * <p>
     * First, the method creates an array which contains the perimeter lines of the rectangle.
     * Then, the method creates a dynamic array who is being filled up with optional intersection points.
     * the intersection point are provided from the "intersectionWith" method.
     * </p>
     *
     * @param line
     * @return a list filled with optional intersection points (possibly empty).
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lines = this.recPerimeter;
        List<Point> intersectPointArray = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {

            if (lines[i].intersectionWith(line) != null) {
                intersectPointArray.add(lines[i].intersectionWith(line));
            }
        }
        return intersectPointArray;
    }

    /**
     * This method is a "get method".
     *
     * @return the width value of this rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This method gets a value and sets it as the width value of a rectangle.
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * This method is a "get method".
     *
     * @return the height value of this rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This method gets a value and sets it as the height value of a rectangle.
     *
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * This method is a "get method".
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This method gets a point and sets it as the upper left point of a rectangle.
     *
     * @param upperLeft
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * This method is a "get method".
     *
     * @return the top line of the rectangle
     */
    public Line getTopRec() {
        return this.topRec;
    }

    /**
     * This method gets a point and a width and sets the top line of a rectangle.
     *
     * @param upperLeft
     * @param width
     */
    public void setTopRec(Point upperLeft, double width) {
        this.topRec = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * This method is a "get method".
     *
     * @return the bottom line of the rectangle
     */
    public Line getBottomRec() {
        return this.bottomRec;
    }

    /**
     * This method gets a point, width and height and sets the bottom line of a rectangle.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public void setBottomRec(Point upperLeft, double width, double height) {
        this.bottomRec = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * This method is a "get method".
     *
     * @return the left line of the rectangle
     */
    public Line getLeftRec() {
        return this.leftRec;
    }

    /**
     * This method gets a point and a height and sets the left line of a rectangle.
     *
     * @param upperLeft
     * @param height
     */
    public void setLeftRec(Point upperLeft, double height) {
        this.leftRec = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * This method is a "get method".
     *
     * @return the right line of the rectangle
     */
    public Line getRightRec() {
        return this.rightRec;
    }

    /**
     * This method gets a point, width and height and sets the right line of a rectangle.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public void setRightRec(Point upperLeft, double width, double height) {
        this.rightRec = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * This method is a "get method".
     *
     * @return an array containing the perimeter lines of a rectangle
     */
    public Line[] getRecArray() {
        return this.recPerimeter;
    }

    /**
     * This method fills an array with the perimeter's lines of a rectangle.
     * <p>
     * the method uses auxiliary methods for getting the lines themselves.
     * </p>
     */
    private void setRecArray() {
        this.recPerimeter = new Line[4];
        recPerimeter[0] = getTopRec();
        recPerimeter[1] = getBottomRec();
        recPerimeter[2] = getLeftRec();
        recPerimeter[3] = getRightRec();

    }

    /**
     * Draw the rectangle on received DrawSurface in the received color.
     * @param surface - the DrawSurface which we want to draw on.
     * @param rectangle - the rectangle which we want to draw.
     * @param color - the color of the rectangle.
     */
    public void drawOn(DrawSurface surface, Rectangle rectangle, Color color) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) (rectangle.getUpperLeft().getY() - rectangle.getHeight()),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) (rectangle.getUpperLeft().getY() - rectangle.getHeight()),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }
}