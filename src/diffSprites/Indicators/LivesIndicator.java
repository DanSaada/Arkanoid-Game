package diffSprites.Indicators;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import interfaces.Sprite;
import theGame.Counter;
import theGame.GameLevel;
import java.awt.Color;

/**
 * Arkanoid.Game.LivesIndicator draws the current amount of live in the game on the gui.
 */
public class LivesIndicator implements Sprite {
    private static final String LIVES = "Lives: ";

    private Counter lives;

    //constructor
    /**
     * @param l - the counter of the lives.
     */
    public LivesIndicator(Counter l) {
        this.lives = l;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(185, 15, LIVES + this.lives.getValue(), 15);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add current Arkanoid.Game.LivesIndicator to the received game.
     * @param g - the game to add the LivesIndicator to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
