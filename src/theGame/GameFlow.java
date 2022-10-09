package theGame;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import Animations.GameOver;
import Animations.KeyPressStoppableAnimation;
import Animations.Win;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;

import java.util.List;

/**
 * GameFlow hold the wanted levels and runs them according to the game rules.
 */
public class GameFlow {
    public static final int SCORE_START = 0;
    public static final int LIVES_START = 3;
    public static final int LOST_A_LIFE = 1;
    private static final String EXIT = "space";

    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private Counter lives;

    //constructor

    /**
     * Set the fields according to the received game.
     *
     * @param g  - gui of the game.
     * @param ar - AnimationRunner of the game.
     * @param ks - KeyboardSensor of the game.
     */
    public GameFlow(GUI g, AnimationRunner ar, KeyboardSensor ks) {
        this.gui = g;
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        //initialize the game score.
        this.score = new Counter(SCORE_START);
        //initialize the game lives.
        this.lives = new Counter(LIVES_START);
    }

    /**
     * Set the fields according to the received game.
     *
     * @param game - holds the relevant fields of the game.
     */
    public GameFlow(Game game) {
        this(game.getGui(), game.getAnimationRunner(), game.getKeyboardSensor());
    }

    /**
     * Runs the game in the order of levels in the received list.
     *
     * @param levels - list of LevelInformation.
     */
    public void runLevels(List<LevelInformation> levels) {

        //For every level in the list, initialize and run it.
        for (LevelInformation levelInfo : levels) {
            //Create a game level according to the current level in the list.
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score, this.lives);

            //Initialize the level.
            level.initialize();

            //while player has lives or blocks, run the current level.
            while (this.lives.getValue() != 0 && level.getRemainingBlocks().getValue() != 0) {
                level.run();
                //no more balls.
                if (level.getRemainingBalls().getValue() == 0) {
                    //decrease life and reset balls and paddle of current level.
                    this.lives.decrease(LOST_A_LIFE);
                    level.resetRemainingBalls(levelInfo.numberOfBalls());
                    level.getPaddle().removeFromGame(level);
                }
            }
            //in case there are no lives - game over message.
            if (this.lives.getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(new GameOver(score),
                        this.keyboardSensor, EXIT));
                this.gui.close();
                break;
            }
        }
        //the user won message.
        this.animationRunner.run(new KeyPressStoppableAnimation(new Win(score),
                this.keyboardSensor, EXIT));
        //close the game.
        this.gui.close();
    }
}

