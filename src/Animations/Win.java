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
 * Print to the user that he won the game and it's final score.
 */
public class Win implements Animation {
    public static final int TEXT_X = 10;
    public static final int TEXT_SIZE = 32;
    private static final String WIN_TEXT = "You Win! Your score is ";

    private Counter score;

    // constructor

    /**
     * @param s - the game current score.
     */
    public Win(Counter s) {
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, d.getHeight() / 2, WIN_TEXT + this.score.getValue(), TEXT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
