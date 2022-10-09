package diffSprites;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import coliisionDetection.Velocity;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import theGame.GameLevel;

import java.awt.Color;

/**
 * This class holds all paddle-related methods.
 * In addition, the class implements the interfaces.Sprite's and the interfaces.Collidable's interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private Color color;
    private int speed;
    private biuoop.KeyboardSensor keyboard;
    private static final double MOVEMENT = 5;
    private static final double NUM_OF_REGION = 5;
    private static final double LEFT_PADDLE_BOUND = 25;
    private static final double RIGHT_PADDLE_BOUND = 775;
    public static final int CHANGE_DIRECTION = -1;
    public static final int BALL_FREE = -1;
    private static final double ANGLE1 = 300;
    private static final double ANGLE2 = 330;
    private static final double ANGLE4 = 30;
    private static final double ANGLE5 = 60;

    //constructor

    /**
     * This constructor gets a rectangle, a color and a gui and sets a paddle.
     *
     * @param rectangle
     * @param color
     * @param gui
     * @param speed
     * @param keyboard
     * @param balls
     */
    public Paddle(Rectangle rectangle, Color color, GUI gui, int speed, KeyboardSensor keyboard) {
        setCollisionRectangle(rectangle);
        setColor(color);
        this.keyboard = keyboard;
        setSpeed(speed);
    }

    /**
     * This method moves the paddle to the left.
     */
    public void moveLeft() {
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        //condition for preventing the paddle to cross the left border
        if (x >= LEFT_PADDLE_BOUND) {
            //moving the paddle.
            this.rectangle = new Rectangle(new Point(x - MOVEMENT, y),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * This method moves the paddle to the right.
     */
    public void moveRight() {
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        //condition for preventing the paddle to cross the right border
        if (x + this.rectangle.getWidth() <= RIGHT_PADDLE_BOUND) {
            //moving the paddle.
            this.rectangle = new Rectangle(new Point(x + MOVEMENT, y),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * This method  checks if the "left" or "right" keys are pressed, and if so move it accordingly..
     */
    public void timePassed() {
        //user pressed left key
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        //user pressed right key
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * This method draws a paddle on a given DrawSurface.
     *
     * @param d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }

    /**
     * This method is a "get method".
     *
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This method gets a rectangle and sets it as collision rectangle.
     *
     * @param rect
     */
    public void setCollisionRectangle(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * This method is a "get method".
     *
     * @return the color of this paddle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This method gets a color and sets it as the paddle's color.
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method gets a gui and sets keyboard.
     *
     * @param gui
     */
    public void setKeyBoard(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * This method is a "get method".
     *
     * @return the keyboard.
     */
    public KeyboardSensor getKeyBoard() {
        return keyboard;
    }


    /**
     * This method is a "get method".
     *
     * @return the color of this paddle.
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * This method gets a color and sets it as the paddle's color.
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * This method gets a point and a velocity and returns a new velocity.
     * <p>
     * First, the method checks if there has been a hit and where, and changes the Dx & Dy values accordingly.
     * case 1: the ball is about to hit the paddle from the sides.
     * solution: changing the velocity Dx.
     * case 2: the ball is about to hit the paddle from above.
     * solution: dividing the length of the paddle into 5 equally-spaced regions, while the behavior of the
     * ball's bounce will depends on where it hits the paddle.
     * Finally, the method returns the new velocity (might hasn't changed).
     * </p>
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return new velocity value
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the ball is about to hit the paddle from the sides.
        if ((collisionPoint.getX() == getCollisionRectangle().getUpperLeft().getX()
                + getCollisionRectangle().getWidth())
                || (collisionPoint.getX()) == getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
            return currentVelocity;
        }
        //if the ball is about to hit the block from above.
        if ((collisionPoint.getY() == getCollisionRectangle().getUpperLeft().getY())) {
            double div = this.rectangle.getWidth() / NUM_OF_REGION;
            double region = (collisionPoint.getX() - getCollisionRectangle().getUpperLeft().getX()) % div;
            //the region are numbered from 0-4 from the left to the right.
            if (region == 0) {
                return Velocity.fromAngleAndSpeed(ANGLE1, currentVelocity.getSpeed());
            } else if (region == 1) {
                return Velocity.fromAngleAndSpeed(ANGLE2, currentVelocity.getSpeed());
            } else if (region == 3) {
                return Velocity.fromAngleAndSpeed(ANGLE4, currentVelocity.getSpeed());
            } else if (region == 4) {
                return Velocity.fromAngleAndSpeed(ANGLE5, currentVelocity.getSpeed());
            } else {
                currentVelocity.setDy(currentVelocity.getDy() * (-1));
                return currentVelocity;
            }
        }
        return currentVelocity;
    }

    /**
     * This method gets a game and adds to it this paddle by adding it to the Sprites & interfaces.Collidable array
     * in the game class.
     *
     * @param g
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * This method gets a game and removes this paddle from it by removing it from being
     * a Sprite & Collidable.
     *
     * @param g
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
}