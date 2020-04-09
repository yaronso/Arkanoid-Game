//package ass5;

import java.util.ArrayList;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author Yaron's Laptop
 * the game flow class is responsible for running the diffrenet levels of the.
 * game and managing the game in a general way.
 * @param <T> - run the task of gaming.
 */
public class GameFlow<T> implements Task {
    // the fields:
    private HighScoresTable table;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter live;
    private Counter score;
    private List<LevelInformation> levels = new ArrayList<LevelInformation>();

    /**
     * create a new game flow based on a gui that gives us all we need to run the
     * game.
     * @param gui the gui that will run the game.
     * @param levels the list of level we will run.
     * @param ar the animation runner.
     * @param key is the keyboard sensor.
     * @param table  - the high scores table of the game
     */
    public GameFlow(List<LevelInformation> levels, AnimationRunner ar, GUI gui, KeyboardSensor key,
            HighScoresTable table) {
        this.levels = levels;
        this.ar = ar;
        this.gui = gui;
        this.ks = key;
        this.live = new Counter();
        // we want to have 4 lives in this game.
        this.live.increase(1);
        this.score = new Counter();
        this.table = table;
    }

    /**
     * create a new game flow based on a gui that gives us all we need to run the.
     * game.
     * @param gui the gui that will run the game.
     * @param levels the list of level we will run.
     * @param t the high score table we will update with the game score.
     */
    public GameFlow(GUI gui, List<LevelInformation> levels, HighScoresTable t) {
        this.levels = levels;
        this.table = t;
        this.live = new Counter();
        // we want to have 7 lives in this game.
        live.increase(7);
        this.score = new Counter();
        this.gui = gui;
        // create an animation runner to run our animations.
        this.ar = new AnimationRunner(this.gui, new Sleeper());
        this.ks = gui.getKeyboardSensor();
    }

    /**
     * from the Task interface this method is responsible for running the levels.
     * supplied in the list one after the other.
     * @return we always return void.
     */
    public T run() {
        boolean win = true;
        // loop over all of the levels in our list.
        for (LevelInformation levelInfo : this.levels) {
            if (levelInfo != null) {
                // create a new game level with the parameters we need from the level
                // inforamtion and the game flow.
                GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.gui, this.live, this.score);
                // initialize the new level.
                level.initialize();

                // if we still have blocks an lives we run the animation of the level.
                while (level.getLives() > 0 && level.blocksRemaining() > 0) {
                    level.playOneTurn();
                    level.removeBallsAndPaddle();
                }

                // if the player looses.
                if (level.getLives() == 0) {
                    // the player lost.
                    win = false;
                    break;
                }
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, new EndScreen(this.score, this.live)));
        DialogManager dialog = gui.getDialogManager();
        String player = dialog.showQuestionDialog("Name", "What is your name?", "");
        this.table.add(new ScoreInfo(player, this.score.getValue()));
        this.ar.run(new KeyPressStoppableAnimation(this.ks, "space", new HighScoresAnimation(this.table)));
        return null;
    }

}
