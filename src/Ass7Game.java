//package ass5;

import java.io.File;
import java.io.IOException;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author Yaron's Laptop
 * create a game and run it.
 */
public class Ass7Game {
    /**
     * initializes a new game and runs it.
     *
     * @param args the command line arguments we don't use.
     */
    public static void main(String[] args) {
        // create the general thing we need to run the game.
        GUI gui = new GUI("GUI", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, new Sleeper());
        // create a new high scores table and animation.
        HighScoresAnimation highScores = new HighScoresAnimation(new HighScoresTable(4));
        // wrap it in a key as it is a key press stopable animation.
        KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "space", highScores);
        // the path to the file that keeps the scores.
        File high = new File("highscores");
        try { // try to create an new file
            if (!high.createNewFile()) { // the file has been created.
                highScores.getTable().load(high); // load the file.
            }
        } catch (IOException e) { // error loading the file.
            System.out.println("error creating table file");
        }
        KeyboardSensor sensor = gui.getKeyboardSensor();
        // create the main menu of the game.
        MenuAnimation<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>(
                "Main Menu", sensor, new MenuBackground(), runner);
        // create the menu of levels.
        MenuAnimation<Task<Void>> levelsMenu = new MenuAnimation<Task<Void>>("ARKNOID",
                sensor, new MenuBackground(), runner);
        // we need this to read the levels.
        LevelSpecificationReader reader = new LevelSpecificationReader();
        LevelSetsReader levelSetsReader = new LevelSetsReader(gui, highScores.getTable());
        if (args.length != 0) { // if we have argument.
            // read the level sets based on the arguments.
            levelsMenu.addAll(levelSetsReader.levelsFromLevelSet(args[0]));
        } else { // read the default level set.
            levelsMenu.addAll(levelSetsReader.levelsFromLevelSet("level_sets.txt"));
        }
        // add the menues to the game.
        mainMenu.addSubMenu("s", "play game", levelsMenu);
        mainMenu.addSelection("h", "high scores", new ShowHiScoresTask(runner, key));
        mainMenu.addSelection("q", "quit", new QuitTask<Void>(highScores.getTable(), high));
        Task<Void> task;
        // run the game.
        while (true) {
            // first run the main manu.
            task = mainMenu.run();
            if (task != null) { // make sure everything was ok.
                task.run();
                mainMenu.zeroStatus(); //we want to run each task once.
            }
        }

    }
}