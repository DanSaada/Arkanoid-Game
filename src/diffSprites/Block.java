package diffSprites;

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import coliisionDetection.Velocity;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import interfaces.HitNotifier;
import interfaces.Comparator;
import interfaces.HitListener;
import theGame.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all block-related methods.
 *
 */
public class Block implements Collidable, Sprite, HitNotifier, Comparator<HitListener> {
    private List<HitListener> hitListeners;
    private Rectangle shape;
    private Color color;

    // constructor
    /**
     * This constructor gets a shape, and a color and sets a block.
     *
     * @param shape
     * @param color
     */
    public Block(Rectangle shape, Color color) {
        setShape(shape);
        setColor(color);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method is a "get method".
     *
     * @return the shape of this block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * This method gets a rectangle and sets the block with its shape.
     *
     * @param shape
     */
    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    /**
     * This method is a "get method".
     *
     * @return the color of this block
     */
    public Color getColor() {
        if (color == null) {
            return Color.GRAY;
        }
        return this.color;
    }

    /**
     * This method gets a color and sets it as the block's color.
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method gets a point and a velocity and returns a new velocity.
     * <p>
     * First, the method checks if there has been a hit and where, and changes the Dx & Dy values accordingly.
     * Finally, the method returns the new velocity (might hasn't changed).
     * </p>
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return new velocity value
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the ball is about to hit the block from the sides.
        if ((collisionPoint.getX() == getCollisionRectangle().getUpperLeft().getX()
                + getCollisionRectangle().getWidth())
                || (collisionPoint.getX()) == getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        //if the ball is about to hit the block from above or below.
        if ((collisionPoint.getY() == getCollisionRectangle().getUpperLeft().getY())
                || (collisionPoint.getY() == getCollisionRectangle().getUpperLeft().getY()
                + getCollisionRectangle().getHeight())) {
            currentVelocity.setDy(currentVelocity.getDy() * (-1));
        }
        //notify hit.
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * This method draws a block on a given DrawSurface.
     *
     * @param d
     */
    @Override
    public void drawOn(biuoop.DrawSurface d) {
        d.setColor(getColor());
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        //cover lines for blocks
        d.setColor(Color.BLACK);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());

    }

    @Override
    /**
     * This method currently do nothing.
     *
     */
    public void timePassed() {
    }

    /**
     * This method gets a game and adds a block to it by adding it to the Sprites array
     * and by making it a Collidable.
     *
     * @param g
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * This method gets a game and removes a block from it by removing it from Sprites array
     * and from being a collidable.
     *
     * @param game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        if (hitListeners == null) {
            hitListeners = new ArrayList<>();
        }
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        List<HitListener> tempListeners = new ArrayList<HitListener>(this.hitListeners);
        for (int i = 0; i < tempListeners.size(); i++) {
            if (compare(tempListeners.get(i), hl)) {
                this.hitListeners.remove(hl);
            }
        }
    }

    /**
     * This method will be called whenever a hit() occurs, and will notify all of the
     * registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Boolean compare(HitListener o1, HitListener o2) {
        return (o1.equals(o2));
    }
}
