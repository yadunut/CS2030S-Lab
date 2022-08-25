
/**
 * This class encapsulates an event in the shop
 * simulation. Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Wei Tsang
 * @version CS2030S AY21/22 Semester 2
 */

abstract class BaseShopEvent extends Event {

    final int customerId;
    boolean[] availableCounters;

    public BaseShopEvent(double time, int customerId, boolean[] availableCounters) {
        super(time);
        this.customerId = customerId;
        this.availableCounters = availableCounters;
    }

}