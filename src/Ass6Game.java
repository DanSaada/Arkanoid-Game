// 208968560 Dan Saada

import Levels.LevelInformationFactory;
import theGame.Game;
import theGame.GameFlow;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */
public class Ass6Game {

    /**
     * This is the main method which creates a game flow and runs the levels.
     *
     * @param args
     */
    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow(new Game());
        gameFlow.runLevels(new LevelInformationFactory().createGameLevels(args));
    }
}

