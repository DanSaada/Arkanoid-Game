package diffSprites;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import biuoop.DrawSurface;
import coliisionDetection.CollisionInfo;
import coliisionDetection.Velocity;
import collection.GameEnvironment;
import geometryPrimitives.Line;
import geometryPrimitives.Point;
import interfaces.Sprite;
import interfaces.Collidable;
import theGame.GameLevel;

import java.awt.Color;

/**
 * This class holds all ball-related methods.
 * In addition, the class implements the interfaces.Sprite's interface.
 */
public class Ball implements Sprite {
    private Point center;
    private double x;
    private double y;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int top;
    private int bottom;
    private int left;
    private int right;
    private GameEnvironment gameEnvironment;

    //constructors

    /**
     * This constructor gets a point, a radius and a color and sets a ball.
     *
     * @param center - the ball's center point.
     * @param r      - the ball's radius.
     * @param color  - the ball's color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        setX(center.getX());
        setY(center.getY());
        setRadius(r);
        setColor(color);
        setCenter(center);
        setGameEnvironment(new GameEnvironment());
    }

    /**
     * This constructor  gets a X & Y values, a radius, a color and a game environment a game  sets a ball.
     *
     * @param x               - the ball's center x.
     * @param y               - the ball's center y.
     * @param r               - the ball's radius.
     * @param color           - the ball's color.
     * @param gameEnvironment - the environment of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        setX(x);
        setY(y);
        setColor(color);
        setRadius(r);
        Point center = new Point(x, y);
        setCenter(center);
        setGameEnvironment(gameEnvironment);
    }

    /**
     * This method is a "get method".
     *
     * @return the x value of this center ball
     */
    public int getX() {
        return (int) this.x;
    }

    /**
     * This method gets a value and sets it as the x value of the center point of a ball.
     *
     * @param x - the ball's center x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This method is a "get method".
     *
     * @return the y value of this center ball
     */
    public int getY() {
        return (int) this.y;
    }

    /**
     * This method gets a value and sets it as the y value of the center point of a ball.
     *
     * @param y - the ball's center y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This method is a "get method".
     *
     * @return the radius of a ball.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * This method gets a radius and sets it as the radius of a ball.
     *
     * @param radius - the ball's radius.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * This method is a "get method".
     *
     * @return the color of a ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This method gets a color and sets it as the color of a ball.
     *
     * @param color - the ball's color.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * This method is a "get method".
     *
     * @return the center point of a ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * This method gets a point and sets it as the center point of a ball.
     *
     * @param center - the ball's center point.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * This method draws a ball on a given DrawSurface.
     *
     * @param surface - the surface to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        //Draw the ball's out side circle
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        //ball's color.
        surface.setColor(this.getColor());
        //ball size and position.
        surface.fillCircle(getX(), getY(), getRadius());
    }

    /**
     * This method moves the ball one step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * This method gets a velocity and sets it as the velocity of a ball.
     *
     * @param v - the ball's velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method gets 2 values and sets the velocity of a ball by using the given values and the constructor
     * of velocity.
     *
     * @param dx - the change on the x-axis.
     * @param dy - the change on the y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * This method is a "get method".
     *
     * @return the velocity  of a ball.
     */
    public Velocity getVelocity() {
        //if there is no velocity --> a default one is provided.
        if (this.velocity == null) {
            setVelocity(1, 1);
        }
        return this.velocity;
    }

    /**
     * This method is a "get method".
     *
     * @return the game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * This method gets a game environment and sets it as the game environment.
     *
     * @param gameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * This method checks whether a ball meets the conditions of the surface that limits it and moves it to its
     * next position accordingly.
     * <p>
     * the method uses "applyToPoint" method which is related to the coliisionDetection.Velocity class.
     * </p>
     */
    public void moveOneStep() {
        Point trajectoryEndPoint = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, trajectoryEndPoint);
        //saving the collision point and the figure in info
        CollisionInfo info = this.getGameEnvironment().getClosestCollision(trajectory);
        //there is a collision
        if (info != null) {
            Collidable obstacle = info.getCollidableFigure();
            Point collisionPoint = info.getCollisionPoint();
            double newXVal = collisionPoint.getX();
            double newYVal = collisionPoint.getY();
            double width = info.getCollidableFigure().getCollisionRectangle().getWidth();
            double height = info.getCollidableFigure().getCollisionRectangle().getHeight();
            //set new velocity.
            Velocity newVelocity = obstacle.hit(this, collisionPoint, this.getVelocity());
            //the ball hits the top
            if (newYVal == info.getCollidableFigure().getCollisionRectangle().getUpperLeft().getY()) {
                newYVal -= 1;
            }
            //the ball hits the bottom.
            if (newYVal == info.getCollidableFigure().getCollisionRectangle().getUpperLeft().getY() + height) {
                newYVal += 1;
            }
            //the ball hits from the left.
            if (newXVal == info.getCollidableFigure().getCollisionRectangle().getUpperLeft().getX()) {
                newXVal -= 1;
            }
            //the ball hits from the right.
            if (newXVal == info.getCollidableFigure().getCollisionRectangle().getUpperLeft().getX() + width) {
                newXVal += 1;
            }
            //move the ball
            this.center.setX(newXVal);
            this.center.setY(newYVal);
            this.velocity.setDx(newVelocity.getDx());
            this.velocity.setDy(newVelocity.getDy());

        } else {
            //regular movement of the ball
            Point nextPoint = new Point(this.center.getX(), this.center.getY());
            nextPoint = this.getVelocity().applyToPoint(nextPoint);
            setX(nextPoint.getX());
            setY(nextPoint.getY());
            center.setX(nextPoint.getX());
            center.setY(nextPoint.getY());
        }
    }

    /**
     * This method gets a game and adds to it this ball by adding it to the Sprites array in the game class.
     *
     * @param game
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * This method gets a game and removes a ball from it by removing it from Sprites array in the game class.
     *
     * @param game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }


}