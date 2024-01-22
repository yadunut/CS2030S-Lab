public class JoinCounterQueueEvent extends Event {
  private Customer customer;
  private ServiceCounter counter;

  public JoinCounterQueueEvent(double time, Customer customer, ServiceCounter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public Event[] simulate() {
    this.counter.joinQueue(customer);
    return new Event[] {};
  }

  @Override
  public String toString() {
    return String.format("%s: %s joined counter queue (at %s)",
        super.toString(),
        this.customer, this.counter);
  }
}
