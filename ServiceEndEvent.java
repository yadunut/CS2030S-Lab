
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
        this.counter.setAvailable(true);
        return new Event[] { new DepartureEvent(this.getTime(), customer, shop) };
    }
}