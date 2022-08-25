
class ServiceEndEvent extends BaseShopEvent {

    int counterId;

    public ServiceEndEvent(double time, int customerId, boolean[] available, int counterId) {
        super(time, customerId, available);
        this.counterId = counterId;
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(": Customer %d service done (by Counter %d)", this.customerId, this.counterId);
    }

    @Override
    public Event[] simulate() {
        this.availableCounters[counterId] = true;
        return new Event[] { new DepartureEvent(this.getTime(), customerId, availableCounters) };
    }
}