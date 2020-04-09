//package ass5;

/**
 * @author Yaron Sofer <yaronsr0604@gmail.com>
 * @version 1.6 (current version number of program)
 * @since 2019-05-09 (the version of the package this class was first added to)
 *        the interface of all of the hit notifiers.
 */
public interface HitNotifier {

    /**
     * add a hit listener to the object.
     * @param hl the hit listener we will add.
     */
    void addHitListener(HitListener hl);

    /**
     * remove a hit listener from the object.
     * @param hl the hit listener we will remove.
     */
    void removeHitListener(HitListener hl);
}
