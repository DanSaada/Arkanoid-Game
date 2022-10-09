package theGame;
// 208968560 Dan Saada

/**
 * @author Dan Saada
 * @version ass6
 * @since 2022/05/23
 */

import Animations.CountdownAnimation;
import Animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import diffSprites.Paddle;
import diffSprites.Block;
import diffSprites.Ball;
import diffSprites.Indicators.LivesIndicator;
import diffSprites.Indicators.NameIndicator;
import diffSprites.Indicators.ScoreIndicator;
import interfaces.Animation;
import interfaces.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import collection.GameEnvironment;
import collection.SpriteCollection;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;

/**
 * This class holds all game level-related methods.
 */
public class GameLevel implements Animation {
    private LevelInformation currentLevel;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private BallRemover ballRemover;
    private Ball[] balls;
    private Counter score;
    private Counter lives;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Paddle paddle;
    private Block[] borders;

    private static final double WIDTH = 800;
    private static final double VERTICAL_BORDER_WIDTH = 20;
    private static final double HORIZONTAL_BORDER_HEIGHT = 20;
    private static final double PADDLE_Y = 560;
    private static final double PADDLE_HEIGHT = 20;
    private static final double BALL_X = 400;
    private static final double BALL_Y = 545;
    private static final int BALL_RADIUS = 7;
    private static final int BONUS_SCORE = 100;

    //constructor

    /**
     * This constructor sets a game level.
     * <p>
     * Initializes members s on which the game depends.
     * </p>
     *
     * @param levelInformation
     * @param k
     * @param r
     * @param s
     * @param l
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor k, AnimationRunner r, Counter s, Counter l) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        setCounters(levelInformation);
        this.runner = r;
        this.keyboard = k;
        this.lives = l;
        this.score = s;
        ballRemover = new BallRemover(this, remainingBalls);
        this.currentLevel = levelInformation;
        this.currentLevel.getBackground().addToGame(this);
        addBalls();
    }

    /**
     * This method sets the counters value.
     * <p>
     *     Set amount of remainingBlocks according to the number of blocks in the current level.
     *     Set amount of remainingBalls according to the number of balls in the current level.
     * </p>
     *
     * @param levelInformation
     */
    public void setCounters(LevelInformation levelInformation) {
        this.remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(levelInformation.numberOfBalls());
    }

    /**
     * This method gets a Collidable object and adds it to this environment.
     *
     * @param c
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.environment.addCollidable(c);
        }
    }

    /**
     * This method gets a Collidable object and removes it from this environment.
     *
     * @param c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method gets a Sprite object and adds it to this sprites.
     *
     * @param s
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.sprites.addSprite(s);
        }
    }

    /**
     * This method gets a Sprite object and removes it from this sprites.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Returns the remaining balls counter of the game.
     *
     * @return remaining balls counter of the game.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Returns the remaining blocks counter of the game.
     *
     * @return remaining blocks counter of the game.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Reset the remaining ball's counter to the received integer.
     *
     * @param balls - the new number of balls.
     */
    public void resetRemainingBalls(int balls) {
        addBalls();
        this.remainingBalls.increase(balls);
    }

    /**
     * Getter of the game paddle.
     *
     * @return the game paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * This method creates an array of the borders Blocks.
     * <p>
     * First, the method creates the "upper-left"s points.
     * Then, the method creates the rectangles with the upper-left points and with width & height.
     * Finally the method creates the array contains the border's blocks, and makes them as
     * Collidable, and adds them to the game.
     * Note that the bottom border is not visual and it is a death region, meaning when a ball
     * hits the bottom border it disappear and being removed form the game.
     * </p>
     */
    private void createBorders() {
        borders = new Block[4];
        //creating upper-left's points.
        Point up = new Point(0, VERTICAL_BORDER_WIDTH);
        Point left = new Point(0, 2 * VERTICAL_BORDER_WIDTH);
        Point right = new Point(WIDTH - VERTICAL_BORDER_WIDTH, 2 * VERTICAL_BORDER_WIDTH);
        Point low = new Point(VERTICAL_BORDER_WIDTH, 630);
        //creating rectangles.
        Rectangle[] rectangles = new Rectangle[4];
        rectangles[0] = new Rectangle(up, WIDTH, HORIZONTAL_BORDER_HEIGHT);
        rectangles[1] = new Rectangle(left, VERTICAL_BORDER_WIDTH, 610);
        rectangles[2] = new Rectangle(right, VERTICAL_BORDER_WIDTH, 610);
        rectangles[3] = new Rectangle(low, WIDTH - 2 * VERTICAL_BORDER_WIDTH, HORIZONTAL_BORDER_HEIGHT);
        //filling an array of borders blocks.
        for (int i = 0; i < 4; i++) {
            borders[i] = new Block(rectangles[i], Color.GRAY);
        }
        for (int i = 0; i < borders.length; i++) {
            borders[i].addToGame(this);
            for (int j = 0; j < balls.length; j++) {
                this.balls[j].getGameEnvironment().addCollidable(borders[i]);
            }
            //death region
            if (i == 3) {
                borders[i].addHitListener(ballRemover);
            }
        }
    }

