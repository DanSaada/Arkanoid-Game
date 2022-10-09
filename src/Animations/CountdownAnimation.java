package Animations;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import collection.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * This class holds all the count down animation-related methods.
 * a CountdownAnimation is an on-screen countdown from 3 to 1, which will
 * show up at the beginning of each turn.
 */
public class CountdownAnimation implements Animation {
    public static final int TEXT_X = 392;
    public static final int TEXT_SIZE = 50;
    public static final double MILLISECOND = 1.0 / 60;
    public static final int DEFAULT_NUM_OF_SECONDS = 2;
    public static final int DEFAULT_COUNT_FROM = 3;

    private SpriteCollection gameScreen;
    private boolean stop;
    private double numOfSeconds;
    private double currentSeconds;
    private int countFrom;
    private int currentCount;

    // constructor

    /**
     * this constructor gets the number of seconds the animation should run and the
     * starting count, and sets the current time of each number appearance.
     * @param numOfSeconds - number of seconds the animation should run.
     * @param countFrom - the starting number of the count.
     * @param gameScreen - the game sprites to be show.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.currentSeconds = numOfSeconds / countFrom;
        this.currentCount = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * This constructor sets default values to numOfSeconds and countFrom.
     * @param gameScreen - the game sprites to be show.
     */
    public CountdownAnimation(SpriteCollection gameScreen) {
        this(DEFAULT_NUM_OF_SECONDS, DEFAULT_COUNT_FROM, gameScreen);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw the game screen sprites.
        this.gameScreen.drawAllOn(d);

        d.setColor(Color.ORANGE);
        //draws the current count on the game screen.
        d.drawText(TEXT_X, d.getHeight() / 2 + 80, String.valueOf(this.currentCount), TEXT_SIZE);

        this.currentSeconds = this.currentSeconds - MILLISECOND;
        //move to next number in the count.
        if (this.currentSeconds <= 0) {
            //decrease to the next number in the count.
            this.currentCount--;
            //reset current number's count.
            this.currentSeconds = this.numOfSeconds / this.countFrom;
        }
        //in case the count has reached 0, stop animation.
        if (this.currentCount == 0) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
