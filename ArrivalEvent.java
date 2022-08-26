
class ArrivalEvent extends BaseShopEvent {

    double serviceTime;

    public ArrivalEvent(double time, Customer customer, Shop shop, double serviceTime) {
        super(time, customer, shop);
        this.serviceTime = serviceTime;
    }

    @Override
    public Event[] simulate() {
        ShopCounter availableCounter = this.shop.getAvailableCounter();
        if (availableCounter == null) {
            return new Event[] { new DepartureEvent(this.getTime(), customer, shop) };
        }
        return new Event[] {
                new ServiceBeginEvent(this.getTime(), customer, shop, this.serviceTime, availableCounter) };

    }

    @Override
    public String toString() {
        return super.toString() + String.format(": %s arrives", this.customer);
    }
}