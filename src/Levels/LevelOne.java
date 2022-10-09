package Levels;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import Backgrounds.TargetBackground;
import coliisionDetection.Velocity;
import diffSprites.Block;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * First level of the game.
 */
public class LevelOne implements LevelInformation {
    public static final int NUM_OF_BALLS = 1;
    public static final int NUM_OF_BLOCKS = 1;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int PADDLE_SPEED = 3;
    public static final int PADDLE_WIDTH = 75;
    public static final int VELOCITY_ANGLE = 1;
    public static final int VELOCITY_SPEED = 5;
    public static final int BLOCK_WIDTH = 25;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCK_X = 395;
    public static final int BLOCK_Y = 145;
    private static final String LEVEL_NAME = "Direct Hit";


    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(VELOCITY_ANGLE, VELOCITY_SPEED));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return new String(LEVEL_NAME);
    }

    @Override
    public Sprite getBackground() {
        //In this background there is a target marking around a single block.
        return new TargetBackground(new Rectangle(new Point(0, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(BLOCK_X, BLOCK_Y), BLOCK_WIDTH,
                BLOCK_HEIGHT), Color.RED));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
