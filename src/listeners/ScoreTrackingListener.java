package listeners;

/**
 * @author Dan Saada
 * @version ass5
 * @since 2022/05/12
 */

import diffSprites.Ball;
import diffSprites.Block;
import interfaces.HitListener;
import theGame.Counter;

/**
 * This class holds all score tracking-related methods.
 * a listeners.ScoreTrackingListener is in charge of updating the current score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private static final int POINTS = 5;

    /**
     * This constructor gets a counter, and sets a score listener.
     *
     * @param scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        setCurrentScore(scoreCounter);
    }

    /**
     * This method gets a counter and sets the score listener's counter.
     *
     * @param currentScore
     */
    public void setCurrentScore(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * This method is a "get method".
     *
     * @return the current score of this score listener.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * This method gets a block that has been hit and the ball that hit it, and increases the current
     * score of the game.
     * <p>
     * Each hit earns the player 5 points.
     * </p>
     *
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(POINTS);
    }
}