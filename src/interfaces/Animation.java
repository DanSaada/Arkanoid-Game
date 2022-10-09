package interfaces;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;

/**
 * Animation is in charge of doing an animation frame in the game.
 */
public interface Animation {

    /**
     * Draws the relevant animation on the received DrawSurface.
     * @param d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Returns a true/false according to the required situation:
     * true - stop the animation.
     * false - continue.
     * @return true if the animation should stop running and false otherwise.
     */
    boolean shouldStop();
}
