package Animations;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import interfaces.Animation;
import theGame.Counter;

/**
 * Print to the user that the game is over and it's current score.
 */
public class GameOver implements Animation {
    public static final int TEXT_X = 10;
    public static final int TEXT_SIZE = 32;
    private static final String LOST_TEXT = "Game Over. Your score is ";

    private Counter score;

    // constructor
    /**
     * @param score - the game current score.
     */
    public GameOver(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, d.getHeight() / 2, LOST_TEXT + this.score.getValue(), TEXT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
