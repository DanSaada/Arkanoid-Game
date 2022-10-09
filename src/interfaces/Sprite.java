package interfaces;

// 208968560 Dan Saada
/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import biuoop.DrawSurface;
import theGame.GameLevel;

/**
 * This interface requires other classes that declare that they are implementing it to implement
 * the methods underlying it.
 * meaning they should have the "drawOn" and the "timePassed" methods.
 */
public interface Sprite {
    /**
     * This method draw the sprite to the screen on a given surface.
     * <p>
     * The method will be implemented in any class that uses this interface.
     * </p>
     *
     * @param d
     */
    void drawOn(DrawSurface d);

    /**
     * This method notify the sprite that time has passed.
     * <p>
     * The method will be implemented in any class that uses this interface.
     * </p>
     */
    void timePassed();

    /**
     * Add the sprite to the game.
     * @param gameLevel
     */
    void addToGame(GameLevel gameLevel);
}