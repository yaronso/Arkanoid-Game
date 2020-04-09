//package ass5;

/**
 * @author Yaron's Laptop
 * the selection is what we can run from the menu.
 * @param <T> the return value of the the selection.
 */
public class Selection<T> {

    // the fields:
    private String key;
    private String message;
    private T returnval;

    /**
     * the constructor of the class.
     * @param key - the string.
     * @param message - the message on the screen.
     * @param returnVal - the task is going on.
     */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnval = returnVal;
    }

    /**
     * return the key of the selection.
     * @return the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * return the message of the selection.
     * @return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * return the val of the selection.
     * @return the val.
     */
    public T getReturnVal() {
        return returnval;
    }

}
