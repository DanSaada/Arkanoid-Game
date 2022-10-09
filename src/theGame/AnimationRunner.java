package theGame;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * Runs the game's animation on the gui.
 */
public class AnimationRunner {
    public static final int FRAMES_PER_SECOND = 60;
    public static final int TOTAL_FRAMES = 1000;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param g - the gui which the game will be showed on.
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = FRAMES_PER_SECOND;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the received animation on the gui of the game until it should be stopped.
     *
     * @param animation - the animation will be showed.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = TOTAL_FRAMES / this.framesPerSecond;

        //As long as the animation should run, show it.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            //Run current frame.
            animation.doOneFrame(d);

            //Show current frame.
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
