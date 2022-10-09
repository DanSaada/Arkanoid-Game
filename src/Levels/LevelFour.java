package Levels;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import Backgrounds.RainCloudsBackground;
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
 * Fourth level of the game.
 */
public class LevelFour implements LevelInformation {
    public static final int NUM_OF_BALLS = 3;
    public static final int NUM_OF_BLOCKS = 95;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int PADDLE_SPEED = 7;
    public static final int PADDLE_WIDTH = 75;
    public static final int BACKGROUND_R = 0;
    public static final int BACKGROUND_G = 163;
    public static final int BACKGROUND_B = 227;
    public static final int BALL1_ANGLE = 351;
    public static final int BALL2_ANGLE = 1;
    public static final int BALL3_ANGLE = 11;
    public static final int BALLS_SPEED = 5;
    private static final double VERTICAL_BORDER_WIDTH = 20;
    private static final double HORIZONTAL_BORDER_HEIGHT = 20;
    private static final double DISTANCE_FROM_TOP = 100;
    private static final double BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 20;
    private static final double MAX_BLOCKS_IN_ROW = 15;
    private static final String LEVEL_NAME = "Final Four";

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(BALL1_ANGLE, BALLS_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(BALL2_ANGLE, BALLS_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(BALL3_ANGLE, BALLS_SPEED));
        return velocities;
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
        return new RainCloudsBackground(new Rectangle(new Point(0, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), new Color(BACKGROUND_R, BACKGROUND_G, BACKGROUND_B));
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN,
                Color.WHITE, Color.PINK, Color.CYAN};
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < MAX_BLOCKS_IN_ROW; j++) {
                blockList.add(new Block(new Rectangle(
                        new Point(FRAME_WIDTH - VERTICAL_BORDER_WIDTH - (j + 1) * BLOCK_WIDTH,
                                DISTANCE_FROM_TOP + i * HORIZONTAL_BORDER_HEIGHT),
                        BLOCK_WIDTH, BLOCK_HEIGHT), colors[i]));
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
            return NUM_OF_BLOCKS;
    }
}
