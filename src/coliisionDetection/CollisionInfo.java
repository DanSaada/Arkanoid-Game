package coliisionDetection;

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import geometryPrimitives.Point;
import interfaces.Collidable;

/**
 * This class holds all collisionInfo-related methods.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable figure;

    // constructor

    /**
     * This constructor gets a point, and a figure and sets the information about them.
     *
     * @param collisionPoint - the point of the collision.
     * @param figure - the object who is being hit.
     */
    public CollisionInfo(Point collisionPoint, Collidable figure) {
        setCollionPoint(collisionPoint);
        setCollidableFigure(figure);
    }

    /**
     * This method is a "get method".
     *
     * @return the collision point.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * This method gets a point and sets it as the collision point.
     *
     * @param collisionPoint - the point of the collision.
     */
    public void setCollionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * This method is a "get method".
     *
     * @return figure of a collidable.
     */
    public Collidable getCollidableFigure() {
        return this.figure;
    }

    /**
     * This method gets a collidable and sets it as the figure.
     *
     * @param figure - the object who is being hit.
     */
    public void setCollidableFigure(Collidable figure) {
        this.figure = figure;
    }

}
