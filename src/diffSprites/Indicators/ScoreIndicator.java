package diffSprites.Indicators;

/**
 * @author Dan Saada
 * @version ass5
 * @since 2022/05/12
 */

import biuoop.DrawSurface;
import interfaces.Sprite;
import theGame.Counter;
import theGame.GameLevel;

import java.awt.Color;


/**
 * This class holds all score indicator-related methods.
 * In addition, the class implements the interfaces.Sprite interface.
 *
 */
public class ScoreIndicator implements Sprite {
    private static final String SCORE = "Score: ";

    private Counter counter;

    /**
     * This constructor gets a counter, and sets a score indicator.
     *
     * @param counter - the score counter.
     */
    public ScoreIndicator(Counter counter) {
        setCounter(counter);
    }

    /**
     * This method is a "get method".
     *
     * @return the counter of this score indicator
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * This method gets a counter and sets the score indicator counter.
     *
     * @param counter - the score counter.
     */
    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    /**
     * This method draws a score indicator in the shape of a rectangle on a given DrawSurface.
     *
     * @param d - the surface to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        String str = SCORE + counter.getValue();
        d.setColor(Color.BLACK);
        d.drawText(360, 15, str, 20);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }


}
