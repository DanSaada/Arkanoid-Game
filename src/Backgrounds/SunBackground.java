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
 * A background with a sun.
 */
public class SunBackground extends Background {
    public static final int GOLD_COLOR_R = 255;
    public static final int GOLD_COLOR_G = 240;
    public static final int GOLD_COLOR_B = 153;
    public static final int YELLOW_COLOR_R = 228;
    public static final int YELLOW_COLOR_G = 238;
    public static final int YELLOW_COLOR_B = 37;
    public static final int SUN_CENTER = 135;
    public static final int BIG_RADIUS = 55;
    public static final int MEDIUM_RADIUS = 45;
    public static final int SMALL_RADIUS = 35;
    public static final int BLOCKS_HEIGHT = 250;
    public static final int X_AXIS_START = 0;
    public static final int X_AXIS_END = 680;
    public static final int LINES_GAP = 10;

    /**
     * Sets the background's properties.
     *
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public SunBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        this.drawSun(surface);
    }

    /**
     * Draws sun on the DrawSurface.
     * <p>
     *     This method uses auxiliary methods from DrawSurface class.
     *     The method works by dividing the steps by the color in which it is currently using.
     *     First, the method paints the outer circle of the sun in gold, then the method draws
     *     the rays coming out of the sun using a loop that runs on the x-axis of the edges of
     *     the sun's rays, so that in each iteration the x value of the lower edge of the ray
     *     increases, which creates an image of smeared rays emanating from the sun.
     *     The method changes the hue to a darker yellow and then prints the medium circle
     *     inside the sun.
     *     Eventually the method changes color to yellow and prints the small circle in the sun.
     * </p>
     * @param surface - the surface to be drawn on.
     */
    private void drawSun(DrawSurface surface) {
        //draws the big circle.
        surface.setColor(new Color(GOLD_COLOR_R, GOLD_COLOR_G, GOLD_COLOR_B));
        surface.fillCircle(SUN_CENTER, SUN_CENTER, BIG_RADIUS);
        int x = X_AXIS_START;
        //Draws the sun's rays.
        while (x <= X_AXIS_END) {
            surface.drawLine(SUN_CENTER, SUN_CENTER, x, BLOCKS_HEIGHT);
            x = x + LINES_GAP;
        }
        //draws the medium and small circles.
        surface.setColor(new Color(YELLOW_COLOR_R, YELLOW_COLOR_G, YELLOW_COLOR_B));
        surface.fillCircle(SUN_CENTER, SUN_CENTER, MEDIUM_RADIUS);
        surface.setColor(Color.YELLOW);
        surface.fillCircle(SUN_CENTER, SUN_CENTER, SMALL_RADIUS);
    }
}
