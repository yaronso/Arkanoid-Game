//package ass5;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 * this class is a counter that we use to count thing we need in the game
 * and save them over functions and classes.
 */
public class Counter {
    private int value = 0;

    /**
     * create a counter, we need to do nothing.
     */
    public Counter() {
        return;
    }

    /**
     * add number to current count.
     * @param number the number we are adding to the count.
     */
    public void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * subtract a number from the count.
     * @param number the number we are subtracting.
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * return the current count.
     * @return the number we are on in the count.
     */
    public int getValue() {
        return this.value;
    }
}