    /**
     * This method adds current level's blocks to the game.
     * <p>
     * First, the method creates a block remover and a score tracking listener.
     * Then, the method adds the block as a listener to the hit events and adds the new block to the game.
     * Finally the method adds the new block as a collidable object with a reference to the balls.
     * </p>
     */
    public void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        //add the blocks to the game.
        for (Block b : this.currentLevel.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            b.addToGame(this);
            //make the block ass collidable.
            for (int i = 0; i < balls.length; i++) {
                this.balls[i].getGameEnvironment().addCollidable(b);
            }
        }
    }

    /**
     * This method creates and adds the current level's balls to the game.
     * <p>
     * First, the method creates a new ball.
     * Then, the method sets the ball velocity and game environment.
     * Finally, the method adds the ball to the game.
     * </p>
     */
    public void addBalls() {
        int numOfBalls = this.currentLevel.numberOfBalls();
        Ball[] tempBalls = new Ball[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            Ball newBall = new Ball(BALL_X, BALL_Y, BALL_RADIUS, Color.WHITE, this.environment);
            newBall.setVelocity(this.currentLevel.initialBallVelocities().get(i));
            newBall.addToGame(this);
            tempBalls[i] = newBall;
        }
        this.balls = tempBalls;
    }

    /**
     * This method creates and adds the current level's paddle to the game.
     * <p>
     * First, the method creates a new rectangle and creates a new paddle with it.
     * Then, the method adds the new paddle to the game.
     * Finally the method adds the new paddle as a collidable object with a reference to the balls.
     * </p>
     */
    public void addPaddle() {
        double paddleX = WIDTH / 2 - this.currentLevel.paddleWidth() / 2;
        Rectangle forPaddle = new Rectangle(new Point(paddleX, PADDLE_Y),
                this.currentLevel.paddleWidth(), PADDLE_HEIGHT);
        Paddle paddle = new Paddle(forPaddle, Color.ORANGE, this.gui, this.currentLevel.paddleSpeed(), this.keyboard);
        paddle.setCollisionRectangle(forPaddle);
        paddle.addToGame(this);
        //make the paddle as collidable
        for (int i = 0; i < balls.length; i++) {
            this.balls[i].getGameEnvironment().addCollidable(paddle);
        }
        this.paddle = paddle;
    }

    /**
     * This method creates and adds all the game indicators to the game.
     */
    public void createIndicators() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        NameIndicator nameIndicator = new NameIndicator(this.currentLevel.levelName());
        nameIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        livesIndicator.addToGame(this);
    }

    /**
     * This method initialize a new game: creates the organs in the game, and adds them to the game.
     * <p>
     * First, the method creates an array of the borders blocks, which delimit the ball within the
     * boundaries of the game.
     * Then the method creates the blocks
     * Finally the method creates all the game indicators
     * </p>
     */
    public void initialize() {
        //create the borders blocks.
        createBorders();
        //create the inside blocks.
        createBlocks();
        //create the game's indicators.
        createIndicators();
    }

    /**
     * This method runs the game.
     * <p>
     *     The method starts the animation loop with a count down animation of 3 seconds.
     * </p>
     */
    public void run() {
        addPaddle();
        //start the animation with a count down.
        this.runner.run(new CountdownAnimation(this.sprites));
        this.running = true;
        //runner runs the current level.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //no more blocks
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(BONUS_SCORE);
            this.running = false;
        }
        //no more balls
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        //pause game
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}

