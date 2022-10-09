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
 * This class holds all block remover-related methods.
 * a listeners.BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private Ball[] balls;

    // constructor

    /**
     * This constructor gets a game, and a counter and sets them.
     *
     * @param game
     * @param remainingBlocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        setGame(game);
        setRemainingBlocks(remainingBlocks);
    }

    /**
     * This method is a "get method".
     *
     * @return the game of this block remover
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * This method gets a game and sets the block remover game.
     *
     * @param game
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * This method is a "get method".
     *
     * @return the remaining blocks of this block remover.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * This method gets a counter and sets the block remover remaining blocks.
     *
     * @param remainingBlocks
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is a "get method".
     *
     * @return the balls of this block remover.
     */
    public Ball[] getBalls() {
        return balls;
    }

    /**
     * This method gets an array of balls and sets it as the block remover balls array.
     *
     * @param balls
     */
    public void setBalls(Ball[] balls) {
        this.balls = balls;
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
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}