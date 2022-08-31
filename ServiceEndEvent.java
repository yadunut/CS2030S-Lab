/**
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
class ServiceEndEvent extends BaseShopEvent {

    ShopCounter counter;

    public ServiceEndEvent(double time, Customer customer, Shop shop, ShopCounter counter) {
        super(time, customer, shop);
        this.counter = counter;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(": %s service done (by %s)", this.customer, this.counter);
    }

    @Override
    public Event[] simulate() {
        this.counter.free();
        return new Event[] { new DepartureEvent(this.getTime(), customer, shop) };
    }
}