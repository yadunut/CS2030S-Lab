
class ArrivalEvent extends BaseShopEvent {

    double serviceTime;

    public ArrivalEvent(double time, int customerId, boolean[] available, double serviceTime) {
        super(time, customerId, available);
        this.availableCounters = available;
        this.serviceTime = serviceTime;
    }

    @Override
    public Event[] simulate() {
        int counter = -1;
        for (int i = 0; i < this.availableCounters.length; i++) {
            if (this.availableCounters[i]) {
                counter = i;
                break;
            }
        }
        if (counter == -1) {
            return new Event[] { new DepartureEvent(this.getTime(), customerId, availableCounters) };
        }

        return new Event[] {
                new ServiceBeginEvent(this.getTime(), this.customerId, this.availableCounters, this.serviceTime,
                        counter) };
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": Customer %d arrives", this.customerId);
    }
}