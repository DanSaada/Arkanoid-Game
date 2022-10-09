package interfaces;

/**
 * @author Dan Saada
 * @version ass5
 * @since 2022/05/12
 */

/**
 * This interface requires other classes that declare that they are implementing it to implement
 * the methods underlying it.
 * meaning they should have the "compare" methods.
 *
 * @param <C> the type of objects that may be compared by this comparator
 */
public interface Comparator<C> {

    /**
     * This method is a comparison method, which imposes a total ordering on some collection of objects.
     *
     * @param o1
     * @param o2
     * @return true / false according to the kind of comparison being made.
     */
    Boolean compare(C o1, C o2);
}
