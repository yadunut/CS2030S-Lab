
class ServiceBeginEvent extends BaseShopEvent {

    double serviceTime;
    int counterId;

    public ServiceBeginEvent(double time, int customerId, boolean[] available, double serviceTime, int counterId) {
        super(time, customerId, available);
        this.serviceTime = serviceTime;
        this.counterId = counterId;
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(": Customer %d service begin (by Counter %d)", this.customerId, this.counterId);
    }

    @Override
    public Event[] simulate() {
        this.availableCounters[this.counterId] = false;
        double endTime = this.getTime() + this.serviceTime;
        return new Event[] {
                new ServiceEndEvent(endTime, this.customerId, this.availableCounters, this.counterId) };
    }
}