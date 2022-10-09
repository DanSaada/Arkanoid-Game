package Backgrounds;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import geometryPrimitives.Rectangle;

import java.awt.Color;
import java.awt.Polygon;

/**
 * A background with building drawing and an emergency call lighting for the batman.
 */
public class BuildingBackground extends Background {
    public static final int BUILDING_X = 70;
    public static final int BUILDING_Y = 430;
    public static final int BUILDING_WIDTH = 100;
    public static final int BUILDING_HEIGHT = 170;
    public static final int WINDOW_X = 80;
    public static final int WINDOW_Y = 440;
    public static final int WINDOWS_WIDTH = 10;
    public static final int WINDOWS_HEIGHT = 30;
    public static final int WINDOWS_IN_ROW = 5;
    public static final int WINDOWS_ROWS = 5;
    public static final int ROWS_GAP = 5;
    public static final int WINDOWS_GAP = 8;
    public static final int ROOF_X = BUILDING_X + BUILDING_WIDTH / 2 - 14;
    public static final int ROOF_HEIGHT = 50;
    public static final int ROOF_Y = BUILDING_Y - ROOF_HEIGHT;
    public static final int ROOF_WIDTH = 30;
    public static final int PILLAR_HEIGHT = 50;
    public static final int PILLAR_WIDTH = 8;
    public static final int PILLAR_X = ROOF_X + ROOF_WIDTH / 2 - 4;
    public static final int BIG_RADIUS = 12;
    public static final int MEDIUM_RADIUS = 8;
    public static final int SMALL_RADIUS = 4;
    public static final int BULB_X = BUILDING_X + BUILDING_WIDTH / 2;
    public static final int BULB_Y = ROOF_Y - PILLAR_HEIGHT - BIG_RADIUS;
    public static final int BAT_BOTTOM = 230;
    public static final int BAT_TOP = 140;
    public static final int BAT_LEFT = 515;
    public static final int LINES_GAP = 10;
    public static final int BAT_BACKGROUND_X = 578;
    public static final int BAT_BACKGROUND_Y = 180;
    public static final int BAT_BACKGROUND_BIG_R = 62;
    public static final int BAT_BACKGROUND_MEDIUM_R = 60;
    public static final int BAT_BACKGROUND_SMALL_R = 55;


    /**
     * Sets the background's properties.
     *
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public BuildingBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //draws the background.
        super.drawOn(surface);
        //draws the building.
        this.drawBuilding(surface);
        //draws the light rays.
        this.drawLightRays(surface);
        //draws the bat.
        this.theBatman(surface);
    }

    /**
     * Draws the building drawing on the surface.
     *
     * @param surface - the surface to be drawn on.
     */
    private void drawBuilding(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        //draws the building's front.
        surface.fillRectangle(BUILDING_X, BUILDING_Y, BUILDING_WIDTH, BUILDING_HEIGHT);
        surface.setColor(Color.WHITE);
        int y = WINDOW_Y;
        //draws the windows of the building.
        for (int i = 0; i < WINDOWS_ROWS; i++) {
            int x = WINDOW_X;
            for (int j = 0; j < WINDOWS_IN_ROW; j++) {
                surface.fillRectangle(x, y, WINDOWS_WIDTH, WINDOWS_HEIGHT);
                x = x + WINDOWS_WIDTH + WINDOWS_GAP;
            }
            y = y + WINDOWS_HEIGHT + ROWS_GAP;
        }
        surface.setColor(Color.GRAY);
        //draws the building's roof.
        surface.fillRectangle(ROOF_X, ROOF_Y, ROOF_WIDTH, ROOF_HEIGHT);
        //draws the building's antenna pillar.
        surface.fillRectangle(PILLAR_X, ROOF_Y - PILLAR_HEIGHT, PILLAR_WIDTH, PILLAR_HEIGHT);
        surface.setColor(Color.ORANGE);
        //draws the antenna light bulb.
        surface.fillCircle(BULB_X, BULB_Y, BIG_RADIUS);
        surface.setColor(Color.RED);
        surface.fillCircle(BULB_X, BULB_Y, MEDIUM_RADIUS);
        surface.setColor(Color.WHITE);
        surface.fillCircle(BULB_X, BULB_Y, SMALL_RADIUS);
    }

    /**
     * Draws the batman drawing on the surface.
     *
     * @param surface - the surface to be drawn on.
     */
    private void theBatman(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        //creating a X & Y arrays for the batman's vertices.
        int[] x = {575, 575, 577, 581, 583, 583, 592, 598, 634, 618, 618,
                600, 585, 578, 571, 556, 538, 538, 522, 558, 564, 573};
        int[] y = {168, 160, 164, 164, 160, 168, 168, 160, 160, 170, 180,
                180, 187, 207, 187, 180, 180, 170, 160, 160, 168, 168};
        Polygon batman = new Polygon(x, y, 22);
        surface.drawPolygon(batman);
        surface.fillPolygon(batman);
    }

    /**
     * Draws the light rays drawing on the surface.
     *
     * @param surface - the surface to be drawn on.
     */
    private void drawLightRays(DrawSurface surface) {
        //draws the bat's background.
        surface.setColor(Color.BLACK);
        surface.fillCircle(BAT_BACKGROUND_X, BAT_BACKGROUND_Y, BAT_BACKGROUND_BIG_R);
        surface.setColor(Color.WHITE);
        surface.fillCircle(BAT_BACKGROUND_X, BAT_BACKGROUND_Y, BAT_BACKGROUND_MEDIUM_R);
        surface.setColor(Color.ORANGE);
        surface.fillCircle(BAT_BACKGROUND_X, BAT_BACKGROUND_Y, BAT_BACKGROUND_SMALL_R);
        int y = BAT_TOP;
        //Draws the bulb's rays.
        while (y <= BAT_BOTTOM) {
            surface.drawLine(BULB_X, BULB_Y, BAT_LEFT, y);
            y = y + LINES_GAP;
        }
    }
}