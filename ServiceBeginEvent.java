
class ServiceBeginEvent extends BaseShopEvent {

    double serviceTime;
    ShopCounter counter;

    public ServiceBeginEvent(double time, Customer customer, Shop shop, double serviceTime, ShopCounter counter) {
        super(time, customer, shop);
        this.serviceTime = serviceTime;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(": %s service begin (by %s)", this.customer, this.counter);
    }

    @Override
    public Event[] simulate() {
        this.counter.setAvailable(false);
        double endTime = this.getTime() + this.serviceTime;
        return new Event[] {
                new ServiceEndEvent(endTime, this.customer, this.shop, this.counter) };
    }
}