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
 * Background with Clouds.
 */
public class RainCloudsBackground extends Background {
    public static final int LINES_GAP = 7;
    public static final int LINES_SLOPE = 25;
    public static final int LINES_NUM = 10;
    public static final int CLOUD1_CENTER_HEIGHT = 380;
    public static final int CLOUD1_CENTER_X = 110;
    public static final int CLOUD2_CENTER_HEIGHT = 500;
    public static final int CLOUD2_CENTER_X = 610;
    public static final int FRAME_HEIGHT = 600;
    public static final int SMALL_CIRCLE = 21;
    public static final int MEDIUM_CIRCLE = 24;
    public static final int BIG_CIRCLE = 27;
    public static final int HUGE_CIRCLE = 30;
    public static final int WHITE_R = 215;
    public static final int WHITE_G = 215;
    public static final int WHITE_B = 222;
    public static final int GREY_R = 191;
    public static final int GREY_G = 191;
    public static final int GREY_B = 198;
    public static final int DARK_GREY_R = 152;
    public static final int DARK_GREY_G = 152;
    public static final int DARK_GREY_B = 158;
    public static final int CIRCLE1_CLOUD1_X = 100;
    public static final int CIRCLE2_CLOUD1_X = 120;
    public static final int CIRCLE3_CLOUD1_X = 140;
    public static final int CIRCLE4_CLOUD1_X = 160;
    public static final int CIRCLE5_CLOUD1_X = 180;
    public static final int CIRCLE1_CLOUD1_Y = 380;
    public static final int CIRCLE2_CLOUD1_Y = 405;
    public static final int CIRCLE3_CLOUD1_Y = 370;
    public static final int CIRCLE4_CLOUD1_Y = 400;
    public static final int CIRCLE5_CLOUD1_Y = 385;
    public static final int CIRCLE1_CLOUD2_X = 600;
    public static final int CIRCLE2_CLOUD2_X = 620;
    public static final int CIRCLE3_CLOUD2_X = 630;
    public static final int CIRCLE4_CLOUD2_X = 655;
    public static final int CIRCLE5_CLOUD2_X = 670;
    public static final int CIRCLE1_CLOUD2_Y = 500;
    public static final int CIRCLE2_CLOUD2_Y = 535;
    public static final int CIRCLE3_CLOUD2_Y = 507;
    public static final int CIRCLE4_CLOUD2_Y = 521;
    public static final int CIRCLE5_CLOUD2_Y = 512;

    /**
     * Sets the background's properties.
     *
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public RainCloudsBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        this.drawClouds(surface);
    }

    /**
     * Draws 2 clouds on the DrawSurface.
     * @param surface - the surface to be drawn on.
     */
    private void drawClouds(DrawSurface surface) {
        //draws the rain.
        surface.setColor(Color.WHITE);
        for (int i = 0, x = CLOUD1_CENTER_X, j = CLOUD2_CENTER_X; i < LINES_NUM;
             i++, x = x + LINES_GAP, j = j + LINES_GAP) {
            surface.drawLine(x, CLOUD1_CENTER_HEIGHT, x - LINES_SLOPE, FRAME_HEIGHT);
            surface.drawLine(j, CLOUD2_CENTER_HEIGHT, j - LINES_SLOPE, FRAME_HEIGHT);
        }
        //draws the brighter circle clouds part.
        surface.setColor(new Color(WHITE_R, WHITE_G, WHITE_B));
        surface.fillCircle(CIRCLE1_CLOUD1_X, CIRCLE1_CLOUD1_Y, BIG_CIRCLE);
        surface.fillCircle(CIRCLE2_CLOUD1_X, CIRCLE2_CLOUD1_Y, MEDIUM_CIRCLE);
        surface.fillCircle(CIRCLE1_CLOUD2_X, CIRCLE1_CLOUD2_Y, MEDIUM_CIRCLE);
        surface.fillCircle(CIRCLE2_CLOUD2_X, CIRCLE2_CLOUD2_Y, BIG_CIRCLE);
        //draws the middle grey circle clouds part.
        surface.setColor(new Color(GREY_R, GREY_G, GREY_B));
        surface.fillCircle(CIRCLE3_CLOUD1_X, CIRCLE3_CLOUD1_Y, HUGE_CIRCLE);
        surface.fillCircle(CIRCLE3_CLOUD2_X, CIRCLE3_CLOUD2_Y, BIG_CIRCLE);
        //draws the darker circle clouds part.
        surface.setColor(new Color(DARK_GREY_R, DARK_GREY_G, DARK_GREY_B));
        surface.fillCircle(CIRCLE4_CLOUD1_X, CIRCLE4_CLOUD1_Y, SMALL_CIRCLE);
        surface.fillCircle(CIRCLE5_CLOUD1_X, CIRCLE5_CLOUD1_Y, HUGE_CIRCLE);
        surface.fillCircle(CIRCLE4_CLOUD2_X, CIRCLE4_CLOUD2_Y, MEDIUM_CIRCLE);
        surface.fillCircle(CIRCLE5_CLOUD2_X, CIRCLE5_CLOUD2_Y, BIG_CIRCLE);
    }
}
