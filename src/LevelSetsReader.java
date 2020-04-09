//package ass5;

import biuoop.GUI;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yaron's Laptop
 *  this class purpose is to read the level sets file and.
 *  return a selection of task for the menu to run.
 */
public class LevelSetsReader {
    private GUI gui;
    private HighScoresTable table;

    /**
     * create a new level sets reader. needs the gui and hige scores table for the
     * creation of the game flow task.
     * @param g the gui we are using in the program.
     * @param t the table we will use to save the scores.
     */
    public LevelSetsReader(GUI g, HighScoresTable t) {
        this.gui = g;
        this.table = t;
    }

    /**
     * create a new task selection of game flow from the level sets file we are.
     * inputing.
     * @param levelSetFile the level sets file we will read the level sets.
     * information from.
     * @return a selection of a task that will run the game flow task.
     */
    public List<Selection<Task<Void>>> levelsFromLevelSet(String levelSetFile) {
        // create the instances we need.
        LevelSpecificationReader reader = new LevelSpecificationReader();
        List<Selection<Task<Void>>> list = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        // use the scanner to scan the level set file.
        Scanner scanner = new Scanner(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetFile)));
        // set the delimiter we want.
        scanner.useDelimiter("\\r\\n");
        // scan and save the file content.
        while (scanner.hasNext()) {
            lines.add(scanner.next());
        }
        // store the formatted lines.
        List<String> newLines = new ArrayList<>();
        // read the old lines, replace bad characters and add them to the new lines.
        for (String s : lines) {
            newLines.add(s.replace("\n", ""));
        }
        // read each line with a file path and its name to create a new game flow task.
        for (int i = 0; i < newLines.size(); i = i + 2) {
            // replace more bad characters.
            String[] split = newLines.get(i).split(":");
            split[0] = split[0].replace((char) -1, '&');
            split[0] = split[0].replace((char) -2, '&');
            split[0] = split[0].replace((char) 0, '&');
            split[0] = split[0].replaceAll("&", "");
            // get the reader we need to create the game flow task.
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(newLines.get(i + 1))));
            // create a new game flow selection and add it to out list.
            list.add(new Selection<Task<Void>>(split[0], split[1],
                    new GameFlow<Task>(this.gui, reader.fromReader(input), this.table)));
        }
        return list;
    }
}
