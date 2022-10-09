package interfaces;

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import coliisionDetection.Velocity;
import diffSprites.Ball;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;

/**
 * This interface requires other classes that declare that they are implementing it to implement
 * the methods underlying it.
 * meaning they should have the "getCollisionRectangle", the "hit" and the "drawOn" methods.
 *
 */
public interface Collidable {

    /**
     * This method return the "collision shape" of the object
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     *
     * @return geometryPrimitives.Rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * This method checks if a hit is about to occur and return the new velocity of the moving object.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     *     The method notify the object that we collided with it at collisionPoint with
     *     a given velocity.
     * </p>
     *
     * @param hitter
     * @param collisionPoint
     * @param currentVelocity
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * This method draws an object on a given DrawSurface.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     *
     * @param d
     */
    void drawOn(biuoop.DrawSurface d);
}
