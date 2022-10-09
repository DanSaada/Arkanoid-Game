package Backgrounds;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import geometryPrimitives.Rectangle;
import interfaces.Sprite;
import theGame.GameLevel;
import java.awt.Color;

/**
 * Arkanoid.Game.Background is the background of the game.
 */
public class Background implements Sprite {
    private Rectangle rectangle;
    private Color color;

    //constructor
    /**
     * Sets the background.
     * @param rectangle - rectangle of the background.
     * @param color - color of the background.
     */
    public Background(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface, this.rectangle, this.color);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Adds current background to the game.
     * @param g - the game to add the background to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
