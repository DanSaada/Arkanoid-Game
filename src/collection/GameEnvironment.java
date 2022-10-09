package collection;

/**
 * @author Dan Saada
 * @version ass3
 * @since 2022/04/06
 */

import coliisionDetection.CollisionInfo;
import geometryPrimitives.Line;
import geometryPrimitives.Point;
import interfaces.Collidable;
import interfaces.Comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all game environment-related methods.
 */
public class GameEnvironment implements Comparator<Collidable> {
    private java.util.List<Collidable> obstacles;

    //constructor

    /**
     * This constructor.
     */
    public GameEnvironment() {
        this.obstacles = new ArrayList<>();
    }

    /**
     * This method gets a collidable and adds it to the obstacles list (to the environment).
     *
     * @param c - a collidable object.
     */
    public void addCollidable(Collidable c) {
        if (obstacles == null) {
            obstacles = new ArrayList<>();
        }
        this.obstacles.add(c);
    }

    /**
     * This method gets a collidable and removes from the obstacles list (from the environment).
     *
     * @param c - a collidable object.
     */
    public void removeCollidable(Collidable c) {
        java.util.List<Collidable> tempObstacles = new ArrayList<Collidable>(this.obstacles);
        for (int i = 0; i < tempObstacles.size(); i++) {
            if (compare(tempObstacles.get(i), c)) {
                this.obstacles.remove(c);
            }
        }
    }

    @Override
    public Boolean compare(Collidable o1, Collidable o2) {
        return (o1.equals(o2));
    }

    /**
     * This method is a "get method".
     *
     * @return the game environment.
     */
    public List<Collidable> getObstacles() {
        return this.obstacles;
    }

    /**
     * This method gets a game environment and sets it as the game environment.
     *
     * @param obstacles - list of collidable objects.
     */
    public void setObstacles(ArrayList obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * This method gets a trajectory line and return the closest collision information (if there is such one)
     * with other object (otherwise returns null).
     * <p>
     * First, the method creates a dynamic array who is being filled up according to the
     * "closestIntersectionToStartOfLine" method.
     * Then, if the array isn't empty, the method creates a temporary minimum point of intersection and
     * initialize it with the first point in the array.
     * Finally the method checks if there is a closer point from the current one in the array, and returns
     * the collision information.
     * </p>
     *
     * @param trajectory
     * @return the closest collision information between a trajectory line and an object or null if there isn't one.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //checking if there are any obstacles in the array.
        if (!obstacles.isEmpty()) {
            List<Point> intersectPointArray = new ArrayList<>();
            List<Integer> collidablePosition = new ArrayList<>();
            Point point;
            //filling the intersect point array.
            // Make a copy of obstacles before iterating over them.
            java.util.List<Collidable> tempObstacles = new ArrayList<Collidable>(this.obstacles);
            for (int i = 0; i < tempObstacles.size(); i++) {
                //checking if trajectory intersect with one of the obstacles (null if not).
                point = trajectory.closestIntersectionToStartOfLine(tempObstacles.get(i).getCollisionRectangle());
                if (point != null) {
                    intersectPointArray.add(point);
                    collidablePosition.add(i);
                }
            }
            //the array isn't empty
            if (!intersectPointArray.isEmpty()) {
                Point tmpMinIntersect = intersectPointArray.get(0);
                int counter = collidablePosition.get(0);
                //checking if the temporary point is the closest.
                for (int i = 0; i < intersectPointArray.size(); i++) {
                    if (trajectory.start().distance(tmpMinIntersect)
                            > trajectory.start().distance(intersectPointArray.get(i))) {
                        tmpMinIntersect = intersectPointArray.get(i);
                        counter = collidablePosition.get(i);
                    }
                }
                Collidable collidable = (Collidable) getObstacles().get(counter);
                return new CollisionInfo(tmpMinIntersect, collidable);
            }
        }
        return null;
    }
}

