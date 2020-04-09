//package ass5;

import java.io.File;
import java.io.IOException;

/**
 * @author Yaron's Laptop
 * this task quits the game.
 * @param <T>
 */
public class QuitTask<T> implements Task {

    // the fields:
    private HighScoresTable table;
    private File save;

    /**
     * the constructor of the QuitTask class.
     * @param table - the high score table that we are going to save.
     * @param file  - the file that is going to store this table.
     */
    public QuitTask(HighScoresTable table, File file) {
        this.table = table;
        this.save = file;
    }

    @Override
    public Object run() {
        try {
            // save the table inside the file
            this.table.save(save);
        } catch (IOException e) {
            System.out.println("unable to save table");
        }
        System.exit(1);
        return null;
    }

}
