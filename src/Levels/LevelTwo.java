package Levels;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import Backgrounds.SunBackground;
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
 * Second level of the game.
 */
public class LevelTwo implements LevelInformation {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int NUM_OF_BLOCKS = 15;
    public static final double BLOCKS_WIDTH = 51;
    public static final int MIDDLE_BLOCK_WIDTH = 46;
    public static final int NUM_OF_BALLS = 10;
    public static final int PADDLE_SPEED = 2;
    public static final int PADDLE_WIDTH = 600;
    public static final int BALLS_SPEED = 5;
    public static final int FIRST_BALL_ANGLE_LEFT = 310;
    public static final int FIRST_BALL_ANGLE_RIGHT = 10;
    public static final double BLOCKS_HEIGHT = 20;
    public static final double BLOCKS_Y = 250;
    public static final int BALLS_ANGLE_GAP = 10;
    private static final String LEVEL_NAME = "Wide Easy";

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        //create left angle balls with a gap of 10 degrees angle between them.
        for (int i = 0, angle = FIRST_BALL_ANGLE_LEFT; i < NUM_OF_BALLS / 2; i++, angle = angle + BALLS_ANGLE_GAP) {
            list.add(Velocity.fromAngleAndSpeed(angle, BALLS_SPEED));
        }
        //create right angle balls with a gap of 10 degrees angle between them.
        for (int i = 0, angle = FIRST_BALL_ANGLE_RIGHT; i < NUM_OF_BALLS / 2; i++, angle = angle + BALLS_ANGLE_GAP) {
            list.add(Velocity.fromAngleAndSpeed(angle, BALLS_SPEED));
        }
        return list;
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
        //In this background there is a shining sun with rays.
        return new SunBackground(new Rectangle(new Point(0, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), Color.white);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<>();
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};
        double x = 20;
        for (int i = 0; i < colors.length; i++, x = x + BLOCKS_WIDTH) {
            //not the middle block.
            if (i != 7) {
                //one after the middle.
                if (i == 8) {
                    x = x - 5;
                    blockList.add(new Block(new Rectangle(new Point(x, BLOCKS_Y),
                            BLOCKS_WIDTH, BLOCKS_HEIGHT), colors[i]));
                } else {
                    blockList.add(new Block(new Rectangle(new Point(x, BLOCKS_Y),
                            BLOCKS_WIDTH, BLOCKS_HEIGHT), colors[i]));
                }
                //the middle block.
            } else {
                blockList.add(new Block(new Rectangle(new Point(x, BLOCKS_Y),
                        MIDDLE_BLOCK_WIDTH, BLOCKS_HEIGHT), colors[i]));
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
