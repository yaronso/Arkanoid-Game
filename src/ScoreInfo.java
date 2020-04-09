//package ass5;

/**
 * @author Yaron Sofer
 * this class is used the information of the score of a.
 * player and it is used to accsess and save that score.
 */

public class ScoreInfo implements Comparable<ScoreInfo>, java.io.Serializable {

    // the fields:
    private String name;
    private int score;

    /**
     * create a new score information.
     * @param name  the name of the player.
     * @param score his score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }


    /**
     * return the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return this.name;
    }


    /**
     * the score the player got to.
     * @return the score of the player.
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(ScoreInfo other) {
        if (this.score > other.getScore()) {
            return -1;
        } else if (this.score < other.getScore()) {
            return 1;
        } else {
            return 0;
        }
    }
}