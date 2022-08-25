
class DepartureEvent extends BaseShopEvent {

    public DepartureEvent(double time, int customerId, boolean[] available) {
        super(time, customerId, available);
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(": Customer %d departed", this.customerId);
    }

    @Override
    public Event[] simulate() {
        return new Event[] {};
    }

}