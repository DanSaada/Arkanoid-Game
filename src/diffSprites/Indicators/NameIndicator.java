package diffSprites.Indicators;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.DrawSurface;
import interfaces.Sprite;
import theGame.GameLevel;
import java.awt.Color;

/**
 * Draws the current level name on the GUI.
 */
public class NameIndicator implements Sprite {
    private static final String LEVEL_NAME = "Level Name: ";

    private String name;

    //constructor
    /**
     * @param n - the level name.
     */
    public NameIndicator(String n) {
        this.name = n;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(525, 15, LEVEL_NAME + this.name, 15);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add current Arkanoid.Game.ScoreIndicator to the received game.
     * @param g - the game to add the ScoreIndicator to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
