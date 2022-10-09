package theGame;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Game holds the game's gui, keyboard sensor, and animation runner.
 */
public class Game {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    private static final String GAMES_NAME = "arkanoid";

    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    //constructor
    /**
     * Create the games properties.
     * @see <a href=https://en.wikipedia.org/wiki/Graphical_user_int"erface></a> more info about GUI.
     */
    public Game() {
        this.gui = new GUI(GAMES_NAME, FRAME_WIDTH, FRAME_HEIGHT);
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(this.gui);
    }

    /**
     * This method is a "get method".
     *
     * @return the gui of the game.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * This method is a "get method".
     *
     * @return the KeyboardSensor of the game.
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }

    /**
     * This method is a "get method".
     *
     * @return the AnimationRunner of the game.
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }
}
