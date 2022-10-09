package Animations;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * Print to the user that the game has stop and providing a key to continue.
 */
public class PauseScreen implements Animation {
    private static final String PAUSED_TEXT = "paused -- press space to continue";

    private KeyboardSensor keyboard;
    private boolean stop;

    // constructor

    /**
     * @param k - the key that stops the animation.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, PAUSED_TEXT, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
