package theGame;

/**
 * @author Dan Saada
 * @version ass5
 * @since 2022/05/12
 */

/**
 * This class holds all counter-related methods.
 * a counter is in charge of holding a reference to a changing value.
 */
public class Counter {
    private int value;

    /**
     * This class holds all counter-related methods.
     *
     * @param value
     */
    public Counter(int value) {
        setValue(value);
    }

    /**
     * This method add number to the current counter.
     *
     * @param number
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * This method subtract number from the current counter.
     *
     * @param number
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * This method is a "get method".
     *
     * @return the current count.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * This method gets a value and sets it as the counter value.
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
}