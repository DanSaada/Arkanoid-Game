package Backgrounds;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import geometryPrimitives.Rectangle;
import java.awt.Color;

/**
 * Background with target.
 */
public class TargetBackground extends Background {
    public static final int NUM_OF_CIRCLES = 3;
    public static final int CIRCLE_CENTER_X = 407;
    public static final int CIRCLE_CENTER_Y = 157;
    public static final int SMALL_RADIUS = 50;
    public static final int RADIUS_GAP = 25;
    public static final int LEFT_LINE_X1 = 425;
    public static final int LEFT_LINE_X2 = 545;
    public static final int RIGHT_LINE_X1 = 270;
    public static final int RIGHT_LINE_X2 = 390;
    public static final int LEFT_RIGHT_Y = 156;
    public static final int UPPER_LINE_Y1 = 175;
    public static final int UPPER_LINE_Y2 = 295;
    public static final int DOWN_LINE_Y1 = 20;
    public static final int DOWN_LINE_Y2 = 140;
    public static final int UPPER_DOWN_X = 407;

    /**
     * Sets the background's properties.
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public TargetBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        this.drawTarget(surface);
    }

    /**
     * Draws a target on the DrawSurface.
     * <p>
     *     This method uses auxiliary methods from DrawSurface class.
     *     First, the method creates 3 circles in a loop using the "drawCircle" method, so that
     *     in each loop the radius of the circles changes, a functionality which creates an image
     *     of 3 circles inside each other with equal gap between them.
     *     Then, the method adds the vertical lines coming out of the red block by using the
     *     "drawLine" method.
     * </p>
     * @param surface - the surface to be drawn on.
     */
    private void drawTarget(DrawSurface surface) {
        surface.setColor(Color.BLUE);
        //Draws the target's circles.
        for (int i = 0, radius = SMALL_RADIUS; i < NUM_OF_CIRCLES; i++, radius = radius + RADIUS_GAP) {
            surface.drawCircle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, radius);
        }
        //Draws the vertical lines coming out of the red block.
        surface.drawLine(LEFT_LINE_X1, LEFT_RIGHT_Y, LEFT_LINE_X2, LEFT_RIGHT_Y);
        surface.drawLine(RIGHT_LINE_X1, LEFT_RIGHT_Y, RIGHT_LINE_X2, LEFT_RIGHT_Y);
        surface.drawLine(UPPER_DOWN_X, UPPER_LINE_Y1, UPPER_DOWN_X, UPPER_LINE_Y2);
        surface.drawLine(UPPER_DOWN_X, DOWN_LINE_Y1, UPPER_DOWN_X, DOWN_LINE_Y2);
    }
}
