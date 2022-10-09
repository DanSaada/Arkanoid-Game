package interfaces;

/**
 * @author Dan Saada
 * @version ass5
 * @since 2022/05/12
 */

import diffSprites.Ball;
import diffSprites.Block;

/**
 * This interface requires other classes that declare that they are implementing it to implement
 * the methods underlying it.
 * meaning they should have the "hitEvent" method.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit,
     * and notify the listener about a hit event.
     * <p>
     * The method will be implemented in any class that uses this interface.
     * The hitter parameter is the Ball that's doing the hitting.
     * </p>
     *
     * @param beingHit
     * @param hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
