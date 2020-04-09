//package ass5;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class is the father class for the animation that can be stopped by the
 * press of a key.
 * @author Yaron Sofer
 */
public class KeyPressStoppableAnimation implements Animation {
    /* the running animation */
    private Animation animation;
    /* keyboard sensor */
    private KeyboardSensor sensor;
    /* the pressed key */
    private String key;
    /* has to stop */
    private boolean stop;
    /* is pressed */
    private boolean isAlreadyPressed;

    /**
     * the construtor of the class.
     * @param sensor    keyboard sensor.
     * @param key       name of the button on keyboard.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * draw to the screen on frame of the animation.
     * @param d the draw surface we are drawing the animation on.
     * @param dt the time that passed between calls.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }

    }

    /**
     * check if the animation needs to stop of not.
     * @return true - we need to stop, false - we don't need to stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
