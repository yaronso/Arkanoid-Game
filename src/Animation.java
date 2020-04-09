//package ass5;

import biuoop.DrawSurface;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2010-03-31 (the version of the package this class was first added to)
 * the following interface include the following template methods.
 */
public interface Animation {

    /**
     * run one frame of the animation.
     * @param d  the draw surface we are drawing the animation on.
     * @param dt the time that passes between calls.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * changes the stopping member of the animation.
     * @return true - stop the animation. false - continue running.
     */
    boolean shouldStop();
}
