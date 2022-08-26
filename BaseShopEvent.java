
/**
 * This class encapsulates an event in the shop
 * simulation. Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Wei Tsang
 * @version CS2030S AY21/22 Semester 2
 */

abstract class BaseShopEvent extends Event {

    final Customer customer;
    Shop shop;

    public BaseShopEvent(double time, Customer customer, Shop shop) {
        super(time);
        this.customer = customer;
        this.shop = shop;
    }

}