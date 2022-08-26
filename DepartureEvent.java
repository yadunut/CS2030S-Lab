
class DepartureEvent extends BaseShopEvent {

    public DepartureEvent(double time, Customer customer, Shop shop) {
        super(time, customer, shop);
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(": %s departed", this.customer);
    }

    @Override
    public Event[] simulate() {
        return new Event[] {};
    }

}