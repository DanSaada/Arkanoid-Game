package Levels;

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import Backgrounds.BuildingBackground;
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
 * Third level of the game.
 */
public class LevelThree implements LevelInformation {
    public static final int NUM_OF_BALLS = 2;
    public static final int NUM_OF_BLOCKS = 40;
    private static final double VERTICAL_BORDER_WIDTH = 20;
    private static final double DISTANCE_FROM_TOP = 117;
    private static final double BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 25;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int PADDLE_SPEED = 6;
    public static final int PADDLE_WIDTH = 75;
    public static final int MAX_BLOCKS_IN_ROW = 10;
    public static final int BALLS_SPEED = 6;
    public static final int BALL1_ANGLE = 50;
    public static final int BALL2_ANGLE = 310;
    private static final String LEVEL_NAME = "Green 3";

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(BALL1_ANGLE, BALLS_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(BALL2_ANGLE, BALLS_SPEED));
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
        //In this background there is a building with an antenna pillar.
        return new BuildingBackground(new Rectangle(new Point(0, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), new Color(27, 71, 3));
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<>();
        //creating an array to iterate on (equals to num of rows).
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < MAX_BLOCKS_IN_ROW - i; j++) {
                blockList.add(new Block(new Rectangle(
                        new Point(FRAME_WIDTH - VERTICAL_BORDER_WIDTH - (j + 1) * BLOCK_WIDTH,
                                DISTANCE_FROM_TOP + i * BLOCK_HEIGHT),
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
