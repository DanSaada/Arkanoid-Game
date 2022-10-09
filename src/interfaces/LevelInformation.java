package interfaces;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import coliisionDetection.Velocity;
import diffSprites.Block;
import java.util.List;

/**
 * LevelInformation specifies the information required to create a level.
 */
public interface LevelInformation {

    /**
     * Returns the number of balls in the level.
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * Returns a list containing the velocity of each ball.
     * @return a list containing the velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the paddle speed.
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the paddle width.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * Returns a string with the level name.
     * @return string with the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * Returns the list of blocks that appears in the level.
     * @return list of blocks that appears in the level.
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed from the game.
     * @return the number of blocks that should be removed from the game.
     */
    int numberOfBlocksToRemove();
}