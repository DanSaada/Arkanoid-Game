package interfaces;

/**
 * @author Dan Saada
 * @version ass5
 * @since 2022/05/12
 */

/**
 * This interface requires other classes that declare that they are implementing it to implement
 * the methods underlying it.
 * meaning they should have the "addHitListener" and the "removeHitListener" methods.
 */
public interface HitNotifier {

    /**
     * This method add hl (hit listener) as a listener to hit events.
     * <p>
     * The method will be implemented in any class that uses this interface.
     * </p>
     *
     * @param hl
     */
    void addHitListener(HitListener hl);

    /**
     * This method removes hl (hit listener) from the list of listeners to hit events.
     * <p>
     * The method will be implemented in any class that uses this interface.
     * </p>
     *
     * @param hl
     */
    void removeHitListener(HitListener hl);
}
