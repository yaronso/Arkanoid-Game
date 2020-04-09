//package ass5;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yaron's Laptop
 * this is a high scores table that is serializable is uses a file.
 */
public class HighScoresTable implements java.io.Serializable {

    // The fields of the class:
    private List<ScoreInfo> scores;
    private int size;

    /**
     * create a new high score table. set the size of the table.
     * @param size the size of the table that will be created.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scores = new ArrayList<ScoreInfo>();
    }

    /**
     * add a high score to the table.
     * @param score - the score we are adding.
     */
    public void add(ScoreInfo score) {
        // get the rank of the score if it was in the table.
        int rank = this.getRank(score.getScore());
        // check if it is enough to be in our table.
        if (rank <= this.size) {
            // means we add it to the list
            this.scores.add(score);
        }
        // call the method that return the sorted table of scores.
        this.scores = this.getHighScores();
    }

    /**
     * return the size of the table.
     * @return the size of the table.
     */
    public int size() {
        // return the size of the table scores
        return this.size;
    }

    /**
     * return the size of the table.
     * @return the size of the table.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * return the high scores of the current table.
     * @return the high scores of the current table.
     */
    public List<ScoreInfo> getHighScores() {
        // sort the scores before we return them.
        Collections.sort(this.scores);
        // return the sorted scores.
        return this.scores;
    }

    /**
     * return the rank of the current score. rank 1 is the top, rank size is the.
     * lowest. rank bigger then size is out of the table.
     * @param score the score we are checking the rank of.
     * @return the rank of the score.
     */
    public int getRank(int score) {
        List<ScoreInfo> list = new ArrayList<ScoreInfo>(this.scores);
        // create a new score info the we will be able to use.
        ScoreInfo check = new ScoreInfo("check", score);
        // add the check and sort the list.
        list.add(check);
        Collections.sort(list);
        int i = 1;
        // compare all the scores to the check, check how many compares we are doing.
        for (ScoreInfo s : list) {
            // if the scores are equal return rank 2, means the second place at the table.
            if (s.compareTo(check) == 0) {
                // return the rank.
                return ++i;
            } else {
                // add to the rank.
                i++;
            }
        }
        return 0;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scores = new ArrayList<ScoreInfo>();
    }

    /**
     * return the list of scoreinfos we have.
     * @return the score infos we have.
     */
    public List<ScoreInfo> getScores() {
        return this.scores;
    }

    /**
     * clear the current table data and load a new table data from a file.
     * @param filename the name of the file we are loading the table from.
     */
    public void load(File filename) {
        // clear the current table.
        this.clear();
        // load the table from the file
        HighScoresTable table = HighScoresTable.loadFromFile(filename);
        // if the load was ok
        if (table != null) {
            // we set the loaded data to be this data.
            this.scores = table.getScores();
        }
    }

    /**
     * save the data of the current table into a specified file.
     * @param filename the file we will save the table to.
     * @throws IOException if we failed to open the file.
     */
    public void save(File filename) throws IOException {
        try {
            // open the file in order to to write inside it.
            FileOutputStream fileOut = new FileOutputStream(filename);
            // serialize the file using the java serialization.
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            // write the table object to the file.
            out.writeObject(this);
            // close the file and the serialization we don't need it anymore.
            out.close();
            fileOut.close();
        } catch (IOException e) { // failed to load the file.
            System.out.println("we had an error writing the scores");
        }

    }

    /**
     * read a table from the file inputed and return it. if there is a problem we.
     * return an empty table.
     * @param filename the name of the file we are loading the table from.
     * @return a new table that has been read from the file.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table;
        try {
            // if the file inputed is empty we have an error and we return an empty table.
            if (filename.length() == 0) {
                return null;
            }
            // read the file.
            InputStream fileIn = new FileInputStream(filename.getName());
            // read the file using the default serialization.
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // read the table object and cast it.
            table = (HighScoresTable) in.readObject();
            // close the serialization and the reading file.
            in.close();
            fileIn.close();
        } catch (IOException e) { // we had an error in reading the file.
            System.out.println("error reading table from file");
            return null;
        } catch (ClassNotFoundException c) { // we had an error in serialization.
            System.out.println("error - class not found");
            return null;
        }
        return table;
    }
}
