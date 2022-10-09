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
import theGame.GameLevel;

/**
 * This class holds all ball remover-related methods.
 * a listeners.BallRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private Counter remainingBalls;
    private GameLevel game;

    // constructor
    /**
     * This constructor gets a game, and a counter and sets them.
     *
     * @param game
     * @param remainingBalls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        setGame(game);
        setRemainingBalls(remainingBalls);
    }

    /**
     * This method is a "get method".
     *
     * @return the game of this ball remover
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * This method gets a game and sets the ball remover game.
     *
     * @param game
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * This method is a "get method".
     *
     * @return the remaining balls of this ball remover.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * This method gets a counter and sets the ball remover remaining balls.
     *
     * @param remainingBalls
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method gets a block that has been hit and the ball that hit it, and removes that block from the game.
     * <p>
     * After removing the block from the game the method decrease the number of the remaining blocks by 1.
     * </p>
     *
     * @param beingHit
     * @param hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
