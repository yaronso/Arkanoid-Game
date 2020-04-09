//package ass5;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * this class is the animation runner that runs the animation loop and stopes it
 * when the animation tells it to.
 * @author Yaron Sofer
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * create a new animation runner based on the gui, frame rate and sleeper.
     * @param gui the gui that will run the animation.
     * @param sleep the sleeper we will use.
     */
    public AnimationRunner(GUI gui, Sleeper sleep) {
        this.framesPerSecond = 60;
        this.gui = gui;
        this.sleeper = sleep;
    }

    /**
     * run the animation loop.
     * @param animation the animation loop that we will run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // template method - shouldStop is also a template method
        // run the animation untill we meet the stop condition
        double dt = (double) 1 / this.framesPerSecond;
        // while shouldStop return false we should continue the game's running
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            // template method - while shouldStop return false we keep call doOneFrame.
            animation.doOneFrame(d, dt);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}
